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
import model.Customer;
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
        List<Category> listC = d.getAll();
        String cid_raw = request.getParameter("cid");
        String sortKey = request.getParameter("sortKey");
        String sortValue = request.getParameter("sortValue");
        String currentPage_raw = request.getParameter("page");
        String radioPrice=request.getParameter("price");
        try {
            int cid = 0;
            if (cid_raw != null && !cid_raw.equals("")) {
                cid = Integer.parseInt(cid_raw);
            }
            Cookie[] arr = request.getCookies();
            String txt = "";
            String token="";
            if (arr != null) {
                for(Cookie o :arr){
                    if(o.getName().equals("token")){
                        token=o.getValue();
                        break;
                    }
                }
                for (Cookie o : arr) {
                    if (o.getName().equals("cart-"+token)) {
                        txt += o.getValue();
                        break;
                    }
                }
                
            }
            Customer c;
            if(token!=null){
                 c= d.getCustomerByToken(token);
            }
            else{
                c=null;
            }
            Cart cart = new Cart(txt);
            List<Item> items = cart.getItems();
            List<Product> listP = new ArrayList<>();
            listP = d.getProductbyCIDandPrice(cid,radioPrice);
            int countProducts = listP.size();
            int totalPage = countProducts / 4 + (countProducts % 4 == 0 ? 0 : 1);
            int currentPage = 1;
            int limitItem = 4;
            if (currentPage_raw != null && !currentPage_raw.equals("")) {
                currentPage = Integer.parseInt(currentPage_raw);
            }
            int begin = (currentPage - 1) * limitItem;
            int end = currentPage * limitItem;
            if (end > listP.size()) {
                end = countProducts;
            }
            String conditionSort = "";
            if (sortKey != null && sortValue != null) {
                listP = d.sortProduct(listP, sortKey, sortValue);
                conditionSort = sortKey + "-" + sortValue;
            }
            int countItemOfOrder=0;
            if(c!=null){
                countItemOfOrder=d.getOrderByCustomerId(c.getId()).size();
            }
            List<Product> listbyPage = d.getListByPage((ArrayList<Product>) listP, begin, end);
            request.setAttribute("conditionSort", conditionSort);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("cid", cid_raw);
            request.setAttribute("categories", listC);
            request.setAttribute("products", listbyPage);
            request.setAttribute("items", items);
            request.setAttribute("Customer", c);
            request.setAttribute("radioPrice", radioPrice);
            request.setAttribute("countItems", countItemOfOrder);
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
