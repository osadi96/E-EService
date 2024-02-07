package bo;

import bo.custom.CustomerBo;
import bo.custom.impl.CustomerBoImpl;
import dao.Util.BoTyppe;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
    public static BoFactory getInstance(){
        return boFactory!=null? boFactory:(boFactory=new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoTyppe type){
        switch (type){
            case CUSTOMER: return (T) new CustomerBoImpl();

        }
        return null;
    }

}
