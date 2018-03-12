package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@PostConstruct
	public void init() {
		User user1 = new User("pedro@uniovi.es", "Pedro", "123456");
		user1.setPassword("123456");
		User user2 = new User("lucas@uniovi.es", "Lucas", "123456");
		user2.setPassword("123456");
		User user3 = new User("maria@uniovi.es", "María", "123456");
		user3.setPassword("123456");
		User user4 = new User("gonzalo@uniovi.es", "Gonzalo", "123456");
		user4.setPassword("123456");
		User user5 = new User("pelayo@uniovi.es", "Pelayo", "123456");
		user5.setPassword("123456");
		User user6 = new User("edward@uniovi.es", "Edward", "123456");
		user6.setPassword("123456");
		
		User user7 = new User("pablo@uniovi.es", "Pablo", "123456");
		user1.setPassword("123456");
		User user8 = new User("adrian@uniovi.es", "Adrian", "123456");
		user2.setPassword("123456");
		User user9 = new User("javier@uniovi.es", "Javer", "123456");
		user3.setPassword("123456");
		User user10 = new User("ricky@uniovi.es", "Ricky", "123456");
		user4.setPassword("123456");
		User user11 = new User("martin@uniovi.es", "Martin", "123456");
		user5.setPassword("123456");
		User user12 = new User("damian@uniovi.es", "Damian", "123456");
		user6.setPassword("123456");
		
		User user13 = new User("Hernán@uniovi.es", "Hernán Pérez", "123456");
		user1.setPassword("123456");
		User user14 = new User("Acebal@uniovi.es", "Pablo Acebal", "123456");
		user2.setPassword("123456");
		User user15 = new User("Calvillo@uniovi.es", "Nacho", "123456");
		user3.setPassword("123456");
		User user16 = new User("Cesar@uniovi.es", "César", "123456");
		user4.setPassword("123456");
		User user17 = new User("Jorge@uniovi.es", "Jorge", "123456");
		user5.setPassword("123456");
		User user18 = new User("Fran@uniovi.es", "Francisco", "123456");
		user6.setPassword("123456");
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		usersService.addUser(user9);
		usersService.addUser(user10);
		usersService.addUser(user11);
		usersService.addUser(user12);
		usersService.addUser(user13);
		usersService.addUser(user14);
		usersService.addUser(user15);
		usersService.addUser(user16);
		usersService.addUser(user17);
		usersService.addUser(user18);
	}

}
