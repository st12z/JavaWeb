/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import model.Customer;
import model.Item;
import model.Order;

import model.Product;

/**
 *
 * @author T
 */
public class DAO extends DBContext {

    public List<Category> getAll() {
        String sql = "select *from Categories";
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("describe"));
                list.add(c);
            }
        } catch (Exception ex) {

        }
        return list;
    }

    public List<Product> getAllProducts() {
        String sql = "select *from products";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"),
                        rs.getDate("releaseDate"), rs.getString("describe"), rs.getString("image"), getCategoryByID(rs.getInt("cid")));
                list.add(p);
            }
        } catch (Exception ex) {

        }
        return list;
    }

    public Category getCategoryByID(int id) {
        String sql = "select *from Categories where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            Category c;
            if (rs.next()) {
                c = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("describe"));
                return c;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public List<Product> getListByPage(ArrayList<Product> list, int begin, int end) {
        List<Product> listRes = new ArrayList<>();
        for (int i = begin; i < end; i++) {
            listRes.add(list.get(i));
        }
        return listRes;
    }

    public List<Product> getProductbyCIDandPrice(int id, String radioPrice) {
        String sql = "select *from products where 1=1";
        List<Product> list = new ArrayList();
        try {
            if (id != 0) {
                sql += " and cid=" + id;
            }
            if (radioPrice != null && !radioPrice.equals("")) {
                switch (radioPrice) {
                    case ">10" ->
                        sql += " and price >10000000";
                    case "6-10" ->
                        sql += " and price between 6000000 and 10000000";
                    case "<5" ->
                        sql += " and price <5000000";
                    default -> {
                    }
                }
            }
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"),
                        rs.getDate("releaseDate"), rs.getString("describe"), rs.getString("image"), getCategoryByID(id));
                list.add(p);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    public List<Product> getProductbyKey(String key) {
        String sql = "select *from products where 1=1";
        List<Product> list = new ArrayList();
        try {

            if (key != null && !key.equals("")) {
                sql += "and name like '%" + key + "%'";
            }
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"),
                        rs.getDate("releaseDate"), rs.getString("describe"), rs.getString("image"), getCategoryByID(rs.getInt("cid")));
                list.add(p);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    public List<Product> sortProduct(List<Product> list, String sortKey, String sortValue) {
        switch (sortKey) {
            case "title":
                if (sortValue.equals("asc")) {
                    Collections.sort(list, (Product o1, Product o2) -> o1.getName().compareTo(o2.getName()));
                } else {
                    Collections.sort(list, (Product o1, Product o2) -> o2.getName().compareTo(o1.getName()));
                }
                break;
            case "price":
                if (sortValue.equals("asc")) {
                    Collections.sort(list, (Product o1, Product o2) -> {
                        if (o1.getPrice() < o2.getPrice()) {
                            return -1;
                        }
                        return 1;
                    });
                } else {
                    Collections.sort(list, (Product o1, Product o2) -> {
                        if (o1.getPrice() < o2.getPrice()) {
                            return 1;
                        }
                        return -1;
                    });
                }
            default:
                break;
        }
        return list;
    }

    public Product getProduct(String id) {
        String sql = "select *from products where id=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product(rs.getString("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"),
                        rs.getDate("releaseDate"), rs.getString("describe"), rs.getString("image"), getCategoryByID(rs.getInt("cid")));
                return p;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public Customer getCustomerByEmail(String email) {
        String sql = "select *from Customer where email=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer c = new Customer(rs.getInt("customerId"), rs.getString("customerName"),
                        rs.getString("password"), rs.getString("token"), rs.getString("email"));
                return c;
            }

            return null;

        } catch (Exception ex) {

        }
        return null;
    }

    public Customer getCustomer(String email, String password) {
        String sql = "select *from Customer where email=? and password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer c = new Customer(rs.getInt("customerId"), rs.getString("customerName"),
                        rs.getString("password"), rs.getString("token"), rs.getString("email"));
                return c;
            }

            return null;

        } catch (Exception ex) {

        }
        return null;
    }

    public int getQuantityRecords(String keyword) {
        switch (keyword) {
            case "Admin" -> {
                String sql = "select count(*)from Admin";
                try {
                    PreparedStatement st = connection.prepareStatement(sql);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count;
                    }
                } catch (Exception ex) {

                }
            }
            case "Customer" -> {
                String sql = "select count(*)from Customer";
                try {
                    PreparedStatement st = connection.prepareStatement(sql);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count;
                    }
                } catch (Exception ex) {

                }
            }
            case "Orders" -> {
                String sql = "select count(*)from Orders";
                try {
                    PreparedStatement st = connection.prepareStatement(sql);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count;
                    }
                } catch (Exception ex) {

                }
            }
            case "OrderProduct" -> {
                String sql = "select count(*)from OrderProduct";
                try {
                    PreparedStatement st = connection.prepareStatement(sql);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count;
                    }
                } catch (Exception e) {

                }
            }
            default ->
                throw new AssertionError();
        }
        return 0;
    }

    public void insertCustomertoDB(Customer c) {
        String sql = "INSERT INTO [dbo].[Customer]\n"
                + "           (\n"
                + "           [customerName]\n"
                + "           ,[password]\n"
                + "           ,[token]\n"
                + "           ,[email])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCustomerName());
            st.setString(2, c.getPassword());
            st.setString(3, c.getToken());  // Sửa lại thứ tự cho đúng
            st.setString(4, c.getEmail());   // Sửa lại thứ tự cho đúng
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi ra để dễ dàng theo dõi vấn đề
        }
    }

    public Customer getCustomerByToken(String token) {

        String sql = "select *from Customer where token=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, token);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer c = new Customer(rs.getInt("customerId"), rs.getString("customerName"),
                        rs.getString("password"), rs.getString("token"), rs.getString("email"));
                return c;
            }

            return null;

        } catch (Exception ex) {

        }
        return null;

    }

    public void insertOrdertoDB(Order o) {
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([orderId]\n"
                + "           ,[customerId]\n"
                + "           ,[fullname]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[totalMoney])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, o.getOrderId());
            st.setInt(2, o.getCustomerID());
            st.setString(3, o.getFullName());
            st.setString(4, o.getAddress());
            st.setString(5, o.getPhone());
            st.setDouble(6, o.getTotalMoney());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert_OrderProducttoDB(Item item, int OrderProductId, int OrderId) {
        String sql = "INSERT INTO [dbo].[OrderProduct]\n"
                + "           ([orderProductId]\n"
                + "           ,[productId]\n"
                + "           ,[quantity]\n"
                + "           ,[orderId])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, OrderProductId);
            st.setString(2, item.getProduct().getId());
            st.setInt(3, item.getQuantity());
            st.setInt(4, OrderId);
            st.executeUpdate();
        } catch (Exception e) {

        }
    }
    
    public void insert_ListOrderProducttoDB(List<Item> items, int OrderId) {
        for (Item item : items) {
            int countOrderProductInDB = getQuantityRecords("OrderProduct");
            insert_OrderProducttoDB(item, countOrderProductInDB, OrderId);
        }
    }

    public List<Item> getOrderByCustomerId(int customerId) {
        String sql = "select *from Orders where customerId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs1 = st.executeQuery();
            List<Item> items = new ArrayList<>();
            while (rs1.next()) {
                int orderId = rs1.getInt("orderId");
                sql = "select productId,quantity from OrderProduct where orderId=?";
                st = connection.prepareStatement(sql);
                st.setInt(1, orderId);

                ResultSet rs2 = st.executeQuery();
                while (rs2.next()) {
                    Item item = new Item(getProduct(rs2.getString("productId")), rs2.getInt("quantity"));
                    items.add(item);
                }

            }
            return items;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public void setProductInDB(List<Product>listP){
        try{
            for(Product p : listP){
                String sql="UPDATE products SET quantity=? where id=?";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, p.getQuantity());
                st.setString(2, p.getId());
                st.executeUpdate();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DAO d = new DAO();
        List<Item> items = d.getOrderByCustomerId(14);
        for (Item item : items) {
            System.out.println(item.getProduct().getName());
        }

    }
}
