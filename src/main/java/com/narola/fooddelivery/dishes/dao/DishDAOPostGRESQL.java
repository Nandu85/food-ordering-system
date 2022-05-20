package com.narola.fooddelivery.dishes.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.exception.DatabaseException;
import com.narola.fooddelivery.utility.DAOFactory;
import com.narola.fooddelivery.utility.DBConnection;

public class DishDAOPostGRESQL implements IDishDAO {

	public Dish addDish(Dish dish) throws DatabaseException{
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps = con.prepareStatement("INSERT INTO dishes\r\n"
					+ "(DishName,Price,Pic,Ingrediants,categoryId,Type,Restaurant,SubCategory)\r\n" + "VALUES\r\n" + "(?,?,?,?,?,?,?,?);\r\n" + "",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dish.getDishName());
			ps.setInt(2, dish.getPrice());
			ps.setBlob(3, dish.getPhoto());
			ps.setString(4, dish.getIngrident());
			ps.setInt(5, dish.getCategoryId());
			ps.setInt(6, dish.getDishtype());
			ps.setInt(7, dish.getRestId());
			ps.setInt(8, dish.getSubCategory().getCategoryId());
			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				dish.setDishId(resultSet.getInt(1));
				
				dish.setRestaurant(DAOFactory.getInstance().getRestDAO().getRestaurantFromId(dish.getRestId()));
//				System.out.println(resultSet.getInt(1)+"  "+dish.getDishId());// Method Running
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while insert dish", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		
		return dish;
	}
	
	public List<Dish> getAllDish() throws DatabaseException{
		return getAllDish(null,null,null);
	}
	

	public List<Dish> getAllDish(String dname,String categoryId,String dtype1) throws DatabaseException{  
		Connection con = DBConnection.getInstance().getConnection();
		List<Dish> dl=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		char dtype;
		if(dtype1==null)
			dtype='2';
		else
			dtype=dtype1.charAt(0);
		try {
			String query="select * from dishes";        //Designing Query
			if(dname != null && categoryId != null) {
			if(!dname.equals("")||dtype!='2'|| !categoryId.equals("0"))
				query+=" where ";
			if(!dname.equals(""))
				query+="DishName='"+dname+"'";
			if(!categoryId.equals("0")) {
				if(!dname.equals(""))
					query+=" or ";
				query+=" categoryid="+categoryId+"";
			}
			if(dtype!='2') {
				if(dname!="" || !categoryId.equals("0"))
					query+=" or ";
				query+=" Type='"+dtype+"'";
			}
			}
			query+=" order by DishId";
			
			
			
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Dish d=new Dish();
				d.setDishId(Integer.parseInt(rs.getString(1)));
				d.setDishName(rs.getString(2));
				d.setPrice(Integer.parseInt(rs.getString(3)));
				
				if(rs.getBlob(4)!=null) {
					d.setPhoto(rs.getBlob(4).getBinaryStream());
					InputStream is = d.getPhoto();
//					System.out.println(is.available());
			        byte[] imageBytes=new byte[is.available()];
			        IOUtils.readFully(is, imageBytes);
			        String base64 = Base64.getEncoder().encodeToString(imageBytes);//Base64encodeToString(imageBytes);
					d.setPhotoAsBase64(base64);
//					System.out.println(d.getPhotoAsBase64()+" Main");

				}
				
				d.setIngrident(rs.getString(5));
//				System.out.println(rs.getString(4));// 4th is pic set it later
				if(!(rs.getString(9)==null))
				d.setCategoryId(Integer.parseInt(rs.getString(9)));
				d.setDishtype(Integer.parseInt(rs.getString(6)));
				d.setRestId(rs.getInt("Restaurant"));
				d.setSubCategory(SubCategoryDAO.getSubCategoryById(rs.getInt("SubCategory")));
//				System.out.println(d.toString());
				dl.add(d);	
			}
			
		} catch (SQLException e) {
			throw new DatabaseException("Error while get all dish", e);
		} catch (DatabaseException e) {
			throw e;
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBConnection.releaseResource(ps, rs);
		} 
		return dl;
	}
	
	public Dish updateDish(Dish dish) throws IOException,DatabaseException{  
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			String query="UPDATE dishes set DishName=?,Price=?,";
			if(dish.getPhoto()==null || dish.getPhoto().available()>0)
				query+="Pic=?,";
			query+="Ingrediants=?,categoryid=?,Type=?,Restaurant=?,SubCategory=? WHERE DishId=?";
		ps=con.prepareStatement(query);
		ps.setString(1,dish.getDishName());
		ps.setString(2,String.valueOf(dish.getPrice()));
		int i=3;
		if(dish.getPhoto()==null || dish.getPhoto().available()>0) {
			ps.setBlob(i, dish.getPhoto());
			i++;
		}
		
		ps.setString(i,dish.getIngrident());
		ps.setString(i+1,String.valueOf(dish.getCategoryId()));
		ps.setString(i+2,String.valueOf(dish.getDishtype()));
		ps.setString(i+3,String.valueOf(dish.getRestId()));
		ps.setInt(i+4, dish.getSubCategory().getCategoryId());
		ps.setString(i+5, String.valueOf(dish.getDishId()));
		System.out.println(dish.getPhoto());
//		System.out.println(dish.getPhoto().available());
		ps.executeUpdate();
		
		
		}
		catch(SQLException e) {
			throw new DatabaseException("Error while Update Dish", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps);
		}
		
		return dish;
		
	}
	
	
	public Dish DeletedDish(Dish dish) throws DatabaseException{
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=con.prepareStatement("DELETE FROM dishes WHERE DishId=?");
			ps.setInt(1,dish.getDishId());
			ps.executeUpdate();

		}
		catch(SQLException e) {
			throw new DatabaseException("Error while delete dish", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps);
		}
		return dish;
		
	}
	
	
	public List<String> getCategories() throws DatabaseException{
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs = null;
		List<String> categories=null;
		
		String query = "select * from category";
		try {
			categories=new ArrayList<>();
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				categories.add(rs.getString(2));
			}
		}
		catch(SQLException e) {
			throw new DatabaseException("Error while get category of dish", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		
		
		return categories;
		
	}
	
	public String CategoryFromId(int id) throws DatabaseException{
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String category=null;
		String query = "select * from category where categoryId=?";
		try {
		ps=con.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		if(rs.next())
		category=rs.getString(2);
		}
		catch(SQLException e) {
			throw new DatabaseException("Error while get category of dish from Id", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		return category;
		
	}
	
	
	public Dish DishFromId(int id) throws IOException {
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Dish d=new Dish();
		String query = "select * from dishes where DishId=?";
		try {
		ps=con.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();

		if(rs.next())
			d.setDishId(rs.getInt(1));
			d.setDishName(rs.getString(2));
			d.setPrice(rs.getInt(3));
			if(rs.getBlob(4)!=null) {
				d.setPhoto(rs.getBlob(4).getBinaryStream());
				InputStream is = d.getPhoto();
				
		        byte[] imageBytes=new byte[is.available()];
		        IOUtils.readFully(is, imageBytes);
		        String base64 = Base64.getEncoder().encodeToString(imageBytes);//Base64encodeToString(imageBytes);
				d.setPhotoAsBase64(base64);
//				System.out.println(d.getPhotoAsBase64()+" Main");

			}
			d.setRestId(rs.getInt("Restaurant"));
			d.setIngrident(rs.getString(5));
			d.setDishtype(rs.getInt(6));
			d.setCategoryId(rs.getInt(9));
			d.setSubCategory(SubCategoryDAO.getSubCategoryById(rs.getInt("SubCategory")));
		
		}
		catch(SQLException e) {
			throw new DatabaseException("Error while get dish from Id", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		return d;
		
	}
	
	
	public List<Dish> getRestaurantMenu(int RestaurantId) throws DatabaseException{
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Dish> dishList=null;
		
		String query = "select * from dishes where Restaurant=?";
		try {
			dishList=new ArrayList<>();
		ps=con.prepareStatement(query);
		ps.setInt(1, RestaurantId);
		rs = ps.executeQuery();

		while(rs.next()) {
			Dish d=new Dish();
			d.setDishId(rs.getInt(1));
			d.setDishName(rs.getString(2));
			d.setPrice(rs.getInt(3));
			if(rs.getBlob(4)!=null) {
				d.setPhoto(rs.getBlob(4).getBinaryStream());
				InputStream is = d.getPhoto();
				
		        byte[] imageBytes=new byte[is.available()];
		        IOUtils.readFully(is, imageBytes);
		        String base64 = Base64.getEncoder().encodeToString(imageBytes);//Base64encodeToString(imageBytes);
				d.setPhotoAsBase64(base64);
			}
			d.setRestId(rs.getInt("Restaurant"));
			d.setIngrident(rs.getString(5));
			d.setDishtype(rs.getInt(6));
			d.setCategoryId(rs.getInt(9));
			dishList.add(d);
		}
			
		
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantMenu", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		
		
		return dishList;
		
	}
	
	
	public List<Dish> getDishesFromSubCategory(int id) throws DatabaseException{
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Dish> dishList=null;
		
		String query = "select * from dishes where SubCategory=?";
		try {
			dishList=new ArrayList<>();
		ps=con.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();

		while(rs.next()) {
			Dish d=new Dish();
			d.setDishId(rs.getInt(1));
			d.setDishName(rs.getString(2));
			d.setPrice(rs.getInt(3));
			if(rs.getBlob(4)!=null) {
				d.setPhoto(rs.getBlob(4).getBinaryStream());
				InputStream is = d.getPhoto();
				
		        byte[] imageBytes=new byte[is.available()];
		        IOUtils.readFully(is, imageBytes);
		        String base64 = Base64.getEncoder().encodeToString(imageBytes);//Base64encodeToString(imageBytes);
				d.setPhotoAsBase64(base64);
			}
			d.setRestId(rs.getInt("Restaurant"));
			d.setIngrident(rs.getString(5));
			d.setDishtype(rs.getInt(6));
			d.setCategoryId(rs.getInt(9));
			dishList.add(d);
		}
			
		
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new DatabaseException("Error while get DishesFromSubCategory", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		
		
		return dishList;
		
	}
	
	
//	public String EncodetoBase64(InputStream is) {
//		String str=null;
//		if(is!=null) {
//			str = Base64.
//		}
//		
//			return str;
//		
//		
//		
//	}
	
	
	
	
	

}

