package com.form.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EnsapaycmiApplication {
    @GetMapping("/home")
    public String home() {
    	return "welcome home asmae";
    }
	public static void main(String[] args) {
		SpringApplication.run(EnsapaycmiApplication.class, args);
	}

}
