/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnbp.Account.AccountDAO;
import longnbp.utilities.EmailConfig;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ValidateServlet", urlPatterns = {"/ValidateServlet"})
public class ValidateServlet extends HttpServlet {

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
        String url = "validatePage.jsp";
        try {
            HttpSession s = request.getSession();
            String username = (String) s.getAttribute("USERNAME");
            AccountDAO aDAO = new AccountDAO();
            if (request.getParameter("txtCode") != null && !request.getParameter("txtCode").equals("")) {
                String code = request.getParameter("txtCode").trim();
                if (aDAO.checkActivate(username, code)) {
                    aDAO.setActivate(username);
                    s.setAttribute("USER", aDAO.getAccountDetail(username));
                    s.removeAttribute("LOGIN_ERROR");
                    url = "SearchServlet";
                } else {
                    request.setAttribute("INVALID", "Invalid code. Please enter again");
                }
            } else {
                EmailConfig ec = new EmailConfig();
                String genCode = ec.genCode();
                aDAO.setCode(username, genCode);
                ec.sendEmail(genCode, username);
            }
        } catch (NamingException ex) {
            log("ValidateServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("ValidateServlet_SQL " + ex.getMessage());
        } catch (MessagingException ex) {
            log("ValidateServlet_Messaging " + ex.getMessage());

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
