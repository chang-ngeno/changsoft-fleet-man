package io.changsoft.fleet_management_mis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		return "about";
	}

}
