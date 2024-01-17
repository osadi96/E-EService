package dao.custom.impl;

import db.DBConnection;
import dto.OrderDto;
import dao.custom.OrderDetailsDao;
import dao.custom.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {

    OrderDetailsDao orderDetailsDao = new OrderDetailsDaoImpl();

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException {
        Connection connection=null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO orders VALUES(?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getOrderId());
            pstm.setString(2, dto.getCustId());
            if (pstm.executeUpdate() > 0) {
                boolean isDetailSaved = orderDetailsDao.saveOrderDetails(dto.getList());
                if (isDetailSaved) {
                    connection.commit();
                    return true;
                }
            }
        }catch (SQLException | ClassNotFoundException ex){
            connection.rollback();
            ex.printStackTrace();
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public OrderDto lastOrder() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM orders ORDER BY id DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            return new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    null
            );
        }

        return null;
    }
}
