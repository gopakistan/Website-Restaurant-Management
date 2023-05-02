<%-- 
    Document   : MenuSearch
    Created on : Mar 31, 2023, 11:40:22 PM
    Author     : student
--%>

<%@page import="ryerson.ca.helper.RestaurantsXML"%>
<%@page import="ryerson.ca.helper.Restaurant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String username = (String) request.getAttribute("username");
String restName = (String) request.getAttribute("restName");
RestaurantsXML rests = (RestaurantsXML) request.getAttribute("bookResults");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Hello <%=username%></h1>
        <h2>Results for: <%=restName%></h2>
        <%
        if(rests == null){
        %>
        
        <h1>No Results :(</h1>
        
        <%
        }else{
        int i = 1;
        %>
        <table>
            <tr><th></th><th>Restaurant Name</th><th>Category</th></tr>
            <%
            for(Restaurant r: rests.getRestaurants()){      
            
            %>
            <tr>
                <td>
                    <%= i%>
                </td>
                <td>
                    <%=r.getName()%>
                </td>
                <td>
                    <%=r.getCategory()%>
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
