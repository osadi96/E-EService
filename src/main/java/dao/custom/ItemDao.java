package dao.custom;

import dto.ItemDto;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao {
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteItem(ItemDto id) throws SQLException, ClassNotFoundException;

    List<ItemDto> allItems() throws SQLException, ClassNotFoundException;
}
