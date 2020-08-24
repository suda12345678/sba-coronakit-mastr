package com.wellsfargo.sba.coronakit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.wellsfargo.sba.coronakit.exception.CkException;
import com.wellsfargo.sba.coronakit.modal.User;

public class UserDao {

	public static final String INS_ITEM_QRY = "INSERT INTO user(name,email,username,password,contactnumber,address) values(?,?,?,?,?,?)";

	public static final String SEL_ITEM_QRY_BY_ID = "SELECT count(*) FROM user WHERE username=?";

	public UserDao() {

	}

	public User add(User user) throws CkException {

		if (user != null){
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_ITEM_QRY)) {

				pst.setString(1, user.getName());
				pst.setString(2, user.getEmail());
				pst.setString(3, user.getUname());
				pst.setString(4, user.getPassword());
				pst.setString(5, user.getContactNumber());
				pst.setString(6, user.getAddress());

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CkException("Saving the item failed! ");
			}
		}

		return user;
	}

	public boolean userAlreadyExists(String username) throws CkException {
		int usercount = 0;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ITEM_QRY_BY_ID)) {

			pst.setString(1, username);

			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				usercount = rs.getInt(1);
				}
			System.out.println(usercount);

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();

			throw new CkException("Retrival the item failed!");
		}
		if (usercount == 1)
			return true;
		else
			return false;
	}

}