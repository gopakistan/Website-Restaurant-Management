<%-- 
    Document   : ViewFood
    Created on : Mar 31, 2023, 10:35:24 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ryerson.ca.helper.FoodItem"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
        String username = (String) request.getAttribute("username");
        FoodItem f = (FoodItem) request.getAttribute("FoodItem");
        %>
        <title>View Details of </title>
        <h1>Hello <%=request.getAttribute("username")%> </h1>
    </head>
    <body>
        <table>
            <tr>
                <th>Name </th>
                <td>     </td>
                <td><%=f.getName()%></td>
            </tr>
                <th>Price </th>
                <td>     </td>
                <td><%=f.getPrice()%></td>
            </tr>
                <th>Calories </th>
                <td>     </td>
                <td><%=f.getCalories()%></td>
            </tr>
                <th>View Count </th>
                <td>     </td>
            <td><%=f.getViewCount()%></td>
        </table>
    </body>
</html>
