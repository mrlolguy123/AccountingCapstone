package com.team7.dfa.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents a payment record
 */
public class Payment {
    /**
     *  Define properties for employee payroll data (rohanPayroll table)
     */
    public final StringProperty ID;
    public final StringProperty Name;
    public final StringProperty Job;
    public final StringProperty Salary;
    public final StringProperty HoursWorked;
    public final StringProperty Deductions;
    public final StringProperty NetPay;

/////////////////////////////////////////////////////////////////////
    /**
     * TDefine properties for employee payment status data (rohanPayStatus table)
     */
    public final StringProperty PPEmployeeID;
    public final StringProperty PPName;
    public final StringProperty PPNetPay;
    public final StringProperty PPStatus;
    public final StringProperty PPOwed;
    public final StringProperty PPDueDate;
    public final StringProperty PPLastPayed;
    public final StringProperty PPAmountPayedLast;
    /////////////////////////////////////////////////////////
    /**
     * Define properties for additional payment data (table 5)
     */
    public final StringProperty APPAmountPayed;
    public final StringProperty APPDateTransfer;
    public final StringProperty APPEmployeePayStatus;
    public final StringProperty APPID;
    public final StringProperty APPName;
    public final StringProperty APPPaymentDueDate;
///////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /**
     * Define properties for date payment data
     */
    public final StringProperty LastPayedDate;
    public final StringProperty NextPayedDate;



    /**
     * Constructor to initialize properties
     */
    public Payment()
    {
        /**
         * Initialize properties for employee payroll data
         */
        ID = new SimpleStringProperty(this, "ID");
        Name = new SimpleStringProperty(this, "Name");
        Job = new SimpleStringProperty(this, "Job");
        Salary = new SimpleStringProperty(this, "Salary");
        HoursWorked = new SimpleStringProperty(this, "HoursWorked");
        Deductions = new SimpleStringProperty(this, "Deductions");
        NetPay = new SimpleStringProperty(this, "NetPay");
        //////////////////////////////////////////////////////////////////////
        /**
         * Initialize properties for employee payment status data
         */
        //For table 2,3,4 (rohanPayStatus table)
        PPEmployeeID = new SimpleStringProperty(this, "PPEmployeeID");
        PPName = new SimpleStringProperty(this, "PPName");
        PPNetPay = new SimpleStringProperty(this, "PPNetPay");
        PPStatus = new SimpleStringProperty(this, "PPStatus");
        PPOwed = new SimpleStringProperty(this, "PPOwed");
        PPDueDate = new SimpleStringProperty(this, "PPDueDate");
        PPLastPayed = new SimpleStringProperty(this, "PPLastPayed");
        PPAmountPayedLast = new SimpleStringProperty(this, "PPAmountPayedLast");
        //////////////////////////////////////////////////////////////////////////////
        /**
         * Initialize properties for additional payment data for table5
         */
        //for table 5
        APPAmountPayed = new SimpleStringProperty(this, "APPAmountPayed");
        APPDateTransfer = new SimpleStringProperty(this, "APPDateTransfer");
        APPEmployeePayStatus = new SimpleStringProperty(this, "APPEmployeePayStatus");
        APPID = new SimpleStringProperty(this, "APPID");
        APPName = new SimpleStringProperty(this, "APPName");
        APPPaymentDueDate = new SimpleStringProperty(this, "APPPaymentDueDate");
        //////////////////////////////////////////////////////////////////////////////
        /**
         * Initialize properties for additional payment data for table5
         */
        //for date settings data
        LastPayedDate = new SimpleStringProperty(this, "LastPayedDate");
        NextPayedDate = new SimpleStringProperty(this, "NextPayedDate");


    }

    public StringProperty LastPayedDateProperty() { return LastPayedDate; }
    public String getLastPayedDate() { return LastPayedDate.get(); }
    public void setLastPayedDate(String newDate) { LastPayedDate.set(newDate); }

    public StringProperty NextPayedDateProperty() { return NextPayedDate; }
    public String getNextPayedDate() { return NextPayedDate.get(); }
    public void setNextPayedDate(String newDate) { NextPayedDate.set(newDate); }




    /**
     * Getter and setter methods for employee payroll data properties for table 5
     */
    public StringProperty APPAmountPayedProperty() { return APPAmountPayed; }
    public String getAPPAmountPayed() { return APPAmountPayed.get(); }
    public void setAPPAmountPayed(String newId) { APPAmountPayed.set(newId); }


    public StringProperty APPDateTransferProperty() { return APPDateTransfer; }
    public String getAPPDateTransfer() { return APPDateTransfer.get(); }
    public void setAPPDateTransfer(String newId) { APPDateTransfer.set(newId); }

    public StringProperty APPEmployeePayStatusProperty() { return APPEmployeePayStatus; }
    public String getAPPEmployeePayStatus() { return APPEmployeePayStatus.get(); }
    public void setAPPEmployeePayStatus(String newId) { APPEmployeePayStatus.set(newId); }

    public StringProperty APPIDProperty() { return APPID; }
    public String getAPPID() { return APPID.get(); }
    public void setAPPID(String newId) { APPID.set(newId); }

    public StringProperty APPNameProperty() { return APPName; }
    public String getAPPName() { return APPName.get(); }
    public void setAPPName(String newId) { APPName.set(newId); }


    public StringProperty APPPaymentDueDateProperty() { return APPPaymentDueDate; }
    public String getAPPPaymentDueDate() { return APPPaymentDueDate.get(); }
    public void setAPPPaymentDueDate(String newId) { APPPaymentDueDate.set(newId); }


    ///////////////////////////////////////////////////////////////////////////

    /**
     * Getter and setter methods for employee payroll data properties for table 2, 3, 4 and 5
     */
    public StringProperty PPAmountPayedLastProperty() { return PPAmountPayedLast; }
    public String getPPAmountPayedLast() { return PPAmountPayedLast.get(); }
    public void setPPAmountPayedLast(String newId) { PPAmountPayedLast.set(newId); }

    public StringProperty PPLastPayedProperty() { return PPLastPayed; }
    public String getPPLastPayed() { return PPLastPayed.get(); }
    public void setPPLastPayed(String newId) { PPLastPayed.set(newId); }


    public StringProperty PPDueDateProperty() { return PPDueDate; }
    public String getPPDueDate() { return PPDueDate.get(); }
    public void setPPDueDate(String newId) { PPDueDate.set(newId); }

    public StringProperty PPOwedProperty() { return PPOwed; }
    public String getPPOwed() { return PPOwed.get(); }
    public void setPPOwed(String newId) { PPOwed.set(newId); }

    public StringProperty PPStatusProperty() { return PPStatus; }
    public String getPPStatus() { return PPStatus.get(); }
    public void setPPStatus(String newId) { PPStatus.set(newId); }

    public StringProperty PPNetPayProperty() { return PPNetPay; }
    public String getPPNetPay() { return PPNetPay.get(); }
    public void setPPNetPay(String newId) { PPNetPay.set(newId); }

    public StringProperty PPNameProperty() { return PPName; }
    public String getPPName() { return PPName.get(); }
    public void setPPName(String newId) { PPName.set(newId); }


    public StringProperty PPEmployeeIDProperty() { return PPEmployeeID; }
    public String getPPEmployeeID() { return PPEmployeeID.get(); }
    public void setPPEmployeeID(String newId) { PPEmployeeID.set(newId); }

    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * Getter and setter methods for employee payroll data properties for table 1
     */

    public StringProperty idProperty() { return ID; }
    public String getId() { return ID.get(); }
    public void setId(String newId) { ID.set(newId); }


    public StringProperty nameProperty() { return Name; }
    public String getName() { return Name.get(); }
    public void setName(String newName) { Name.set(newName); }


    public StringProperty JobProperty() { return Job; }
    public String getJob() { return Job.get(); }
    public void setJob(String newJob) { Job.set(newJob); }

    public StringProperty SalaryProperty() { return Salary; }
    public String getSalary() { return Salary.get(); }
    public void setSalary(String newSalary) { Salary.set(newSalary); }

    public StringProperty HoursWorkedProperty() { return HoursWorked; }
    public String getHoursWorked() { return HoursWorked.get(); }
    public void setHoursWorked(String newHoursWorked) { HoursWorked.set(newHoursWorked); }

    public StringProperty DeductionsProperty() { return Deductions; }
    public String getDeductions() { return Deductions.get(); }
    public void setDeductions(String newDeductions) { Deductions.set(newDeductions); }

    public StringProperty NetPayProperty() { return NetPay; }
    public String getNetPay() { return NetPay.get(); }
    public void setNetPay(String newNetPay) { NetPay.set(newNetPay); }

}
