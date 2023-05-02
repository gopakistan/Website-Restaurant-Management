/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.viewfooditem.Helper;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "restaurant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Restaurant {
    private String password;
    private String username;
    private String category;
    private int RestaurantID;
    private ArrayList<FoodItem> menu = new ArrayList<>();
    
    public Restaurant(String username, String category, int restaurantID) {
        this.username = username;
        this.category = category;
        this.RestaurantID = restaurantID;
    }
    
    public Restaurant(String username, String category, int restaurantID, String password) {
        this.username = username;
        this.category = category;
        this.RestaurantID = restaurantID;
        this.password = password;
    }
    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRestaurantID() {
        return RestaurantID;
    }

    public void setRestaurantID(int RestaurantID) {
        this.RestaurantID = RestaurantID;
    }
    
    public ArrayList<FoodItem> getMenu(){
        return this.menu;
    }
    
    public void addToMenu(FoodItem f){
        menu.add(f);
    }

    public String getName() {
        return username;
    }

    public String getCategory() {
        return category;
    }
    
}
