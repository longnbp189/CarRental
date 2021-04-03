/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.car;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longnbp.utilities.DBHelper;

/**
 *
 * @author Admin
 */
public class CarDAO implements Serializable {

  
    public List<CarDTO> getAllCar(String carName, String startDate, String endDate, int quantity, int page) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CarDTO> list = null;
        int num = (page - 1) * 20;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT c.id, carName, color, year, c.typeId, c.categoryId, quantity, picture, t.typeName, ca.categoryName, price "
                    + "FROM Car c join Type t on c.typeId = t.id "
                    + "	join Category ca on c.categoryId = ca.categoryId "
                    + "WHERE carName LIKE ? AND status = 1 "
                    + "AND c.id IN (SELECT c.id FROM Car c left join "
                    + "(SELECT SUM(r.quantity) as 'quantity', r.carId "
                    + "FROM RentalDetail r join Car c ON c.id = r.carId "
                    + "WHERE NOT(? < r.startDate AND ? < r.startDate) "
                    + "AND NOT (? > r.endDate AND ? > r.endDate) "
                    + "GROUP BY r.carId) r ON r.carId = c.id "
                    + "WHERE c.quantity - ISNULL(r.quantity, 0) >= ?) "
                    + "order by year "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT 20 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + carName + "%");
            ps.setString(2, startDate);
            ps.setString(4, startDate);
            ps.setString(3, endDate);
            ps.setString(5, endDate);
            ps.setInt(6, quantity);
            ps.setInt(7, num);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int carId = rs.getInt("id");
                String name = rs.getString("carName");
                String color = rs.getString("color");
                String image = rs.getString("picture");
                int year = rs.getInt("year");
                int q = rs.getInt("quantity");
                String categoryId = rs.getString("categoryId");
                String typeId = rs.getString("typeId");
                String typeName = rs.getString("typeName");
                String categoryName = rs.getString("categoryName");
                float price = rs.getFloat("price");
                list.add(new CarDTO(name, color, image, categoryId, typeId, categoryName, typeName, year, q, carId, price, true));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<CarDTO> getCarWithType(String carName, String startDate, String endDate, int quantity, String typeId, int page) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CarDTO> list = null;
        int num = (page - 1) * 20;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT c.id, carName, color, year, c.typeId, c.categoryId, quantity, picture, t.typeName, ca.categoryName, price "
                    + "FROM Car c join Type t on c.typeId = t.id "
                    + "join Category ca on c.categoryId = ca.categoryId "
                    + "WHERE carName LIKE ? AND status = 1 AND typeId = ? "
                    + "	AND c.id IN (SELECT c.id FROM Car c left join "
                    + "(SELECT SUM(r.quantity) as 'quantity', r.carId "
                    + "FROM RentalDetail r join Car c ON c.id = r.carId "
                    + "WHERE NOT(? < r.startDate AND ? < r.startDate) "
                    + "AND NOT (? > r.endDate AND ? > r.endDate) "
                    + "GROUP BY r.carId) r ON r.carId = c.id "
                    + "WHERE c.quantity - ISNULL(r.quantity, 0) >= ?) "
                    + "order by year "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT 20 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + carName + "%");
            ps.setString(2, typeId);
            ps.setString(3, startDate);
            ps.setString(5, startDate);
            ps.setString(4, endDate);
            ps.setString(6, endDate);
            ps.setInt(7, quantity);
            ps.setInt(8, num);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int carId = rs.getInt("id");
                String name = rs.getString("carName");
                String color = rs.getString("color");
                String image = rs.getString("picture");
                int year = rs.getInt("year");
                int q = rs.getInt("quantity");
                String categoryId = rs.getString("categoryId");
                String typeName = rs.getString("typeName");
                String categoryName = rs.getString("categoryName");
                float price = rs.getFloat("price");
                list.add(new CarDTO(name, color, image, categoryId, typeId, categoryName, typeName, year, q, carId, price, true));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public int pageCountWithType(String search, String typeId, int quantity, String startDate, String endDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(c.id) as row "
                        + "FROM Car c join Type t on c.typeId = t.id "
//                        + "join Category ca on c.categoryId = ca.categoryId "
                        + "WHERE carName LIKE ? AND status = 1 AND typeId = ? "
                        + "AND c.id IN (SELECT c.id FROM Car c left join "
                        + "(SELECT SUM(r.quantity) as 'quantity', r.carId "
                        + "FROM RentalDetail r join Car c ON c.id = r.carId "
                        + "WHERE NOT(? < r.startDate AND ? < r.startDate) "
                        + "AND NOT (? > r.endDate AND ? > r.endDate) "
                        + "GROUP BY r.carId) r ON r.carId = c.id "
                        + "WHERE c.quantity - ISNULL(r.quantity, 0) >= ?) ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                ps.setString(2, typeId);
                ps.setString(3, startDate);
                ps.setString(5, startDate);
                ps.setString(4, endDate);
                ps.setString(6, endDate);
                ps.setInt(7, quantity);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int count = (rs.getInt("row") - 1) / 20;
                    return count + 1;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public int pageCount(String search, int quantity, String startDate, String endDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(c.id) as row "
                        + "FROM Car c join Type t on c.typeId = t.id "
//                        + "join Category ca on c.categoryId = ca.categoryId "
                        + "WHERE carName LIKE ? AND status = 1 "
                        + "AND c.id IN (SELECT c.id FROM Car c left join "
                        + "(SELECT SUM(r.quantity) as 'quantity', r.carId "
                        + "FROM RentalDetail r join Car c ON c.id = r.carId "
                        + "WHERE NOT(? < r.startDate AND ? < r.startDate) "
                        + "AND NOT (? > r.endDate AND ? > r.endDate) "
                        + "GROUP BY r.carId) r ON r.carId = c.id "
                        + "WHERE c.quantity - ISNULL(r.quantity, 0) >= ?) ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                ps.setString(2, startDate);
                ps.setString(4, startDate);
                ps.setString(3, endDate);
                ps.setString(5, endDate);
                ps.setInt(6, quantity);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int count = (rs.getInt("row") - 1) / 20;
                    return count + 1;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public CarDTO findCarByID(int carID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT id, carName, color, year, typeId, categoryId, quantity, picture, price "
                    + "FROM Car "
                    + "WHERE id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, carID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("carName");
                String color = rs.getString("color");
                int year = rs.getInt("year");
                String categoryId = rs.getString("categoryId");
                String typeId = rs.getString("typeId");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                return new CarDTO(name, color, "", categoryId, typeId, year, quantity, carID, true, price);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public int getCurrentQuantity(int carID, String startDate, String endDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT quantity - ISNULL((SELECT SUM(r.quantity) "
                    + "FROM RentalDetail r join Car c ON c.id = r.carId "
                    + "WHERE NOT(? < r.startDate AND ? < r.startDate) "
                    + "AND NOT(? > r.endDate AND ? > r.endDate) "
                    + "AND c.id = ? "
                    + "GROUP BY r.carId), 0) as 'quantity' "
                    + "FROM Car WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(5, carID);
            ps.setInt(6, carID);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ps.setString(3, startDate);
            ps.setString(4, endDate);
            rs = ps.executeQuery();
            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                return quantity;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

}
