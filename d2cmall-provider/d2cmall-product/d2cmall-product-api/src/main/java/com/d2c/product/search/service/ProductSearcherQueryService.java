package com.d2c.product.search.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.d2c.common.api.dto.CountDTO;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.product.search.model.SearcherProduct;
import com.d2c.product.search.model.SearcherRecProduct;
import com.d2c.product.search.query.ProductProSearchQuery;
import com.d2c.product.search.support.ProductSearchHelp;

public interface ProductSearcherQueryService {

	/**
	 * 分页查询商品搜索数据
	 * 
	 * @param searcher
	 * @param page
	 * @return
	 */
	PageResult<SearcherProduct> search(ProductProSearchQuery searcher, PageModel page);

	/**
	 * 商品搜索数量
	 * 
	 * @param searcher
	 * @return
	 */
	int count(ProductProSearchQuery searcher);

	/**
	 * 分页查询商品搜索数据
	 * 
	 * @param searcher
	 * @param page
	 * @return
	 */
	PageResult<SearcherRecProduct> searchRec(ProductProSearchQuery searcher, PageModel page);

	/**
	 * 分页查询所有数据，不排序
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	List<SearcherProduct> findByPage(int page, int limit);

	/**
	 * 通过id，得到商品搜索数据
	 * 
	 * @param id
	 * @return
	 */
	SearcherProduct findById(String id);

	/**
	 * 批量查出商品
	 * 
	 * @param productIds
	 * @return
	 */
	List<SearcherProduct> findByIds(List<String> productIds, Integer mark);

	/**
	 * 根据ids查询
	 * 
	 * @param ids
	 * @return
	 */
	Map<Long, SearcherProduct> findMapByIds(String[] ids);

	/**
	 * 查询推荐商品
	 * 
	 * @param liveId
	 * @param productId
	 * @return
	 */
	SearcherRecProduct findRecById(Long liveId, Long productId);

	/**
	 * 获取推荐值最高的前limit商品
	 * 
	 * @param limit
	 * @return
	 */
	List<SearcherProduct> findTopRecom(int limit);

	/**
	 * 获取推荐值最高的商品
	 */
	PageResult<SearcherProduct> findTopRecomBy(Long[] topIds, PageModel page);

	PageResult<SearcherProduct> findTopRecom(ProductProSearchQuery query, PageModel page);

	PageResult<SearcherProduct> findTopRecomWorld(PageModel page);

	/**
	 * 获取推荐值最高的前limit商品, 根据品类ID
	 * 
	 * @param categoryId
	 * @param limit
	 * @return
	 */
	List<SearcherProduct> findTopByCategoryId(Long categoryId, int limit);

	/**
	 * 获取有活动的推荐值最高的前limit商品
	 * 
	 * @param limit
	 * @return
	 */
	List<SearcherProduct> findTopPromotion(Long[] topIds, int limit);

	/**
	 * 根据设计师ID 聚合出设计师下系列包含商品的数量
	 * 
	 * @param searcher
	 * @return
	 */
	List<Long> findFactSeries(Long designerId);

	/**
	 * 聚合得到符合查询条件的设计师以及全部的分类
	 * 
	 * @param searcher
	 * @param parentCategoryId
	 * @param topCategoryId
	 * @return
	 */
	Map<String, List<CountDTO<?>>> findFactDesignerAndCategory(ProductProSearchQuery searcher, Long parentCategoryId,
			Long topCategoryId);

	/**
	 * 设计师商品列表
	 * 
	 * @param designerId
	 * @param page
	 * @return
	 */
	PageResult<SearcherProduct> findSaleProductByDesigner(Long designerId, PageModel page,
			ProductProSearchQuery searcherBean);

	/**
	 * 系列的商品查询
	 * 
	 * @param seriesId
	 * @param page
	 * @return
	 */
	List<SearcherProduct> findSaleProductBySeries(Long seriesId, PageModel page);

	/**
	 * 设计师商品列表
	 * 
	 * @param designerId
	 * @param page
	 * @return
	 */
	PageResult<SearcherProduct> findSaleProductByDesigners(List<Long> designerIds, PageModel page);

	/**
	 * 推荐商品列表
	 * 
	 * @param designerId
	 * @param page
	 * @return
	 */
	PageResult<SearcherProduct> findRecommendProductByDesigner(Long designerId, PageModel pageModel);

	/**
	 * 活动商品列表
	 * 
	 * @param promotionId
	 * @param PromotionScope
	 * @param page
	 * @return
	 */
	PageResult<SearcherProduct> findSaleProductByPromotion(Long promotionId, Integer PromotionScope, PageModel page);

	/**
	 * 顶级类目下，推荐商品列表，包括不可查询，已下架商品
	 * 
	 * @param topId
	 * @param page
	 * @param limit
	 * @return
	 */
	List<SearcherProduct> findProductByTopCategory(Long topId, Date afterModifyDate, int page, int limit);

	/**
	 * 相似商品对比数据
	 * 
	 * @param topId
	 * @param page
	 * @param limit
	 * @return
	 */
	List<SearcherProduct> findProductTargets(Long topId, int page, int limit);

	/**
	 * 热卖商品列表
	 * 
	 * @param categoryId
	 * @param page
	 * @return
	 */
	PageResult<SearcherProduct> findSaleProductByCategory(Long productCategoryId, PageModel pageModel);

	/**
	 * 设计师销售商品数量
	 * 
	 * @param categoryId
	 * @param page
	 * @return
	 */
	int countSaleProductByDesigner(Long designerId);

	/**
	 * 根据关键字聚合 搜索帮助
	 * 
	 * @param page
	 * @return
	 */
	Map<String, List<ProductSearchHelp>> findProductSearchHelp(ProductProSearchQuery searcher, String[] params);

	/**
	 * 商品筛选服务
	 * 
	 * @param searcher
	 * @return
	 */
	JSONObject filterService(ProductProSearchQuery searcher);

	/**
	 * 商品筛选属性
	 * 
	 * @param searcher
	 * @return
	 */
	JSONObject filterProperties(ProductProSearchQuery searcher);

	/**
	 * 筛选列表的品牌
	 * 
	 * @param searcher
	 * @param page
	 * @return
	 */
	PageResult<JSONObject> brandList(ProductProSearchQuery searcher, PageModel page);

	/**
	 * 筛选列表的品牌
	 * 
	 * @param searcher
	 * @param page
	 * @return
	 */
	PageResult<JSONObject> seriesList(ProductProSearchQuery searcher, PageModel page);

	/**
	 * 首页查询推荐商品
	 * 
	 * @param searcher
	 * @param page
	 * @return
	 */
	PageResult<SearcherProduct> searchRecToHomePage(ProductProSearchQuery searcher, PageModel page);

	/**
	 * 查询有上新的品牌(upMarketDate)
	 * 
	 * @param pageSize
	 * @param productSize
	 * @param designerIds
	 * @return
	 */
	Map<Long, List<SearcherProduct>> findNewUpMarketByBrand(Integer pageSize, Integer productSize,
			List<Long> designerIds);

	/**
	 * 查询某一段时间内有上新的品牌(upMarketDate)
	 * 
	 * @param day
	 * @param size
	 * @param topIds
	 * @return
	 */
	List<Long> findNewUpMarketGroupByBrand(Integer day, Integer size, Long[] topIds);

	/**
	 * 最新创建商品的品牌(createDate)
	 * 
	 * @param day
	 * @param size
	 * @return
	 */
	List<Long> findNewUpGoodsGroupByBrand(Integer day, Integer size, Long[] topIds);

	/**
	 * 查询品牌新上架的商品数量
	 * 
	 * @return
	 */
	JSONArray findNewUpGoodsCountGroupBrand(Integer day, Integer size);

	/**
	 * 查询品类新上架的商品数量
	 * 
	 * @return
	 */
	Map<Long, Long> findNewUpGoodsCountGroupTopCategory(Integer day, Integer size);

	/**
	 * 根据商品id搜索
	 * 
	 * @param productIds
	 * @param mark
	 * @return
	 */
	Map<String, SearcherProduct> findOrderProductIds(List<String> productIds, Integer mark);

}
