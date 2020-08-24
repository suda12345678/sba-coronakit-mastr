package com.wellsfargo.sba.coronakit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.wellsfargo.sba.coronakit.modal.CoronaKit;
import com.wellsfargo.sba.coronakit.modal.KitDetail;
import com.wellsfargo.sba.coronakit.modal.OrderSummary;
import com.wellsfargo.sba.coronakit.modal.ProductMaster;
import com.wellsfargo.sba.coronakit.modal.User;
import com.wellsfargo.sba.coronakit.service.ProductService;
import com.wellsfargo.sba.coronakit.service.ProductServiceImpl;

@WebServlet("/user")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private KitDao kitDao;
	private ProductMasterDao productMasterDao;
	private ProductService productService;
	private UserDao userDao;
	private CoronaKit coronakit;
	private OrderSummary ordersummary;

	public HttpSession session;

	// private KitDetail kitdetail;
	public void setUserDao(OrderSummary ordersummary) {
		this.ordersummary = ordersummary;
	}

	public void setUserDao(CoronaKit coronakit) {
		this.coronakit = coronakit;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setKitDAO(KitDao kitDao) {
		this.kitDao = kitDao;
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

		this.kitDao = new KitDao();
		this.productMasterDao = new ProductMasterDao();
		this.userDao = new UserDao();
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

			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "existinguser":
				viewName = existinguserLogin(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;
			default:
				viewName = "notfound.jsp";
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);

	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		String view = "";

		view = "ordersummary.jsp";
		return view;
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		session = request.getSession();
		coronakit = new CoronaKit();
		ordersummary = new OrderSummary();
		double totalamount = 0;

		List<KitDetail> kitdetails = null;

		try {
			kitdetails = kitDao.getByCoronaKitId(Integer.parseInt(request.getParameter("id")));

		} catch (NumberFormatException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
			e.printStackTrace();
		} catch (CkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
			e.printStackTrace();
		}

		for (KitDetail temp : kitdetails) {
			totalamount += temp.getAmount();
		}
		session.setAttribute("totalamount", totalamount);

		coronakit.setId(Integer.parseInt(request.getParameter("id")));
		coronakit.setPersonName(request.getParameter("name"));
		coronakit.setEmail(request.getParameter("email"));
		coronakit.setContactNumber(request.getParameter("contact"));
		coronakit.setTotalAmount(totalamount);
		coronakit.setDeliveryAddress(request.getParameter("address"));
		coronakit.setOrderDate(request.getParameter("date"));
		coronakit.setOrderFinalized(request.getParameter("final") != null);

		if (request.getParameter("final") != null) {

			ordersummary.setKitDetails(kitdetails);
			ordersummary.setCoronaKit(coronakit);
			request.setAttribute("ordersummary", ordersummary);

			view = "ordersummary.jsp";
		}
		else
			request.setAttribute("msg", "Order is not finalized");

		return view;
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		String view = "";

		view = "placeorder.jsp";
		return view;
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) {

		String view = "";

		session = request.getSession();

		try {
			List<KitDetail> kitdetail = kitDao.getByCoronaKitId(1);
			request.setAttribute("kitlist", kitdetail);
			view = "showkitdetails.jsp";
		} catch (CkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}

		return view;
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String view = "";

		try {
			kitDao.deleteById(id);
			request.setAttribute("msg", "Item Got Deleted!");
			view = "index.jsp";
		} catch (CkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("add new item method");
		KitDetail kitdetail = new KitDetail();
		session = request.getSession();

		kitdetail.setCoronaKitId(1);
		kitdetail.setProductId(Integer.parseInt(request.getParameter("id")));
		kitdetail.setQuantity(1);
		kitdetail.setAmount(Double.parseDouble(request.getParameter("cost")));

		String view = "";

		try {
			kitDao.add(kitdetail);
			request.setAttribute("msg", "Item Got Added!");
			List<ProductMaster> products = productService.getAllItems();
			request.setAttribute("productslist", products);
			view = "showproductstoadd.jsp";
		} catch (CkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		try {
			List<ProductMaster> products = productService.getAllItems();
			request.setAttribute("productslist", products);
			view = "showproductstoadd.jsp";
		} catch (CkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errorPage.jsp";
		}
		System.out.println(view);
		return view;
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();

		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setUname(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setContactNumber(request.getParameter("contact"));
		user.setAddress(request.getParameter("address"));

		String view = "";

		try {
			if (userDao.userAlreadyExists(request.getParameter("username"))) {
				request.setAttribute("msg", "Username Already Exists!");
				view = "userregister.jsp";
			} else {
				user = userDao.add(user);
				request.setAttribute("user", user);
				request.setAttribute("msg", "Item Got Saved!");
				view = "userlogin.jsp";
			}
		} catch (CkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}

		return view;
	}

	private String existinguserLogin(HttpServletRequest request, HttpServletResponse response) throws CkException {

		String view = "";
		// HttpSession session = request.getSession();
		//
		// String uname = request.getParameter("loginid");
		// String pass = request.getParameter("password");
		// System.out.println(uname + "and " + pass);
		// boolean userexists=userDao.userAlreadyExists(uname);

		view = "userlogin.jsp";

		/*
		 * if (uname.isEmpty() || pass.isEmpty() || uname==null || pass==null) { view =
		 * "index.jsp"; } else if (!uname.equalsIgnoreCase("admin") &&
		 * !pass.equalsIgnoreCase("admin") && userexists) {
		 * 
		 * session.setAttribute("username", uname); view = "userlogin.jsp"; } else {
		 * view = "userlogin.jsp"; }
		 */

		return view;
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		request.setAttribute("user", user);

		return "userregister.jsp";
	}
}