package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Admin;
import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Category;
import com.cg.osm.entity.Customer;
import com.cg.osm.entity.OrderBill;
import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetItemInput;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.entity.User;
import com.cg.osm.error.AdminNotFoundException;
import com.cg.osm.error.CartNotFoundException;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.model.AdminDTO;
import com.cg.osm.repository.ICategoryRepository;
import com.cg.osm.service.AdminServiceImpl;
import com.cg.osm.service.CartServiceImp;
import com.cg.osm.service.CategoryServiceImpl;
import com.cg.osm.service.CustomerServiceImp;
import com.cg.osm.service.IAdminService;
import com.cg.osm.service.ICartService;
import com.cg.osm.service.ICategoryService;
import com.cg.osm.service.ICustomerService;
import com.cg.osm.service.IProductService;
import com.cg.osm.service.ISweetItemService;
import com.cg.osm.service.IUserService;
import com.cg.osm.service.ProductServiceImpl;
import com.cg.osm.service.SweetItemServiceImp;
import com.cg.osm.service.UserServiceImpl;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AdminServiceImplTest {

	@Autowired
	IAdminService adminService;
	@Autowired
	ICustomerService custService;
	@Autowired 
	IUserService userService;
	@Autowired 
	ISweetItemService sweetItemService;
	@Autowired
	ICategoryService categoryService;
	@Autowired 
	ICartService cartService;
	@Autowired
	IProductService productService;
	
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
	User user1;
	User user2;
	Customer customer;
	SweetItem  sweetItem;
	Category category;
	Cart cart;
	Product product;
	Admin admin1;
	Admin admin2;
	@BeforeAll
	void beforeAll() throws CartNotFoundException {
		user1 = new User();
		user2 = new User();
		customer = new Customer();
		sweetItem = new SweetItem();
		category = new Category();
		cart = new Cart();
		product = new Product();
		user1.setUserId(userService.addUser(user1).getUserId());
		user2.setUserId(userService.addUser(user2).getUserId());
		customer.setUserId(custService.addCustomer(customer).getUserId());
		sweetItem.setOrderItemId(sweetItemService.addSweetItem(sweetItem).getOrderItemId());
		category.setCategoryId(categoryService.addCategory(category).getCategoryId());
		cart.setCartId(cartService.addCart(cart).getCartId());
		product.setProductid(productService.addProduct(product).getProductid());
	}
	
	
	@AfterAll
	void afterAll() throws Exception {
		adminService.cancelAdmin(admin1.getId());
		if (admin2 != null) {
			adminService.cancelAdmin(admin2.getId());
		}
		custService.cancelCustomer(customer.getUserId());
		sweetItemService.cancelSweetItem(sweetItem.getOrderItemId());
		cartService.cancelCart(cart.getCartId());
		userService.cancelUser(user1.getUserId());
		userService.cancelUser(user2.getUserId());
		categoryService.cancelCategory(category.getCategoryId());
		productService.cancelProduct(product.getProductid());
	}
	
	
	@BeforeEach
	void beforeEach() {
		admin2 =  new Admin(1,customer,user1,sweetItem,category,cart,product);
		AdminDTO adminDTO = adminService.addAdmin(admin2);
		admin2.setId(adminDTO.getId());
	}
	
	@AfterEach
	void afterEach() throws AdminNotFoundException {
		if (admin2 != null) {
			adminService.cancelAdmin(admin2.getId());
			admin2 = null;
		}
	}
	
	@Test
	void testAddAdmin() {
		admin1 = new Admin(1,customer,user1,sweetItem,category,cart,product);
		AdminDTO adminDTO = adminService.addAdmin(admin1);
		assertNotNull(adminDTO);
		admin1.setId(adminDTO.getId());
		assertNull(adminService.addAdmin(null));
	}

	@Test
	void testUpdateAdmin() throws AdminNotFoundException {
		admin2.setUser(user2);
		assertNotNull(adminService.updateAdmin(admin2));
		assertNull(adminService.updateAdmin(null));
	}

	@Test
	void testCancelAdmin() throws AdminNotFoundException {
		int id = admin2.getId();
		assertNotNull(adminService.cancelAdmin(id));
		assertThrows(AdminNotFoundException.class, () -> adminService.cancelAdmin(id));
		admin2 = null;
	}

	@Test
	void testShowAllAdmins() {
		int id = admin2.getId();
		List<AdminDTO> adminDTOList = adminService.showAllAdmins();
		assertNotNull(adminDTOList);
		assertTrue(adminDTOList.size() > 0);
		assertEquals(1L,adminDTOList.stream().filter((AdminDTO adminDTO) -> (adminDTO.getId() == id)).count());
		
	}

	@Test
	void testShowAllAdminsInt() {
		int id = admin2.getId();
		List<AdminDTO> adminDTOList = adminService.showAllAdmins(id);
		assertNotNull(adminDTOList);
		assertTrue(adminDTOList.size() > 0);
		assertEquals(1L,adminDTOList.stream().filter((AdminDTO adminDTO) -> (adminDTO.getId() == id)).count());
		
	}

	@Test
	void testValidateId() {
		Admin admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertTrue(AdminServiceImpl.validateId(admin));
		admin = new Admin(-1,customer,user1,sweetItem,category,cart,product);
		assertFalse(AdminServiceImpl.validateId(admin));
		admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertFalse(AdminServiceImpl.validateId(null));
	}

	@Test
	void testValidateCustomer() {
		Admin admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertTrue(AdminServiceImpl.validateCustomer(admin));
		admin = new Admin(1,null,user1,sweetItem,category,cart,product);
		assertFalse(AdminServiceImpl.validateCustomer(admin));
		admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertFalse(AdminServiceImpl.validateCustomer(null));
	}

	@Test
	void testValidateUser() {
		Admin admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertTrue(AdminServiceImpl.validateUser(admin));
		admin = new Admin(1,customer,null,sweetItem,category,cart,product);
		assertFalse(AdminServiceImpl.validateUser(admin));
		admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertFalse(AdminServiceImpl.validateUser(null));
	}

	@Test
	void testValidateSweetItem() {
		Admin admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertTrue(AdminServiceImpl.validateSweetItem(admin));
		admin = new Admin(1,customer,user1,null,category,cart,product);
		assertFalse(AdminServiceImpl.validateSweetItem(admin));
		admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertFalse(AdminServiceImpl.validateSweetItem(null));
	}

	@Test
	void testValidateCategory() {
		Admin admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertTrue(AdminServiceImpl.validateCategory(admin));
		admin = new Admin(1,customer,user1,sweetItem,null,cart,product);
		assertFalse(AdminServiceImpl.validateCategory(admin));
		admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertFalse(AdminServiceImpl.validateCategory(null));
	}

	@Test
	void testValidateCart() {
		Admin admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertTrue(AdminServiceImpl.validateCart(admin));
		admin = new Admin(1,customer,user1,sweetItem,category,null,product);
		assertFalse(AdminServiceImpl.validateCart(admin));
		admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertFalse(AdminServiceImpl.validateCart(null));
	}

	@Test
	void testValidateProduct() {
		Admin admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertTrue(AdminServiceImpl.validateProduct(admin));
		admin = new Admin(1,customer,user1,sweetItem,category,cart,null);
		assertFalse(AdminServiceImpl.validateProduct(admin));
		admin = new Admin(1,customer,user1,sweetItem,category,cart,product);
		assertFalse(AdminServiceImpl.validateProduct(null));
	}

}
