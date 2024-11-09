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
import model.User;

/**
 *
 * @author T
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/change-password"})
public class ChangePasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath() + "</h1>");
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
        Cookie arr[] = request.getCookies();
        String token = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("token")) {
                    token = o.getValue();
                    break;
                }
            }
        }
        DAO d = new DAO();
        User user = d.getUserByToken(token);
        request.setAttribute("User", user);
        request.getRequestDispatcher("/client/change-password.jsp").forward(request, response);
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
        Cookie arr[] = request.getCookies();
        String token = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("token")) {
                    token = o.getValue();
                    break;
                }
            }
        }
        String passwordCurrent = request.getParameter("passwordCurrent");
        String passwordNew1 = request.getParameter("passwordNew1");
        String passwordNew2 = request.getParameter("passwordNew2");
        DAO d = new DAO();
        User user = d.getUserByToken(token);
        if (!user.getPassword().equals(passwordCurrent)) {
            request.setAttribute("error", "Mật khẩu hiện tại không đúng!");
            request.setAttribute("User", user);
            request.getRequestDispatcher("/client/change-password.jsp").forward(request, response);
        } else if (!passwordNew1.equals(passwordNew2)) {
            request.setAttribute("error", "Mật khẩu bạn nhập không trùng!");
            request.setAttribute("User", user);
            request.getRequestDispatcher("/client/change-password.jsp").forward(request, response);
        } else if (passwordNew1.length() < 8 || passwordNew2.length() < 8) {
            request.setAttribute("error", "Mật khẩu ít nhất 8 kí tự!");
            request.setAttribute("User", user);
            request.getRequestDispatcher("/client/change-password.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Cập thành thành công!");
            d.updatePassword(user, passwordNew1);
            response.sendRedirect("/Shop/change-password");
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
