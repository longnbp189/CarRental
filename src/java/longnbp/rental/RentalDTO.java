/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.rental;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RentalDTO implements Serializable{
    private int id, discountId;
    private  String username, rentalDate;
    private float total, discountPercent;
    private boolean status;

    public RentalDTO() {
    }

    public RentalDTO(int id, int discountId, String username, String rentalDate, float total, boolean status) {
        this.id = id;
        this.discountId = discountId;
        this.username = username;
        this.rentalDate = rentalDate;
        this.total = total;
        this.status = status;
    }
    
    public RentalDTO(int id, String username, String rentalDate, float total, boolean status) {
        this.id = id;
        this.username = username;
        this.rentalDate = rentalDate;
        this.total = total;
        this.status = status;
    }

    public RentalDTO(int id, int discountId, String username, String rentalDate, float total, float discountPercent, boolean status) {
        this.id = id;
        this.discountId = discountId;
        this.username = username;
        this.rentalDate = rentalDate;
        this.total = total;
        this.discountPercent = discountPercent;
        this.status = status;
    }
    

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
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
     * @return the discountId
     */
    public int getDiscountId() {
        return discountId;
    }

    /**
     * @param discountId the discountId to set
     */
    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the rentalDate
     */
    public String getRentalDate() {
        return rentalDate;
    }

    /**
     * @param rentalDate the rentalDate to set
     */
    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
