/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.discount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import longnbp.utilities.DBHelper;

/**
 *
 * @author Admin
 */
public class DiscountDAO implements Serializable{
    
    public DiscountDTO getDiscountByCode(String code) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "select id, expireDate, discountPercent " 
                    + "from Discount " 
                    + "where discountCode = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                float discountPercent = rs.getFloat("discountPercent");
                Date expireDate = rs.getDate("expireDate");
                return new DiscountDTO(code, id, expireDate, discountPercent);
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
}
