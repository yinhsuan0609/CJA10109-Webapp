package com.product.model;

import java.util.List;

public interface ProductDAOInterface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void delete(Integer productId);
	public ProductVO findByPK(Integer productId);
	public List<ProductVO> getAll();
}
