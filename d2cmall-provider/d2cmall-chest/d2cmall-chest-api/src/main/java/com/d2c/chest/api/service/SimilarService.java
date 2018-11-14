package com.d2c.chest.api.service;


import java.util.List;
import java.util.Map;

import com.d2c.chest.api.dto.SimilarDTO;
import com.d2c.common.api.query.model.MongoQuery;

/**
 * 商品相似度
 * @author wull
 */
public interface SimilarService {
	
	public List<Object> findTopRandomByIds(List<?> beanIds, int limit);

	public List<Object> findTopPromotionByIds(List<?> beanIds, int limit);

	public Map<Long, Object> findTopOneCategoryByIds(List<?> beanIds, int limit);
	
	public Map<String, List<Object>> findCategoryJsonByIds(List<?> beanIds, int limit, int groupSize);

	public List<Object> findTopCategoryById(List<?> beanIds, Long categoryId, int limit);
	
	public List<Object> findTopRandom(Object beanId, Integer limit);

	public List<SimilarDTO> findRebuildByBeanId(Object beanId, MongoQuery query, Integer page, Integer limit);
	
	public List<SimilarDTO> findByBeanId(Object beanId, MongoQuery query, Integer page, Integer limit);

	public long countByBeanId(Object beanId, MongoQuery query);
	
	public void buildSimilarJob();
	
	public int rebuildSimilar(Object beanId);
    
}
