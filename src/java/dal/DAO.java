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
import java.sql.Date;
import model.ColorProduct;
import model.User;
import model.Item;
import model.OrderItem;
import model.OrderDetail;
import model.Product;
import model.Review;
import model.Statics;

/**
 *
 * @author Thuc
 */
public class DAO extends DBContext {

    public List<Category> getAll() {
        String sql = "select *from Category";
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
                list.add(c);
            }
        } catch (Exception ex) {

        }
        return list;
    }

    public List<Product> getAllProducts() {
        String sql = "select *from Product";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"),
                        rs.getDate("releaseDate"), rs.getString("image"), rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("status"),
                        rs.getDouble("discountPercentage"), rs.getString("promotion"), rs.getString("warranty"), rs.getInt("deleted"), getCategoryByID(rs.getInt("categoryId")), rs.getInt("rating"));
                list.add(p);
            }
        } catch (Exception ex) {

        }
        return list;
    }

    public Category getCategoryByID(int id) {
        String sql = "select *from Category where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            Category c;
            if (rs.next()) {
                c = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
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

    public List<Product> getProductbyCondition(int id, String radioPrice, String keyword) {
        String sql = "select *from Product where 1=1";
        List<Product> list = new ArrayList();
        try {
            if (id != 0) {
                sql += " and categoryId=" + id;
            }
            if (keyword != null && !keyword.equals("")) {
                sql += "and name like '%" + keyword + "%'";
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
                        rs.getDate("releaseDate"), rs.getString("image"), rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("status"),
                        rs.getDouble("discountPercentage"), rs.getString("promotion"), rs.getString("warranty"), rs.getInt("deleted"), getCategoryByID(rs.getInt("categoryId")), rs.getInt("rating"));
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
        String sql = "select *from Product where id=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product(rs.getString("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"),
                        rs.getDate("releaseDate"), rs.getString("image"), rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("status"),
                        rs.getDouble("discountPercentage"), rs.getString("promotion"), rs.getString("warranty"), rs.getInt("deleted"), getCategoryByID(rs.getInt("categoryId")), rs.getInt("rating"));
                return p;
            }
        } catch (Exception ex) {

        }
        return null;

    }

    public List<ColorProduct> getColorsProduct(String id) {
        String sql = "SELECT ColorOfProduct.colorId,name,image from ColorOfProduct join Color\n"
                + "on ColorOfProduct.colorId=Color.id and ColorOfProduct.productId=?";
        List<ColorProduct> list = new ArrayList();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ColorProduct(rs.getInt("colorId"), rs.getString("name"), rs.getString("image")));
            }
            return list;
        } catch (Exception ex) {

        }
        return null;
    }

    public String getColorName(int id) {
        String sql = "select *from Color where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String color = rs.getString("name");
                return color;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public String getColorImage(int id) {
        String sql = "select *from ColorOfProduct where colorId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String image = rs.getString("image");
                return image;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public User getUserById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[fullName]\n"
                + "      ,[password]\n"
                + "      ,[token]\n"
                + "      ,[email]\n"
                + "      ,[avatar]\n"
                + "      ,[cartId]\n"
                + "  FROM [dbo].[Users]\n"
                + "  where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User c = new User(rs.getInt("id"), rs.getString("fullName"),
                        rs.getString("password"), rs.getString("token"), rs.getString("email"), rs.getString("avatar"), rs.getString("cartId"));
                return c;
            }

            return null;

        } catch (Exception ex) {

        }
        return null;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT [id]\n"
                + "      ,[fullName]\n"
                + "      ,[password]\n"
                + "      ,[token]\n"
                + "      ,[email]\n"
                + "      ,[avatar]\n"
                + "      ,[cartId]\n"
                + "  FROM [dbo].[Users]\n"
                + "  where email=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User c = new User(rs.getInt("id"), rs.getString("fullName"),
                        rs.getString("password"), rs.getString("token"), rs.getString("email"), rs.getString("avatar"), rs.getString("cartId"));
                return c;
            }

            return null;

        } catch (Exception ex) {

        }
        return null;
    }

    public User getUser(String email, String password) {
        String sql = "SELECT [id]\n"
                + "      ,[fullName]\n"
                + "      ,[password]\n"
                + "      ,[token]\n"
                + "      ,[email]\n"
                + "      ,[avatar]\n"
                + "      ,[cartId]\n"
                + "  FROM [dbo].[Users]\n"
                + "  where email=? and password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User c = new User(rs.getInt("id"), rs.getString("fullName"),
                        rs.getString("password"), rs.getString("token"), rs.getString("email"), rs.getString("avatar"), rs.getString("cartId"));
                return c;
            }

            return null;

        } catch (Exception ex) {

        }
        return null;
    }

    public int getOrderId(String keyword) {
        switch (keyword) {
            case "OrderDetail" -> {
                String sql = "SELECT TOP 1 [id]\n"
                        + "FROM [ShopPhone].[dbo].[OrderDetail]\n"
                        + "ORDER BY [id] DESC;";
                try {
                    PreparedStatement st = connection.prepareStatement(sql);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        int orderId = rs.getInt(1);
                        return orderId;
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

    public void inserUsertoDB(String fullName, String password, String token, String email, String avatar, String cartId) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([fullName]\n"
                + "           ,[password]\n"
                + "           ,[token]\n"
                + "           ,[email]\n"
                + "           ,[avatar]\n"
                + "           ,[cartId])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, password);
            st.setString(3, token);  // Sửa lại thứ tự cho đúng
            st.setString(4, email);   // Sửa lại thứ tự cho đúng
            st.setString(5, avatar);
            st.setString(6, cartId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi ra để dễ dàng theo dõi vấn đề
        }
    }

    public User getUserByToken(String token) {

        String sql = "SELECT [id]\n"
                + "      ,[fullName]\n"
                + "      ,[password]\n"
                + "      ,[token]\n"
                + "      ,[email]\n"
                + "      ,[avatar]\n"
                + "      ,[cartId]\n"
                + "  FROM [dbo].[Users]\n"
                + "  where token=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, token);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User c = new User(rs.getInt("id"), rs.getString("fullName"),
                        rs.getString("password"), rs.getString("token"), rs.getString("email"), rs.getString("avatar"), rs.getString("cartId"));
                return c;
            }

            return null;

        } catch (Exception ex) {

        }
        return null;

    }

    public void insertOrdertoDB(OrderDetail o) {
        String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                + "           ([userId]\n"
                + "           ,[fullName]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[totalPayment])\n" // Đã chỉnh sửa
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?);"; // Đã chỉnh sửa
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, o.getUserId());
            st.setString(2, o.getFullName());
            st.setString(3, o.getAddress());
            st.setString(4, o.getPhone());
            st.setDouble(5, o.getTotalPayment());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertOneItemtoDB(Item item, int orderId) {
        String sql = "INSERT INTO [dbo].[Item]\n"
                + "           ([productId]\n"
                + "           ,[quantity]\n"
                + "           ,[orderId]\n"
                + "           ,[colorId])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, item.getProduct().getId());
            st.setInt(2, item.getQuantity());
            st.setInt(3, orderId);
            st.setInt(4, item.getColorId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//
    public void insertItemstoDB(List<Item> items) {
        for (Item item : items) {
            int orderId = getOrderId("OrderDetail");
            insertOneItemtoDB(item, orderId);
        }
    }

    public List<OrderDetail> getOrderByUserId(int userId) {
        String sql = "SELECT [id]\n"
                + "      ,[userId]\n"
                + "      ,[fullName]\n"
                + "      ,[address]\n"
                + "      ,[phone]\n"
                + "      ,[totalPayment]\n"
                + "      ,[createAt]\n"
                + "      ,[updatedAt]\n"
                + "  FROM [dbo].[OrderDetail]\n"
                + "  where userId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs1 = st.executeQuery();
            List<OrderDetail> orders = new ArrayList<>();
            while (rs1.next()) {
                List<Item> items = new ArrayList<>();
                int orderId = rs1.getInt("id");
                sql = "SELECT [id]\n"
                        + "      ,[productId]\n"
                        + "      ,[quantity]\n"
                        + "      ,[orderId]\n"
                        + "      ,[colorId]\n"
                        + "      ,[createAt]\n"
                        + "      ,[updatedAt]\n"
                        + "  FROM [dbo].[Item]\n"
                        + "  where orderId=?";
                st = connection.prepareStatement(sql);
                st.setInt(1, orderId);

                ResultSet rs2 = st.executeQuery();
                while (rs2.next()) {
                    String colorName = getColorName(rs2.getInt("colorId"));
                    String image = getColorImage(rs2.getInt("colorId"));
                    Item item = new Item(getProduct(rs2.getString("productId")),
                            rs2.getInt("quantity"), rs2.getInt("colorId"), colorName, image);
                    items.add(item);
                }
                OrderDetail o = new OrderDetail(userId, rs1.getString("fullName"), rs1.getString("address"), rs1.getString("phone"), rs1.getDouble("totalPayment"));
                o.setOrderId(orderId);
                o.setList(items);
                orders.add(o);

            }
            return orders;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void updateUser(User u, String fullName, String email, String avatar) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [fullName] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[avatar] = ?\n"
                + " WHERE token=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, fullName);
            st.setString(2, email);
            st.setString(3, avatar);
            st.setString(4, u.getToken());
            st.executeUpdate();
        } catch (Exception ex) {

        }
    }

    public void updatePassword(User u, String password) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [password] = ?\n"
                + " WHERE token=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, u.getToken());
            st.executeUpdate();
        } catch (Exception ex) {

        }
    }

    public Statics getStatic(String productId) {
        String sql = "select * from Statics where productId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Statics a = new Statics(getProduct(rs.getString("productId")), rs.getString("Screen"), rs.getString("Camera"),
                        rs.getString("Processor"), rs.getString("Graphics"),
                        rs.getString("Storage"), rs.getString("Battery"), rs.getString("Weight"));
                return a;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public void insertReview(Review r) {
        String sql = "INSERT INTO [dbo].[Review]\n"
                + "           ([userId]\n"
                + "           ,[productId]\n"
                + "           ,[content]\n"
                + "           ,[rating])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, r.getUser().getId());
            st.setString(2, r.getProduct().getId());
            st.setNString(3, r.getContent());
            st.setInt(4, r.getRating());
            st.executeUpdate();
        } catch (Exception ex) {

        }
    }

    public ArrayList<Review> getAllReview(String productId) {
        String sql = "select * from Review where productId=? order by createdAt desc";
        ArrayList<Review> list = new ArrayList();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Review(rs.getInt("id"), getProduct(rs.getString("productId")),
                        getUserById(rs.getInt("userId")), rs.getNString("content"), rs.getInt("rating"), rs.getDate("createdAt")));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getProductId(int reviewId) {
        String sql = "select * from Review where id=? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, reviewId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("productId");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteReview(int reviewId) {
        String sql = "DELETE FROM [dbo].[Review]\n"
                + "      WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, reviewId);
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void updateRatingOfProduct(String productId, int rating) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [rating] =?\n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rating);
            st.setString(2, productId);
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateReview(int reviewId, int rating, String content, Date date) {
        String sql = "UPDATE [dbo].[Review]\n"
                + "   SET \n"
                + "      [content] = ?\n"
                + "      ,[rating] = ?\n"
                + "      ,[createdAt] = ?\n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, content);
            st.setInt(2, rating);
            st.setDate(3, date);
            st.setInt(4, reviewId);
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAO d = new DAO();
        java.util.Date utilDate = new java.util.Date();
        d.updateReview(Integer.parseInt("8"),6, "a11", new java.sql.Date(utilDate.getTime()));
    }
}
