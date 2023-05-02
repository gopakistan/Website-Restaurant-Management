<%-- 
    Document   : restaurantview
    Created on : Mar 26, 2023, 1:31:25 PM
    Author     : student
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="ryerson.ca.helper.FoodItem" import="ryerson.ca.helper.Restaurant"%>
<!DOCTYPE html>


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your restaurant</title>
    </head>
    <style>

body {
  background-color: #a3ccb6;

}
</style>
<% 
    ArrayList<FoodItem> menu= (ArrayList)request.getAttribute("restaurantInfo");
    Restaurant rest = (Restaurant) request.getAttribute("rest");
  
%>
<%
    session.setAttribute("restMenu", rest.getMenu());
%>

<center>
    <h2>Hello <%=session.getAttribute("uname")%></h2>
    <h2><%=rest.getCategory()%></h2>        
</center>
<br>
<center><h3> The following is your menu</h3></center>
<br>
<table border="2" align="center" cellpadding="5" cellspacing="5">

<tr>
    <td> Food </td>
    <td> Calories </td>
    <td> ID </td>
    <td> Details </td>
</tr>
    
<% for(FoodItem f: menu){ %>

  <tr>
    <td> <%=f.getName()%> </td>
    <td> <%=f.getCalories()%></td>
    <td> <%=f.getID()%> </td>
    <% if (f.isViewDetails()){ %>
      <td> 
        <form action="Extend" method="post">
          <input type="hidden" name="foodID" value="<%=f.getID()%>" />
          <input type="submit" value="View Details" >
        </form>
      </td>
    <%} else {%>
      <td> Unavailable </td>
    <% } %>
  </tr>
<% } %>
</table>
</form>
</body>
</html>

