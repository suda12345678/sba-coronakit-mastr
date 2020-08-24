package com.wellsfargo.sba.coronakit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.wellsfargo.sba.coronakit.exception.CkException;
import com.wellsfargo.sba.coronakit.modal.ProductMaster;



public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public static final String INS_ITEM_QRY = "INSERT INTO products(prodname,cost,prodesc) values(?,?,?)";
	public static final String UPD_ITEM_QRY = "UPDATE products SET prodname=?,cost=?,prodesc=? WHERE productid=?";
	public static final String DEL_ITEM_QRY = "DELETE FROM products WHERE productid=?";
	public static final String SEL_ITEM_QRY_BY_ID = "SELECT productid,prodname,cost,prodesc FROM products WHERE productid=?";
	public static final String SEL_ALL_ITEMS_QRY = "SELECT productid,prodname,cost,prodesc FROM products";

	public ProductMasterDao() {
		
	}

	/*protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}*/

	public ProductMaster add(ProductMaster prodmaster) throws CkException {
		if (prodmaster != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_ITEM_QRY)) {

				//pst.setInt(1, prodmaster.getId());
				pst.setString(1, prodmaster.getProductName());
				pst.setDouble(2, prodmaster.getCost());

				pst.setString(3, prodmaster.getProductDescription());

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CkException("Saving the item failed!");
			}
		}
		return prodmaster;
	}

	public ProductMaster save(ProductMaster prodmaster) throws CkException {
		if (prodmaster != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_ITEM_QRY)) {

				pst.setInt(1, prodmaster.getId());
				pst.setString(2, prodmaster.getProductName());
				pst.setDouble(3, prodmaster.getCost());
				pst.setString(4, prodmaster.getProductDescription());
				pst.setInt(5, prodmaster.getId());
				
				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();

				throw new CkException("Saving the item failed!");
			}
		}
		return prodmaster;
	}

	public boolean deleteById(Integer prodid) throws CkException {
		boolean isDeleted = false;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_ITEM_QRY)) {

			pst.setInt(1, prodid);

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CkException("Deleting the item failed!");
		}

		return isDeleted;
	}

	public ProductMaster getById(Integer prodid) throws CkException {
		ProductMaster prodmaster = null;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ITEM_QRY_BY_ID)) {

			pst.setInt(1, prodid);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				prodmaster = new ProductMaster();
				prodmaster.setId(rs.getInt(1));
				prodmaster.setProductName(rs.getString(2));
				prodmaster.setCost(rs.getDouble(3));
				prodmaster.setProductDescription(rs.getString(4));
			}

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();

			throw new CkException("Retrival the item failed!");
		}

		return prodmaster;
	}

	public List<ProductMaster> getAll() throws CkException {
		List<ProductMaster> prodmasterdetails = new ArrayList<>();

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ALL_ITEMS_QRY)) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ProductMaster prodmaster = new ProductMaster();

				prodmaster.setId(rs.getInt(1));
				prodmaster.setProductName(rs.getString(2));
				prodmaster.setCost(rs.getDouble(3));
				prodmaster.setProductDescription(rs.getString(4));

				prodmasterdetails.add(prodmaster);
			}

			if (prodmasterdetails.isEmpty()) {
				prodmasterdetails = null;
			}
		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();

			throw new CkException("Retrival the item failed!");
		}
		System.out.println("in showproducts getll() function");

		return prodmasterdetails;
	}
}