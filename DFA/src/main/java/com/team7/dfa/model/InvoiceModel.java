package com.team7.dfa.model;

public class InvoiceModel {
    /**
     * This class is the model for the invoice.
     * It contains the attributes of the invoice.
     */

    // Attributes of the invoice
    private String inv_date;
    private String inv_due_date;

    private String inv_id;
    private String inv_order_id;
    private String inv_cust_name;
    private String inv_shipping;
    private String inv_billing;
    private String inv_group;
    private String inv_state;
    private double inv_subtotal;
    private double inv_discount;
    private double inv_tax_rate;
    private double inv_total;
    private double inv_balance;
    private int inv_repeat;
    private String inv_cust_notes;
    private String inv_terms;

    /**
     * Constructor for the invoice model.
     * @param inv_date
     * @param inv_due_date
     * @param inv_id
     * @param inv_order_id
     * @param inv_cust_name
     * @param inv_shipping
     * @param inv_billing
     * @param inv_group
     * @param inv_state
     * @param inv_subtotal
     * @param inv_discount
     * @param inv_tax_rate
     * @param inv_total
     * @param inv_balance
     * @param inv_repeat
     * @param inv_cust_notes
     * @param inv_terms
     */
    public InvoiceModel(String inv_date, String inv_due_date, String inv_id, String inv_order_id, String inv_cust_name, String inv_shipping, String inv_billing, String inv_group, String inv_state, double inv_subtotal, double inv_discount, double inv_tax_rate, double inv_total, double inv_balance, int inv_repeat, String inv_cust_notes, String inv_terms) {
        this.inv_date = inv_date;
        this.inv_due_date = inv_due_date;
        this.inv_id = inv_id;
        this.inv_order_id = inv_order_id;
        this.inv_cust_name = inv_cust_name;
        this.inv_shipping = inv_shipping;
        this.inv_billing = inv_billing;
        this.inv_group = inv_group;
        this.inv_state = inv_state;
        this.inv_subtotal = inv_subtotal;
        this.inv_discount = inv_discount;
        this.inv_tax_rate = inv_tax_rate;
        this.inv_total = inv_total;
        this.inv_balance = inv_balance;
        this.inv_repeat = inv_repeat;
        this.inv_cust_notes = inv_cust_notes;
        this.inv_terms = inv_terms;
    }

    /**
     * Default constructor for the invoice model.
     */
    public InvoiceModel() {
    }

    /**
     * Constructor for the invoice model.
     * @param invDate
     * @param invId
     * @param invCustName
     * @param invGroup
     * @param invState
     * @param invTotal
     */
    public InvoiceModel(String invDate, String invId, String invCustName, String invGroup, String invState, int invTotal) {
        this.inv_date = invDate;
        this.inv_id = invId;
        this.inv_cust_name = invCustName;
        this.inv_group = invGroup;
        this.inv_state = invState;
        this.inv_total = invTotal;
    }

    /**
     * Method to get the invoice date.
     * @return inv_date
     */
    public String getInv_date() {
        return inv_date;
    }

    /**
     * Method to set the invoice date.
     * @param inv_date
     */
    public void setInv_date(String inv_date) {
        this.inv_date = inv_date;
    }

    /**
     * Method to get the invoice due date.
     * @return inv_due_date
     */
    public String getInv_due_date() {
        return inv_due_date;
    }

    /**
     * Method to set the invoice due date.
     * @param inv_due_date
     */
    public void setInv_due_date(String inv_due_date) {
        this.inv_due_date = inv_due_date;
    }

    /**
     * Method to get the invoice ID.
     * @return inv_id
     */
    public String getInv_id() {
        return inv_id;
    }

    /**
     * Method to set the invoice ID.
     * @param inv_id
     */
    public void setInv_id(String inv_id) {
        this.inv_id = inv_id;
    }

    /**
     * Method to get the invoice order ID.
     * @return inv_order_id
     */
    public String getInv_order_id() {
        return inv_order_id;
    }

    /**
     * Method to set the invoice order ID.
     * @param inv_order_id
     */
    public void setInv_order_id(String inv_order_id) {
        this.inv_order_id = inv_order_id;
    }

    /**
     * Method to get the invoice customer name.
     * @return inv_cust_name
     */
    public String getInv_cust_name() {
        return inv_cust_name;
    }

    /**
     * Method to set the invoice customer name.
     * @param inv_cust_name
     */
    public void setInv_cust_name(String inv_cust_name) {
        this.inv_cust_name = inv_cust_name;
    }

    /**
     * Method to get the invoice shipping address.
     * @return inv_shipping
     */
    public String getInv_shipping() {
        return inv_shipping;
    }

    /**
     * Method to set the invoice shipping address.
     * @param inv_shipping
     */
    public void setInv_shipping(String inv_shipping) {
        this.inv_shipping = inv_shipping;
    }

    /**
     * Method to get the invoice billing address.
     * @return inv_billing
     */
    public String getInv_billing() {
        return inv_billing;
    }

    /**
     * Method to set the invoice billing address.
     * @param inv_billing
     */
    public void setInv_billing(String inv_billing) {
        this.inv_billing = inv_billing;
    }

    /**
     * Method to get the invoice group.
     * @return inv_group
     */
    public String getInv_group() {
        return inv_group;
    }

    /**
     * Method to set the invoice group.
     * @param inv_group
     */
    public void setInv_group(String inv_group) {
        this.inv_group = inv_group;
    }

    /**
     * Method to get the invoice state.
     * @return inv_state
     */
    public String getInv_state() {
        return inv_state;
    }

    /**
     * Method to set the invoice state.
     * @param inv_state
     */
    public void setInv_state(String inv_state) {
        this.inv_state = inv_state;
    }

    /**
     * Method to get the invoice subtotal.
     * @return inv_subtotal
     */
    public double getInv_subtotal() {
        return inv_subtotal;
    }

    /**
     * Method to set the invoice subtotal.
     * @param inv_subtotal
     */
    public void setInv_subtotal(double inv_subtotal) {
        this.inv_subtotal = inv_subtotal;
    }

    /**
     * Method to get the invoice discount.
     * @return inv_discount
     */
    public double getInv_discount() {
        return inv_discount;
    }

    /**
     * Method to set the invoice discount.
     * @param inv_discount
     */
    public void setInv_discount(double inv_discount) {
        this.inv_discount = inv_discount;
    }

    /**
     * Method to get the invoice tax rate.
     * @return inv_tax_rate
     */
    public double getInv_tax_rate() {
        return inv_tax_rate;
    }

    /**
     * Method to set the invoice tax rate.
     * @param inv_tax_rate
     */
    public void setInv_tax_rate(double inv_tax_rate) {
        this.inv_tax_rate = inv_tax_rate;
    }

    /**
     * Method to get the invoice total.
     * @return inv_total
     */
    public double getInv_total() {
        return inv_total;
    }

    /**
     * Method to set the invoice total.
     * @param inv_total
     */
    public void setInv_total(double inv_total) {
        this.inv_total = inv_total;
    }

    /**
     * Method to get the invoice balance.
     * @return inv_balance
     */
    public double getInv_balance() {
        return inv_balance;
    }

    /**
     * Method to set the invoice balance.
     * @param inv_balance
     */
    public void setInv_balance(double inv_balance) {
        this.inv_balance = inv_balance;
    }

    /**
     * Method to get the invoice repeat.
     * @return inv_repeat
     */
    public int getInv_repeat() {
        return inv_repeat;
    }

    /**
     * Method to set the invoice repeat.
     * @param inv_repeat
     */
    public void setInv_repeat(int inv_repeat) {
        this.inv_repeat = inv_repeat;
    }

    /**
     * Method to get the invoice customer notes.
     * @return inv_cust_notes
     */
    public String getInv_cust_notes() {
        return inv_cust_notes;
    }

    /**
     * Method to set the invoice customer notes.
     * @param inv_cust_notes
     */
    public void setInv_cust_notes(String inv_cust_notes) {
        this.inv_cust_notes = inv_cust_notes;
    }

    /**
     * Method to get the invoice terms.
     * @return inv_terms
     */
    public String getInv_terms() {
        return inv_terms;
    }

    /**
     * Method to set the invoice terms.
     * @param inv_terms
     */
    public void setInv_terms(String inv_terms) {
        this.inv_terms = inv_terms;
    }

    /**
     * toString() override method.
     * @return string
     */
    @Override
    public String toString() {
        return "InvoiceModel{" +
                "inv_date='" + inv_date + '\'' +
                ", inv_due_date='" + inv_due_date + '\'' +
                ", inv_id='" + inv_id + '\'' +
                ", inv_order_id='" + inv_order_id + '\'' +
                ", inv_cust_name='" + inv_cust_name + '\'' +
                ", inv_shipping='" + inv_shipping + '\'' +
                ", inv_billing='" + inv_billing + '\'' +
                ", inv_group='" + inv_group + '\'' +
                ", inv_state='" + inv_state + '\'' +
                ", inv_subtotal=" + inv_subtotal +
                ", inv_discount=" + inv_discount +
                ", inv_tax_rate=" + inv_tax_rate +
                ", inv_total=" + inv_total +
                ", inv_balance=" + inv_balance +
                ", inv_repeat=" + inv_repeat +
                ", inv_cust_notes='" + inv_cust_notes + '\'' +
                ", inv_terms='" + inv_terms + '\'' +
                '}';
    }
}
