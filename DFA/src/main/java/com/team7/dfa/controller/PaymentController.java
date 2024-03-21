package com.team7.dfa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentController implements Initializable {


    //  @FXML
    //private Button logoutButton;

    //@FXML
    //protected void logoutClicked(ActionEvent event)
    // {
    //     System.exit(0);
    //  }

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
    private TableColumn<Payment, String> SalaeyJobCol;

    @FXML
    private TableColumn<Payment, String> SalaryNameCol;

    @FXML
    private TableColumn<Payment, String> SalarySalaryCol;

    @FXML
    private TableView<Payment> SalaryTable;

    @FXML
    private TextField txtPayName;

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
    private TextField txtHoursWorked;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtJob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    void Add(ActionEvent event) {
        String ID,Name,Job,Salary,HoursWorked,Deductions,NetPay; // stname,mobile,course;
        ID = txtID.getText();
        Name = txtName.getText();
        Job = txtJob.getText();
        Salary = txtSalary.getText();
        HoursWorked = txtHoursWorked.getText();
        Deductions  = "0";
        NetPay = "9000";

        //int SalInt = Integer.parseInt(Salary);
        //int calcTax = SalInt / 100;
        //Deductions = String.valueOf(calcTax*8);
        //NetPay = String.valueOf(SalInt - Integer.parseInt(Deductions));

        try
        {
            pst = con.prepareStatement("insert into payroll(ID,Name,Job,Salary,HoursWorked,Deductions,NetPay)values(?,?,?,?,?,?,?)");
            pst.setString(1, ID);
            pst.setString(2, Name);
            pst.setString(3, Job);
            pst.setString(4, Salary);
            pst.setString(5, HoursWorked);
            pst.setString(6, Deductions);
            pst.setString(7, NetPay);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Registation");

            alert.setHeaderText("Employee Payroll");
            alert.setContentText("Record Addedddd!");
            alert.showAndWait();
            table();

            txtID.setText("");
            txtName.setText("");
            txtJob.setText("");
            txtSalary.setText("");
            txtHoursWorked.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void table()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select ID,Name,Job,Salary,HoursWorked,Deductions,NetPay from payroll");
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
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setRowFactory( tv -> {
            TableRow<Payment> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();

                    //id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtID.setText(table.getItems().get(myIndex).getId());
                    txtName.setText(table.getItems().get(myIndex).getName());
                    txtJob.setText(table.getItems().get(myIndex).getJob());
                    txtSalary.setText(table.getItems().get(myIndex).getSalary());
                    txtHoursWorked.setText(table.getItems().get(myIndex).getHoursWorked());



                }
            });
            return myRow;
        });


    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void table2()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select Name,NetPay from payroll");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Payment st = new Payment();
                    st.setName(rs.getString("Name"));
                    st.setNetPay(rs.getString("NetPay"));
                    payments.add(st);
                }
            }
            PayStatustable.setItems(payments);
            PayNameCol.setCellValueFactory(f -> f.getValue().nameProperty());
            PayNetPayCol.setCellValueFactory(f -> f.getValue().NetPayProperty());
            //PayPayStatusCol.setCellValueFactory("payed");


        }

        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PayStatustable.setRowFactory( tv -> {
            TableRow<Payment> myRow = new TableRow<>();

            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    /////////////NOTHING
                    myIndex =  PayStatustable.getSelectionModel().getSelectedIndex();

                    txtPayName.setText(PayStatustable.getItems().get(myIndex).getName());

                }
            });
            return myRow;
        });


    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void table3()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select Name,Salary,Job from payroll");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Payment st = new Payment();
                    st.setName(rs.getString("Name"));
                    st.setSalary(rs.getString("Salary"));
                    st.setJob(rs.getString("Job"));

                    payments.add(st);
                }
            }
            SalaryTable.setItems(payments);
            SalaryNameCol.setCellValueFactory(f -> f.getValue().nameProperty());
            SalarySalaryCol.setCellValueFactory(f -> f.getValue().SalaryProperty());
            SalaeyJobCol.setCellValueFactory(f -> f.getValue().JobProperty());
            //PayPayStatusCol.setCellValueFactory("payed");


        }

        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PayStatustable.setRowFactory( tv -> {
            TableRow<Payment> myRow = new TableRow<>();

            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    /////////////NOTHING

                }
            });
            return myRow;
        });


    }







    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void Delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();

        //id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        try
        {
            //System.out.println("!!!!!!!!!!"+txtID.getText());
            String tempID = txtID.getText();
            pst = con.prepareStatement("delete from emppayroll where ID ="+ tempID );
            //pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("employee payroll delete");

            alert.setHeaderText("delete");
            alert.setContentText("Deletedd!");
            alert.showAndWait();
            table();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void Update(ActionEvent event) {
        /*
        String ID,Name,Job,Salary,HoursWorked,Deductions,NetPay;

        myIndex = table.getSelectionModel().getSelectedIndex();

        //id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        ID = txtID.getText();
        Name = txtName.getText();
        Job = txtJob.getText();
        Salary = txtSalary.getText();
        HoursWorked = txtHoursWorked.getText();
        Deductions  = "0";
        NetPay = "9000";
        try
        {
            pst = con.prepareStatement("update registation set ID = ?,Name = ? ,Job = ? where id = ? ");
            pst.setString(1, stname);
            pst.setString(2, mobile);
            pst.setString(3, course);
            pst.setInt(4, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Registationn");

            alert.setHeaderText("Student Registation");
            alert.setContentText("Updateddd!");
            alert.showAndWait();
            table();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }

    @FXML
    void logoutClicked(ActionEvent event) {

    }

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    /*
    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/payroll","root","rohan");
        }catch (ClassNotFoundException ex){

        }catch(SQLException ex){
            ex.printStackTrace();

        }
    }
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


    @Override
    public void initialize(URL url, ResourceBundle rb){
        Connect();
        table();
        table2();
        table3();
    }


}
