package dao;

import dao.Util.DaoType;
import dao.custom.impl.CustomerDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){
    }
    public static DaoFactory getInstance(){
        return daoFactory!=null? daoFactory:(daoFactory=new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case CUSTOMER: return(T) new CustomerDaoImpl();
        }
        return null;
    }
}
