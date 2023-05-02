/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.viewmenu.Helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import ryerson.ca.viewmenu.Helper.FoodItem;

@XmlRootElement(name = "restaurants")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlSeeAlso({Restaurant.class, FoodItem.class})
       public class RestaurantsXML{
     @XmlElement(name="restaurant")
           private ArrayList<Restaurant> restaurants;
           
           
           public List<Restaurant> getRestaurants(){
               return restaurants;
               
           }
          public RestaurantsXML(){
               restaurants = new ArrayList<Restaurant>();
               
           }
           public void setRestaurant(ArrayList<Restaurant> bs){
               restaurants=bs;
               
           }
           
       }