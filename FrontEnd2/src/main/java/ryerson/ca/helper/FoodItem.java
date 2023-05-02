/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "fooditem")
@XmlAccessorType(XmlAccessType.FIELD)
public class FoodItem {
    
    private int calories;
    private String name;
    private boolean details;
    private int FoodID;
    private float price = 0;
    private int viewCount;
    
    public FoodItem(){}
    
    public FoodItem(String name, int calories, int FoodID, float price){
        this.calories = calories;
        this.name = name;
        this.details = true;
        this.FoodID = FoodID;
        this.price = price;
        this.viewCount = 0;
    }
    
    public FoodItem(String name, int calories, int FoodID, float price, int viewCount){
        this.calories = calories;
        this.name = name;
        this.details = true;
        this.FoodID = FoodID;
        this.price = price;
        this.viewCount = viewCount;
    }
    
    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
      
    public int getCalories(){
        return this.calories;
    }
    
    public void setCalories(int calories){
        this.calories = calories;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(){
        this.name = name;
    }
    
    public boolean isViewDetails(){
        return this.details;
    }
    
    public void setViewDetails(boolean details){
        this.details = details;
    }
    
    public int getID(){
        return this.FoodID;
    }
    
    
}
