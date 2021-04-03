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
import java.util.Set;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnbp.Account.AccountDTO;
import longnbp.cart.CartObj;
import longnbp.rental.RentalDAO;
import longnbp.rental.RentalDTO;
import longnbp.rentaldetail.RentalDetailDAO;
import longnbp.rentaldetail.RentalDetailDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

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
        String url = "showCart.jsp";
        try {
            HttpSession s = request.getSession();
            CartObj cart = (CartObj) s.getAttribute("CART");
            AccountDTO aDTO = (AccountDTO) s.getAttribute("USER");
            SimpleDateFormat formater1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            RentalDAO rDAO = new RentalDAO();
            RentalDetailDAO rdDAO = new RentalDetailDAO();

            if (cart.getDiscountId() != 0) {
                rDAO.addRental(new RentalDTO(0, cart.getDiscountId(), aDTO.getUsername(), "", cart.getTotal(), true));
            } else {
                rDAO.addRentalWithoutDiscount(new RentalDTO(0, aDTO.getUsername(), "", cart.getTotal(), true));
            }
            int rentalId = rDAO.newestIdRental(aDTO.getUsername());
            Set<Integer> keySet = cart.getCarList().keySet();
            for (int key : keySet) {
                Date date1 = formater1.parse(cart.getCarList().get(key).getStartDate());
                Date date2 = formater1.parse(cart.getCarList().get(key).getEndDate());
                String start = formater2.format(date1);
                String end = formater2.format(date2);
                rdDAO.addRentalDetail(new RentalDetailDTO(0, rentalId, key, cart.getCarList().get(key).getQuantity(),
                        0, start, end, "",
                        cart.getCarList().get(key).getPrice()));
                url = "SearchServlet";
                s.removeAttribute("CART");
            }
        } catch (SQLException ex) {
            log("CheckOutServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutServlet_Naming " + ex.getMessage());
        } catch (ParseException ex) {
            log("CheckOutServlet_Parse " + ex.getMessage());
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
