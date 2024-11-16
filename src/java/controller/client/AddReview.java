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
import java.util.ArrayList;
import java.util.Date;
import model.Review;
import model.Statics;
import model.User;

/**
 *
 * @author T
 */
@WebServlet(name = "AddReview", urlPatterns = {"/review"})
public class AddReview extends HttpServlet {

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
            out.println("<title>Servlet AddReview</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddReview at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String description = request.getParameter("description");
        String rating_raw = request.getParameter("rating");
        String productId = request.getParameter("productId");
        DAO d = new DAO();
        Cookie[] arr = request.getCookies();
        String token = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("token")) {
                    token = o.getValue();
                    break;
                }
            }
        }
        if (token.equals("")) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Vui lòng đăng nhập để đánh giá!");
            String url_redirect = "/Shop/detail/" + productId;
            response.sendRedirect(url_redirect);

        } else {
            User user = d.getUserByToken(token);
            
            try {
                int rating = Integer.parseInt(rating_raw);
                java.util.Date utilDate = new Date();
                ArrayList<Review> list = d.getAllReview(productId);
                
                Review r = new Review(list.size()+1,d.getProduct(productId), user, description, rating, new java.sql.Date(utilDate.getTime()));
                d.insertReview(r);
                int sumRating=0;
                for(Review review:list){
                    sumRating+=review.getRating();
                }
                int averageRating=(sumRating+rating)/(list.size()+1);
                d.updateRatingOfProduct(productId, averageRating);
                String url_redirect = "/Shop/detail/" + productId;
                response.sendRedirect(url_redirect);
            } catch (Exception ex) {
                System.out.println(ex);
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
