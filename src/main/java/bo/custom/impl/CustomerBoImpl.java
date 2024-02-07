package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.Util.DaoType;
import dto.CustomerDto;
import entity.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> entityList = customerDao.getAll();
        List<CustomerDto> list = new ArrayList<>();
        for (Customer customer:entityList) {
            list.add(new CustomerDto(
                    customer.getName(),
                    customer.getEmailaddress(),
                    customer.getAddress(),
                    customer.getOrderId()
            ));
        }
        return list;
    }
}
