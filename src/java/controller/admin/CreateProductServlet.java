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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import model.Category;
import model.Product;
import model.User;

/**
 *
 * @author T
 */
@WebServlet(name = "CreateProductServlet", urlPatterns = {"/admin/create-product"})
public class CreateProductServlet extends HttpServlet {

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
            out.println("<title>Servlet CreateProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateProductServlet at " + request.getContextPath() + "</h1>");
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

        DAO d = new DAO();
        ArrayList<Category> listCategories = (ArrayList<Category>) d.getAll();
        request.setAttribute("listCategories", listCategories);
        request.getRequestDispatcher("/admin/create-product.jsp").forward(request, response);
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

        try {
            String name = request.getParameter("name");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            double discountPercentage = Double.parseDouble(request.getParameter("discountPercentage"));
            String status = request.getParameter("status");
            Part avatarPart = request.getPart("image");
            String avatarFileName = avatarPart.getSubmittedFileName();
            String uploadPath = "C:/Users/T/Documents/NetBeansProjects/Shop/web/client/images/" + avatarFileName;
            String urlImage = "client/images/" + avatarFileName;
            String id = name.toLowerCase();
            DAO d = new DAO();
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = avatarPart.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
            java.util.Date utilDate = new Date();
            Product p = new Product(id, name, quantity, price, new java.sql.Date(utilDate.getTime()),
                    urlImage, new java.sql.Date(utilDate.getTime()), new java.sql.Date(utilDate.getTime()), status,
                    discountPercentage, "Giảm 10%", "1 năm", 0, d.getCategoryByID(categoryId), 0);
            System.out.println(p);
            d.insertProduct(p);
        } catch (Exception e) {
            System.out.println(e);
        }

        response.sendRedirect("/Shop/admin/products");
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
