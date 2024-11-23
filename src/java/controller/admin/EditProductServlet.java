/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import model.Category;
import model.Product;

/**
 *
 * @author T
 */
@WebServlet(name = "EditProductServlet", urlPatterns = {"/admin/edit-product/*"})
@MultipartConfig
public class EditProductServlet extends HttpServlet {

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
            out.println("<title>Servlet EditProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProductServlet at " + request.getContextPath() + "</h1>");
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
                String productId = pathInfo.substring(1); // Lấy ID sản phẩm (bỏ dấu "/")
                DAO d = new DAO();
                Product p = d.getProduct(productId);
                request.setAttribute("product", p);
                ArrayList<Category> listCategories = (ArrayList<Category>) d.getAll();
                request.setAttribute("listCategories", listCategories);
                request.getRequestDispatcher("/admin/edit-product.jsp").forward(request, response);

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
                String productId = pathInfo.substring(1); // Lấy ID sản phẩm (bỏ dấu "/")
                String name = request.getParameter("name");
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price").replace(".", ""));
                double discountPercentage = Double.parseDouble(request.getParameter("discountPercentage"));
                String status = request.getParameter("status");
                System.out.println(name);
                System.out.println(categoryId);
                System.out.println(quantity);
                System.out.println(quantity);
                Part imagePart = request.getPart("image");
                String avatarFileName = imagePart.getSubmittedFileName();
                String uploadPath = "C:/Users/T/Documents/NetBeansProjects/Shop/web/client/images/" + avatarFileName;

                String urlImage = "client/images/" + avatarFileName;
                DAO d = new DAO();
                Product productAno = d.getProduct(productId);
                if (avatarFileName.equals("")) {
                    java.util.Date utilDate = new Date();
                    
                    Product p = new Product(productAno.getId(), name, quantity, price, new java.sql.Date(utilDate.getTime()),
                            productAno.getImage(), new java.sql.Date(utilDate.getTime()), new java.sql.Date(utilDate.getTime()), status,
                            discountPercentage, "Giảm 10%", "1 năm", 0, d.getCategoryByID(categoryId), 0);
                    d.updateProduct(p, productId);
                } else {
                    FileOutputStream fos = new FileOutputStream(uploadPath);
                    InputStream is = imagePart.getInputStream();
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);
                    fos.close();

                    java.util.Date utilDate = new Date();
                    Product p = new Product(productAno.getId(), name, quantity, price, new java.sql.Date(utilDate.getTime()),
                            urlImage, new java.sql.Date(utilDate.getTime()), new java.sql.Date(utilDate.getTime()), status,
                            discountPercentage, "Giảm 10%", "1 năm", 0, d.getCategoryByID(categoryId), 0);

                    d.updateProduct(p, productId);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
