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
import java.util.List;
import model.Cart;
import model.Customer;
import model.Item;
import model.Order;
import model.Product;

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
        DAO d = new DAO();
        Cookie[] arr = request.getCookies();
        String txt = "";
        String token="";
        String cartID = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("token")) {
                    token = o.getValue();
                    break;
                }
            }
            for (Cookie o : arr) {
                if (o.getName().equals("cartID")) {
                    cartID = o.getValue();
                    break;
                }
            }
            for (Cookie o : arr) {
                if (o.getName().equals("cart-" + cartID)) {
                    txt += o.getValue();
                }
            }
        }
        if(token.equals("")){
            response.sendRedirect("login");
            return;
        }
        Cart cart = new Cart(txt);
        List<Item> items = cart.getItems();
        request.setAttribute("items", items);
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
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Cookie[] arr = request.getCookies();
        String txt = "";
        String cartID = "";
        String token = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("token")) {
                    token = o.getValue();
                    break;
                }
            }
            for (Cookie o : arr) {
                if (o.getName().equals("cartID")) {
                    cartID = o.getValue();
                    break;
                }

            }
            for (Cookie o : arr) {
                if (o.getName().equals("cart-" + cartID)) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }

        }
        DAO d = new DAO();
        Cart cart = new Cart(txt);
        List<Item> items = cart.getItems();

        List<Product> listProduct = d.getAllProducts();
        for (Product p : listProduct) {
            for (Item i : items) {
                if (p.getId().equals(i.getProduct().getId())) {
                    p.setQuantity(p.getQuantity() - i.getQuantity());
                }
            }
        }

        Customer c = d.getCustomerByToken(token);
        int customerId = c.getId();
        double totalPrice = cart.getTotalMoney();
        int countOrderInDB = d.getQuantityRecords("Orders");
        Order o = new Order(countOrderInDB + 1, customerId, fullname, address, phone, totalPrice);
        d.insertOrdertoDB(o);
        d.insert_ListOrderProducttoDB(items, o.getOrderId());
        d.setProductInDB(listProduct);
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
