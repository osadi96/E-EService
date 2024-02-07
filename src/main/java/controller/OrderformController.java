package controller;

import bo.custom.CustomerBo;
import bo.custom.OrderBo;
import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDetailsDto;
import dto.OrderDto;
import dto.Tm.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderformController {
    @FXML
    private Label lblOrderId;

    @FXML
    private TextField txtCustName;

    @FXML
    private TextField txtCustEmail;

    @FXML
    private TextField txtCustAddress;

    @FXML
    private TextField txtCustNumber;

    @FXML
    private JFXComboBox<?> cmbItemCode;

    @FXML
    private TextField txtDesc;

    @FXML
    private Label lblDate;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXComboBox<?> cmbItemCata;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Label lblTotal;

    @FXML
    private JFXTreeTableView<OrderTm> tblOrder;

    @FXML
    private TreeTableColumn colCode;

    @FXML
    private TreeTableColumn colDesc;

    @FXML
    private TreeTableColumn colQty;

    @FXML
    private TreeTableColumn colAmount;

    @FXML
    private TreeTableColumn colOption;

    private CustomerBo customerBo = new CustomerBoImpl();
    private OrderBo orderBo= (OrderBo) new OrderBoImpl();
    private ItemDao itemDao = new ItemDaoImpl();
    private List<CustomerDto> customers;
    private List<ItemDto> items;
    private double total=0;

    private ObservableList<OrderTm> tmList = FXCollections.observableArrayList();


    public void initialize(){
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new TreeItemPropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colAmount.setCellValueFactory(new TreeItemPropertyValueFactory<>("amount"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

        try {
            customers = customerBo.allCustomers();
            items = itemDao.allItems();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadItemCodes();
        loadItemCategory();

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (ItemDto dto:items) {
                if (dto.getCode().equals(newValue.toString())){
                    txtDesc.setText(dto.getDesc());
                    txtUnitPrice.setText(String.format("%.2f",dto.getUnitPrice()));
                }
            }
        });

        cmbItemCata.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (ItemDto dto:items) {
                if (dto.getCategory().equals(newValue.toString())){
                    txtDesc.setText(dto.getDesc());
                    txtUnitPrice.setText(String.format("%.2f",dto.getUnitPrice()));
                }
            }
        });

        setOrderId();
    }

    private void loadItemCategory() {
        ObservableList list = FXCollections.observableArrayList();
        for (ItemDto dto:items) {
            list.add(dto.getCategory());
        }
        cmbItemCata.setItems(list);
    }

    private void setOrderId() {
        try {
            lblOrderId.setText(orderBo.generateId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        ObservableList list = FXCollections.observableArrayList();
        for (ItemDto dto:items) {
            list.add(dto.getCode());
        }
        cmbItemCode.setItems(list);
    }

    @FXML
    void addToCartButtonOnAction(ActionEvent event) {
        JFXButton btn = new JFXButton("Delete");

        OrderTm tm = new OrderTm(
                cmbItemCode.getValue().toString(),
                txtDesc.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtUnitPrice.getText())*Integer.parseInt(txtQty.getText()),
                btn
        );
        btn.setOnAction(actionEvent -> {
            tmList.remove(tm);
            total-=tm.getAmount();
            lblTotal.setText(String.format("%.2f",total));
            tblOrder.refresh();
        });

        boolean isExist = false;
        for (OrderTm order:tmList) {
            if (order.getCode().equals(tm.getCode())){
                order.setQty(order.getQty()+tm.getQty());
                order.setAmount(order.getAmount()+tm.getAmount());
                isExist = true;
                total+= tm.getAmount();
            }
        }
        if (!isExist){
            tmList.add(tm);
            total+=tm.getAmount();
        }

        lblTotal.setText(String.format("%.2f",total));

        TreeItem treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
        tblOrder.setRoot(treeItem);
        tblOrder.setShowRoot(false);
    }

    @FXML
    void placeOrderButtonOnAction(ActionEvent event) {
//        List<OrderDetailsDto> list = new ArrayList<>();
//        for (OrderTm tm:tmList) {
//            list.add(new OrderDetailsDto(
//                    lblOrderId.getText(),
//                    tm.getCode(),
//                    tm.getQty(),
//                    tm.getAmount()/tm.getQty()
//            ));
//        }

//        OrderDto dto = new OrderDto(
//                lblOrderId.getText(),
//                LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")),
//                cmbItemCode,
//                list
//        );


//        try {
//            OrderDto dto;
//            boolean isSaved = orderBo.saveOrder(dto);
//            if (isSaved){
//                new Alert(Alert.AlertType.INFORMATION, "Order Saved!").show();
//                setOrderId();
//            }else{
//                new Alert(Alert.AlertType.ERROR, "Order Not Saved!").show();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
    }

    public void savecustomerOnAction(ActionEvent event) {

    }

    @FXML
    void backbuttonOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
