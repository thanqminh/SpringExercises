package com.example.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {
	
  @GetMapping("/")
  public String index()
  {
	  if (SecurityContextHolder.getContext().getAuthentication() != null &&
		    SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && 
		    	  !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken))
		  return "redirect:/books";
	  return "redirect:/login";
  }
  
  
}
