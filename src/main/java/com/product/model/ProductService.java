package com.product.model;

import java.util.List;

public class ProductService {
	private ProductDAOInterface dao;

	public ProductService() {
		dao = new ProductJDBCDAO();
	}

	public ProductVO addProduct(Integer categoryId, String name, Double price, String desc, Integer qty, Integer status) {
		ProductVO vo = new ProductVO();
		vo.setProductCategoryId(categoryId);
		vo.setProductName(name);
		vo.setProductPrice(price);
		vo.setProductDescription(desc);
		vo.setProductQuantity(qty);
		vo.setProductStatus(status);
		dao.insert(vo);
		return vo;
	}

	public ProductVO updateProduct(Integer id, Integer categoryId, String name, Double price, String desc, Integer qty, Integer status) {
		ProductVO vo = new ProductVO();
		vo.setProductId(id);
		vo.setProductCategoryId(categoryId);
		vo.setProductName(name);
		vo.setProductPrice(price);
		vo.setProductDescription(desc);
		vo.setProductQuantity(qty);
		vo.setProductStatus(status);
		dao.update(vo);
		return vo;
	}

	public void deleteProduct(Integer id) {
		dao.delete(id);
	}

	public ProductVO getOneProduct(Integer id) {
		return dao.findByPK(id);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
}
