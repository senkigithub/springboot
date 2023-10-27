package com.example.demo.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Empcontroller {
	@Autowired
	Empservice is1;
	@PostMapping("ins")
	public String like1(@RequestBody Employe_pojo a) {
		
		String i=is1.empinsert(a);
		
		return i;
		
	}
}
