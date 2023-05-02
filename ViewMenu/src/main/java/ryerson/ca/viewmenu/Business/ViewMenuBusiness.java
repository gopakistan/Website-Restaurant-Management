/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.viewmenu.Business;

import ryerson.ca.viewmenu.Helper.FoodItemsXML;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import ryerson.ca.viewmenu.Helper.FoodItem;
import ryerson.ca.viewmenu.Helper.Restaurant;
import ryerson.ca.viewmenu.Helper.RestaurantsXML;
import ryerson.ca.viewmenu.Persistence.FoodItem_CRUD;
import ryerson.ca.viewmenu.Persistence.Restaurant_CRUD;

/**
 *
 * @author student
 */
public class ViewMenuBusiness {
    
    public FoodItemsXML getFoodItemsByQuery(String query){
       Set<FoodItem> fooditems = FoodItem_CRUD.searchForFoodItems(query);
       Map<Integer ,FoodItem> foodList= new HashMap ();
           System.out.println("&&&&&&&&&&&&&&&&&&&&&&"+ fooditems.size());
        for(FoodItem f : fooditems){
            foodList.put(f.getID(), f);
            
        }
        System.out.println("**********************"+ foodList.size());
        FoodItemsXML bs;
        bs = new FoodItemsXML();
        bs.setFoodItem(new ArrayList(foodList.values()));
        return (bs);
    }
    
    public RestaurantsXML getRestaurantsByQuery(String query){
       Restaurant res = getRestaurantByQuery(query);
           System.out.println("QUERY AFTER &&&&:" + query);
        RestaurantsXML bs;
        bs = new RestaurantsXML();
        ArrayList<Restaurant> restList = new ArrayList<>();
        restList.add(res);
        if(res != null) System.out.println("query work?: " + res.getName() + " " + res.getPassword());
        bs.setRestaurant(restList);
        return (bs);
    } 
    
    public Restaurant getRestaurantByQuery(String username){
        Restaurant rest;
        rest = Restaurant_CRUD.readUser(username);
        System.out.println("getRestaurantByQuery: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " + rest.getName());
        return rest;
    }
    
      
}
