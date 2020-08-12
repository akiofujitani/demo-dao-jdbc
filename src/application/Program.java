package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();		//SellerDao interface >> SellerDaoJDBC being instantiated by DaoFactory method
		
		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDao.findById(3);					//SellerDaoJDBC frindById method
		
		System.out.println(seller);

	}

}
