package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import db.DBConnection;
import dto.ItemDto;
import dto.Tm.ItemTm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.function.Predicate;

public class ItemformController {
    @FXML
    private BorderPane pane;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtcategory;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTreeTableView <ItemTm> tblItem;

    @FXML
    private TreeTableColumn colCode;

    @FXML
    private TreeTableColumn colCategory;

    @FXML
    private TreeTableColumn colDesc;

    @FXML
    private TreeTableColumn colUnitPrice;

    @FXML
    private TreeTableColumn colQty;

    @FXML
    private TreeTableColumn colOption;

    private ItemDao itemDao = new ItemDaoImpl();

    public void initialize(){
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        colCategory.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        colDesc.setCellValueFactory(new TreeItemPropertyValueFactory<>("desc"));
        colUnitPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        loadItemTable();

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                tblItem.setPredicate(new Predicate<TreeItem<ItemTm>>() {
                    @Override
                    public boolean test(TreeItem<ItemTm> treeItem) {
                        return treeItem.getValue().getCode().toLowerCase().contains(newValue.toLowerCase()) ||
                                treeItem.getValue().getDesc().toLowerCase().contains(newValue.toLowerCase());

                    }
                });
            }
        });
    }

    public void saveButtonOnAction(ActionEvent event) {
        ItemDto dto = new ItemDto(txtCode.getText(),
                txtcategory.getText(),
                txtDesc.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );

        String sql = "INSERT INTO item VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1,dto.getCode());
            pstm.setString(2,dto.getCategory());
            pstm.setString(3,dto.getDesc());
            pstm.setDouble(4,dto.getUnitPrice());
            pstm.setInt(5,dto.getQty());
            int result = pstm.executeUpdate();
            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
                loadItemTable();
                clearFields();
            }

        } catch (SQLIntegrityConstraintViolationException ex){
            new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadItemTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM item";

        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            ResultSet result = stm.executeQuery(sql);

            while (result.next()){
                JFXButton btn = new JFXButton("Delete");
                ItemTm tm = new ItemTm(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getInt(5),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteItem(tm.getCode());
                });

                tmList.add(tm);
            }

            TreeItem<ItemTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblItem.setRoot(treeItem);
            tblItem.setShowRoot(false);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteItem(String code) {
        String sql = "DELETE from item WHERE code=?";

        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1,code);
            int result = pstm.executeUpdate();
            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted!").show();
                loadItemTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tblItem.refresh();
        txtcategory.clear();
        txtDesc.clear();
        txtUnitPrice.clear();
        txtQty.clear();
        txtCode.setEditable(true);
    }

    public void backButtonOnAction(javafx.event.ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dashboardform.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
