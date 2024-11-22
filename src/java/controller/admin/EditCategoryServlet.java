/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

/**
 *
 * @author T
 */
@WebServlet(name = "EditCategoryServlet", urlPatterns = {"/admin/edit-category/*"})
public class EditCategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet EditCategoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCategoryServlet at " + request.getContextPath() + "</h1>");
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
        String pathInfo = request.getPathInfo(); // Lấy thông tin đường dẫn
        if (pathInfo != null && pathInfo.matches("/\\w+")) { // Kiểm tra ID có dạng /id
            try {
                String categoryId = pathInfo.substring(1); // Lấy ID sản phẩm (bỏ dấu "/")
                DAO d = new DAO();
                Category c = d.getCategoryByID(Integer.parseInt(categoryId));
                request.setAttribute("category", c);
                request.getRequestDispatcher("/admin/edit-category.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            // Nếu không có ID hợp lệ, trả về lỗi 404
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
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
        String pathInfo = request.getPathInfo(); // Lấy thông tin đường dẫn
        if (pathInfo != null && pathInfo.matches("/\\w+")) { // Kiểm tra ID có dạng /id
            try {
                String name=request.getParameter("name");
                String description=request.getParameter("description");
                String categoryId = pathInfo.substring(1); // Lấy ID sản phẩm (bỏ dấu "/")
                DAO d = new DAO();
                d.updateCategory(Integer.parseInt(categoryId), name, description);
                response.sendRedirect("/Shop/admin/categories");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            // Nếu không có ID hợp lệ, trả về lỗi 404
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
