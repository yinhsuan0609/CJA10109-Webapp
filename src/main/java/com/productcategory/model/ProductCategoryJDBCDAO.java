package com.productcategory.model;

import java.sql.*;
import java.util.*;

public class ProductCategoryJDBCDAO implements ProductCategoryDAOInterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/islevilla?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "Aa581107";

	private static final String INSERT_STMT =
		"INSERT INTO product_category (product_category_name) VALUES (?)";
	private static final String GET_ALL_STMT =
		"SELECT product_category_id, product_category_name FROM product_category";
	private static final String GET_ONE_STMT =
		"SELECT product_category_id, product_category_name FROM product_category WHERE product_category_id = ?";
	private static final String DELETE =
		"DELETE FROM product_category WHERE product_category_id = ?";
	private static final String UPDATE =
		"UPDATE product_category SET product_category_name = ? WHERE product_category_id = ?";

	@Override
	public void insert(ProductCategoryVO categoryVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
		     PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			Class.forName(driver);
			pstmt.setString(1, categoryVO.getProductCategoryName());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Database error: " + e.getMessage());
		}
	}

	@Override
	public void update(ProductCategoryVO categoryVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
		     PreparedStatement pstmt = con.prepareStatement(UPDATE)) {

			Class.forName(driver);
			pstmt.setString(1, categoryVO.getProductCategoryName());
			pstmt.setInt(2, categoryVO.getProductCategoryId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Database error: " + e.getMessage());
		}
	}

	@Override
	public void delete(Integer categoryId) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
		     PreparedStatement pstmt = con.prepareStatement(DELETE)) {

			Class.forName(driver);
			pstmt.setInt(1, categoryId);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Database error: " + e.getMessage());
		}
	}

	@Override
	public ProductCategoryVO findByPK(Integer categoryId) {
		ProductCategoryVO vo = null;

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
		     PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT)) {

			Class.forName(driver);
			pstmt.setInt(1, categoryId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new ProductCategoryVO();
				vo.setProductCategoryId(rs.getInt("product_category_id"));
				vo.setProductCategoryName(rs.getString("product_category_name"));
			}
			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Database error: " + e.getMessage());
		}
		return vo;
	}

	@Override
	public List<ProductCategoryVO> getAll() {
		List<ProductCategoryVO> list = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
		     PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
		     ResultSet rs = pstmt.executeQuery()) {

			Class.forName(driver);

			while (rs.next()) {
				ProductCategoryVO vo = new ProductCategoryVO();
				vo.setProductCategoryId(rs.getInt("product_category_id"));
				vo.setProductCategoryName(rs.getString("product_category_name"));
				list.add(vo);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Database error: " + e.getMessage());
		}
		return list;
	}
}
