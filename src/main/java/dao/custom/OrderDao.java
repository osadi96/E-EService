package dao.custom;

import dto.OrderDto;
import java.sql.SQLException;

public interface OrderDao {
    boolean save(OrderDto dto);
    OrderDto getLastOrder() throws SQLException, ClassNotFoundException;
}
