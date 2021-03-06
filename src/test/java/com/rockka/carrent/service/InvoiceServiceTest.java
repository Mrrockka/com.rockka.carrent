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
		logger.info("\nInvoiceServiceTest: TEST_GET_ALL\n");
		for (Invoice invoice : invoiceService.getAll()) {
			logger.info(
					"Invoice id: " + invoice.getId()
							+ "| car id: " + invoice.getCar().getId()
							+ "| username: " + invoice.getUser().getUsername()
							+ "| description: " + invoice.getDescription()
							+ "| price: " + invoice.getPrice()
							+ "| starts at: " + invoice.getStartsAt()
							+ "| expires at: " + invoice.getExpiresAt()
							+ "| status: " + invoice.getStatus().toString()
							+ "| status int: " + invoice.getStatus().toInt()
			);
            for(Invoice inv :invoice.getBoundedInvoices()){
                logger.info("Bounded with id:" + inv.getId());
            }
		}
	}

	@Test
	@Category(DetailTest.class)
	public void testGetAllWithUsername() {
		String username = "Zmei";
		logger.info("\nInvoiceServiceTest: TEST_GET_ALL_WITH_USERNAME\n");
		for (Invoice invoice : invoiceService.getAllWithUser(username)) {
			logger.info(
					"| invoice id: " + invoice.getId()
							+ "| car id: " + invoice.getCar().getId()
							+ "| user username: " + invoice.getUser().getUsername()
							+ "| description: " + invoice.getDescription()
							+ "| price: " + invoice.getPrice()
							+ "| starts at: " + invoice.getStartsAt()
							+ "| expires at: " + invoice.getExpiresAt()
							+ "| status: " + invoice.getStatus().toString()
                            + "| status int: " + invoice.getStatus().toInt()
			);
		}
	}

	@Test
	@Category(DetailTest.class)
	public void testGetAllWithCar() {
		long car_id = 1;
		logger.info("\nInvoiceServiceTest: TEST_GET_ALL_WITH_CAR\n");
		for (Invoice invoice : invoiceService.getAllWithCar(car_id)) {
			logger.info(
					"Car id: " + invoice.getCar().getId()
							+ "| invoice id: " + invoice.getId()
							+ "| username: " + invoice.getUser().getUsername()
							+ "| description: " + invoice.getDescription()
							+ "| price: " + invoice.getPrice()
							+ "| starts at: " + invoice.getStartsAt()
							+ "| expires at: " + invoice.getExpiresAt()
							+ "| status: " + invoice.getStatus().toString()
							+ "| status int: " + invoice.getStatus().toInt()
			);
		}
	}

}
