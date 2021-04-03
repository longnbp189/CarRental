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
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnbp.Account.AccountDTO;
import longnbp.rental.RentalDAO;
import longnbp.rental.RentalDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RentHistoryServlet", urlPatterns = {"/RentHistoryServlet"})
public class RentHistoryServlet extends HttpServlet {

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
        String url = "rentHistory.jsp";
        try {
            HttpSession s = request.getSession();
            AccountDTO aDTO = (AccountDTO) s.getAttribute("USER");
            RentalDAO rDAO = new RentalDAO();
            List<RentalDTO> list;
            
            String searchName = request.getParameter("searchName");
            String quantity = request.getParameter("quantity");
            String startDate = request.getParameter("srartDate");
            String endDate = request.getParameter("endDate");
            String type = request.getParameter("type");
            String page = request.getParameter("page");
            request.setAttribute("SEARCH_NAME", searchName);
            request.setAttribute("QUANTITY", quantity);
            request.setAttribute("START_DATE", startDate);
            request.setAttribute("END_DATE", endDate);
            request.setAttribute("TYPE", type);
            request.setAttribute("PAGE", page);
            
            if (request.getParameter("txtDate") == null || request.getParameter("txtDate").equals("")) {
                list = rDAO.getRentalbyUsername(aDTO.getUsername());
            } else {
                String date = request.getParameter("txtDate");
                list = rDAO.getRentalbyUsernameWithDate(aDTO.getUsername(), date);
            }
            if (list != null) {
                request.setAttribute("RENTAL_LIST", list);
            }

        } catch (SQLException ex) {
            log("RentHistoryServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("RentHistoryServlet_Naming " + ex.getMessage());
        } catch (ParseException ex) {
            log("RentHistoryServlet_Parse " + ex.getMessage());
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
