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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author T
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/add-cart/*"})
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddToCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCartServlet at " + request.getContextPath() + "</h1>");
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
            String productId = pathInfo.substring(1); // Lấy ID sản phẩm (bỏ dấu "/")
            DAO d = new DAO();
            Product p = d.getProduct(productId);
            String colorId = request.getParameter("colorId");
            // Thêm product vào cartId
            String txt = "";
            String cartId = "";
            Cookie[] arr = request.getCookies();
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cartId")) {
                        cartId = o.getValue();
                        break;
                    }
                }
                for (Cookie o : arr) {
                    if (o.getName().equals("cart-" + cartId)) {
                        txt += o.getValue();
                        o.setMaxAge(0);
                        response.addCookie(o);
                        break;
                    }
                }
            }
            if (txt.isEmpty()) {
                txt = productId+"$"+colorId + "-";
            } else {
                txt += productId+"$"+colorId + "-";
            }
            Cookie c = new Cookie("cart-" + cartId, txt);
            c.setMaxAge(2 * 24 * 60 * 60);
            c.setPath("/Shop");
            response.addCookie(c);
            response.sendRedirect("/Shop/home");
            // End thêm product vào cartId
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
