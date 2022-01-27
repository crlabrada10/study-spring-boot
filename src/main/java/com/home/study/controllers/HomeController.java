package com.home.study.controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author crlabrada10
 *
 */
@RestController //Stereotype que es una metaanotación que engloba el @Controller y @ResponseBody
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping
	public String helloWorld() {
		return "Hello world";
	}
}
