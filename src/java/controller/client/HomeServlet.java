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
import java.util.List;
import model.Cart;
import model.Category;
import model.User;
import model.Item;
import model.Product;

/**
 *
 * @author T
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        List<Category> listC =d.getAll();
        String keyword=request.getParameter("keyword");
        String cid_raw = request.getParameter("cid");
        String sortKey = request.getParameter("sortKey");
        String sortValue = request.getParameter("sortValue");
        String currentPage_raw = request.getParameter("page");
        String radioPrice = request.getParameter("price");
        try {
            // Tạo cartId giỏ hàng
            String cartId="";
            Cookie arr[]= request.getCookies();
            if(arr!=null){
                for(Cookie o:arr){
                    if(o.getName().equals("cartId")){
                        cartId=o.getValue();
                        break;
                    }
                }
            }
            if(cartId.equals("")){
                cartId=helper.helperClass.generateToken(10);
                response.addCookie(new Cookie("cartId",cartId));
            }
            
            // end tạo cartId
            // Lọc theo danh mục
            int cid = 0;
            if (cid_raw != null && !cid_raw.equals("")) {
                cid = Integer.parseInt(cid_raw);
            }
            List<Product> listP = d.getProductbyCondition(cid, radioPrice, keyword);
            // Pagination
            int countProducts = listP.size();
            int currentPage = 1;
            int limitItem = 6;
            int totalPage = countProducts / limitItem + (countProducts % limitItem == 0 ? 0 : 1);
            
            if (currentPage_raw != null && !currentPage_raw.equals("")) {
                currentPage = Integer.parseInt(currentPage_raw);
            }
            int begin = (currentPage - 1) * limitItem;
            int end = currentPage * limitItem;
            if (end > listP.size()) {
                end = countProducts;
            }
            // End Pagination
            
            // Check login
            String token="";
            if(arr!=null){
                for(Cookie o:arr){
                    if(o.getName().equals("token")){
                        token=o.getValue();
                        break;
                    }
                }
            }
            User user=d.getUserByToken(token);
            // End check login
            
            //sort price
            String conditionSort = "";
            if (sortKey != null && sortValue != null) {
                listP = d.sortProduct(listP, sortKey, sortValue);
                conditionSort = sortKey + "-" + sortValue;
            }
            
            List<Product> listbyPage = d.getListByPage((ArrayList<Product>) listP, begin, end);
           
            request.setAttribute("conditionSort", conditionSort);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);
            // active pagination
            request.setAttribute("cid", cid_raw);
            
            // return categories
            request.setAttribute("categories", listC);
            
            // return products
            request.setAttribute("products", listbyPage);
            

            // active radioprice
            request.setAttribute("radioPrice", radioPrice);
            
            request.setAttribute("keyword",keyword);
            
            // user
            request.setAttribute("User", user);

            request.getRequestDispatcher("client/home.jsp").forward(request, response);
        } catch (Exception e) {

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
        processRequest(request, response);
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
