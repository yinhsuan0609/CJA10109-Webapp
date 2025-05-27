package com.product.model;

import java.sql.*;
import java.util.*;

import com.product.model.ProductVO;

public class ProductJDBCDAO implements ProductDAOInterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/islevilla?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "Aa581107";

	private static final String INSERT_STMT = "INSERT INTO product (product_category_id, product_name, product_price, product_description, product_quantity, product_status) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM product";
	private static final String GET_ONE_STMT = "SELECT * FROM product WHERE product_id = ?";
	private static final String DELETE = "DELETE FROM product WHERE product_id = ?";
	private static final String UPDATE = "UPDATE product SET product_category_id=?, product_name=?, product_price=?, product_description=?, product_quantity=?, product_status=? WHERE product_id = ?";

	@Override
	public void insert(ProductVO productVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
			 PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			Class.forName(driver);

			pstmt.setInt(1, productVO.getProductCategoryId());
			pstmt.setString(2, productVO.getProductName());
			pstmt.setDouble(3, productVO.getProductPrice());
			pstmt.setString(4, productVO.getProductDescription());
			pstmt.setInt(5, productVO.getProductQuantity());
			pstmt.setInt(6, productVO.getProductStatus());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("A database error occurred: " + e.getMessage());
		}
	}

	@Override
	public void update(ProductVO productVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
			 PreparedStatement pstmt = con.prepareStatement(UPDATE)) {

			Class.forName(driver);

			pstmt.setInt(1, productVO.getProductCategoryId());
			pstmt.setString(2, productVO.getProductName());
			pstmt.setDouble(3, productVO.getProductPrice());
			pstmt.setString(4, productVO.getProductDescription());
			pstmt.setInt(5, productVO.getProductQuantity());
			pstmt.setInt(6, productVO.getProductStatus());
			pstmt.setInt(7, productVO.getProductId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("A database error occurred: " + e.getMessage());
		}
	}

	@Override
	public void delete(Integer productId) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
			 PreparedStatement pstmt = con.prepareStatement(DELETE)) {

			Class.forName(driver);
			pstmt.setInt(1, productId);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("A database error occurred: " + e.getMessage());
		}
	}

	@Override
	public ProductVO findByPK(Integer productId) {
		ProductVO productVO = null;

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
			 PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT)) {

			Class.forName(driver);
			pstmt.setInt(1, productId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getInt("product_id"));
				productVO.setProductCategoryId(rs.getInt("product_category_id"));
				productVO.setProductName(rs.getString("product_name"));
				productVO.setProductPrice(rs.getDouble("product_price"));
				productVO.setProductDescription(rs.getString("product_description"));
				productVO.setProductQuantity(rs.getInt("product_quantity"));
				productVO.setProductStatus(rs.getInt("product_status"));
			}

			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("A database error occurred: " + e.getMessage());
		}

		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
			 PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
			 ResultSet rs = pstmt.executeQuery()) {

			Class.forName(driver);

			while (rs.next()) {
				ProductVO productVO = new ProductVO();
				productVO.setProductId(rs.getInt("product_id"));
				productVO.setProductCategoryId(rs.getInt("product_category_id"));
				productVO.setProductName(rs.getString("product_name"));
				productVO.setProductPrice(rs.getDouble("product_price"));
				productVO.setProductDescription(rs.getString("product_description"));
				productVO.setProductQuantity(rs.getInt("product_quantity"));
				productVO.setProductStatus(rs.getInt("product_status"));
				list.add(productVO);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("A database error occurred: " + e.getMessage());
		}

		return list;
	}
}
