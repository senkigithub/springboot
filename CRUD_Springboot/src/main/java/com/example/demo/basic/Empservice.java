package com.example.demo.basic;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class Empservice {
@Autowired
JdbcTemplate ob;
public String empinsert(Employe_pojo a) {
//	String sql="select * from emplogin where roleid='h' or roleid='adm' ";
  String role2="";
 	String result="";
	String usr=a.getUsername();
	String pass=a.getPassword();
	String sql1="select * from emplogin2 where username=? and password=?";
	List<Map<String,Object>> hm=new ArrayList();
	hm=ob.queryForList(sql1,usr,pass);
	if(hm.size()!=0) {
	for(Map rl:hm) {
		String rol=(String) rl.get("emproleid");
		if(rol.contentEquals("h")||rol.contentEquals("a")) {
		String id=a.getId();
		String nam=a.getName();
		String rolemp=a.getEmprole();
		DateTimeFormatter obj=DateTimeFormatter.ofPattern("HHmm");
		LocalDateTime ldt=LocalDateTime.now();
		String str=obj.format(ldt);
		String usr1=Character.toUpperCase(nam.charAt(0))+id+rolemp;
		 String pass1=id+rolemp+str;
		 String s="select * from emplogin";
		 List<Map<String,Object>> mp=ob.queryForList(s);
		 		 for(Map b:mp) {
		 			if(b.get("role").equals(rolemp)) 
		 				role2=""+b.get("role");
		 		 }
		 			if(role2!="") {
		 				try {
		 					String sql="insert into emplogin2 values(?,?,?,?,?)";
					 int find =ob.update(sql,id,nam,role2,usr1,pass1);	 
					 if (find>0)
						 result="inserted";
					         }
		 			catch(Exception e) {
		 				result="Error id already present "+e;
		 			                   }
		                           }
		 			else
		 				result="invalid emp_name";
		 		 }
		          else
			     result="only admin or hr can insert";
	}
	              }else
		          result="invalid credentials(username or password)";
	
  
	
	return result;
	
}

}
