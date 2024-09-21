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
import jakarta.servlet.http.HttpSession;
import model.Product;

/**
 *
 * @author T
 */
@WebServlet(name = "BuyServlet", urlPatterns = {"/add-cart"})
public class BuyServlet extends HttpServlet {

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
            out.println("<title>Servlet BuyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyServlet at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        Cookie arr[] = request.getCookies();
        DAO d = new DAO();
        String txt = "";
        String token = "";
        HttpSession session = request.getSession(true);
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("token")) {
                    token = o.getValue();
                    break;
                }
            }
            if (token.equals("")) {

                session.setAttribute("errorCart", "Hãy đăng nhập để thêm giỏ hàng");
                response.sendRedirect("chome");
                return;
            }
            if (session != null) {
                // Xóa một thuộc tính cụ thể của session
                session.removeAttribute("errorCart");
            }
            for (Cookie o : arr) {
                if (o.getName().equals("cart-" + token)) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                    break;
                }

            }
        }
        String a[] = txt.split("-");
        int countProductByID = 0;
        for (String x : a) {
            if (x.equals(id)) {
                countProductByID++;
            }
        }
        Product p = d.getProduct(id);
        if (txt.isEmpty()) {
            txt=id+"-";
        } else {
            if (countProductByID + 1 <= p.getQuantity()) {
                txt += id+"-";
                session.removeAttribute("errorCart");
            } else {
                session.setAttribute("errorCart", "Sản phẩm không đủ số lượng!");
            }
        }
        Cookie c = new Cookie("cart-" + token, txt);
        c.setMaxAge(2 * 24 * 60 * 60);
        response.addCookie(c);
        response.sendRedirect("home");
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
        HttpSession session = request.getSession();

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
