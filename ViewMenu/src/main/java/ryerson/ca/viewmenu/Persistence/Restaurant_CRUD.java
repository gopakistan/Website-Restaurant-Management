/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.viewmenu.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ryerson.ca.viewmenu.Helper.Restaurant;

/**
 *
 * @author student
 */
public class Restaurant_CRUD {
    
    private static Connection getCon(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connection = System.getenv("DB_URL");
            System.out.println("connection string at restaurant_crud = " + connection);
            con = DriverManager.getConnection("jdbc:mysql://"
                    + connection
                    + "/Menu_LBS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            System.out.println("Connection established.");
        }catch(Exception e){System.out.println(e);}
        return con;
    }
    
    public static Restaurant readUser(String username){
        Restaurant bean = null;
        try{
            Connection con = getCon();
            //READ THIS: i dont think theyre demoing but their shit just isnt working
            System.out.println(username);
            
            String q = "select * from Restaurant where username LIKE \"" + username + "\"";
            
            //String q = "SELECT * FROM Restaurant WHERE username = " + username;
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //bean = new UserInfo();
                String user = rs.getString("username");
                String pass = rs.getString("password");
                String category = rs.getString("category");
                int restID = rs.getInt("RestaurantID");
                
                System.out.println(user + pass + category +  restID);
                
                bean = new Restaurant(username, category, restID, pass);
                // given a restaurant ID, you can find all the food items belonging to that restaurant
                String findMenuSQL = "SELECT DISTINCT FoodItem.nameFood, FoodItem.price, FoodItem.calories, FoodItem.FoodID, FoodItem.ViewCount" +
                    "\nFROM FoodItem, Restaurant, FoodItem_Restaurant_ContainedBy" +
                    "\nWHERE Restaurant.RestaurantID = " + String.valueOf(restID) + 
                    " \nAND Restaurant.RestaurantID = FoodItem_Restaurant_ContainedBy.RestaurantID" +
                    " \nAND FoodItem.FoodID = FoodItem_Restaurant_ContainedBy.FoodID;";
                System.out.println(findMenuSQL);
                FoodItem_CRUD.createMenu(bean, findMenuSQL);
            }
            con.close();
        }catch(Exception e){System.out.println(e);}
        return bean;
    }
    
}
