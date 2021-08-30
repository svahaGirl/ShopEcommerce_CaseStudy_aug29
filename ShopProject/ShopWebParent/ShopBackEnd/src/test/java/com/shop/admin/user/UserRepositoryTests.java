package com.shop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.shop.common.entity.Role;
import com.shop.common.entity.User;

@DataJpaTest // if set showSQL as false in properties, results show as a list
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		//test create new user
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userTiger= new User("tiger@codejava.net", "tiger1234", "Tiger", " Sports");
		userTiger.addRole(roleAdmin);	
		
		User savedUser = repo.save(userTiger);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		
		User userWill = new User("will52@hotmail.com", "will145","Will", "Goote");		
		
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userWill.addRole(roleAssistant);
		userWill.addRole(roleEditor);		
				
		User savedUser = repo.save(userWill);
			
		assertThat(savedUser.getId()).isGreaterThan(0);

	}
	
	
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {
		User userHeather = repo.findById(1).get();
		System.out.println(userHeather);
		assertThat(userHeather).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userHeather = repo.findById(1).get();
		userHeather.setEnabled(true);
		userHeather.setEmail("hea.er@gmail.com");
		
		repo.save(userHeather);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User userMay = repo.findById(2).get();
		Role roleEditor = new Role(3); // must declare here for role 3 before you remove it.
		Role roleSalesperson = new Role(2);
		
		userMay.getRoles().remove(roleEditor);
		userMay.addRole(roleSalesperson);
		
		repo.save(userMay);
		
	}
	
	@Test              // delete user
	public void testDeleteUser() {
		Integer userId=2;
		repo.deleteById(userId);
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "frank@gmail.com"; // this is a valid user email
		//String email = "nana@gmail.com"; //this user does not exist.
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testCountById() {
		Integer id = 14; // Valid user, test passed
//		Integer id = 100; // Invalid user,test failed
		Long countById = repo.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void testDisableUser() {
		Integer id = 1;
		repo.updateEnabledStatus(id, false); //false is disabled
		
	}
	@Test
	public void testEnableUser() {
		Integer id = 1;
		repo.updateEnabledStatus(id, true); // true is enabled
	}
	
	@Test
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 4;
		//import .domain not .web...
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(pageable);
		List<User> listUsers = page.getContent();
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isEqualTo(pageSize);
	}
	
	@Test
	public void testSearchUsers() {
		String keyword = "dave";
		
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(keyword, pageable);
		
		List<User> listUsers = page.getContent();
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isGreaterThan(0);
		
	}
	
	
	
}
