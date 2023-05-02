/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.viewfooditem.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ryerson.ca.viewfooditem.Helper.FoodItem;
import ryerson.ca.viewfooditem.Helper.Restaurant;

/**
 *
 * @author student
 */
public class FoodItem_CRUD {
    
    private static Connection getCon(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connection = System.getenv("DB_URL");
            con = DriverManager.getConnection("jdbc:mysql://"
                    + connection
                    + "/FoodItem_LBS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            System.out.println("Connection established.");
        }catch(Exception e){System.out.println(e);}
        return con;
    }
    
    public static void createMenu(Restaurant res, String q){
        try{
            Connection con = getCon();
            
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //been = new UserInfo();
                String name = rs.getString("nameFood");
                float price = rs.getFloat("price");
                int calories = rs.getInt("calories");
                int FoodID = rs.getInt("FoodID");
                int viewCount = rs.getInt("ViewCount");
                
                res.addToMenu(new FoodItem(name, calories, FoodID, price, viewCount));
            }
            con.close();
        }catch(Exception e){System.out.println(e);}
    }

    public static Set<FoodItem> searchForFoodItems(String query) {
        Set<FoodItem> res = new HashSet<FoodItem>();
        int FoodID = -1;
        int viewCount = -1;
        try{
            Connection con = getCon();
            String q = "SELECT * FROM FoodItem";// LIKE " + query;
            System.out.println("here:" + query);
            if (!query.equals("")) q += " WHERE nameFood LIKE \"" + query + "\""; 
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //been = new UserInfo();
                String name = rs.getString("nameFood");
                float price = rs.getFloat("price");
                int calories = rs.getInt("calories");
                FoodID = rs.getInt("FoodID");
                viewCount = rs.getInt("ViewCount");
                
                res.add(new FoodItem(name, calories, FoodID, price, viewCount));
            }
            con.close();
        }catch(Exception e){System.out.println(e);}
        if(!res.isEmpty()){
            try {
                updateViewCount(FoodID, viewCount);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FoodItem_CRUD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FoodItem_CRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    return res;
    }   
    
    
    public static boolean updateViewCount(int FoodID, int viewCount) throws ClassNotFoundException, SQLException{
        if(FoodID < 0 || viewCount < 0){
            return false;
        }
        Connection con= getCon();
        viewCount += 1;
        

         String q = "UPDATE FoodItem "
                 + "SET ViewCount = "
                 + viewCount
                 + " WHERE FoodID = "
                 + FoodID;
         Statement stmt = con.createStatement();   
         System.out.println(q);
         stmt.execute(q);
                     con.close();
                     return true;
    }
    
}
