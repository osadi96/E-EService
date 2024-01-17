package bo;

import bo.custom.impl.CustomerBoImpl;
import dao.util.Botype;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
    public static BoFactory getInstance(){
        return boFactory!=null? boFactory:(boFactory=new BoFactory());
    }

    public <T extends SuperBo>T getBo(Botype type){
        switch (type){
            case Customer: return (T) new CustomerBoImpl();
        }
        return null;
    }
}