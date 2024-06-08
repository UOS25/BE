package uos.uos25.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // Common
    INTERNAL_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error Occured."),

    // Customer
    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Customer is not found."),

    // Employee
    EMPLOYEE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Employee is not found."),

    // Employee WorkingHistory
    DUPLICATED_HISTORY(HttpStatus.BAD_REQUEST.value(), "Working History is duplicated."),

    // Event
    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Event is not found."),

    // Inventory
    INVENTORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Inventory is not found."),
    INVENTORY_EA_NOT_ENOUGH(HttpStatus.BAD_REQUEST.value(), "Inventory ea is not enough."),
    INVENTORY_TOO_MANY_DISPLAY(
            HttpStatus.BAD_REQUEST.value(), "Display ea can't be greater than inventory ea."),
    TOO_MANY_PRODUCT_PURCHASE(
            HttpStatus.BAD_REQUEST.value(), "Purchase ea can't be greater than display ea."),

    // Orders
    ORDERS_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Orders is not found."),
    ORDERS_NOT_REQUESTED(HttpStatus.BAD_REQUEST.value(), "Orders is not requested."),
    ORDERS_NOT_DELIVERING(HttpStatus.BAD_REQUEST.value(), "Orders is not being delivered."),

    // Product
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Prodcut is not found."),
    TOO_MANY_MILEAGE(
            HttpStatus.BAD_REQUEST.value(), "Mileage can't be greater than purchase price."),

    // Receipt
    RECEIPT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Receipt is not found."),

    // ReceiptDetails
    RECEIPT_DETAILS_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Receipt Details is not found."),

    // Shop
    SHOP_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Shop is not found."),

    // Disbursement
    DISBURSEMENT_ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), "Disbursement already exists."),

    // Returns
    RETURNS_NOT_FOUND(HttpStatus.NOT_EXTENDED.value(), "Returns is not found.");

    private Integer status;
    private String message;
}
