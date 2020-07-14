package hasan.kara.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hasan.kara.web.security.AuthenticationSystem;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		if (!AuthenticationSystem.isLogged()) {
			return "login";
		}
		return "index";
	}
}
