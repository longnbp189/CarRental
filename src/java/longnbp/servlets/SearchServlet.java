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
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longnbp.car.CarDAO;
import longnbp.car.CarDTO;
import longnbp.type.TypeDAO;
import longnbp.type.TypeDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        String url = "search.jsp";
        try {
            boolean err = false;
            String carName = "";
            if (request.getParameter("txtCarName") != null) {
                carName = request.getParameter("txtCarName").trim();
            }

            int page = 1;
            if (request.getParameter("btnPage") != null) {
                if (!request.getParameter("btnPage").equals("")) {
                    page = Integer.parseInt(request.getParameter("btnPage"));
                }
            }
            request.setAttribute("PAGE_NUM", page);

            TypeDAO tDAO = new TypeDAO();
            List<TypeDTO> typeList = tDAO.getTypeList();

            request.setAttribute("TYPE_LIST", typeList);

            String type = "";
            if (request.getParameter("cbType") != null) {
                type = request.getParameter("cbType");
            }
            request.setAttribute("SELECTED_TYPE", type);

            SimpleDateFormat formater1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date d = new Date();

            String startDate = formater1.format(d);
            if (request.getParameter("txtStartDate") != null) {
                startDate = request.getParameter("txtStartDate");
            }
            request.setAttribute("START", startDate);

            String endDate = formater1.format(d);
            if (request.getParameter("txtEndDate") != null) {
                endDate = request.getParameter("txtEndDate");
            }
            request.setAttribute("END", endDate);

            Date date1 = formater1.parse(startDate);
            Date date2 = formater1.parse(endDate);
            if (date2.compareTo(date1) < 0) {
                err = true;
            }
            if (err) {
                request.setAttribute("DATE_ERR", "Start date can not > end date");
            } else {
                startDate = formater2.format(date1);
                endDate = formater2.format(date2);
                String quantityString;
                int quantity = 1;
                if (request.getParameter("txtQuantity") != null) {
                    quantityString = request.getParameter("txtQuantity");
                    try {
                        int q = Integer.parseInt(quantityString);
                        if (q != 0) {
                            quantity = q;
                        } else {
                            request.setAttribute("QUANTITY_ERR", "Quantity > 0");
                        }
                    } catch (NumberFormatException e) {
                        quantity = 1;
                    }
                }
                request.setAttribute("QUANTITY", quantity);
                CarDAO cdao = new CarDAO();
                List<CarDTO> list;

                if (type.equals("")) {
                    request.setAttribute("PAGE_COUNT", cdao.pageCount(carName, quantity, startDate, endDate));
                    list = cdao.getAllCar(carName, startDate, endDate, quantity, page);

                } else {
                    request.setAttribute("PAGE_COUNT", cdao.pageCountWithType(carName, type, quantity, startDate, endDate));
                    list = cdao.getCarWithType(carName, startDate, endDate, quantity, type, page);
                }
                if (list != null) {

                    request.setAttribute("LIST_CAR", list);
                }
            }
        } catch (SQLException ex) {
             log("SearchServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
             log("SearchServlet_Naming " + ex.getMessage());
        } catch (ParseException ex) {
             log("SearchServlet_Parse " + ex.getMessage());
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
