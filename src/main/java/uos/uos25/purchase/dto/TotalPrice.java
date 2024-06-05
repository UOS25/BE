package uos.uos25.purchase.dto;

import uos.uos25.purchase.exception.TooManyMileageException;

public class TotalPrice {
    private Integer totalPrice;

    public TotalPrice() {
        this.totalPrice = 0;
    }

    public Integer get() {
        return totalPrice;
    }

    public void plus(Integer price) {
        this.totalPrice += price;
    }

    public void discountMileage(Integer mileage) {
        if (this.totalPrice - mileage < 0) throw new TooManyMileageException();
        totalPrice -= mileage;
    }
}
