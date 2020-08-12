package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	/*
	 * the class method returns the implementation
	 * 
	 * this is useful to don't make explicit the implementation 
	 */

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());	//Instantiate the constructor from SellerDaoJDBC and set the conn connection to database
	}
}
