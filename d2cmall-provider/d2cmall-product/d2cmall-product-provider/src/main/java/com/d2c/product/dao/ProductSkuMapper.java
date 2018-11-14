package com.d2c.product.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.product.dto.ProductSkuDto;
import com.d2c.product.model.ProductSku;
import com.d2c.product.query.ProductSkuSearcher;
import com.d2c.product.query.ProductSkuStockSearcher;
import com.d2c.product.third.kaola.KaolaProduct;

public interface ProductSkuMapper extends SuperMapper<ProductSku> {

	List<ProductSku> findByIds(@Param("ids") Long[] ids);

	List<ProductSku> findBySearch(@Param("searcher") ProductSkuSearcher searcher, @Param("pager") PageModel pager);

	int countBySearch(@Param("searcher") ProductSkuSearcher searcher);

	ProductSku findByBarCode(String barCode);

	List<ProductSku> findListByBarCode(String barCode);

	int initPopStore(@Param("designerId") Long designerId);

	int updatePopStore(@Param("popStore") Integer popStore, @Param("barCode") String barCode);

	int updateFlashStore(@Param("skuId") Long skuId, @Param("quantity") int quantity,
			@Param("collagePrice") BigDecimal collagePrice, @Param("flashPrice") BigDecimal flashPrice);

	int doSyncSkuStore();

	int doSumSkuStore();

	int doRepairFreezeStock();

	List<ProductSku> findByProductId(Long productId);

	int countByProductId(@Param("productId") Long id);

	List<Map<String, Object>> findStockByStore(@Param("barCode") String barCode, @Param("primary") int primary);

	/**
	 * 冻结库存 或 消除冻结库存
	 */
	int freezeStock(@Param("skuId") Long skuId, @Param("quantity") int quantity);

	/**
	 * 消除冻结库存 和 减去真实库存
	 */
	int cancelFreezeAndDeduceStock(@Param("skuId") Long skuId, @Param("quantity") int quantity, @Param("pop") int pop);

	/**
	 * 消除冻结库存 和 减去活动库存
	 */
	int cancelFreezeAndDeduceFlashStock(@Param("skuId") Long skuId, @Param("quantity") int quantity,
			@Param("pop") int pop);

	int updateProductSkuSales(@Param("productSkuId") Long productSkuId, @Param("productQuantity") int productQuantity);

	int updateStatusByProduct(@Param("productId") Long productId, @Param("status") Integer status,
			@Param("adminName") String adminName);

	int updateExternalSnBySkuSn(@Param("barCode") String skuSn, @Param("externalSn") String externalSn);

	int updateAPrice(@Param("skuId") Long skuId, @Param("aPrice") BigDecimal aPrice);

	List<KaolaProduct> findByProductSource(@Param("source") String source, @Param("pager") PageModel page);

	int countByProductSource(@Param("source") String source);

	int updateKaolaByBarCode(@Param("popStore") Integer popStore, @Param("barCode") String barCode,
			@Param("kaolaPrice") BigDecimal kaolaPrice);

	int countKaolaByWaitingForRepair();

	List<ProductSku> findKaolaByWaitingForRepair(@Param("pager") PageModel page);

	List<ProductSku> findByBrandIdAndExternalSn(@Param("brandId") Long brandId, @Param("externalSn") String externalSn);

	List<ProductSkuDto> findByStore(@Param("storeCode") String storeCode,
			@Param("searcher") ProductSkuStockSearcher searcher, @Param("pager") PageModel page);

	int countByStore(@Param("storeCode") String storeCode, @Param("searcher") ProductSkuStockSearcher searcher);

}
