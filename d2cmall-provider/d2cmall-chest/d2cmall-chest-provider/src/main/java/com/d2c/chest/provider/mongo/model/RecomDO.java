package com.d2c.chest.provider.mongo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.d2c.chest.api.dto.ProductRecomDTO;
import com.d2c.chest.api.dto.recom.RecomDimenDTO;
import com.d2c.common.mongodb.model.SuperMongoDO;

@Document
public class RecomDO extends SuperMongoDO {

	private static final long serialVersionUID = -4903284310787030896L;

	@Id
	private Long productId;
	
	private Object product;

	private ProductRecomDTO data;

	private List<RecomDimenDTO> dimens = new ArrayList<>();

	@Indexed
	private double score;

	public RecomDO(){}
	
	public RecomDO(Long id, Object product){
		this.productId = id;
		this.product = product;
		this.data = new ProductRecomDTO(id);
	}

	public void addRecomDimen(RecomDimenDTO dimen) {
		dimens.add(dimen);
	}

	// **************************************

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Object getProduct() {
		return product;
	}

	public void setProduct(Object product) {
		this.product = product;
	}

	public void setData(ProductRecomDTO data) {
		this.data = data;
	}

	public ProductRecomDTO getData() {
		return data;
	}

	public List<RecomDimenDTO> getDimens() {
		return dimens;
	}

	public void setDimens(List<RecomDimenDTO> dimens) {
		this.dimens = dimens;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
