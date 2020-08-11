package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	/*
	 * the class method returns the implementation
	 * 
	 * this is useful to don't make explicit the implementation 
	 */

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
