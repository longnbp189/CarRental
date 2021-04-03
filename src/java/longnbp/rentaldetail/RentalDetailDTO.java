/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.rentaldetail;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class RentalDetailDTO implements Serializable{
   private int id, rentalId, carId, quantity, rating;
    private String startDate, endDate, feedbackContent, carName;
    private float price;

    public RentalDetailDTO() {
    }

    public RentalDetailDTO(int id, int rentalId, int carId, int quantity, int rating, String startDate, String endDate, String feedbackContent, float price) {
        this.id = id;
        this.rentalId = rentalId;
        this.carId = carId;
        this.quantity = quantity;
        this.rating = rating;
        this.startDate = startDate;
        this.endDate = endDate;
        this.feedbackContent = feedbackContent;
        this.price = price;
    }

    public RentalDetailDTO(int id, int rentalId, String carName, int quantity, int rating, String startDate, String endDate, String feedbackContent, float price) {
        this.id = id;
        this.rentalId = rentalId;
        this.carName = carName;
        this.quantity = quantity;
        this.rating = rating;
        this.startDate = startDate;
        this.endDate = endDate;
        this.feedbackContent = feedbackContent;
        this.price = price;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
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
     * @return the rentalId
     */
    public int getRentalId() {
        return rentalId;
    }

    /**
     * @param rentalId the rentalId to set
     */
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    /**
     * @return the carId
     */
    public int getCarId() {
        return carId;
    }

    /**
     * @param carId the carId to set
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the feedbackContent
     */
    public String getFeedbackContent() {
        return feedbackContent;
    }

    /**
     * @param feedbackContent the feedbackContent to set
     */
    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }
    
}
