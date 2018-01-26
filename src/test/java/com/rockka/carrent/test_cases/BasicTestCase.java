package com.rockka.carrent.test_cases;

import com.rockka.carrent.controllers.AdminAccountControllerTest;
import com.rockka.carrent.service.CarServiceTest;
import com.rockka.carrent.service.InvoiceServiceTest;
import com.rockka.carrent.service.UserServiceTest;
import com.rockka.carrent.test_categories.BasicTest;
import com.rockka.carrent.test_categories.DetailTest;
import com.rockka.carrent.test_categories.TransactionalTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(BasicTest.class)
@Categories.ExcludeCategory({DetailTest.class, TransactionalTest.class})
@Suite.SuiteClasses({
		CarServiceTest.class
		,InvoiceServiceTest.class
		,UserServiceTest.class
		,AdminAccountControllerTest.class
})
public class BasicTestCase {

}
