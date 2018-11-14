package com.d2c.frame.spark.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.d2c.common.base.utils.AssertUt;
import com.d2c.common.base.utils.JsonUt;
import com.d2c.common.mongodb.build.AggrBuild;
import com.d2c.common.mongodb.model.SparkStreamMongoDO;
import com.d2c.common.mongodb.utils.BsonUt;
import com.d2c.frame.spark.utils.SparkUt;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;
import com.mongodb.spark.MongoConnector;
import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.MongoCollectionConfig;
import com.mongodb.spark.config.WriteConfig;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;

/**
 * Spark模板控制
 * @author wull
 */
@Component
public class SparkTemplate implements Serializable {
	
	private static final long serialVersionUID = 4277109882613218035L;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 加载Mongo数据
	 */
	public JavaMongoRDD<Document> load(JavaSparkContext jsc){
		return MongoSpark.load(jsc);
	}
	
	public JavaMongoRDD<Document> load(JavaSparkContext jsc, AggrBuild ab){
		return load(jsc).withPipeline(ab.toBson());
	}
	
	public <T> JavaRDD<T> load(JavaSparkContext jsc, Class<T> clz){
		return load(jsc).map(doc -> BsonUt.toBean(doc, clz));
	}
	
	public <T> JavaRDD<T> load(JavaSparkContext jsc, AggrBuild ab, Class<T> clz){
		return load(jsc, ab).map(doc -> BsonUt.toBean(doc, clz));
	}

	/**
	 * 插入数据
	 */
	public void insert(JavaRDD<?> rdd){
		insertBson(rdd.map(v -> BsonUt.toBson(v)));
	}
	
	public void insertBson(JavaRDD<Document> rdd){
		MongoSpark.save(rdd);
	}
	
	/**
	 * 保存数据
	 */
	public <T> void save(JavaRDD<T> rdd){
		save(rdd, null, null);
	}
	
	public <T> void save(JavaRDD<T> rdd, Function2<T, T, T> mergeFunc){
		save(rdd, null, mergeFunc);
	}
	
	public <T> void save(JavaRDD<T> rdd, Class<?> collClz){
		save(rdd, collClz, null);
	}
	
	private <T> void save(JavaRDD<T> rdd, Class<?> collClz, Function2<T, T, T> mergeFunc){
		if(rdd == null || rdd.isEmpty()) return;
		WriteConfig wconf = null;
		if(collClz != null){
			Map<String, String> map = new HashMap<>();
			map.put("collection", SparkUt.collect(collClz));
			wconf = WriteConfig.create(rdd.context().conf(), map);
		}else{
			wconf = WriteConfig.create(rdd.context().conf());
		}
		saveImpl(rdd, wconf, mergeFunc);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T> void saveImpl(JavaRDD<T> rdd, WriteConfig wconf, Function2<T, T, T> mergeFunc){
		AssertUt.notNull(wconf);
		MongoConnector conn =  MongoConnector.create(rdd.context().conf());
		rdd.foreachPartition(iter -> {
			if(iter.hasNext()){
				conn.withCollectionDo(wconf, Document.class, coll -> {
					UpdateOptions upOpt = new UpdateOptions();
					upOpt.upsert(true);
					List<WriteModel<Document>> list = new ArrayList<>();
					iter.forEachRemaining(bean -> {
						Document doc = BsonUt.toBson(bean);
						Object id = doc.get("_id");
						if(id != null){
							Document idBson = new Document("_id", id);
							if(mergeFunc != null){
								try {
									Document last = coll.find(idBson).first();
									if(last != null){
										doc = BsonUt.toBson(mergeFunc.call(bean, (T) BsonUt.toBean(last, bean.getClass())));
									}
								} catch (Exception e) {
									logger.warn("saveImpl func 合并数据方法异常...", e);
								}
							}
							logger.debug("保存Document = " + doc );
							if (wconf.replaceDocument()) {
								list.add(new ReplaceOneModel(idBson, doc, upOpt));
							}else{
								doc.remove("_id");
								list.add(new UpdateOneModel(idBson, new Document("$set", doc), upOpt));
							}
						}else{
							list.add(new InsertOneModel(doc));
						}
					});
					
					BulkWriteResult res = null;
					try {
						res = coll.bulkWrite(list);
					}catch (Exception e) {
						logger.error("批量保存失败, 尝试逐个写入... count: " + list.size(), e);
						WriteModel<Document> bean = null;
						try{
							for(WriteModel<Document> wm : list){
								bean = wm;
								res = coll.bulkWrite(Arrays.asList(bean));
							}
						}catch (Exception ex) {
							if(bean != null){
								if(bean instanceof ReplaceOneModel){
									logger.error("逐个写入ReplaceOne失败... save : " + JsonUt.serialize(((ReplaceOneModel) bean).getReplacement()));
								}else if(bean instanceof InsertOneModel){
									logger.error("逐个写入InsertOneModel失败... insert : " + JsonUt.serialize(((InsertOneModel) bean).getDocument()));
								}else if(bean instanceof UpdateOneModel){
									logger.error("逐个写入UpdateOneModel失败... update : " + ((UpdateOneModel) bean).getUpdate());
								}
							} else {
								logger.error("逐个写入失败... null", ex);
							}
						}
					}
					return res;
				});
			}
		});
	}
	
	public void dropCollection(JavaSparkContext jsc){
		dropCollection(jsc, WriteConfig.create(jsc));
	}
	
	public void dropCollection(JavaSparkContext jsc, MongoCollectionConfig config){
		MongoConnector.create(jsc).withCollectionDo(config, Document.class, coll -> {
			coll.drop();
			return null;
		});
	}
	
	//****************** Spark Stream *****************

	/**
	 * Stream更新数据
	 */
	public void updateStream(JavaRDD<? extends SparkStreamMongoDO> rdd){
		if(rdd == null || rdd.isEmpty()) return;
		save(rdd.filter(v -> {
			Boolean up = v.getStreamUpdate();
			if(up){
				v.setStreamUpdate(false);
			}
			return up;
		}));
	}

	
	//************************************

//	protected MongoClient dropDatabase(final String collection) {
//	    MongoClientURI uri = new MongoClientURI(sparkConfig.getMongoUrl() + "." + collection);
//	    MongoClient client = new MongoClient(uri);
//	    client.dropDatabase(uri.getDatabase());
//	    return client;
//	}

}
