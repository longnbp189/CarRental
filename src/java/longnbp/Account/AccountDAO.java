/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.Account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import longnbp.utilities.DBHelper;

/**
 *
 * @author Admin
 */
public class AccountDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.getConnection();
            String url = "Select fullname "
                    + "From account "
                    + "Where username=? and password=?";
            ps = con.prepareStatement(url);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }

    public AccountDTO getAccountDetail(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select username, password, fullname, role, status, phone, address, createDate "
                        + "From account "
                        + "Where username=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    Timestamp date = rs.getTimestamp("createDate");
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    String createDate = format.format(date);
                    boolean role = rs.getBoolean("role");
                    boolean status = rs.getBoolean("status");
                    return new AccountDTO(username, fullname, password, phone, address, createDate, role, status);
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
        return null;
    }

    public boolean createAccount(AccountDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Insert Into account(username, password, fullname, role, status, phone, address, createDate) "
                        + "Values(?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getUsername());
                ps.setString(2, dto.getPassword());
                ps.setString(3, dto.getFullname());
                ps.setString(6, dto.getPhone());
                ps.setString(7, dto.getAddress());
                Timestamp date = new Timestamp(new Date().getTime());
                ps.setTimestamp(8, date);
                ps.setBoolean(4, dto.isRole());
                ps.setBoolean(5, dto.isStatus());
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean checkActivate(String username, String code) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.getConnection();
            String url = "Select fullname "
                    + "From account "
                    + "Where username=? and activation=?";
            ps = con.prepareStatement(url);
            ps.setString(1, username);
            ps.setString(2, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }
    
    public void setActivate(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.getConnection();
            String url = "UPDATE Account "
                        + "SET status = 1 "
                        + "WHERE username = ?";
            ps = con.prepareStatement(url);
            ps.setString(1, username);
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
    
      public void setCode(String username, String code) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.getConnection();
            String url = "UPDATE Account "
                        + "SET activation = ? "
                        + "WHERE username = ?";
            ps = con.prepareStatement(url);
            ps.setString(1, code);
            ps.setString(2, username);
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
