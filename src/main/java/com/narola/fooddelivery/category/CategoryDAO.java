package com.narola.fooddelivery.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.fooddelivery.DBConnection;
import com.narola.fooddelivery.DatabaseException;

public class CategoryDAO {

	public static Category AddCategory(String name, int popularity) throws DatabaseException {
		Category cat = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("INSERT INTO category\r\n"
					+ "(`CategoryName`, `Popularity`)\r\n" + "VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ps.setInt(2, popularity);

			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				cat = new Category();
				cat.setCategoryId(resultSet.getInt(1));
				cat.setCategoryName(name);
				cat.setPopularity(popularity);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while insert category", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return cat;
	}

	public static List<Category> getAllCategories() throws DatabaseException  {
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Category> categories = null;

		
		try {
			ps = con.prepareStatement("select * from category");
			rs=ps.executeQuery();
			categories=new ArrayList<>();
			while (rs.next()) {
				Category cat = new Category();
				cat.setCategoryId(rs.getInt("categoryId"));
				cat.setCategoryName(rs.getString("CategoryName"));
				cat.setPopularity(rs.getInt("Popularity"));
				categories.add(cat);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get all category", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps,rs);
		}
		return categories;

	}

	public static Category getCategoryById(int Id) throws DatabaseException  {
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement ps=null;
		Category cat = null;

		try {
			ps=con.prepareStatement("select * from category where categoryId=?");
			ps.setInt(1, Id);
			rs = ps.executeQuery();
			while (rs.next()) {
				cat = new Category();
				cat.setCategoryId(rs.getInt("categoryId"));
				cat.setCategoryName(rs.getString("CategoryName"));
				cat.setPopularity(rs.getInt("Popularity"));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get by id of category", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		return cat;

	}

	public static Category UpdateCategory(Category cat) throws DatabaseException  {

		Connection con = DBConnection.getConnection();
		PreparedStatement ps=null;;
		ResultSet resultSet=null;
		try {
			ps = con.prepareStatement("UPDATE category\r\n" + "SET `CategoryName` = ?,\r\n"
					+ "`Popularity` = ?\r\n" + "WHERE `categoryId` = ?", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, cat.getCategoryName());
			ps.setInt(2, cat.getPopularity());
			ps.setInt(3, cat.getCategoryId());

			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				return cat;
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while update category", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return null;
	}

	public static Category deleteCategoryById(int Id) throws DatabaseException  {
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		Category cat = getCategoryById(Id);
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from category where categoryId=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseException("Error while delete category", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps,rs);
		}
		return cat;

	}

	public static List<Category> getPopularCategories() throws DatabaseException  {
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Category> categories = null;

		String query = "SELECT * FROM category where Popularity>0 and categoryId in(select SuperCategory from subcategory group by SuperCategory HAVING count(subcategoryId)>4)";

		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			categories=new ArrayList<>();
			int i = 0;
			while (rs.next() && i < 5) {
				Category cat = new Category();
				cat.setCategoryId(rs.getInt("categoryId"));
				cat.setCategoryName(rs.getString("CategoryName"));
				cat.setPopularity(rs.getInt("Popularity"));
				categories.add(cat);
				i++;
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getting popular category", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		return categories;

	}

	public static void main(String[] args) {
//	Checked and Working
	}

}
