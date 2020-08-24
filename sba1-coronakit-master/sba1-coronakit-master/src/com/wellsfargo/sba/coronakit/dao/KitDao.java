package com.wellsfargo.sba.coronakit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.wellsfargo.sba.coronakit.exception.CkException;
import com.wellsfargo.sba.coronakit.modal.KitDetail;


public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public static final String INS_ITEM_QRY = "INSERT INTO kitdetail(coronaKitid,productid,quantity,amount) values(?,?,?,?)";
	public static final String UPD_ITEM_QRY = "UPDATE kitdetail SET coronaKitid=?,productid=?,quantity=?,amount=? WHERE kiddetailid=?";
	public static final String DEL_ITEM_QRY = "DELETE FROM kitdetail WHERE kiddetailid=?";
	public static final String SEL_ITEM_QRY_BY_ID = "SELECT kiddetailid,coronaKitid,productid,quantity,amount FROM kitdetail WHERE kiddetailid=?";
	public static final String SEL_ITEM_QRY_BY_COROID = "SELECT kiddetailid,coronaKitid,productid,quantity,amount FROM kitdetail WHERE coronaKitid=?";

	public static final String SEL_ALL_ITEMS_QRY = "SELECT kiddetailid,coronaKitid,productid,quantity,amount FROM kitdetail";

	/*public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
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

	public KitDetail add(KitDetail kitdetail) throws CkException {
		if (kitdetail != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_ITEM_QRY)) {

				//pst.setInt(1, kitdetail.getId());
				pst.setInt(1, kitdetail.getCoronaKitId());
				pst.setInt(2, kitdetail.getProductId());
				pst.setInt(3, kitdetail.getQuantity());
				pst.setDouble(4, kitdetail.getAmount());

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CkException("Saving the item failed!");
			}
		}
		return kitdetail;
	}

	public KitDetail save(KitDetail kitdetail) throws CkException {
		if (kitdetail != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_ITEM_QRY)) {

				pst.setInt(2, kitdetail.getCoronaKitId());
				pst.setInt(3, kitdetail.getProductId());
				pst.setInt(4, kitdetail.getQuantity());
				pst.setDouble(5, kitdetail.getAmount());

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();

				throw new CkException("Saving the item failed!");
			}
		}
		return kitdetail;
	}

	public boolean deleteById(Integer kitdetailid) throws CkException {
		boolean isDeleted = false;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_ITEM_QRY)) {

			pst.setInt(1, kitdetailid);

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CkException("Deleting the item failed!");
		}

		return isDeleted;
	}
	public List<KitDetail> getAll() throws CkException {
		List<KitDetail> kitdetails = new ArrayList<>();

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ALL_ITEMS_QRY)) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				KitDetail kitdetail = new KitDetail();

				kitdetail.setId(rs.getInt(1));
				kitdetail.setCoronaKitId(rs.getInt(2));
				kitdetail.setProductId(rs.getInt(3));
				kitdetail.setQuantity(rs.getInt(4));
				kitdetail.setAmount(rs.getDouble(5));

				kitdetails.add(kitdetail);
			}

			if (kitdetails.isEmpty()) {
				kitdetails = null;
			}
		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();

			throw new CkException("Retrival the item failed!");
		}
		return kitdetails;
	}
	public List<KitDetail> getByCoronaKitId(Integer coronakitid) throws CkException {
		List<KitDetail> kitdetails = new ArrayList<>();

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ITEM_QRY_BY_COROID)) {
			pst.setInt(1, coronakitid);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				KitDetail kitdetail = new KitDetail();
				kitdetail.setId(rs.getInt(1));
				kitdetail.setCoronaKitId(rs.getInt(2));
				kitdetail.setProductId(rs.getInt(3));
				kitdetail.setQuantity(rs.getInt(4));
				kitdetail.setAmount(rs.getDouble(5));
				kitdetails.add(kitdetail);

			}

			if (kitdetails.isEmpty()) {
				kitdetails = null;
			}
		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();

			throw new CkException("Retrival the item failed!");
		}
		return kitdetails;

	}
	public KitDetail getById(Integer kitdetailid) throws CkException {
		KitDetail kitdetail = null;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ITEM_QRY_BY_ID)) {

			pst.setInt(1, kitdetailid);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				kitdetail = new KitDetail();
				kitdetail.setId(rs.getInt(1));
				kitdetail.setCoronaKitId(rs.getInt(2));
				kitdetail.setProductId(rs.getInt(3));
				kitdetail.setQuantity(rs.getInt(4));
				kitdetail.setAmount(rs.getDouble(5));
			}

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();

			throw new CkException("Retrival the item failed!");
		}

		return kitdetail;
	}

	

}