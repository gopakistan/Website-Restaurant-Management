<%-- 
    Document   : MenuStart
    Created on : Mar 27, 2023, 4:04:02 AM
    Author     : student
--%>

<%@page import="ryerson.ca.helper.RestaurantsXML"%>
<%@page import="ryerson.ca.persistence.Restaurant_CRUD"%>
<%@page import="ryerson.ca.helper.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="ryerson.ca.helper.FoodItem" import="ryerson.ca.helper.FoodItemsXML"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restaurant Menu</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #F7F7F7;
            }
            #header {
                background-color: #FFC107;
                padding: 20px;
                text-align: center;
                font-size: 24px;
            }
            #menu {
                margin: 0 auto;
                width: 80%;
                border-collapse: collapse;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            }
            #menu td, #menu th {
                padding: 12px 15px;
                text-align: center;
                border-bottom: 1px solid #E1E1E1;
            }
            #menu th {
                background-color: #FFC107;
                color: #FFF;
                font-size: 18px;
            }
            #menu tr:nth-child(even) {
                background-color: #F5F5F5;
            }
            #menu tr:hover {
                background-color: #EEE;
            }
            #sidebar {
                float: left;
                width: 20%;
                padding: 20px;
                background-color: #FFF;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
                margin-right: 20px;
            }
            #sidebar h2 {
                font-size: 18px;
                margin-top: 0;
            }
            #sidebar input[type="text"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 2px solid #E1E1E1;
                border-radius: 4px;
                font-size: 16px;
            }
            #sidebar input[type="submit"] {
                background-color: #FFC107;
                color: #FFF;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }
            /* Add a class for the menu table */
            .menu-table {
                width: 50%;
                margin: 0 auto;
            }

            /* Rest of the existing styling */
            body {
                font-family: Arial, sans-serif;
                background-color: #F7F7F7;
            }
            #header {
                background-color: #FFC107;
                padding: 20px;
                text-align: center;
                font-size: 24px;
            }
            #menu {
                border-collapse: collapse;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            }
            #menu td, #menu th {
                padding: 12px 15px;
                text-align: center;
                border-bottom: 1px solid #E1E1E1;
            }
            #menu th {
                background-color: #FFC107;
                color: #FFF;
                font-size: 18px;
            }
            #menu tr:nth-child(even) {
                background-color: #F5F5F5;
            }
            #menu tr:hover {
                background-color: #EEE;
            }
            #sidebar {
                float: left;
                width: 20%;
                padding: 20px;
                background-color: #FFF;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
                margin-right: 20px;
            }
            #sidebar h2 {
                font-size: 18px;
                margin-top: 0;
            }
            #sidebar input[type="text"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 2px solid #E1E1E1;
                border-radius: 4px;
                font-size: 16px;
            }
            #sidebar input[type="submit"] {
                background-color: #FFC107;
                color: #FFF;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }
        </style>
    </head>
    <body>
        <div id="header">Hello <%=request.getAttribute("username")%></div>
        <div id="sidebar">
            <h2>Search</h2>
            <div style="display:flex; justify-content:center;">
  <form action="FrontEnd" method="post" style="margin-right: 20px;"> 
    <input type="hidden" name="pageName" value="menu"/>
    <input type="text" name="menuSearch" placeholder="Search Menu" style="margin-right: 5px;">
    <input type="submit" value="Search Menu"> 
  </form>
  
  <form action="FrontEnd" method="post"> 
    <input type="hidden" name="pageName" value="foodItem"/>
    <input type="text" name="foodItemSearch" placeholder="Search FoodItem" style="margin-right: 5px;">
    <input type="submit" value="Search FoodItem"> 
  </form>
</div>

<br>

<table class="menu-table" style="border-collapse:collapse; text-align:center;">
  <thead>
    <tr>
      <th>Name</th>
      <th>Calories</th>
      <th>Hold Request</th>
    </tr>
  </thead>
  <tbody>
    <%  
      RestaurantsXML foods = (RestaurantsXML) request.getAttribute("bookResults");
      if(foods != null){
        for (FoodItem f : foods.getRestaurants().get(0).getMenu()) {%>
          <tr>
            <td><%=f.getName()%></td>
            <td><%=f.getCalories()%></td>
            <td>
              <%if (f.isViewDetails()){ %>
                <form action="FrontEnd" method="post">
                  <input type="hidden" name="pageName" value="hold"/>
                  <input type="hidden" name="foodItemId" value="<%=f.getID()%>"/>
                  <input type="submit" value="Hold" style="background-color: green; color: white; border-radius: 5px; padding: 5px;">
                </form>
              <% } else { %>
                <button style="background-color: grey; color: white; border-radius: 5px; padding: 5px;" disabled>Hold</button>
              <% } %>
            </td>
          </tr>
        <% }
      } else { %>
        <tr>
          <td colspan="3">No results found</td>
        </tr>
      <% } %>
  </tbody>
</table>

