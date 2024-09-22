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
import model.Customer;

/**
 *
 * @author T
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    public static int countLogin = 0;
    public static String emailLogin="";

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
        Cookie[] arr = request.getCookies();
        String email = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cusEmail")) {
                    email = o.getValue();
                    request.setAttribute("email", o.getValue());
                }
                if (o.getName().equals("cusPass")) {
                    request.setAttribute("password", o.getValue());
                }
            }
        }
        if (!email.equals("") || email == null) {
            request.setAttribute("checked", true);
        }
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
        if(emailLogin.equals("")) emailLogin=email;
        String password = request.getParameter("password");
        String checkBox = request.getParameter("checkBox");
        DAO d = new DAO();
        Customer c = d.getCustomerByEmail(email);
        Customer authAccount = d.getCustomer(email, password);
        if (c == null) {
            request.setAttribute("error", "Email không tồn tại!");
            request.getRequestDispatcher("client/login.jsp").forward(request, response);
        } else {
            if (authAccount == null) {
                this.countLogin+=1;
                if(emailLogin.equals(email)){
                    if(this.countLogin>=3){
                        request.setAttribute("error", "Bạn đã đăng nhập quá 3 lần vui lòng thay đổi mật khẩu!");
                    }
                    else{
                        request.setAttribute("error", "Mật khẩu sai!");
                    }
                }
                else{
                    this.countLogin=1;
                    this.emailLogin=email;
                    request.setAttribute("error", "Mật khẩu sai!");
                }
                request.getRequestDispatcher("client/login.jsp").forward(request, response);

            } else {
                this.countLogin=0;
                this.emailLogin="";
                Cookie tokenCookie = new Cookie("token", c.getToken());
                Cookie emailCookie = new Cookie("cusEmail", c.getEmail());
                Cookie passCookie = new Cookie("cusPass", c.getPassword());
                emailCookie.setMaxAge(3600 * 24);
                passCookie.setMaxAge(3600 * 24);
                tokenCookie.setMaxAge(3600 * 24);
                response.addCookie(tokenCookie);
                if (checkBox != null && checkBox.equals("1")) {
                    response.addCookie(emailCookie);
                    response.addCookie(passCookie);
                } else {
                    Cookie[] arr = request.getCookies();

                    if (arr != null) {
                        for (Cookie o : arr) {
                            if (o.getName().equals("cusEmail")) {
                                o.setMaxAge(0);
                                response.addCookie(o);
                            }
                            if (o.getName().equals("cusPass")) {
                                o.setMaxAge(0);
                                response.addCookie(o);
                            }
                        }
                    }
                }
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
