/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.rental;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import longnbp.utilities.DBHelper;

/**
 *
 * @author Admin
 */
public class RentalDAO implements Serializable {

    public void addRental(RentalDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO Rental ( total, rentalDate, username, discountId, status ) "
                    + "VALUES (?, ?, ?, ?, ?) ";
            ps = con.prepareStatement(sql);
            ps.setString(3, dto.getUsername());
            Date d = new Date();
            Timestamp t = new Timestamp(d.getTime());
            ps.setTimestamp(2, t);
            ps.setBoolean(5, dto.isStatus());
            ps.setInt(4, dto.getDiscountId());
            ps.setFloat(1, dto.getTotal());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void addRentalWithoutDiscount(RentalDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO Rental ( total, rentalDate, username, status ) "
                    + "VALUES (?, ?, ?, ?) ";
            ps = con.prepareStatement(sql);
            ps.setString(3, dto.getUsername());
            Date d = new Date();
            Timestamp t = new Timestamp(d.getTime());
            ps.setTimestamp(2, t);
            ps.setBoolean(4, dto.isStatus());
            ps.setFloat(1, dto.getTotal());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public List<RentalDTO> getRentalbyUsername(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RentalDTO> list = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select r.id, r.total, r.rentalDate, r.discountId, d.discountPercent "
                    + "from Rental r left join Discount d on r.discountId = d.id "
                    + "Where username = ? And status = 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int id = rs.getInt("id");
                int disID = rs.getInt("discountId");
                float total = rs.getFloat("total");
                float discount = rs.getFloat("discountPercent");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String date = format.format(rs.getTimestamp("rentalDate"));
                list.add(new RentalDTO(id, disID, username, date, total, discount, true));

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

    public List<RentalDTO> getRentalbyUsernameWithDate(String username, String rentalDate) throws SQLException, NamingException, ParseException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RentalDTO> list = null;
        String start = rentalDate.concat(" 00:00");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String end = format1.format(new Date(format1.parse(rentalDate).getTime() + 86400000)).concat(" 00:00");
        try {
            con = DBHelper.getConnection();
            String sql = "Select r.id, r.total, r.rentalDate, r.discountId, d.discountPercent "
                    + "from Rental r left join Discount d on r.discountId = d.id  "
                    + "Where username = ? And status = 1 And r.rentalDate >= ? And r.rentalDate < ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, start);
            ps.setString(3, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int id = rs.getInt("id");
                int disID = rs.getInt("discountId");
                float total = rs.getFloat("total");
                float discount = rs.getFloat("discountPercent");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String date = format.format(rs.getTimestamp("rentalDate"));
                list.add(new RentalDTO(id, disID, username, date, total, discount, true));

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

    public int newestIdRental(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT TOP 1 id  "
                    + "FROM Rental "
                    + "WHERE username= ? "
                    + "ORDER BY id DESC ";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
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

    public void deleteRent(int id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "UPDATE Rental "
                        + "SET status = 0 "
                        + "WHERE id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
