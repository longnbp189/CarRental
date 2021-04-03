/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.discount;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DiscountDTO implements Serializable{
    private String discountCode;
    private int id;
    private Date expireDate;
    private float discountPercent;

    public DiscountDTO() {
    }

    public DiscountDTO(String discountCode, int id, Date expireDate, float discountPercent) {
        this.discountCode = discountCode;
        this.id = id;
        this.expireDate = expireDate;
        this.discountPercent = discountPercent;
    }

    /**
     * @return the discountCode
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @param discountCode the discountCode to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the expireDate
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * @return the discountPercent
     */
    public float getDiscountPercent() {
        return discountPercent;
    }

    /**
     * @param discountPercent the discountPercent to set
     */
    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }
    
}
