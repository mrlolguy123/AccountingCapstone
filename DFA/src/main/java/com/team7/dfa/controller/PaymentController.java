package com.team7.dfa.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentController extends ParentController implements Initializable {

    /**
     * This class is the controller for handling Payments.
     * It contains the methods that are used to update all payment table views and contains add,update,delete and pay employee from company payroll.
     */

    //@FXML
    //private Button BtnDownload;
    @FXML
    private TableView<Payment> table5;

    @FXML
    private TableColumn<Payment, String> APPAmountPayedCol;

    @FXML
    private TableColumn<Payment, String> APPDateTransferCol;

    @FXML
    private TableColumn<Payment, String> APPEmployeePayStatusCol;

    @FXML
    private TableColumn<Payment, String> APPIDCol;

    @FXML
    private TableColumn<Payment, String> APPNameCol;

    @FXML
    private TableColumn<Payment, String> APPPaymentDueDateCol;

    @FXML
    private TableColumn<Payment, String> PPAmountOwedCol;

    @FXML
    private TableColumn<Payment, String> PPAmountPayedCol;

    @FXML
    private TableColumn<Payment, String> PPDueDateCol;

    @FXML
    private TableColumn<Payment, String> PPIDcol;

    @FXML
    private TableColumn<Payment, String> PPLastPayedCol;

    @FXML
    private TableColumn<Payment, String> PPNameCol;

    @FXML
    private TableColumn<Payment, String> PPStatusCol;

    @FXML
    private TableColumn<Payment, String> Deductionscol;

    @FXML
    private TableColumn<Payment, String> HoursWorkedcol;

    @FXML
    private TableColumn<Payment, String> IDcol;

    @FXML
    private TableColumn<Payment, String> Jobcol;

    @FXML
    private TableColumn<Payment, String> NameCol;

    @FXML
    private TableColumn<Payment, String> NetPaycol;

    @FXML
    private TableColumn<Payment, String> Salarycol;

    @FXML
    private TableColumn<Payment, String> PayNameCol;

    @FXML
    private TableColumn<Payment, String> PayNetPayCol;

    @FXML
    private TableColumn<Payment, String> PayPayStatusCol;

    @FXML
    private TableView<Payment> PayStatustable;

    @FXML
    private TableView<Payment> table3;

    @FXML
    private TableView<Payment> Table4;

    @FXML
    private TableColumn<Payment, String> PrevPayDateCol;

    @FXML
    private TableColumn<Payment, String> PrevPayIDCol;

    @FXML
    private TableColumn<Payment, String> PrevPayNameCol;

    @FXML
    private TableColumn<Payment, String> PrevPayStatusCol;

    @FXML
    private TableColumn<Payment, String> SalaeyJobCol;

    @FXML
    private TableColumn<Payment, String> SalaryNameCol;

    @FXML
    private TableColumn<Payment, String> SalarySalaryCol;

    @FXML
    private TableView<Payment> SalaryTable;

    //@FXML
    //private TextField txtPayName;

    @FXML
    private Button btnPPPay;



    @FXML
    private Button accountingButton;

    @FXML
    private Button bthDelete;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label companyName;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button homeButton;

    @FXML
    private Button invoiceButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button payrollButton;

    @FXML
    private VBox sidebar;

    @FXML
    private TableView<Payment> table;

    @FXML
    private AnchorPane titleBar;

    @FXML
    private Button treasuryButton;

    @FXML
    private TextField txtPPAmountTransfer;

    @FXML
    private TextField txtPPID;

    @FXML
    private TextField txtPPName;

    @FXML
    private TextField txtHoursWorked;

    //@FXML
    //private TextField txtID;

    @FXML
    private TextField txtJob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private Text txtNextPayedDate;

    @FXML
    private Text txtLastPayedDate;


    /**
     * This method is called when the user chicks the add employee button on the payroll overview screen.
     * It populates rohanPayroll table then updates rohanPayrollStatus by calling FillTable3 method and adding a new employee into the company, uses the button click event.
     * @param event
     */
    @FXML
    void Add(ActionEvent event) {

        String Name,Job,Salary,HoursWorked,Deductions,NetPay;
        Name = txtName.getText();
        Job = txtJob.getText();
        Salary = txtSalary.getText();
        HoursWorked = txtHoursWorked.getText();
        Deductions  = "0";
        NetPay = "9000";
        float SalFloat = Float.parseFloat(Salary);;
        float calcTaxSocial = (float) ((SalFloat / 100) * 6.2);
        float calcTax2Medi = (float) ((SalFloat / 100) * 1.45);

        Deductions = String.valueOf(calcTaxSocial + calcTax2Medi );
        NetPay = String.valueOf(SalFloat - calcTaxSocial - calcTax2Medi);
        int employeeID=0;

        try
        {
            pst = con.prepareStatement("insert into rohanPayroll(Name,Job,Salary,HoursWorked,Deductions,NetPay)values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, Name);
            pst.setString(2, Job);
            pst.setString(3, Salary);
            pst.setString(4, HoursWorked);
            pst.setString(5, Deductions);
            pst.setString(6, NetPay);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Added");
            alert.setHeaderText("Employee Added to Payroll");
            alert.setContentText("Record Added!");
            alert.showAndWait();
            table();
            table3();
            table2();
            table4();

            txtName.setText("");
            txtJob.setText("");
            txtSalary.setText("");
            txtHoursWorked.setText("");

            pst.executeQuery();

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS MaxID FROM rohanPayroll");

                if (rs.next()) {
                    int maxID = rs.getInt("MaxID");
                    employeeID = maxID;
                    System.out.println("Largest ID: " + maxID);
                }

                rs.close();
                stmt.close();

            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }

            FillTable3(employeeID, Name, Job, Salary, NetPay);
            FillTableGraphData(employeeID, Name, Job, Salary, NetPay);

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void FillTableGraphData(int employeeID, String Name,String Job,String Salary,String NetPay){
        //System.out.println(Name);
        String ID =Integer.toString(employeeID);
        BigDecimal DecimalSalary = new BigDecimal(Salary);
        BigDecimal DecimalNetPay = new BigDecimal(NetPay);

        try
        {

            pst = con.prepareStatement("INSERT INTO rohanPayrollGraphData (ID,Name,Job,Salary,NetPay)values(?,?,?,?,?)");
            pst.setInt(1, employeeID);
            pst.setString(2, Name);
            pst.setString(3, Job);
            pst.setBigDecimal(4, DecimalSalary);
            pst.setBigDecimal(5, DecimalNetPay);
            pst.executeUpdate();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called when an new employee is added into rohanPayroll table. It also adds the employee into rohanPayStatus to keep track of payment status for employee.
     * It updates all table views for payroll section.
     */
    public void FillTable3(int employeeID, String Name,String Job,String Salary,String NetPay){
        //System.out.println(Name);
        String ID =Integer.toString(employeeID);
        String Status = "Not payed";
        String Owed = NetPay;
        String DueDate = "18/4/2024";
        String LastPayed = "Never";
        String AmountPayedLast = "0";

        try
        {
            // Calculate DueDate by adding two weeks to the current date
            LocalDate currentDate = LocalDate.now();
            LocalDate dueDate = currentDate.plusWeeks(2);

            // Format the date as "dd/MM/yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dueDateString = dueDate.format(formatter);

            String NextPayDate = txtNextPayedDate.getText();
            String LastPayDate = txtLastPayedDate.getText();



            pst = con.prepareStatement("INSERT INTO rohanPayStatus (EmployeeID,Name,NetPay,Status,Owed,DueDate,LastPayed,AmountPayedLast)values(?,?,?,?,?,?,?,?)");
            pst.setString(1, ID);
            pst.setString(2, Name);
            pst.setString(3, NetPay);
            pst.setString(4, Status);
            pst.setString(5, Owed);
            pst.setString(6, NextPayDate);
            pst.setString(7, LastPayed);
            pst.setString(8, AmountPayedLast);
            pst.executeUpdate();

            table3();
            table2();
            table4();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



    /**
     * This method is called whenever there is a add,delete,update or payment functions are used. Used to update rohanPayroll table information into table1 view.
     * It updates table view for table1 in payroll overview page.
     */
    public void table()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select ID,Name,Job,Salary,HoursWorked,Deductions,NetPay from rohanPayroll");

            //pst = con.prepareStatement("select EmployeeID,Name,NetPay,Status,Owed,DueDate,LastPayed,AmountPayedLast from rohanPayStatus");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Payment st = new Payment();
                    st.setId(rs.getString("ID"));
                    st.setName(rs.getString("Name"));
                    st.setJob(rs.getString("Job"));
                    st.setSalary(rs.getString("Salary"));
                    st.setHoursWorked(rs.getString("HoursWorked"));
                    st.setDeductions(rs.getString("Deductions"));
                    st.setNetPay(rs.getString("NetPay"));
                    payments.add(st);
                }
            }
            table.setItems(payments);
            IDcol.setCellValueFactory(f -> f.getValue().idProperty());
            NameCol.setCellValueFactory(f -> f.getValue().nameProperty());
            Jobcol.setCellValueFactory(f -> f.getValue().JobProperty());
            Salarycol.setCellValueFactory(f -> f.getValue().SalaryProperty());
            HoursWorkedcol.setCellValueFactory(f -> f.getValue().HoursWorkedProperty());
            Deductionscol.setCellValueFactory(f -> f.getValue().DeductionsProperty());
            NetPaycol.setCellValueFactory(f -> f.getValue().NetPayProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setRowFactory( tv -> {
            TableRow<Payment> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    //txtID.setText(table.getItems().get(myIndex).getId());
                    txtName.setText(table.getItems().get(myIndex).getName());
                    txtJob.setText(table.getItems().get(myIndex).getJob());
                    txtSalary.setText(table.getItems().get(myIndex).getSalary());
                    txtHoursWorked.setText(table.getItems().get(myIndex).getHoursWorked());



                }
            });
            return myRow;
        });


    }


    /**
     * This method is called whenever there is a add,delete,update or payment functions are used. Used to update rohanPayStatus table information into table3 view.
     * It updates table view for table3 in payroll pay employees page.
     */
    public void table3()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select EmployeeID,Name,NetPay,Status,Owed,DueDate,LastPayed,AmountPayedLast from rohanPayStatus");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Payment st = new Payment();
                    st.setPPEmployeeID(rs.getString("EmployeeID"));
                    st.setPPName(rs.getString("Name"));
                    st.setPPOwed(rs.getString("Owed"));
                    st.setPPNetPay(rs.getString("NetPay"));
                    st.setPPDueDate(rs.getString("DueDate"));
                    st.setPPLastPayed(rs.getString("LastPayed"));
                    st.setPPAmountPayedLast(rs.getString("AmountPayedLast"));
                    st.setPPStatus(rs.getString("Status"));
                    payments.add(st);
                }
            }
            table3.setItems(payments);
            PPIDcol.setCellValueFactory(f -> f.getValue().PPEmployeeIDProperty());
            PPNameCol.setCellValueFactory(f -> f.getValue().PPNameProperty());
            PPAmountOwedCol.setCellValueFactory(f -> f.getValue().PPOwedProperty());
            PPDueDateCol.setCellValueFactory(f -> f.getValue().PPDueDateProperty());
            PPLastPayedCol.setCellValueFactory(f -> f.getValue().PPLastPayedProperty());
            PPAmountPayedCol.setCellValueFactory(f -> f.getValue().PPAmountPayedLastProperty());
            PPStatusCol.setCellValueFactory(f -> f.getValue().PPStatusProperty());

        }

        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table3.setRowFactory( tv -> {
            TableRow<Payment> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    System.out.println("Clicked");
                    myIndex =  table3.getSelectionModel().getSelectedIndex();
                    txtPPID.setText(table3.getItems().get(myIndex).getPPEmployeeID());
                    System.out.println(table3.getItems().get(myIndex).getPPEmployeeID());
                    txtPPName.setText(table3.getItems().get(myIndex).getPPName());
                    txtPPAmountTransfer.setText(table3.getItems().get(myIndex).getPPOwed());


                }
            });
            return myRow;
        });

    }

    /**
     * This method is called whenever there is a add,delete,update or payment functions are used. Used to update rohanPayStatus table information into table2 view.
     * It updates table view for table2 in Unpayed employees page.
     */
    public void table2()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select Name,Owed, Status from rohanPayStatus where Status = 'Not Payed'");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Payment st = new Payment();
                    st.setPPName(rs.getString("Name"));
                    st.setPPOwed(rs.getString("Owed"));
                    st.setPPStatus(rs.getString("Status"));
                    payments.add(st);
                }
            }
            PayStatustable.setItems(payments);
            PayNameCol.setCellValueFactory(f -> f.getValue().PPNameProperty());
            PayNetPayCol.setCellValueFactory(f -> f.getValue().PPOwedProperty());
            PayPayStatusCol.setCellValueFactory(f -> f.getValue().PPStatusProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }




    }



    /**
     * This method is called whenever there is a add,delete,update or payment functions are used. Used to update rohanPayStatus table information into table4 view.
     * It updates table view for table4 in Payed employees page.
     */
    public void table4()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select EmployeeID,Name,LastPayed, Status from rohanPayStatus where Status = 'Payed'");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Payment st = new Payment();
                    st.setPPEmployeeID(rs.getString("EmployeeID"));
                    st.setPPName(rs.getString("Name"));
                    st.setPPOwed(rs.getString("LastPayed"));
                    st.setPPStatus(rs.getString("Status"));
                    payments.add(st);
                }
            }
            Table4.setItems(payments);
            PrevPayIDCol.setCellValueFactory(f -> f.getValue().PPEmployeeIDProperty());
            PrevPayNameCol.setCellValueFactory(f -> f.getValue().PPNameProperty());
            PrevPayDateCol.setCellValueFactory(f -> f.getValue().PPOwedProperty());
            PrevPayStatusCol.setCellValueFactory(f -> f.getValue().PPStatusProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    /**
     * This method is called whenever there is a add,delete,update or payment functions are used. Used to update rohanPayStatus table information into table5 view.
     * It updates table view for table5 in All previous payments page.
     */
    public void table5()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select EmployeeID,Name,AmountPayed, DateTransfer,PaymentDueDate,PayStatus from rohanPreviousPayments ");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Payment st = new Payment();
                    st.setAPPID(rs.getString("EmployeeID"));
                    st.setAPPName(rs.getString("Name"));
                    st.setAPPAmountPayed(rs.getString("AmountPayed"));
                    st.setAPPDateTransfer(rs.getString("DateTransfer"));
                    st.setAPPPaymentDueDate(rs.getString("PaymentDueDate"));
                    st.setAPPEmployeePayStatus(rs.getString("PayStatus"));
                    payments.add(st);
                }
            }
            table5.setItems(payments);
            APPIDCol.setCellValueFactory(f -> f.getValue().APPIDProperty());
            APPNameCol.setCellValueFactory(f -> f.getValue().APPNameProperty());
            APPAmountPayedCol.setCellValueFactory(f -> f.getValue().APPAmountPayedProperty());
            APPDateTransferCol.setCellValueFactory(f -> f.getValue().APPDateTransferProperty());
            APPPaymentDueDateCol.setCellValueFactory(f -> f.getValue().APPPaymentDueDateProperty());
            APPEmployeePayStatusCol.setCellValueFactory(f -> f.getValue().APPEmployeePayStatusProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called when the user clicks the delete employee button on the payroll overview screen.
     * It deletes the spefic employee using id from rohanPayroll and rohanPayStatus tables then updates all table views for payroll screens, uses the button click event.
     * @param event
     */
    @FXML
    void Delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        try
        {
            //System.out.println("!!!!!!!!!!"+txtID.getText());
            //String tempID = txtID.getText();
            pst = con.prepareStatement("delete from rohanPayroll where id = ?" );
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Payroll Delete");

            alert.setHeaderText("Employees List Updated");
            alert.setContentText("Deleted Employee!");
            alert.showAndWait();
            table();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }


        // id = Integer.parseInt(String.valueOf(table3.getItems().get(myIndex).getPPEmployeeID()));
        System.out.println("ID is =  " + id);
        try
        {
            //System.out.println("!!!!!!!!!!"+txtID.getText());
            //String tempID = txtID.getText();
            pst = con.prepareStatement("delete from rohanPayStatus where EmployeeID = ?" );
            pst.setInt(1, id);
            pst.executeUpdate();

            table3();
            table2();
            table4();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("didnt delete from rohanPayStatus");
        }

        try
        {
            //System.out.println("!!!!!!!!!!"+txtID.getText());
            //String tempID = txtID.getText();
            pst = con.prepareStatement("delete from rohanPayrollGraphData where ID = ?" );
            pst.setInt(1, id);
            pst.executeUpdate();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("didnt delete from rohanPayStatus");
        }


    }


    /**
     * This method is called when the user clicks the update employee button on the payroll overview screen.
     * It updates rohanPayroll table then updates rohanPayroll by taking user input as arguments for updated info, uses the button click event.
     * @param event
     */
    @FXML
    void Update(ActionEvent event) {

        String Name,Job,Salary,HoursWorked,Deductions,NetPay ,Status,Owed;

        myIndex = table.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        //ID = txtID.getText();
        Name = txtName.getText();
        Job = txtJob.getText();
        Salary = txtSalary.getText();
        HoursWorked = txtHoursWorked.getText();
        Deductions  = "0";
        NetPay = "9000";

        float SalFloat = Float.parseFloat(Salary);;
        float calcTax = (SalFloat / 100) * 8;
        Deductions = String.valueOf(calcTax);
        NetPay = String.valueOf(SalFloat - calcTax);

        Status = "Not Payed";
        Owed = NetPay;


        try
        {
            pst = con.prepareStatement("update rohanPayroll set Name = ? ,Job = ?, Salary = ? , Deductions = ? , NetPay = ? where id = ? ");
            pst.setString(1, Name);
            pst.setString(2, Job);
            pst.setString(3, Salary);
            pst.setString(4, Deductions);
            pst.setString(5, NetPay);
            pst.setInt(6, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Update");

            alert.setHeaderText("Employee Updated");
            alert.setContentText("Updated Employee!");
            alert.showAndWait();
            table();
            table3();
            table2();
            table4();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ADD FUNCTION TO MAKE SURE EMPLOYEE IS FULLY PAYED FOR LAST PAYMENT
        System.out.println("ID FOR PAYSTATUS: "+id);

        try
        {
            pst = con.prepareStatement("update rohanPayStatus set Name = ? ,NetPay = ?, Status = ? , Owed = ?  where EmployeeID = ? ");
            pst.setString(1, Name);
            pst.setString(2, NetPay);
            pst.setString(3, Status);
            pst.setString(4, Owed);
            pst.setInt(5, id);
            pst.executeUpdate();

            table();
            table3();
            table2();
            table4();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("didnt Update from rohanPayStatus");
        }

        try
        {
            BigDecimal DecimalSalary = new BigDecimal(Salary);
            BigDecimal DecimalNetPay = new BigDecimal(NetPay);


            pst = con.prepareStatement("update rohanPayrollGraphData set Name = ? ,Job = ?, Salary = ? , NetPay = ?  where ID = ? ");
            pst.setString(1, Name);
            pst.setString(2, Job);
            pst.setBigDecimal(3, DecimalSalary);
            pst.setBigDecimal(4, DecimalNetPay);
            pst.setInt(5, id);
            pst.executeUpdate();


        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("didnt Update from rohanPayGraphData");
        }

    }


    /**
     * This method is called when the user clicks the pay employee button on the pay employee screen.
     * It updates rohanPayStatus table then updates rohanPreviousPayments by taking user input as arguments for updated payment info and handles changing employee pay status, uses the button click event.
     * @param event
     */
    @FXML
    void PayAmount(ActionEvent event) {
        String ID, Name, Owe, Status, AmountTransfer,LastPayDate, TransferedAmount, Duedate;

        myIndex = table3.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table3.getItems().get(myIndex).getPPEmployeeID()));
        ID = String.valueOf(table3.getItems().get(myIndex).getPPEmployeeID());
        Name = String.valueOf(table3.getItems().get(myIndex).getPPName());
        Owe = String.valueOf(table3.getItems().get(myIndex).getPPOwed());

        Duedate = String.valueOf(table3.getItems().get(myIndex).getPPDueDate());

        Status = "Not Payed";
        AmountTransfer = txtPPAmountTransfer.getText();
        TransferedAmount = AmountTransfer;
        Float NewOwe = Float.parseFloat(Owe) - Float.parseFloat(AmountTransfer);
        String NNewOwe = Float.toString(NewOwe);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);
        LastPayDate = formattedDate;

        if(NewOwe <= 0 ){
            Status = "Payed";
            NewOwe = 0.00F;
            NNewOwe= "0";
        }

        try
        {
            pst = con.prepareStatement("update rohanPayStatus set Owed = ? ,LastPayed = ?, AmountPayedLast = ? , Status = ? where EmployeeID = ? ");
            pst.setString(1, NNewOwe);
            pst.setString(2, LastPayDate);
            pst.setString(3, AmountTransfer);
            pst.setString(4, Status);
            pst.setInt(5, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Payroll");

            alert.setHeaderText("Employee Paid");
            alert.setContentText("Money Transferred!");
            alert.showAndWait();
            table3();

            table4();
            table2();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }


        try
        {
            pst = con.prepareStatement("INSERT INTO rohanPreviousPayments (EmployeeID,Name,AmountPayed,DateTransfer,PaymentDueDate,PayStatus)values(?,?,?,?,?,?)");
            pst.setString(1, ID);
            pst.setString(2, Name);
            pst.setString(3, TransferedAmount);
            pst.setString(4, LastPayDate);
            pst.setString(5, Duedate);
            pst.setString(6, Status);
            pst.executeUpdate();

            table5();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

/*
    @FXML
    void DownloadTable5(ActionEvent event) {

        System.out.println("Downloading data");

    }

 */


    public void DateUpdate()
    {

        try {
            pst = con.prepareStatement("select PrevDueDate,NextDueDate from rohanPayrollDateSettings ");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Payment st = new Payment();
                    st.setLastPayedDate(rs.getString("PrevDueDate"));
                    st.setNextPayedDate(rs.getString("NextDueDate"));
                    String PrevDue=rs.getString("PrevDueDate");
                    String NextDue = rs.getString("NextDueDate");
                    txtLastPayedDate.setText(PrevDue);
                    txtNextPayedDate.setText(NextDue);

                }
            }
            System.out.println("After:" +txtLastPayedDate.getText());
            System.out.println("After:" +txtNextPayedDate.getText());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }



    }


    public void CheckDate()
    {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String CurrDate = currentDate.format(formatter);

        LocalDate dueDate = currentDate.plusWeeks(2);
        String dueDateString = dueDate.format(formatter);


        String LastPayedDate = txtLastPayedDate.getText();
        String NextPayedDate = txtNextPayedDate.getText();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        LastPayedDate = addLeadingZero(LastPayedDate);
        NextPayedDate = addLeadingZero(NextPayedDate);
        CurrDate = addLeadingZero(CurrDate);

        System.out.println("Last: " + LastPayedDate);
        System.out.println("Next: " + NextPayedDate);
        System.out.println("Curr: " + CurrDate);
        try
        {


            java.util.Date date1 =  dateFormat.parse(LastPayedDate);
            java.util.Date date2 =  dateFormat.parse(NextPayedDate);
            java.util.Date currDate1 = dateFormat.parse(CurrDate);


            // Compare dates
            if (currDate1.compareTo(date2) > 0)
            {
                System.out.println(currDate1 + " is after " + date2);

                try
                {

                    pst = con.prepareStatement("UPDATE rohanPayrollDateSettings SET PrevDueDate = ?, NextDueDate = ? ");
                    pst.setString(1, LastPayedDate);
                    pst.setString(2, dueDateString);
                    pst.executeUpdate();

                    txtLastPayedDate.setText(LastPayedDate);
                    txtNextPayedDate.setText(dueDateString);




                    pst = con.prepareStatement("update rohanPayStatus set DueDate = ?, Status = ? where Status = ? or Status = ?");
                    pst.setString(1, dueDateString);
                    pst.setString(2, "Not Payed");
                    pst.setString(3, "Payed");
                    pst.setString(4, "Not Payed");
                    pst.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("New Payment Period Started");
                    alert.setHeaderText("New Payment Period has started");
                    alert.setContentText("A new 2 week window for new payroll has started. We have automatically payed all unpaid employees");
                    alert.showAndWait();


                    pst = con.prepareStatement("UPDATE rohanPayStatus SET Owed = NetPay WHERE Status = 'Not payed'");
                    pst.executeUpdate();
                    table3();
                    table4();
                    table2();

                }
                catch (SQLException ex)
                {
                    Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }



        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    private static String addLeadingZero(String dateString) {
        String[] parts = dateString.split("/");
        if (parts[0].length() == 1) {
            parts[0] = "0" + parts[0];
        }
        return String.join("/", parts);
    }


    //@FXML
    //void logoutClicked(ActionEvent event) {

    //}

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;


    /**
     * This method is called to connect to database
     */

    public void Connect(){
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            String connString = "jdbc:sqlserver://dfaserver.database.windows.net:1433;database=DFADatabase;user=capstoneAdmin@dfaserver;password=Group7@Capstone;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
            con = DriverManager.getConnection(connString);
        } catch(SQLException ex){
            ex.printStackTrace();

        }
    }

    /**
     * This method is called to initilize all table views for screens and connect to database when opening payroll section.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        Connect();
        table();
        DateUpdate();
        table3();
        table2();
        table4();
        table5();
        CheckDate();


    }


}
