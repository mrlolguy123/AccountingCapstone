package com.team7.dfa.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Payment {
    private final StringProperty ID;
    private final StringProperty Name;
    private final StringProperty Job;
    private final StringProperty Salary;
    private final StringProperty HoursWorked;
    private final StringProperty Deductions;
    private final StringProperty NetPay;
    //private final StringProperty mobile;
    //private final StringProperty  course;

    public Payment()
    {
        ID = new SimpleStringProperty(this, "ID");
        Name = new SimpleStringProperty(this, "Name");
        Job = new SimpleStringProperty(this, "Job");
        Salary = new SimpleStringProperty(this, "Salary");
        HoursWorked = new SimpleStringProperty(this, "HoursWorked");
        Deductions = new SimpleStringProperty(this, "Deductions");
        NetPay = new SimpleStringProperty(this, "NetPay");
    }
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
