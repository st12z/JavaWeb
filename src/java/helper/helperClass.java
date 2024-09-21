/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import jakarta.servlet.http.Cookie;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author T
 */
public class helperClass {

    public static String generateToken(int length) {
        String s = "abcdefghijklmnopqrstuvwxyz0123456789";
        String token = "";
        for (int i = 0; i < length; i++) {
            token += s.charAt((int) (Math.random() * (s.length())));
        }
        return token;
    }

    public static String getToken(Cookie arr[]) {
        String token = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("token")) {
                    token = o.getValue();
                }
            }
        }
        return token;
    }
    public static String moneyVND(double price){
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        String formattedAmount = formatter.format(price);
        return formattedAmount;
    }
}
