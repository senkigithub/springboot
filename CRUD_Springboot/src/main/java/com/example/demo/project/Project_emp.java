package com.example.demo.project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Project_emp {
	@Autowired
	JdbcTemplate oj;
	public String empinsert(Project_Deatails_Pojo o) {
		String result="";
		String usr=o.getUsername();
		String pwd=o.getPassword();
		String empid=o.getEmpid();
		
		String sql1="select * from emplogin2 where username=? and password=? and emproleid='p' ";
		List<Map<String,Object>> hm=new ArrayList<Map<String,Object>>();
		hm=oj.queryForList(sql1,usr,pwd);
		String empname="";
   	 String joindate="";
         String phn="";
   	  String mail="";
  	String prjid="";
	String prname="";
	
	DateTimeFormatter a=DateTimeFormatter.ofPattern("dd/MM/YYYY");
	LocalDateTime b=LocalDateTime.now();
	String date=a.format(b);
     if(!hm.isEmpty()) {
    	 String sql="select * from emplogin2 where id=? and emproleid='e'";
    	 List<Map<String,Object>> h=new ArrayList<Map<String,Object>>();
 		h=oj.queryForList(sql,empid);
 		if(!h.isEmpty()) {
       for(Map i:h) {
    	 empname=(String)i.get("username");
    	  joindate=(String)i.get("join_date");
           phn=(String)i.get("phonenumber");
    	  mail=(String)i.get("email-id"); 
       }
       
       String sql2="select * from project_details where project_manager=?";
  	 List<Map<String,Object>> objk=new ArrayList<Map<String,Object>>();
		objk=oj.queryForList(sql2,usr);
		if(!objk.isEmpty()) {
		for(Map j:objk) {
			prjid=(String)j.get("id");
			 prname=(String)j.get("project_name");
		}
		try {
    	String ins="insert into prj_emp_details values(?,?,?,?,?,?,?,?,?) ";
    	oj.update(ins,prjid,prname,empid,empname,phn,mail,joindate,date,usr);
    	String upd="update emplogin2 set status='a' where id=?";
		oj.update(upd,empid);
    	result="inserted";
		}catch(Exception e) {
			result="duplicate entry:YOU ALREADY USED THIS ID"+e;
		}
		}
		else
			result="no project for project manager";
		}else
 			result="invalid employee details";
     }else {
    	 result="invalid project manager username or password";
     }
	return result;

	}
}
