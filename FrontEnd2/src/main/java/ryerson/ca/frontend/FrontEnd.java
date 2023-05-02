/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.NewCookie;
import ryerson.ca.business.Business;
import ryerson.ca.helper.FoodItem;
import ryerson.ca.helper.FoodItemsXML;
import ryerson.ca.helper.Restaurant;
import ryerson.ca.helper.RestaurantsXML;

/**
 *
 * @author student
 */
@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            if (this.autho.verify(token).getKey()) {
                  Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>
                             (token,this.autho.verify(token).getValue());
            return entry;

            } else {
                 Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }

       Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        String hiddenParam = request.getParameter("pageName");
        //String passwrod = request.getParameter("psw");
        //System.out.println("USER:" + username + "\nPASSWORD: " + passwrod);
        
        RequestDispatcher requestDispatcher;
        boolean isAuthenticated = false;
                //String query = request.getParameter("query");
        switch (hiddenParam) {
            case "login":
                isAuthenticated = Business.isAuthenticated(request.getParameter("username"), request.getParameter("psw"));
                if (isAuthenticated) {
                    String username = request.getParameter("username");
                    request.setAttribute("username", username);
                    token = autho.createJWT("FrontEnd", username, 100000);

                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);

                    RestaurantsXML result = retreiveServicesFromBackendRestaurantsXML(username, token);

//                    for(Restaurant r: result.getRestaurants()){
//                        System.out.println("FrontEnd.java Test: " + r.getName());
//                    }

                    request.setAttribute("bookResults", result);

                    requestDispatcher = request.
                        getRequestDispatcher("MenuStart.jsp");

                    requestDispatcher.forward(request, response);
                }
                else{
                    requestDispatcher =
                        request.getRequestDispatcher("loginfailed.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;
            case "viewdetails":
                request.setAttribute("username", uname);
                
//                int foodItemID = Integer.valueOf(request.getParameter("foodItemID"));
//                int foodItemCalories = Integer.valueOf(request.getParameter("foodItemCalories"));
//                float foodItemPrice = Float.valueOf(request.getParameter("foodItemID"));
//                int foodItemViewCount = Integer.valueOf(request.getParameter("foodItemViewCount"));
                String foodItemName = (String) request.getParameter("foodItemName");
                
                FoodItemsXML resultFV = retreiveServicesFromBackendFoodItemsXML(foodItemName, token);
                FoodItem f = resultFV.getFoodItems().get(0);

                //FoodItem f = new FoodItem(foodItemName, foodItemCalories, foodItemID, foodItemPrice, foodItemViewCount);

                request.setAttribute("FoodItem", f);

                requestDispatcher = request.
                    getRequestDispatcher("ViewFood.jsp");

                requestDispatcher.forward(request, response);

                break;

            case "foodItem":
                request.setAttribute("username", uname);
                String foodName = (String) request.getParameter("fooditemName");
                if(!foodName.equals("") && !foodName.equals(" ")){
                    request.setAttribute("foodName", foodName);
                    FoodItemsXML resultF = retreiveServicesFromBackendFoodItemsXML(foodName, token);
                    System.out.println("TEST IF NULL AT FRONTEND: ");
                    if(resultF.getFoodItems() != null) request.setAttribute("bookResults", resultF);
                    else request.setAttribute("bookResults", null);
                }

                requestDispatcher = request.
                    getRequestDispatcher("FoodSearch.jsp");

                requestDispatcher.forward(request, response);
                break;

            case "menu":
                request.setAttribute("username", uname);
                String menuSearch = (String) request.getParameter("menuSearch");
                if(!menuSearch.equals("") && !menuSearch.equals(" ")){
                    request.setAttribute("restName", menuSearch);
                    RestaurantsXML resultR = retreiveServicesFromBackendRestaurantsXML(menuSearch, token);
                    System.out.println("TEST IF NULL AT FRONTEND: ");
                    //System.out.println(resultR.getRestaurants().get(0).getName());
                    if(resultR.getRestaurants() != null) request.setAttribute("bookResults", resultR);
                    else request.setAttribute("bookResults", null);
                }
                

                requestDispatcher = request.
                    getRequestDispatcher("MenuSearch.jsp");

                requestDispatcher.forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private FoodItemsXML retreiveServicesFromBackendFoodItemsXML(String foodName, String token) {
        try {
            return (Business.getServicesFoodItem(foodName, token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }
    
    private RestaurantsXML retreiveServicesFromBackendRestaurantsXML(String username, String token) {
        try {
            return (Business.getServicesRestaurant(username, token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }

}
