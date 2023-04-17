package net.ibtech.hibernate.xbag;

import java.util.List;

import net.ibtech.hibernate.model.Address;
import net.ibtech.hibernate.model.Phone;

public enum XBagKey {
//for customer
FIRSTNAME,
LASTNAME,
EMAIL ,
ADDRESSES,
PHONES,CUSTOMER_ID,
//for phone
TYPE, NUMBER, PHONE_ID,

//for address
STREET,CITY,STATE,ZIP,ADDRESS_ID,
//FOR ACCOUNT 

ACCOUNT_NUMBER,ACCOUNT_TYPE,BALANCE,CURRENCY_CODE, ACCOUNT_ID,CREATE_DATE,UPDATE_DATE
}
