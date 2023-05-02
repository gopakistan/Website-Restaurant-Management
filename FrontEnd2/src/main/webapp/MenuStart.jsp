<%-- 
    Document   : MenuStart
    Created on : Mar 27, 2023, 4:04:02 AM
    Author     : student
--%>

<%@page import="ryerson.ca.helper.RestaurantsXML"%>
<%--<%@page import="ryerson.ca.persistence.Restaurant_CRUD"%>--%>
<%@page import="ryerson.ca.helper.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="ryerson.ca.helper.FoodItem" import="ryerson.ca.helper.FoodItemsXML"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Hello 
        <%=request.getAttribute("username")%>
        </h1>
    </body>
    <form action="FrontEnd" method="post"> 
        <table>
            <tr><th>name!</th><th>Calories </th><th>View Count</th><th>View Details</th></tr>

                    <%  //String username = (String) request.getAttribute("username");
                        RestaurantsXML foods = (RestaurantsXML) request.getAttribute("bookResults");
                    //Restaurant rest = Restaurant_CRUD.readUser(username);
                    if(foods!=null){
                    for (FoodItem f : foods.getRestaurants().get(0).getMenu()) {%>
                        <tr>
                          <td><%=f.getName()%></td>
                          <td><%=f.getCalories()%></td>
                          <td><%=f.getViewCount()%></td>
                          <td>
                            <%if (f.isViewDetails()){ %>
                              <form action="FrontEnd" method="post">
                                <input type="hidden" name="pageName" value="viewdetails"/>
                                <input type="hidden" name="foodItemName" value="<%=f.getName()%>"/>
<!--                                <input type="hidden" name="foodItemID" value="<%=f.getID()%>"/>
                                <input type="hidden" name="foodItemCalories" value="<%=f.getCalories()%>"/>
                                <input type="hidden" name="foodItemPrice" value="<%=f.getPrice()%>"/>
                                <input type="hidden" name="foodItemViewCount" value="<%=f.getViewCount()%>"/>-->
                                <input type="hidden" name="username" value="<%=request.getAttribute("username")%>"/>
                                <input type="submit" value="Details" style="background-color: green; color: white; border-radius: 5px; padding: 5px;">
                              </form>
                            <% } else { %>
                              <button style="background-color: grey; color: white; border-radius: 5px; padding: 5px;" disabled>Hold</button>
                            <% } %>
                          </td>
                        </tr>
                      <% }
                      }
                %></tr>
            </table>
            </form>
            
            <form action="FrontEnd" method="post" style="margin-right: 20px;"> 
                <input type="hidden" name="pageName" value="menu"/>
                <input type="text" name="menuSearch" placeholder="Search Menu" style="margin-right: 5px;">
                <input type="submit" value="Search Menu"> 
            </form>

            <form action="FrontEnd" method="post"> 
                <input type="hidden" name="pageName" value="foodItem"/>
                <input type="text" name="fooditemName" placeholder="Search FoodItem" style="margin-right: 5px;">
                <input type="submit" value="Search FoodItem"> 
            </form>
            
</html>
