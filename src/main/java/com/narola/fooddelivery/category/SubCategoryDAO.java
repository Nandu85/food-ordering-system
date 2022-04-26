package com.narola.fooddelivery.category;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Blob;
import com.narola.fooddelivery.DBConnection;
import com.narola.fooddelivery.DatabaseException;
import java.util.Base64;

public class SubCategoryDAO {

	public static SubCategory AddSubCategory(String name,int catId) {
		SubCategory cat = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("INSERT INTO `subcategory`\r\n"
					+ "(`subcatName`,`SuperCategory`)\r\n"
					+ "VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ps.setInt(2, catId);

			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				cat = new SubCategory();
				cat.setCategoryId(resultSet.getInt(1));
				cat.setCategoryName(name);
				cat.setCategory(CategoryDAO.getCategoryById(catId));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while insert Subcategory", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
 		}

		return cat;
	}
	
	public static SubCategory UpdateSubCategory(SubCategory cat) {
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("UPDATE `subcategory`\r\n"
					+ "SET `subcatName` = ?,\r\n"
					+ "`SuperCategory` = ?\r\n"
					+ "WHERE `subcategoryId` = ?", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, cat.getCategoryName());
			ps.setInt(2, cat.getCategory().getCategoryId());
			ps.setInt(3, cat.getCategoryId());
			System.out.println(ps);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				cat = new SubCategory();
				cat.setCategoryId(rs.getInt("subcategoryId"));
				cat.setCategoryName(rs.getString("subcatName"));
				cat.setCategory(CategoryDAO.getCategoryById(rs.getInt("SuperCategory")));
				
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while Update Sub category", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps,rs);
		}
		return cat;
		
	}
	
	
	public static List<SubCategory> getAllSubCategories() throws DatabaseException  {
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SubCategory> categories = null;

		
		try {
			ps = con.prepareStatement("SELECT * FROM subcategory order by SuperCategory");
			rs=ps.executeQuery();
			categories=new ArrayList<>();
			while (rs.next()) {
				SubCategory cat = new SubCategory();
				cat.setCategoryId(rs.getInt("subcategoryId"));
				cat.setCategoryName(rs.getString("subcatName"));
				cat.setCategory(CategoryDAO.getCategoryById(rs.getInt("SuperCategory")));
				Blob blob=(Blob) rs.getBlob("Image");
				String x=Base64.getEncoder().encodeToString(blob.getBytes(1, (int)blob.length()));
				cat.setImageAsBase64(x);
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
	
	public static SubCategory AddImage(InputStream image, int subCatId) {
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		SubCategory cat = null;
		try {
			ps = con.prepareStatement("UPDATE `subcategory` SET `Image` = ?\r\n"
					+ "WHERE `subcategoryId` = ?", Statement.RETURN_GENERATED_KEYS);
//			Blob blob=(Blob) con.createBlob();
//			blob.setBytes(1, image.getBytes());
			ps.setBlob(1, image);
			ps.setInt(2, subCatId);
//			System.out.println(ps);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				cat = new SubCategory();
				cat.setCategoryId(rs.getInt("subcategoryId"));
				cat.setCategoryName(rs.getString("subcatName"));
				cat.setCategory(CategoryDAO.getCategoryById(rs.getInt("SuperCategory")));
				
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get all category", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps,rs);
		}
		return cat;
		
	}
	
	
	public static SubCategory getSubCategoryById(int id) throws DatabaseException  {
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		SubCategory cat = null;

		
		try {
			ps = con.prepareStatement("SELECT * FROM subcategory where subcategoryId=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			cat=new SubCategory();
			while (rs.next()) {
				cat = new SubCategory();
				cat.setCategoryId(rs.getInt("subcategoryId"));
				cat.setCategoryName(rs.getString("subcatName"));
				cat.setCategory(CategoryDAO.getCategoryById(rs.getInt("SuperCategory")));
				Blob blob=(Blob) rs.getBlob("Image");
				String x=Base64.getEncoder().encodeToString(blob.getBytes(1, (int)blob.length()));
				cat.setImageAsBase64(x);
				
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get all category", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps,rs);
		}
		return cat;

	}
	
	
	public static SubCategory deleteSubCategoryById(int Id) throws DatabaseException  {
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		SubCategory cat = getSubCategoryById(Id);
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from subcategory where subcategoryId=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseException("Error while delete subcategory", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps,rs);
		}
		return cat;

	}

	
	
	
	public static void main(String[] args) {

//		System.out.println(getSubCategoryById(3));
	}

}
