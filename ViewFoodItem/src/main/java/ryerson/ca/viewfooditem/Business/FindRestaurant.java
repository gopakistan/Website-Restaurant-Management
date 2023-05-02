/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.viewfooditem.Business;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ryerson.ca.viewfooditem.Helper.Restaurant;
import ryerson.ca.viewfooditem.Helper.FoodItem;
import ryerson.ca.viewfooditem.Persistence.Restaurant_CRUD;

/**
 *
 * @author student
 */
@WebServlet(name = "FindRestaurant", urlPatterns = {"/FindRestaurant"})
public class FindRestaurant extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FindRestaurant</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FindRestaurant at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private Restaurant getRestaurant(String username, String password){
        
        Restaurant beans;
        beans = Restaurant_CRUD.read(username, password);
        return beans;
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
        //processRequest(request, response);
        doPost(request, response);
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
        
        String username=(String) request.getParameter("username");
        String password=(String) request.getParameter("password");
        
        //UserInfo uinfo=getUserInfo(username, password);
        System.out.println("MAN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Restaurant rest = getRestaurant(username, password);
        

          if(rest == null){
              RequestDispatcher rd = request.getRequestDispatcher("loginfailed.jsp");
              rd.forward(request, response);
          }
          else{
              request.getSession().setAttribute("uname", username);
              request.setAttribute("restaurantInfo", rest.getMenu());
              request.setAttribute("rest", rest);
              
              RequestDispatcher rd= request.getRequestDispatcher("restaurantview.jsp");
              rd.forward(request, response);
              
          }
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

}
