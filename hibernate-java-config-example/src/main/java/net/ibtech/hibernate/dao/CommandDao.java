package net.ibtech.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import net.ibtech.hibernate.model.Command;
import net.ibtech.hibernate.util.HibernateUtil;

public class CommandDao {
	private SessionFactory sessionFactory;
	private Transaction transaction;
	private Session session;

	public CommandDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public Command getCommandByName(String commandName) {
	    try {
	        session = sessionFactory.getCurrentSession();
	        transaction = session.beginTransaction();
	        Query<Command> query = session.createQuery("from Command where commandName=:commandName", Command.class);
	        query.setParameter("commandName", commandName);
	        List<Command> commands = query.getResultList();
	        transaction.commit();
	        return commands.isEmpty() ? null : commands.get(0);
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return null;
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}
}