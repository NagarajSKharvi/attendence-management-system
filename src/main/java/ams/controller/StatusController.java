package ams.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class StatusController {
	
	@GetMapping("/status")
	public String getStatus() {
		return "AMS is live, up and running";
	}
	
	@GetMapping("/health")
	public String getHealth() {
		return "AMS is live";
	}
}
