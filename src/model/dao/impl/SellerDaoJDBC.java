package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				Department dep = intantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dep);
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
	
	//Function created for reuse purpuse
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();							//Instantiate a temporary Seller object
		obj.setId(rs.getInt("Id"));							//Retrieves and set the desired value in temporary object
		obj.setName(rs.getString("Name"));					//Retrieves and set the desired value in temporary object
		obj.setEmail(rs.getString("Email"));				//Retrieves and set the desired value in temporary object
		obj.setBaseSalary(rs.getDouble("BaseSalary"));		//Retrieves and set the desired value in temporary object
		obj.setBirthDate(rs.getDate("BirthDate"));			//Retrieves and set the desired value in temporary object
		obj.setDepartment(dep);								//Set the temporary Department object to the Seller object
		return obj;
	}


	private Department intantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();					//Instantiate a temporary Department object
		dep.setId(rs.getInt("DepartmentId"));				//Retrieves and set the desired value in temporary object
		dep.setName(rs.getString("DepName"));				//Retrieves and set the desired value in temporary object
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "		//SELCT >> seller . * < all rows, department.Name as DepName << nickname
					+ "FROM seller INNER JOIN department "				//Merge the following values from seller and department tables
					+ "ON seller.DepartmentId = department.Id "			//seller.DepartmentId = department.Id
					+ "ORDER BY Name ");								//Order result by name
			
			rs = st.executeQuery();									
			
			List<Seller> list = new ArrayList<>();						//create list to store results
			Map<Integer, Department> map = new HashMap<>();				//map created to check Department instance existence
			
			//Associated objects
			while (rs.next()) {											//various results can return, so while is needed
				Department dep = map.get(rs.getInt("DepartmentId"));	//Instantiate Department dep receiving Department from map
				if (dep == null) {										//if map doesn't have Department obj
					dep = intantiateDepartment(rs);						//Instantiate Department
					map.put(rs.getInt("DepartmentId"), dep);			//Store it in map
				}
				
				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
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
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "		//SELCT >> seller . * < all rows, department.Name as DepName << nickname
					+ "FROM seller INNER JOIN department "				//Merge the following values from seller and department tables
					+ "ON seller.DepartmentId = department.Id "			//seller.DepartmentId = department.Id
					+ "WHERE DepartmentId = ? "							//condition given
					+ "ORDER BY Name ");								//Order result by name
			
			st.setInt(1, department.getId());							//define argument value as argument from method
			rs = st.executeQuery();									
			
			List<Seller> list = new ArrayList<>();						//create list to store results
			Map<Integer, Department> map = new HashMap<>();				//map created to check Department instance existence
			
			//Associated objects
			while (rs.next()) {											//various results can return, so while is needed
				Department dep = map.get(rs.getInt("DepartmentId"));	//Instantiate Department dep receiving Department from map
				if (dep == null) {										//if map doesn't have Department obj
					dep = intantiateDepartment(rs);						//Instantiate Department
					map.put(rs.getInt("DepartmentId"), dep);			//Store it in map
				}
				
				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
