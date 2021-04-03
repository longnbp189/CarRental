/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnbp.car.CarDAO;
import longnbp.car.CarDTO;
import longnbp.cart.CartObj;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {

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
            
            int carId = Integer.parseInt(request.getParameter("txtCarId"));
            int quantity = Integer.parseInt(request.getParameter("txtChangeQuantity"));

            CarDAO cDAO = new CarDAO();
            CarDTO cDTO = cDAO.findCarByID(carId);
            SimpleDateFormat formater1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date d = new Date();

            String startDate = formater1.format(d);
            if (request.getParameter("txtChangeStartDate") != null) {
                startDate = request.getParameter("txtChangeStartDate");
            }

            String endDate = formater1.format(d);
            if (request.getParameter("txtChangeEndDate") != null) {
                endDate = request.getParameter("txtChangeEndDate");
            }

            Date date1 = formater1.parse(startDate);
            Date date2 = formater1.parse(endDate);
            long day;
            if (((date2.getTime() - date1.getTime()) > 3600000) && date1.getTime() >= new Date().getTime()) {
                day = (date2.getTime() - date1.getTime());
                HttpSession s = request.getSession();
                String start = formater2.format(date1);
                String end = formater2.format(date2);
                CartObj cart = (CartObj) s.getAttribute("CART");
                if (cart == null) {
                    cart = new CartObj();
                }
                int quantityDB = cDAO.getCurrentQuantity(carId, start, end);
                if (quantityDB < quantity) {
                    request.setAttribute("OUTOFSTOCK_ERROR", carId);
                } else {
                    if (quantity == 0) {
                        request.setAttribute("QUANTITY_ERROR", carId);
                    } else {
                        cart.updateItem(carId, quantity, cDTO.getPrice(), cDTO.getCarName(), startDate, endDate, (day / 3600000));
                        s.setAttribute("CART", cart);
                    }
                }
            } else {
                request.setAttribute("UPDATE_ERROR", carId);
            }
        } catch (SQLException ex) {
            log("UpdateCartServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateCartServlet_Naming " + ex.getMessage());
        } catch (ParseException ex) {
            log("UpdateCartServlet_Parse " + ex.getMessage());
        } finally {
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
