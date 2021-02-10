package com.project.angularjava.controller;

import static org.springframework.http.ResponseEntity.ok;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.angularjava.config.JwtTokenProvider;
import com.project.angularjava.model.AuthBody;
import com.project.angularjava.model.Role;
import com.project.angularjava.model.User;
import com.project.angularjava.repository.UserRepository;
import com.project.angularjava.service.CustomUserDetailsService;



//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository users;

	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthBody data) {
		System.out.println("INSIDE LOGIN");
		try {
			String username = data.getEmail();
			User name = users.findByEmail(username);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenProvider.createToken(username, this.users.findByEmail(username).getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("email", username);
			model.put("token", token);
			model.put("username", name.getFullname());
			String s=name.getRoles().toString();
			model.put("userrole", s.substring(1, s.length()-1));
			
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid email/password supplied");
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody User user) {
		System.out.println(user.toString());
		System.out.println("comming reg"+user.getAddress().getCity());
//		Set<Role> role = new HashSet<Role>();
//		((Role) role).setRole("ADMIN");
//		user.setRoles(role);
		System.out.println("comming11"+user.getEmail());
		User userExists = userService.findUserByEmail(user.getEmail());
		System.out.println("comming11");
		if (userExists != null) {
			throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
		}
		System.out.println("comming");

			userService.saveUser(user);
			Map<Object, Object> model = new HashMap<>();
			model.put("email", user.getEmail());
			model.put("message", "User Registerd Sucessfully");
			return ok(model);
	}
}