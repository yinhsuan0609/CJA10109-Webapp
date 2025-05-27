package com.productcategory.model;

import java.util.List;

public interface ProductCategoryDAOInterface {
	public void insert(ProductCategoryVO productCategoryVO);
	public void update(ProductCategoryVO productCategoryVO);
	public void delete(Integer productCategoryId);
	public ProductCategoryVO findByPK(Integer productCategoryId);
	public List<ProductCategoryVO> getAll();
	

}
