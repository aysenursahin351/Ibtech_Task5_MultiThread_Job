package net.ibtech.hibernate.command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.ibtech.hibernate.dao.CommandDao;
import net.ibtech.hibernate.model.Command;
import net.ibtech.hibernate.model.Customer;
import net.ibtech.hibernate.operation.CustomerOperation;
import net.ibtech.hibernate.xbag.XBag;
import net.ibtech.hibernate.xbag.XBagKey;

public class CommandExecutor {
	public void executeCommand(String commandName, Object object) throws Exception {
	    try {
	        CommandDao commandDao = new CommandDao();
	        Command command = commandDao.getCommandByName(commandName);
	        if (command == null) {
	            throw new Exception("Command not found");
	        }

	        Class<?> c = Class.forName("net.ibtech.hibernate.operation." + command.getClassName());
	        System.out.println(command.getClassName());

	        Object object1 = c.newInstance();
	        Method method = c.getDeclaredMethod(command.getMethodName(), Object.class); // Tek parametreli method seçildi
	        System.out.println(command.getMethodName());

	        method.invoke(object1, object); // Tek parametreli method çağrıldı

	        System.out.println("Command executed successfully");
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	
	
	public void executeBag(String commandName, XBag xBag) throws Exception {
	    try {
	        CommandDao commandDao = new CommandDao();
	        Command command = commandDao.getCommandByName(commandName);
	        if (command == null) {
	            throw new Exception("Command not found");
	        }

	        Class<?> c = Class.forName("net.ibtech.hibernate.operation." + command.getClassName());
	        System.out.println(command.getClassName());

	        Object object1 = c.newInstance();
	        Method method = c.getDeclaredMethod(command.getMethodName(), XBag.class);
	        System.out.println(command.getMethodName());

	        method.invoke(object1, xBag);

	        System.out.println("Command executed successfully");
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}




}
