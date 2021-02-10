package com.project.angularjava.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.angularjava.model.Address;
import com.project.angularjava.model.Role;
import com.project.angularjava.model.User;
import com.project.angularjava.repository.RoleRepository;
import com.project.angularjava.repository.UserRepository;


@Service
public class RegistrationService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
//	@Autowired
//	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	public void populateDefaultData() {
		System.out.println("Reg service called");
		long count = userRepository.count();
		System.out.println(count);
		if (count == 0) {
			Role role = roleRepository.findByRole("ADMIN");
			System.out.println(role);
			if (role == null) {
				role = new Role();
				role.setRole("ADMIN");
				role = roleRepository.save(role);
			}
			System.out.println(role);
			User user = userRepository.findByEmail("admin@gmail.com");
			System.out.println("is role" + role);
			if (user == null) {
				user = new User();
				user.setFullname("ADMINUSER");
				user.setEmail("admin@gmail.com");
				user.setPassword("password");
				user.setPhoneNumber("9487911594");
				Address address = new Address();
				address.setCity("Chennai");
				address.setState("Tamil Nadu");
				address.setCountry("India");
				address.setPostelCode("629001");
				user.setAddress(address);

				user.setEnabled(true);
//				user.setPassword(bCryptPasswordEncoder.encode("password"));
				//user.setActive(true);

				Set<Role> adminRole = new HashSet<>();
				//List<Role> adminRole =new ArrayList<>();
				adminRole.add(role);

				user.setRoles(adminRole);
				customUserDetailsService.saveUser(user);
				//userRepository.save(user);

			}
		} else {
			System.out.println("Already availble");

		}
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
