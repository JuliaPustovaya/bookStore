import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Assert;

import entities.User;

public class Main {
	
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
		srBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setName("Peter");
		session.save(user);
		tx.commit();

		@SuppressWarnings("uncheked")
		List<User> list = (List<User>) session.createQuery("from User").list();
		if (list.size() > 1) {
			Assert.fail("Message configuration in error; table should contain only one."
					+ " Set ddl to create-drop.");
		}
		if (list.size() == 0) {
			Assert.fail("Read of initial message failed; check saveMessage() for errors."
					+ " How did this test run?");
		}
		for (User m : list) {
			System.out.println(m.getName());
		}
		session.close();
	}
}
