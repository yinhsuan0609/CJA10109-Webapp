package com.product.controller;

import java.io.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.product.model.ProductService;
import com.product.model.ProductVO;

public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String str = req.getParameter("productId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入商品編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer productId = null;
			try {
				productId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("商品編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			ProductService productSvc = new ProductService();
			ProductVO productVO = productSvc.getOneProduct(productId);
			if (productVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("productVO", productVO);
			String url = "/product/listOneProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer productId = Integer.valueOf(req.getParameter("productId"));

			ProductService productSvc = new ProductService();
			ProductVO productVO = productSvc.getOneProduct(productId);

			req.setAttribute("productVO", productVO);
			String url = "/product/update_product_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer productId = Integer.valueOf(req.getParameter("productId").trim());

			String productName = req.getParameter("productName");
			String productNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.add("商品名稱: 請勿空白");
			} else if (!productName.trim().matches(productNameReg)) {
				errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			Double productPrice = null;
			try {
				productPrice = Double.valueOf(req.getParameter("productPrice").trim());
			} catch (NumberFormatException e) {
				productPrice = 0.0;
				errorMsgs.add("價格請填數字.");
			}

			String productDescription = req.getParameter("productDescription");
			if (productDescription == null || productDescription.trim().length() == 0) {
				errorMsgs.add("描述請勿空白");
			} else {
				productDescription = productDescription.trim();
			}

			Integer productQuantity = null;
			try {
				productQuantity = Integer.valueOf(req.getParameter("productQuantity").trim());
			} catch (NumberFormatException e) {
				productQuantity = 0;
				errorMsgs.add("庫存量請填數字.");
			}

			Integer productStatus = null;
			String statusStr = req.getParameter("productStatus");
			if (statusStr == null || statusStr.trim().isEmpty()) {
				productStatus = 0;
				errorMsgs.add("請選擇商品狀態");
			} else {
				try {
					productStatus = Integer.valueOf(statusStr.trim());
					if (productStatus != 0 && productStatus != 1) {
						errorMsgs.add("商品狀態只能是 0 或 1");
					}
				} catch (NumberFormatException e) {
					productStatus = 0;
					errorMsgs.add("商品狀態格式錯誤");
				}
			}

			Integer productCategoryId = Integer.valueOf(req.getParameter("productCategoryId").trim());

			ProductVO productVO = new ProductVO();
			productVO.setProductId(productId);
			productVO.setProductCategoryId(productCategoryId);
			productVO.setProductName(productName);
			productVO.setProductPrice(productPrice);
			productVO.setProductDescription(productDescription);
			productVO.setProductQuantity(productQuantity);
			productVO.setProductStatus(productStatus);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product_input.jsp");
				failureView.forward(req, res);
				return;
			}

			ProductService productSvc = new ProductService();
			productVO = productSvc.updateProduct(productId, productCategoryId, productName, productPrice, productDescription, productQuantity, productStatus);

			req.setAttribute("productVO", productVO);
			String url = "/product/listOneProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String productName = req.getParameter("productName");
			String productNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.add("商品名稱: 請勿空白");
			} else if (!productName.trim().matches(productNameReg)) {
				errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			Double productPrice = null;
			try {
				productPrice = Double.valueOf(req.getParameter("productPrice").trim());
			} catch (NumberFormatException e) {
				productPrice = 0.0;
				errorMsgs.add("價格請填數字.");
			}

			String productDescription = req.getParameter("productDescription");
			if (productDescription == null || productDescription.trim().length() == 0) {
				errorMsgs.add("描述請勿空白");
			} else {
				productDescription = productDescription.trim();
			}

			Integer productQuantity = null;
			try {
				productQuantity = Integer.valueOf(req.getParameter("productQuantity").trim());
			} catch (NumberFormatException e) {
				productQuantity = 0;
				errorMsgs.add("庫存量請填數字.");
			}

			Integer productStatus = null;
			String statusStr = req.getParameter("productStatus");
			if (statusStr == null || statusStr.trim().isEmpty()) {
				productStatus = 0;
				errorMsgs.add("請選擇商品狀態");
			} else {
				try {
					productStatus = Integer.valueOf(statusStr.trim());
					if (productStatus != 0 && productStatus != 1) {
						errorMsgs.add("商品狀態只能是 0 或 1");
					}
				} catch (NumberFormatException e) {
					productStatus = 0;
					errorMsgs.add("商品狀態格式錯誤");
				}
			}

			Integer productCategoryId = Integer.valueOf(req.getParameter("productCategoryId").trim());

			ProductVO productVO = new ProductVO();
			productVO.setProductCategoryId(productCategoryId);
			productVO.setProductName(productName);
			productVO.setProductPrice(productPrice);
			productVO.setProductDescription(productDescription);
			productVO.setProductQuantity(productQuantity);
			productVO.setProductStatus(productStatus);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
				failureView.forward(req, res);
				return;
			}

			ProductService productSvc = new ProductService();
			productVO = productSvc.addProduct(productCategoryId, productName, productPrice, productDescription, productQuantity, productStatus);

			String url = "/product/listAllProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer productId = Integer.valueOf(req.getParameter("productId"));

			ProductService productSvc = new ProductService();
			productSvc.deleteProduct(productId);

			String url = "/product/listAllProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
