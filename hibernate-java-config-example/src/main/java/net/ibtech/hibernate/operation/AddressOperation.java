package net.ibtech.hibernate.operation;
import java.util.*;

import net.ibtech.hibernate.dao.AddressDao;
import net.ibtech.hibernate.model.Address;
import net.ibtech.hibernate.xbag.XBag;
import net.ibtech.hibernate.xbag.XBagKey;
public class AddressOperation {
    AddressDao addressDao = new AddressDao();    
    public void add(XBag xbag) {
    	
            Address address = new Address();
            address.setStreet((String) xbag.getValue(XBagKey.STREET));
            address.setCity((String) xbag.getValue(XBagKey.CITY));
            address.setState((String) xbag.getValue(XBagKey.STATE));
            address.setZip((String) xbag.getValue(XBagKey.ZIP));
            addressDao.saveAddressAndGetId(address);
            
        
    }
    
    public void update(XBag xbag) {
        Address address = new Address();
        address.setStreet((String) xbag.getValue(XBagKey.STREET));
        address.setCity((String) xbag.getValue(XBagKey.CITY));
        address.setState((String) xbag.getValue(XBagKey.STATE));
        address.setZip((String) xbag.getValue(XBagKey.ZIP));
         addressDao.updateAddress(address);
        
    }
    
    public void delete(XBag xbag) {
        int addressId = (int) xbag.getValue(XBagKey.ADDRESS_ID);
        addressDao.deleteAddress(addressId);
    }
}

