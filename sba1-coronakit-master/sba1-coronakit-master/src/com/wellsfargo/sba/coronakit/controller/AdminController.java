package com.wellsfargo.sba.coronakit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellsfargo.sba.coronakit.dao.KitDao;
import com.wellsfargo.sba.coronakit.dao.ProductMasterDao;
import com.wellsfargo.sba.coronakit.dao.UserDao;
import com.wellsfargo.sba.coronakit.exception.CkException;
import com.wellsfargo.sba.coronakit.modal.ProductMaster;
import com.wellsfargo.sba.coronakit.service.ProductService;
import com.wellsfargo.sba.coronakit.service.ProductServiceImpl;

@WebServlet("/admin")

public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}



	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		// String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		// String jdbcUsername =
		// config.getServletContext().getInitParameter("jdbcUsername");
		// String jdbcPassword = config.
		// getServletContext().getInitParameter("jdbcPassword");

		this.productMasterDao = new ProductMasterDao();
		this.productService = new ProductServiceImpl();

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		System.out.println("action=" + action);
		String viewName = "";
		try {
			switch (action) {
			case "login":
				viewName = adminLogin(request, response);
				break;
			case "newproduct":
				viewName = showNewProductForm(request, response);
				break;

			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "saveproduct":
				viewName = saveProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
				break;
			default:
				viewName = "notfound.jsp";
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);

	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = "";
		HttpSession session = request.getSession();

		String uname = request.getParameter("loginid");
		String pass = request.getParameter("password");
		System.out.println(uname + "and " + pass);

		if (uname.isEmpty() || pass.isEmpty()) {
			view = "index.jsp";
		}
		else if (uname.equalsIgnoreCase("admin") && (pass.equalsIgnoreCase("admin"))) {

			session.setAttribute("username", uname);
			view = "adminlogin.jsp";
		} 
		
		return view;

	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		String view = "";

		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
		view = "index.jsp";

		return view;
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) {

		String view = "";
		try {
			List<ProductMaster> products = productService.getAllItems();
			request.setAttribute("productslist", products);
			view = "listproducts.jsp";
		} catch (CkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errorPage.jsp";
		}

		return view;

	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		String view = "";

		try {
			ProductMaster product = productService.getItemById(id);
			request.setAttribute("product", product);
			view = "newproduct.jsp";
		} catch (CkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;

	}

	private String saveProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductMaster products = new ProductMaster();

		products.setId(Integer.parseInt(request.getParameter("id")));
		products.setProductName(request.getParameter("title"));
		products.setCost(Double.parseDouble(request.getParameter("cost")));
		products.setProductDescription(request.getParameter("desc"));

		String view = "";

		try {
			productService.validateAndAdd(products);
			request.setAttribute("msg", "Item Got Saved!");
			view = "adminlogin.jsp";
		} catch (CkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("integer param=" + id);
		String view = "";

		try {
			productService.deleteItem(id);
			request.setAttribute("msg", "Item Got Deleted!");
			view = "adminlogin.jsp";
		} catch (CkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;

	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) throws CkException {

		String view = "";

		ProductMaster product = new ProductMaster();
		request.setAttribute("product", product);
		view = "newproduct.jsp";
		return view;

	}

}
