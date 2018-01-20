package com.rockka.carrent.test_cases;

import com.rockka.carrent.controllers.AdminAccountControllerTest;
import com.rockka.carrent.dao.CarServiceTest;
import com.rockka.carrent.dao.OrderServiceTest;
import com.rockka.carrent.dao.UserServiceTest;
import com.rockka.carrent.test_categories.BasicTest;
import com.rockka.carrent.test_categories.DetailTest;
import com.rockka.carrent.test_categories.TransactionalTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory({DetailTest.class})
@Categories.ExcludeCategory({BasicTest.class, TransactionalTest.class})
@Suite.SuiteClasses({
		CarServiceTest.class
		,OrderServiceTest.class
		,UserServiceTest.class
		,AdminAccountControllerTest.class
})
public class DetailTests {
}
