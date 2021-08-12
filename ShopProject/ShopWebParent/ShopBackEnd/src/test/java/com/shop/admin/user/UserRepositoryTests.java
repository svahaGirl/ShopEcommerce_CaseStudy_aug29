package com.shop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shop.common.entity.Role;
import com.shop.common.entity.User;

@DataJpaTest
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
	
}
