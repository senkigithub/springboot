package com.example.demo.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Project_details_controller {
@Autowired
Project_details_service pds;
@Autowired
Project_emp ok;
@PostMapping("prj")
public String prj(@RequestBody Project_Deatails_Pojo pj)	{

	String i=pds.prjinsert(pj);
	
	return i;

}
@PostMapping("table3")
public String empinsert1(@RequestBody Project_Deatails_Pojo pj)	{

	String i=ok.empinsert(pj);
	
	return i;


}
@PostMapping("table4")
public String empupdate(@RequestBody Project_Deatails_Pojo pj)	{

	String i=pds.projectUpdate(pj);
	
	return i;


}
}
