package com.example.demo.basic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class Insert_service {
@Autowired
JdbcTemplate o;
public int insert1(Insert_pojo ob1) {
	String firstname=ob1.getFirstname();
	String lastname=ob1.getLastname();
	String phonenumber=ob1.getPhonenumber();
	String branch=ob1.getBranch();
	String pincode=ob1.getPincode();
	String dob=ob1.getDob();
	DateTimeFormatter obj=DateTimeFormatter.ofPattern("HHmm");
	LocalDateTime ldt=LocalDateTime.now();
	String str=obj.format(ldt);
	int i=ob1.getLastname().length();
	String username=Character.toUpperCase(firstname.charAt(0))+lastname;
	String password=firstname.charAt(0)+lastname+str;
	String email=firstname.charAt(0)+lastname+"@miraclesoft.com";
	String sql="insert into studentdata values(?,?,?,?,?,?,?,?,?)";
	return o.update(sql, firstname,lastname,phonenumber,email,branch,pincode,dob,username,password);
}
//@Autowired
//NamedParameterJdbcTemplate obj2;
//public int insert2(pojo objk) {
//	MapSqlParameterSource obja=new MapSqlParameterSource().addValue("name", objk.getName())
//			.addValue("dept", objk.getDept()).addValue("address", objk.getAddress());

//	String sql="insert into town values(:name,:dept,:address)";
//	return obj2.update(sql, obja);
//}
public int update(Insert_pojo ob1) {
	String firstname=ob1.getFirstname();
	String lastname=ob1.getLastname();
	String phonenumber=ob1.getPhonenumber();
	String branch=ob1.getBranch();
	String pincode=ob1.getPincode();
	String dob=ob1.getDob();
	String username=ob1.getUsername();
	String password=ob1.getPassword();
	String email=ob1.getEmail();
	String sql="update studentdata set firstname=?,lastname=?,phonenumber=?,email=?,branch=?,pincode=?,dob=? where username=? and password=?";
	return o.update(sql, firstname,lastname,phonenumber,email,branch,pincode,dob,username,password);
}
public int delete(Insert_pojo ob1) {
	String firstname=ob1.getFirstname();
	String lastname=ob1.getLastname();
	String sql="delete from studentdata where firstname=? and lastname=?";
	return o.update(sql, firstname,lastname);
}

public List selectAll(){
  String sql="select * from studentdata";
  List get=new ArrayList();
     List<Map<String,Object>> req=new ArrayList<Map<String,Object>>();
     req=o.queryForList(sql);

   Map data ;
   for(Map itr:req) {
	   data= new HashMap();
	   data.put("fname", itr.get("firstname"));
	   data.put("lname", itr.get("lastname"));
	   data.put("phonenumber", itr.get("phonenumber"));
	   data.put("email", itr.get("email"));
	   data.put("branch", itr.get("branch"));
	   data.put("pincode", itr.get("pincode"));
	   data.put("dob", itr.get("dob"));
	   data.put("username", itr.get("username"));
	   data.put("password", itr.get("password"));
	   get.add(data);
   }
//return req;
 
return get;
  
}
public List selectById(Insert_pojo name) {
	String firstname=name.getFirstname();
	String sql="select * from studentdata where firstname=?";
	List get=new ArrayList();
    List<Map<String,Object>> req=new ArrayList<Map<String,Object>>();
     req=o.queryForList(sql,firstname);
//   Map data =new HashMap<>();
//   data.put("fname", data.get("firstname"));
     return req;
	
		
	
    
}
public List like(Insert_pojo phn) {
	String p=phn.getPhonenumber();
	String uname=phn.getUsername();
	String mail=phn.getEmail();
	String sql=" ";
	//String sql1="select * from studentdata where username like '"+uname+"%'";+
  if(p==null && mail==null && uname==null) {
	  sql="select *from studentdata";
  }else if((p!=null && uname!=null)){
	   sql="select * from studentdata where phonenumber like '"+p+"%' and username like '"+uname+"%'  ";
  }
  else if((uname!=null && mail!=null)){
	   sql="select * from studentdata where username like '"+uname+"%' and email like '"+mail+"%'  ";
 }else if((p!=null && mail!=null)){
	   sql="select * from studentdata where phonenumber like '"+p+"%' and email like '"+mail+"%'  ";
}
  else  {
	  sql="select * from studentdata where phonenumber like '"+p+"%' or username like '"+uname+"%' or email like '"+mail+"%'  ";
  }
	//List get=new ArrayList();
	  List<Map<String,Object>> req=new ArrayList<Map<String,Object>>();
	     req=o.queryForList(sql);
	     return req;

}

}
