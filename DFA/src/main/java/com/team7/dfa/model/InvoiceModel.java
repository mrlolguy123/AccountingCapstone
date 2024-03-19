package com.team7.dfa.model;

public class InvoiceModel {
    private String inv_date;
    private String inv_id;
    private String inv_order_id;
    private String inv_cust_name;
    private String inv_group;
    private String inv_state;
    private double inv_subtotal;
    private double inv_discount;
    private double inv_tax_rate;
    private double inv_total;
    private int inv_repeat;
    private String inv_cust_notes;
    private String inv_terms;
    private boolean inv_published;

    public InvoiceModel(String inv_date, String inv_id, String inv_order_id, String inv_cust_name, String inv_group, String inv_state, double inv_subtotal, double inv_discount, double inv_tax_rate, double inv_total, int inv_repeat, String inv_cust_notes, String inv_terms, boolean inv_published) {
        this.inv_date = inv_date;
        this.inv_id = inv_id;
        this.inv_order_id = inv_order_id;
        this.inv_cust_name = inv_cust_name;
        this.inv_group = inv_group;
        this.inv_state = inv_state;
        this.inv_subtotal = inv_subtotal;
        this.inv_discount = inv_discount;
        this.inv_tax_rate = inv_tax_rate;
        this.inv_total = inv_total;
        this.inv_repeat = inv_repeat;
        this.inv_cust_notes = inv_cust_notes;
        this.inv_terms = inv_terms;
        this.inv_published = inv_published;
    }

    public InvoiceModel() {
    }

    public InvoiceModel(String invDate, String invId, String invCustName, String invGroup, String invState, int invTotal) {
        this.inv_date = invDate;
        this.inv_id = invId;
        this.inv_cust_name = invCustName;
        this.inv_group = invGroup;
        this.inv_state = invState;
        this.inv_total = invTotal;
    }


    public String getInv_date() {
        return inv_date;
    }

    public void setInv_date(String inv_date) {
        this.inv_date = inv_date;
    }

    public String getInv_id() {
        return inv_id;
    }

    public void setInv_id(String inv_id) {
        this.inv_id = inv_id;
    }

    public String getInv_order_id() {
        return inv_order_id;
    }

    public void setInv_order_id(String inv_order_id) {
        this.inv_order_id = inv_order_id;
    }

    public String getInv_cust_name() {
        return inv_cust_name;
    }

    public void setInv_cust_name(String inv_cust_name) {
        this.inv_cust_name = inv_cust_name;
    }

    public String getInv_group() {
        return inv_group;
    }

    public void setInv_group(String inv_group) {
        this.inv_group = inv_group;
    }

    public String getInv_state() {
        return inv_state;
    }

    public void setInv_state(String inv_state) {
        this.inv_state = inv_state;
    }

    public double getInv_subtotal() {
        return inv_subtotal;
    }

    public void setInv_subtotal(double inv_subtotal) {
        this.inv_subtotal = inv_subtotal;
    }

    public double getInv_discount() {
        return inv_discount;
    }

    public void setInv_discount(double inv_discount) {
        this.inv_discount = inv_discount;
    }

    public double getInv_tax_rate() {
        return inv_tax_rate;
    }

    public void setInv_tax_rate(double inv_tax_rate) {
        this.inv_tax_rate = inv_tax_rate;
    }

    public double getInv_total() {
        return inv_total;
    }

    public void setInv_total(double inv_total) {
        this.inv_total = inv_total;
    }

    public int getInv_repeat() {
        return inv_repeat;
    }

    public void setInv_repeat(int inv_repeat) {
        this.inv_repeat = inv_repeat;
    }

    public String getInv_cust_notes() {
        return inv_cust_notes;
    }

    public void setInv_cust_notes(String inv_cust_notes) {
        this.inv_cust_notes = inv_cust_notes;
    }

    public String getInv_terms() {
        return inv_terms;
    }

    public void setInv_terms(String inv_terms) {
        this.inv_terms = inv_terms;
    }

    public boolean isInv_published() {
        return inv_published;
    }

    public void setInv_published(boolean inv_published) {
        this.inv_published = inv_published;
    }
}
