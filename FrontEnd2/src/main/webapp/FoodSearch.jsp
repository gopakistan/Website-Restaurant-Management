<%-- 
    Document   : FoodSearch
    Created on : Mar 31, 2023, 11:40:22 PM
    Author     : student
--%>

<%@page import="ryerson.ca.helper.FoodItemsXML"%>
<%@page import="ryerson.ca.helper.FoodItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String username = (String) request.getAttribute("username");
String foodName = (String) request.getAttribute("foodName");
FoodItemsXML foods = (FoodItemsXML) request.getAttribute("bookResults");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Hello <%=username%></h1>
        <h2>Results for: <%=foodName%></h2>
        <%
        if(foods == null){
        %>
        
        <h1>No Results :(</h1>
        
        <%
        }else{
        int i = 1;
        %>
        <table>
            <tr><th></th><th>Food Name</th><th>Calories</th><th>View Count</th><th>Price</th></tr>
            <%
            for(FoodItem f: foods.getFoodItems()){      
            
            %>
            <tr>
                <td>
                    <%= i%>
                </td>
                <td>
                    <%=f.getName()%>
                </td>
                <td>
                    <%=f.getCalories()%>
                </td>
                <td>
                    <%=f.getViewCount()%>
                </td>
                <td>
                    <%=f.getPrice()%>
                </td>
            </tr>
            <%
                i++;
                }
            %>
        </table>
        <%
        }
        %>
        
    </body>
</html>
