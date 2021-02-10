package com.project.angularjava;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.angularjava.service.RegistrationService;

@Service
public class Bootstrap {
	@Autowired
	RegistrationService registrationService;
	//static Logger log = Logger.getLogger(Bootstrap.class);

	@PostConstruct
	public void initialize() {
		try {
			System.out.println("Init");
			//log.info("<-----Init Method Starts----->");
			registrationService.populateDefaultData();
		} catch (Exception e) {
			System.out.println("init err " + e);
			//log.info("Error Occured in init" + e);
		}

	}

}
