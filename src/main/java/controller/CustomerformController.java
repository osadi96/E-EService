package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import dao.Util.BoTyppe;
import dto.CustomerDto;
import dto.Tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerformController {

    @FXML
    private Button txtId;

    @FXML
    private Button txtName;

    @FXML
    private Button txtEmailAddress;

    @FXML
    private Button txtAddress;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colEmailAddress;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colOrId;

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoTyppe.CUSTOMER);
    private JPasswordField txtDate;

    public void initialize(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailaddress"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOrId.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        loadCustomerTable();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });

    }

    private void setData(CustomerTm newValue) {
        if (newValue != null) {
            txtName.setText(newValue.getName());
            txtEmailAddress.setText(newValue.getEmailaddress());
            txtAddress.setText(newValue.getAddress());
            txtId.setText(newValue.getOrderid());
        }
    }

    private void loadCustomerTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList  = customerBo.allCustomers();
            for (CustomerDto dto:dtoList) {
                CustomerTm c = new CustomerTm(
                        dto.getName(),
                        dto.getEmailaddress(),
                        dto.getAddress(),
                        dto.getOrderid()
                );

                tmList.add(c);
            }
            tblCustomer.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Storeform.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}

