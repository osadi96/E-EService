package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBo  {
    List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException;
}
