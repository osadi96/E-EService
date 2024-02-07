package dao.custom.impl;

import db.DBConnection;
import dto.ItemDto;
import dao.custom.ItemDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(ItemDto id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<ItemDto> list = new ArrayList<>();
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM item");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)
            ));
        }
        return list;
    }

}
