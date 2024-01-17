package dao.custom.impl;

import db.DBConnection;
import dto.CustomerDto;
import dao.custom.CustomerDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES(?,?,?,?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET name=?, emailaddress=?, address=? WHERE id=?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,dto.getName());
        pstm.setString(2,dto.getEmailaddress());
        pstm.setString(3,dto.getAddress());
        pstm.setString(4,dto.getId());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE from customer WHERE id=?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        List<CustomerDto> list = new ArrayList<>();

        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return list;
    }

    @Override
    public CustomerDto searchCustomer(String id) {
        return null;
    }
}



