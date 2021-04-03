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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

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
        String url = "SearchServlet";
        try {
                CarDAO cDAO = new CarDAO();
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date d = new Date();

                String startRentDate;
                if (request.getParameter("txtStartRentDate") != null) {
                    startRentDate = request.getParameter("txtStartRentDate");
                } else {
                    startRentDate = formater.format(d);
                }
                request.setAttribute("START_RENT", startRentDate);
                String endRentDate;
                if (request.getParameter("txtEndRentDate") != null) {
                    endRentDate = request.getParameter("txtEndRentDate");
                } else {
                    endRentDate = formater.format(d);
                }
                request.setAttribute("END_RENT", endRentDate);

                int carId = 0;
                if (request.getParameter("txtCarRentID") != null) {
                    carId = Integer.parseInt(request.getParameter("txtCarRentID"));
                }
                CarDTO carRent = new CarDTO();
                if (carId != 0) {
                    carRent = cDAO.findCarByID(carId);
                }

                Date date1 = formater.parse(startRentDate);
                Date date2 = formater.parse(endRentDate);
                long day;

                if (((date2.getTime() - date1.getTime()) > 3600000) && date1.getTime() >= new Date().getTime()) {
                    day = (date2.getTime() - date1.getTime());
                    String start = formater2.format(date1);
                    String end = formater2.format(date2);
                    int quantityDB = cDAO.getCurrentQuantity(carId, start, end);
                    if (quantityDB < 1) {
                        request.setAttribute("CREATE_QUANTITY_ERROR", carId);
                    } else {
                        HttpSession s = request.getSession();
                        CartObj cart = (CartObj) s.getAttribute("CART");
                        if (cart == null) {
                            cart = new CartObj();
                        }
                        if (cart.getCarList().isEmpty()) {
                            cart.addItem(carId, 1, carRent.getPrice(), carRent.getCarName(), startRentDate, endRentDate, (day / 3600000));
                            s.setAttribute("CART", cart);
                        } else {
                            if (!cart.getCarList().containsKey(carId)) {
                                cart.addItem(carId, 1, carRent.getPrice(), carRent.getCarName(), startRentDate, endRentDate, (day / 3600000));
                                s.setAttribute("CART", cart);
                            } else {
                                request.setAttribute("EXIST_ERROR", carId);
                            }
                        }
                    }
                } else {
                    request.setAttribute("DATE_ERROR", carId);
                }
        } catch (SQLException ex) {
            log("AddToCartServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("AddToCartServlet_Naming " + ex.getMessage());
        } catch (ParseException ex) {
            log("AddToCartServlet_Parse " + ex.getMessage());
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
