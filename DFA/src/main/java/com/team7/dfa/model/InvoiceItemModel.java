package com.team7.dfa.model;

/**
 * This class is the model for the invoice item.
 * It contains the attributes of the invoice item.
 */
public class InvoiceItemModel {
    // Attributes of the invoice item
    private String desc;
    private int quantity;
    private double price;
    private double total;

    /**
     * Constructor for the invoice item model.
     * @param desc Description of the invoice item
     * @param quantity Quantity of the invoice item
     * @param price Price of the invoice item
     */
    public InvoiceItemModel(String desc, int quantity, double price) {
        this.desc = desc;
        this.quantity = quantity;
        this.price = price;
        calculateTotal();
    }

    /**
     * This method returns the description of the invoice item.
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method sets the description of the invoice item.
     * @param desc Description of the invoice item
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * This method returns the quantity of the invoice item.
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This method sets the quantity of the invoice item.
     * It also calls the calculateTotal method to update the total.
     * @param quantity Quantity of the invoice item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotal();
    }

    /**
     * This method returns the price of the invoice item.
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method sets the price of the invoice item.
     * It also calls the calculateTotal method to update the total.
     * @param price Price of the invoice item
     */
    public void setPrice(double price) {
        this.price = price;
        calculateTotal();
    }

    /**
     * This method returns the total of the invoice item.
     * @return total
     */
    public double getTotal() {
        return total;
    }

    /**
     * This method calculates the total of the invoice item.
     * It directly updates the total attribute by multiplying the quantity and price.
     */
    public void calculateTotal() {
        total = quantity * price;
    }
}
