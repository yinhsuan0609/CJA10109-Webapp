package com.productcategory.model;

import java.util.List;

public class ProductCategoryService {

	private ProductCategoryDAOInterface dao;

	public ProductCategoryService() {
		dao = new ProductCategoryJDBCDAO();
	}

	// 新增一筆分類
	public ProductCategoryVO addProductCategory(String categoryName) {
		ProductCategoryVO vo = new ProductCategoryVO();
		vo.setProductCategoryName(categoryName);
		dao.insert(vo);
		return vo;
	}

	// 修改分類
	public ProductCategoryVO updateProductCategory(Integer categoryId, String categoryName) {
		ProductCategoryVO vo = new ProductCategoryVO();
		vo.setProductCategoryId(categoryId);
		vo.setProductCategoryName(categoryName);
		dao.update(vo);
		return vo;
	}

	// 刪除分類
	public void deleteProductCategory(Integer categoryId) {
		dao.delete(categoryId);
	}

	// 查詢單一分類
	public ProductCategoryVO getOneProductCategory(Integer categoryId) {
		return dao.findByPK(categoryId);
	}

	// 查詢全部分類
	public List<ProductCategoryVO> getAll() {
		return dao.getAll();
	}
}
