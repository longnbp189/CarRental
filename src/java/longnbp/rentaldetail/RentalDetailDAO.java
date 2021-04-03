/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.rentaldetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longnbp.utilities.DBHelper;

/**
 *
 * @author Admin
 */
public class RentalDetailDAO implements Serializable{
      public void addRentalDetail(RentalDetailDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO RentalDetail ( rentalId, carId, quantity, price, startDate, endDate, feedbackContent, rating ) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, dto.getRentalId());
            ps.setInt(2, dto.getCarId());
            ps.setInt(3, dto.getQuantity());
            ps.setFloat(4, dto.getPrice());
            ps.setString(5, dto.getStartDate());
            ps.setString(6, dto.getEndDate());
            ps.setInt(8, dto.getRating());
            ps.setString(7, dto.getFeedbackContent());
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

    public List<RentalDetailDTO> getRentalDetailDTOs(int rentalId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RentalDetailDTO> list = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select rd.id, c.carName, rd.quantity, rd.price, startDate, endDate, feedbackContent, rating "
                    + "from RentalDetail rd join Car c on rd.carId = c.id "
                    + "Where rentalId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, rentalId);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int id = rs.getInt("id");
                String carName = rs.getString("carName");
                int quantity = rs.getInt("quantity");
                int rating = rs.getInt("rating");
                float price = rs.getFloat("price");
                String feedback = rs.getString("feedbackContent");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String start = format.format(rs.getTimestamp("startDate"));
                String end = format.format(rs.getTimestamp("endDate"));
                list.add(new RentalDetailDTO(id, rentalId, carName, quantity, rating, start, end, feedback, price));

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
    
     public void addFeedback(int detailId, int rating, String feedback) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "UPDATE RentalDetail "
                    + "SET rating = ? , feedbackContent = ? "
                    + "WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, rating);
            ps.setString(2, feedback);
            ps.setInt(3, detailId);
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
}