package com.d2c.boss.product.service;

import java.util.List;

import com.d2c.boss.product.model.SkuStockSync;
import com.d2c.common.api.page.PageModel;

public interface SkuStockSyncService {

	/**
	 * 通过版本号查看sku同步记录
	 */
	List<SkuStockSync> findByVersion(int version, PageModel page);

	/**
	 * 通过版本号查看sku同步记录
	 */
	Integer countByVersion(int version);

	/**
	 * 更新key值
	 */
	void doUpdateSkuStoreKey();

	/**
	 * 新增sku库存同步记录
	 */
	void doInsertSkuStore();

	/**
	 * 删除sku库存同步记录
	 */
	void doDeleteSkuStore();

	/**
	 * 修改sku库存同步记录
	 */
	void doUpdateSkuStore();

	/**
	 * 同步成功
	 */
	void doSyncSuccess();

	/**
	 * 单条同步成功
	 */
	void doSyncSuccess(Long id);

}
