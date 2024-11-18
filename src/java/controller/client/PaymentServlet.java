/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.client;

import dal.DAO;
import helper.helperClass;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import model.Cart;
import model.Item;
import model.OrderDetail;
import model.Product;
import model.User;

/**
 *
 * @author T
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {

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
            out.println("<title>Servlet PaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();

        // Kiểm tra và lấy lỗi từ session nếu có
        String error = (String) session.getAttribute("error");
        if (error != null) {
            // Gửi lỗi tới trang JSP
            request.setAttribute("error", error);

            // Xóa lỗi khỏi session sau khi hiển thị
            session.removeAttribute("error");
        }
        DAO d = new DAO();
        Cookie[] arr = request.getCookies();
        String txt = "";
        String cartId = "";
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
                }
            }
        }
        //Check login
        String token = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("token")) {
                    token = o.getValue();
                    break;
                }
            }
        }
        User user = d.getUserByToken(token);
        Cart cart = new Cart(txt);
        List<Item> items = cart.getItems();
        request.setAttribute("items", items);
        request.setAttribute("User", user);
        request.setAttribute("totalMoney", cart.getTotalMoneyVND());
        request.getRequestDispatcher("client/payment.jsp").forward(request, response);
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

        String fullname = request.getParameter("fullName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Cookie[] arr = request.getCookies();
        String txt = "";
        String cartId = "";
        String token = "";
        DAO d = new DAO();
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("token")) {
                    token = o.getValue();
                    break;
                }
            }
            for (Cookie o : arr) {
                if (o.getName().equals("cartId")) {
                    cartId = o.getValue();
                    break;
                }

            }
            for (Cookie o : arr) {
                if (o.getName().equals("cart-" + cartId)) {
                    txt += o.getValue();
                    response.addCookie(o);
                }
            }

        }
        if (token.equals("")) {

            session.setAttribute("error", "Bạn cần đăng nhập để thanh toán!");
            response.sendRedirect("/Shop/payment");
            return;
        }
        // Xóa bỏ giỏ hàng
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart-" + cartId)) {
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }

        }

        User user = d.getUserByToken(token);

        request.setAttribute("User", user);
        java.util.Date utilDate = new Date();
        Cart cart = new Cart(txt);
        List<Item> items = cart.getItems();
        int userId = user.getId();
        double totalPayment = cart.getTotalMoney();
        String code = helperClass.generateToken(10);
        OrderDetail o = new OrderDetail(userId, fullname, address, phone, totalPayment,new java.sql.Date(utilDate.getTime()),code);
        d.insertOrdertoDB(o);
        d.insertItemstoDB(items);
        response.sendRedirect("home");
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
