/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.cart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartObj {
    private Map<Integer, CarInCart> carList;
    private float total;
    private float discount = 0;
    private int discountId = 0;
    

    public CartObj() {
        this.carList = new HashMap<>();
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

   
    
    public Map<Integer, CarInCart> getCarList() {
        return carList;
    }

    public float getTotal() {
        return total * ( 100 - discount) /100;
    }

    
    
    public void updateItem(int id, int quantity, float price, String carName, String startDate, String endDate,float time) {
        if (this.carList == null) {
            this.carList = new HashMap<>();
            this.total = 0;
        }
        CarInCart car = new CarInCart(startDate, endDate, carName, quantity, price * quantity * time);
        if (this.carList.containsKey(id)) {
                total = total - this.getCarList().get(id).getPrice() + car.getPrice();
        } else {
            total += quantity * price * time;
        }
        this.carList.put(id, car);
    }
    
    public void addItem(int id, int quantity, float price, String carName, String startDate, String endDate,float time) {
        if (this.carList == null) {
            this.carList = new HashMap<>();
            this.total = 0;
        }
        CarInCart car = new CarInCart(startDate, endDate, carName, quantity, price * quantity * time);
        if (this.carList.containsKey(id)) {
            car.setQuantity(this.carList.get(id).getQuantity() + quantity);
            car.setPrice(car.getQuantity() * price * time);
            total = total - this.getCarList().get(id).getPrice() + car.getQuantity() * price * time;
        } else {
            total += quantity * price * time;
        }
        this.carList.put(id, car);
    }
    
    public void removeItem(int id) {
        if (this.carList == null) {
            total = 0;
            return;
        }
        if (this.carList.containsKey(id)) {
            float p = this.carList.get(id).getPrice();
            this.total -= p;
            this.carList.remove(id);
            if (this.carList.isEmpty()) {
                this.carList = null;
            }
        }
    }
}
