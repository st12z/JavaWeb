/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.client;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author T
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String key = request.getParameter("keyword");
        String sortKey = request.getParameter("sortKey");
        String sortValue = request.getParameter("sortValue");
        DAO d = new DAO();
        String currentPage_raw = request.getParameter("page");
        List<Product> listP = d.getProductbyKey(key);
        int countProducts = listP.size();
        int totalPage = countProducts / 4 + (countProducts % 4 == 0 ? 0 : 1);
        int currentPage = 1;
        int limitItem = 4;
        if (currentPage_raw != null && !currentPage_raw.equals("")) {
            currentPage = Integer.parseInt(currentPage_raw);
        }
        int begin = (currentPage - 1) * limitItem;
        int end = currentPage * limitItem;
        if (end > listP.size()) {
            end = countProducts;
        }
        String conditionSort = "";
        if (sortKey != null && sortValue != null) {
            listP = d.sortProduct(listP, sortKey, sortValue);
            conditionSort = sortKey + "-" + sortValue;
        }
        List<Product> listbyPage = d.getListByPage((ArrayList<Product>) listP, begin, end);
        List<Category> listC = d.getAll();
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("categories", listC);
        request.setAttribute("products", listP);
        request.setAttribute("keyword", key);
        request.setAttribute("products", listbyPage);
        request.setAttribute("conditionSort", conditionSort);
        request.getRequestDispatcher("client/home.jsp").forward(request, response);
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
