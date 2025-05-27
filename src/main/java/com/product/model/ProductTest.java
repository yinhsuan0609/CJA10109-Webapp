package com.product.model;

import java.util.List;

public class ProductTest {

	public static void main(String[] args) {

		ProductJDBCDAO dao = new ProductJDBCDAO();

		// 1. 新增
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setProductCategoryId(1); 
//		productVO1.setProductName("無線滑鼠");
//		productVO1.setProductPrice(499.0);
//		productVO1.setProductDescription("人體工學設計藍牙連線");
//		productVO1.setProductQuantity(50);
//		productVO1.setProductStatus(1);
//		dao.insert(productVO1);

		// 2. 修改
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setProductId(1); 
//		productVO2.setProductCategoryId(2);
//		productVO2.setProductName("無線鍵盤");
//		productVO2.setProductPrice(699.0);
//		productVO2.setProductDescription("靜音設計，支援多系統");
//		productVO2.setProductQuantity(30);
//		productVO2.setProductStatus(1);
//		dao.update(productVO2);

		// 3. 刪除
//		dao.delete(2); // 假設刪除 product_id = 2 的資料

		// 4. 查詢單筆
		ProductVO productVO3 = dao.findByPK(2); // 假設查 product_id = 2
		if (productVO3 != null) {
			System.out.print(productVO3.getProductId() + ", ");
			System.out.print(productVO3.getProductCategoryId() + ", ");
			System.out.print(productVO3.getProductName() + ", ");
			System.out.print(productVO3.getProductPrice() + ", ");
			System.out.print(productVO3.getProductDescription() + ", ");
			System.out.print(productVO3.getProductQuantity() + ", ");
			System.out.println(productVO3.getProductStatus());
			System.out.println("--------------------------");
		} else {
			System.out.println("找不到資料");
		}

		// 5. 查詢全部
		List<ProductVO> list = dao.getAll();
		for (ProductVO aProduct : list) {
			System.out.print(aProduct.getProductId() + ", ");
			System.out.print(aProduct.getProductCategoryId() + ", ");
			System.out.print(aProduct.getProductName() + ", ");
			System.out.print(aProduct.getProductPrice() + ", ");
			System.out.print(aProduct.getProductDescription() + ", ");
			System.out.print(aProduct.getProductQuantity() + ", ");
			System.out.println(aProduct.getProductStatus());
		}
	}
}
