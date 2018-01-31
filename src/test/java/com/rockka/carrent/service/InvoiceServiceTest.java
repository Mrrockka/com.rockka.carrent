package com.rockka.carrent.service;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.dao.InvoiceDao;
import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.InvoiceService;
import com.rockka.carrent.services.UserService;
import com.rockka.carrent.test_categories.BasicTest;
import com.rockka.carrent.test_categories.DetailTest;
import com.rockka.carrent.test_categories.TransactionalTest;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {
				AppConfig.class
		})
public class InvoiceServiceTest extends Assert {
	@Autowired
	private CarDao carDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private UserService userService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private CarService carService;

	private Logger logger = LoggerFactory.getLogger(InvoiceServiceTest.class);

	@Before
	public void startUp() {
	}

	@Test
	@Category(TransactionalTest.class)
	public void testSaveDelete() {
		Car car = new Car()
				.setName("Batmobile v1000")
				.setCountry("Mexico")
				.setColor("Blackest")
				.setPrice(9999)
				.setReleaseDate(new LocalDate(1939, 12, 02));
		carService.save(car);

		User user = new User()
				.setRoles("ROLE_USER")
				.setUsername("Batman")
				.setPassword("bat")
				.setFirstName("Bruce")
				.setSecondName("Wayne")
				.setBirthday(new LocalDate(1930, 03, 01))
				.setAddress("Gotham");
		userService.save(user);

		Invoice invoice = new Invoice()
				.setCar(car)
				.setUser(user)
				.setDescription("Everyday rent")
				.setPrice(1000000000)
				.setStartsAt(new LocalDateTime(1939, 12, 03, 16, 00))
				.setExpiresAt(new LocalDateTime(2100, 12, 03, 16, 00));

		assertNotNull(
				invoiceService.delete(
						invoiceService.save(invoice)
				)
		);
		testGetAll();
		invoiceDao.delete(invoice);
		carDao.delete(car);
		userDao.delete(user);
	}

	@Test
	@Category(BasicTest.class)
	public void testConnection() {
		assertEquals(1, invoiceService.getById(1).getId());
	}

	@Test
	@Category(DetailTest.class)
	public void testGetAll() {
		for (Invoice invoice : invoiceService.getAll()) {
			System.out.println(
					"Invoice id: " + invoice.getId()
							+ "| car id: " + invoice.getCar().getId()
							+ "| user nickname: " + invoice.getUser().getUsername()
							+ "| description: " + invoice.getDescription()
							+ "| price: " + invoice.getPrice()
							+ "| starts at: " + invoice.getStartsAt()
							+ "| expires at: " + invoice.getExpiresAt()
							+ "| status: " + invoice.getInvoiceStatus().toString()
							+ "| orderStatus int: " + invoice.getInvoiceStatus().toInt()
							+ "| orderStatus string: " + invoice.getInvoiceStatus().toString()
			);
		}
	}

	@Test
	@Category(DetailTest.class)
	public void testGetAllWithUser() {
		String username = "somebody";
		for (Invoice invoice : invoiceService.getAllWithUser(username)) {
			System.out.println(
					"Username: " + username
							+ "| invoice id: " + invoice.getId()
							+ "| car id: " + invoice.getCar().getId()
							+ "| invoice user username: " + invoice.getUser().getUsername()
							+ "| description: " + invoice.getDescription()
							+ "| price: " + invoice.getPrice()
							+ "| starts at: " + invoice.getStartsAt()
							+ "| expires at: " + invoice.getExpiresAt()
							+ "| status: " + invoice.getInvoiceStatus().toString()
							+ "| orderStatus int: " + invoice.getInvoiceStatus().toInt()
							+ "| orderStatus string: " + invoice.getInvoiceStatus().toString()
			);
		}

	}

}
