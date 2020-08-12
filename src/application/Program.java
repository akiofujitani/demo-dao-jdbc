package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();		//SellerDao interface >> SellerDaoJDBC being instantiated by DaoFactory method
		
		Seller seller = sellerDao.findById(3);					//SellerDaoJDBC frindById method
		
		System.out.println(seller);

	}

}
