package com.uniovi.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Peticion;
import com.uniovi.entities.User;
import com.uniovi.services.PeticionAmistadService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class UsersControllers {
	
	@Autowired 
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired 
	private SecurityService securityService;
	
	@Autowired
	private PeticionAmistadService peticionService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String setUser(@ModelAttribute @Validated User user, 
			BindingResult result, Model modelo) {
		
		signUpFormValidator.validate(user, result); 
		
		if (result.hasErrors()) { 
			return "signup"; 
		}
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET) 
	public String home(Model model) { 
		return "home"; 
	}
	
	@RequestMapping("/user/list")
	public String getListado(Model model, Pageable pageable,
			@RequestParam(value = "", required=false) String searchText){
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if(searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUserByNameOrEmail(pageable, searchText);
		}
		else
		{
			users = usersService.getUsers(pageable);
		}
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		return "user/list";
	}
	
	@RequestMapping("/friendRequest/list")
	public String getFriendRequests(Model model, Pageable pageable){
		Page<Peticion> fr = new PageImpl<Peticion>(new LinkedList<Peticion>());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userEnvia = usersService.getUserByEmail(auth.getName());
		fr=peticionService.getFriendRequestsByUserEnvia(pageable, auth.getName());
		
		model.addAttribute("friendRequestsList", fr.getContent());
		model.addAttribute("page", fr);
		return "/friendRequest/list";
	}
	
	@RequestMapping(value="/user/{id}/send", method=RequestMethod.GET)
	public String sendFriendRequest(Model model, @PathVariable Long id){
		User userRecibe = usersService.getUser(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userEnvia = usersService.getUserByEmail(auth.getName());
		
		peticionService.addPeticion(new Peticion(userEnvia,userRecibe,false));
		return "redirect:/user/list";
	}

}
