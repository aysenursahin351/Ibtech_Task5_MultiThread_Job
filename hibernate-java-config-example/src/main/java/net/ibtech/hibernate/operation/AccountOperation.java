package net.ibtech.hibernate.operation;

import net.ibtech.hibernate.dao.AccountDao;
import net.ibtech.hibernate.model.Account;
import net.ibtech.hibernate.xbag.XBag;
import net.ibtech.hibernate.xbag.XBagKey;

public class AccountOperation {
    AccountDao accountDao=new AccountDao();

    public void add(XBag xbag) {
            Account account = new Account();
            account.setAccountNumber((String) xbag.getValue(XBagKey.ACCOUNT_NUMBER));
            account.setAccountType((String) xbag.getValue(XBagKey.ACCOUNT_TYPE));
            account.setBalance((double) xbag.getValue(XBagKey.BALANCE));
           account.setCurrencyCode((String) xbag.getValue(XBagKey.CURRENCY_CODE));
            accountDao.addAccount(account);
        
    }
    
    public void update(XBag xbag) {
            Account account = new Account();
            accountDao.updateAccount(account);
        
    }
    
    public void delete(XBag xbag) {
            Account account = new Account();
            accountDao.deleteAccount(account.getId());
        
    }
}