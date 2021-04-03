/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnbp.cart.CartObj;
import longnbp.discount.DiscountDAO;
import longnbp.discount.DiscountDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckDiscountServlet", urlPatterns = {"/CheckDiscountServlet"})
public class CheckDiscountServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String url = "ShowCartServlet";
        try {

            String discount = request.getParameter("txtDiscount");
            HttpSession s = request.getSession();
            CartObj cart = (CartObj) s.getAttribute("CART");
            DiscountDAO dDAO = new DiscountDAO();
                DiscountDTO dDTO = dDAO.getDiscountByCode(discount);
                if( dDTO != null) {
                Date d = new Date();
                if (d.getTime() < dDTO.getExpireDate().getTime()) {
                    cart.setDiscountId(dDTO.getId());
                    cart.setDiscount(dDTO.getDiscountPercent());
                    s.setAttribute("CART", cart);
                } else {
                    request.setAttribute("DISCOUNT_ERR", "Discount is out of date");
                }
                } else {
                    request.setAttribute("DISCOUNT_ERR", "Not found discound code");
                }
            
        } catch (SQLException ex) {
            log("CheckDiscountServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckDiscountServlet_Naming " + ex.getMessage());
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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

}
