/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.client;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import model.User;

/**
 *
 * @author T
 */
@WebServlet(name = "DetailUserServlet", urlPatterns = {"/detail-user"})
@MultipartConfig
public class DetailUserServlet extends HttpServlet {

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
            out.println("<title>Servlet DetailUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailUserServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/client/detail-user.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        // Đọc file avatar từ request
        Part avatarPart = request.getPart("avatar");
        String avatarFileName = avatarPart.getSubmittedFileName();
        String uploadPath = "C:/Users/T/Documents/NetBeansProjects/Shop/web/client/images/" + avatarFileName;
        System.out.println(uploadPath);

        // Lấy token từ cookie
        String token = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // Lấy thông tin user từ token và cập nhật vào database
        DAO dao = new DAO();
        User user = dao.getUserByToken(token);

        try {
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = avatarPart.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (avatarFileName.equals("")) {
            dao.updateUser(user, fullName, email, user.getAvatar());
        } else {
            dao.updateUser(user, fullName, email, "client/images/" + avatarFileName);
        }

        // Chuyển hướng về trang chi tiết user
        HttpSession session = request.getSession();
        session.setAttribute("error", "Cập thành thành công!");
        response.sendRedirect("/Shop/detail-user");
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
