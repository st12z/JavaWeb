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
import java.util.List;
import model.Cart;
import model.Item;
import model.User;

/**
 *
 * @author T
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("client/login.jsp").forward(request, response);
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
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        DAO d = new DAO();
        User existEmail = d.getUserByEmail(email);
        User user = d.getUser(email, password);
        String txt = "";
        String txtUser = "";
        String cartId = "";
        if (existEmail == null) {
            request.setAttribute("error", "Email không tồn tại!");
            request.getRequestDispatcher("client/login.jsp").forward(request, response);
        } else {
            if (user == null) {
                request.setAttribute("error", "Mật khẩu sai!");
                request.getRequestDispatcher("client/login.jsp").forward(request, response);

            } else {
                Cookie token = new Cookie("token", user.getToken());
                token.setMaxAge(3600 * 24);
                response.addCookie(token);

                Cookie[] arr = request.getCookies();
                String cartIdUser = user.getCartId();
                if (arr != null) {
                    for (Cookie o : arr) {
                        if (o.getName().equals("cartId")) {
                            cartId = o.getValue();
                            o.setMaxAge(0);
                            o.setValue("");
                            o.setPath("/Shop");
                            response.addCookie(o);
                            break;
                        }
                    }
                    for (Cookie o : arr) {
                        if (o.getName().equals("cart-" + cartId)) {
                            txt += o.getValue();
                            o.setMaxAge(0);
                            o.setValue("");
                            response.addCookie(o);
                            o.setPath("/Shop");
                            break;
                        }
                    }
                    for (Cookie o : arr) {
                        if (o.getName().equals("cartId-" + cartIdUser)) {
                            txtUser += o.getValue();
                            break;
                        }
                    }
                }
                System.out.println(txt);
                Cookie c = new Cookie("cartId", cartIdUser);
                c.setMaxAge(25 * 60 * 60);
                response.addCookie(c);
                c.setPath("/Shop");
                Cookie o = new Cookie("cart-" + cartIdUser, txt + txtUser);
                o.setMaxAge(24 * 60 * 60);
                o.setPath("/Shop");
                response.addCookie(o);
                response.sendRedirect("home");
            }

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
