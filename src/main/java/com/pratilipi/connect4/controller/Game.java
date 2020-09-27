package com.pratilipi.connect4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratilipi.connect4.service.GameService;

@RestController
@RequestMapping("/connect4")
public class Game {
	
	@Autowired
	GameService service;
	
	@GetMapping("/start")
	public String startGame() {
		//service.reset();
		
		return service.init();
	}
	
	@GetMapping("/start/{user}/{col}")
	public String play(@PathVariable String user, @PathVariable int col) {
		if(col < 0  || col > 6)
			return "Invalid";
		return service.add(user, col);
	}
}
