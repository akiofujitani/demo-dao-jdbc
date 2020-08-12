package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;					//object Connection conn to be used by all methods
	
	public SellerDaoJDBC(Connection conn) {		//Contructor to receives the conn object
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "	//SELCT >> seller . * < all rows, department.Name as DepName << nickname
					+ "FROM seller INNER JOIN department "			//Merge the following values from seller and department tables
					+ "ON seller.DepartmentId = department.Id "		//seller.DepartmentId = department.Id
					+ "WHERE seller.Id = ? ");						//condition to return just one result
			
			st.setInt(1, id);										//define argument value as argument from method
			rs = st.executeQuery();									
			
			//Associated objects
			if (rs.next()) {
				Department dep = new Department();					//Instantiate a temporary Department object
				dep.setId(rs.getInt("DepartmentId"));				//Retrieves and set the desired value in temporary object
				dep.setName(rs.getString("DepName"));				//Retrieves and set the desired value in temporary object
				
				Seller obj = new Seller();							//Instantiate a temporary Seller object
				obj.setId(rs.getInt("Id"));							//Retrieves and set the desired value in temporary object
				obj.setName(rs.getString("Name"));					//Retrieves and set the desired value in temporary object
				obj.setEmail(rs.getString("Email"));				//Retrieves and set the desired value in temporary object
				obj.setBaseSalary(rs.getDouble("BaseSalary"));		//Retrieves and set the desired value in temporary object
				obj.setBirthDate(rs.getDate("BirthDate"));			//Retrieves and set the desired value in temporary object
				obj.setDepartment(dep);								//Set the temporary Department object to the Seller object
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
