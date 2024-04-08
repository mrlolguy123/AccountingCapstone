package com.team7.dfa.model;

/**
 * This class is the model for the invoice log.
 * It contains the attributes of the invoice log used on the Log table on the invoicing home page.
 */
public class InvoiceLog {
    // Attributes of the invoice log
    private String log_date;
    private String log_id;
    private String log_desc;

    /**
     * Constructor for the invoice log model.
     * @param log_date Invoice Log Update Date
     * @param log_id Invoice Log ID
     * @param log_desc Invoice Log Description
     */
    public InvoiceLog(String log_date, String log_id, String log_desc) {
        setLog_id(log_id);
        setLog_date(log_date);
        setLog_desc(log_desc);
    }

    /**
     * This method returns the date of the log.
     * @return log_date
     */
    public String getLog_date() {
        return log_date;
    }

    /**
     * This method sets the date of the log.
     * @param log_date Invoice Log Update Date
     */
    public void setLog_date(String log_date) {
        this.log_date = log_date.substring(0, log_date.indexOf('.'));
    }

    /**
     * This method returns the id of the log.
     * @return log_id
     */
    public String getLog_id() {
        return log_id;
    }

    /**
     * This method sets the id of the log.
     * @param log_id Invoice Log ID
     */
    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    /**
     * This method returns the description of the log.
     * @return log_desc
     */
    public String getLog_desc() {
        return log_desc;
    }

    /**
     * This method sets the description of the log.
     * @param log_desc Invoice Log Description
     */
    public void setLog_desc(String log_desc) {
        this.log_desc = log_desc;
    }

    /**
     * This method overrides the toString method.
     * @return string
     */
    @Override
    public String toString() {
        return "InvoiceLog{" +
                "log_date='" + log_date + '\'' +
                ", log_id='" + log_id + '\'' +
                ", log_desc='" + log_desc + '\'' +
                '}';
    }
}
