package com.d2c.product.search.enums;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.alibaba.dubbo.common.utils.StringUtils;

/*
 *  1.综合排序:显示规则“有货“-”主推”-“按销量”-“上架时间-无货” 
 *  2.最新：上架时间-有货-主推；
 *  3.销量：销量-有货-主推 
 *  4.价格：价格-有货-主推； 
 *  5.店铺作品排序：有货-系列-推荐值
 * 
 */
public enum ProductOrderEnum {
	
	def("默认排序"){
		Order[] makeOrders(){
			return new Order[]{new Order(Direction.DESC, "store"),
	    			new Order(Direction.DESC, "recomScore"),
	    			new Order(Direction.DESC, "sales"),
	    			new Order(Direction.DESC, "upMarketDate")
	    	};
		}
	}, 
	pa("价格从低到高"){
		Order[] makeOrders(){
			return new Order[]{
	    			new Order(Direction.DESC, "store"),
	    			new Order(Direction.ASC, "promotionPrice"),
	    			new Order(Direction.DESC, "recomScore")
	    	};
		}
	}, 
	pd("价格从高到低"){
		Order[] makeOrders(){
			return new Order[]{
	    			new Order(Direction.DESC, "store"),
	    			new Order(Direction.DESC, "promotionPrice"),
	    			new Order(Direction.DESC, "sales")
	    	};
		}
	},  
	da("上架时间最早"){
		Order[] makeOrders(){
			return new Order[]{
	    			new Order(Direction.DESC, "store"),
	    			new Order(Direction.ASC, "upMarketDate"),
	    			new Order(Direction.DESC, "sales")
	    	};
		}
	},  
	dd("上架时间最晚"){
		Order[] makeOrders(){
			return new Order[]{
	    			new Order(Direction.DESC, "store"),
	    			new Order(Direction.DESC, "upMarketDate"),
	    			new Order(Direction.DESC, "sales")
	    	};
		}
	},  
	sa("销量从少到多 "){
		Order[] makeOrders(){
			return new Order[]{
	    			new Order(Direction.DESC, "store"),
	    			new Order(Direction.ASC, "sales"),
	    			new Order(Direction.DESC, "recomScore")
	    	};
		}
	},  
	sd("销量从多到少"){
		Order[] makeOrders(){
			return new Order[]{
	    			new Order(Direction.DESC, "store"),
	    			new Order(Direction.DESC, "sales"),
	    			new Order(Direction.DESC, "recomScore")
	    	};
		}
	},  
	bd("作品排序"){
		Order[] makeOrders(){
			return new Order[]{
	    			new Order(Direction.DESC, "store"),
	    			new Order(Direction.DESC, "seriesUpDate"),
	    			new Order(Direction.DESC, "recomScore")
	    	};
		}
	};

	String display;
	Sort sort;
	
	ProductOrderEnum(String display) {
		this.display = display;
	}
	
	public static Sort getSort(String key){
		ProductOrderEnum en = getDefault();
		if(StringUtils.isNotEmpty(key)){
			try {
				en = valueOf(key);
			} catch (Exception e) {}
		}
		return en.getSort();
	}
	
	public static ProductOrderEnum getDefault(){
		return def;
	}
	
	//****************** private *********************
	
	Order[] makeOrders(){
		return getDefault().makeOrders();
	}
	
	Sort getSort(){
		if(sort == null){
			sort = new Sort(makeOrders());
		}
		return sort;
	}

}
