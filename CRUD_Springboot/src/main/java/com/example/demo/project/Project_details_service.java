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
public class Project_details_service {
@Autowired
JdbcTemplate obj;

public String prjinsert(Project_Deatails_Pojo insert) {
	String result="";
	String usr=insert.getUsername();
	String pwd=insert.getPassword();
	String sql1="select * from emplogin2 where username=? and password=? and emproleid='a' ";
	List<Map<String,Object>> hm=new ArrayList<Map<String,Object>>();
	hm=obj.queryForList(sql1,usr,pwd);
	if(!hm.isEmpty()) {
		String nam=insert.getProject_name();
		String descrption=insert.getDescrption();
		String sdate=insert.getStart_date();
		String edate=insert.getEnd_date();
		String nemp=insert.getNo_of_emp();
		String prjmanager=insert.getProject_manager();
		String hr=insert.getHr();
		String id=nemp+nam;
		String asn=usr;
		String sql2="select *from emplogin2 where username=? and emproleid='p'";
		List<Map<String,Object>> objusr=new ArrayList<Map<String,Object>>();
		objusr=obj.queryForList(sql2,prjmanager);
		String sql3="select *from emplogin2 where username=? and emproleid='h'";
		List<Map<String,Object>> objhr=new ArrayList<Map<String,Object>>();
		objhr=obj.queryForList(sql3,hr);
		if(!objhr.isEmpty()) {
		if(!objusr.isEmpty()) {
		for(Map ur:objusr) {
			
				
					if(ur.get("emproleid").equals("p")) {
				for(Map h:objhr) {
					if(h.get("emproleid").equals("h")) {
						try {
						String s="insert into project_details values(?,?,?,?,?,?,?,?,?)";
						obj.update(s,id,nam,descrption,sdate,edate,nemp,prjmanager,hr,asn);
						String updt="update emplogin2 set status='a' where username=?";
						obj.update(updt,prjmanager);
						String upd="update emplogin2 set status='a' where username=?";
						obj.update(upd,hr);
						result="inserted";
						}catch(Exception e) {
							result="duplicate entry"+e;
						}
					}
				}
			}
		}
		}else {
			result="enter project manager proper username";
		}
		}
		else {
			result="enter hr manager proper username";
		}		
		
	}else {
		result="admin username or password invalid";
	}
	return result;
}

//########################################################################################
public String projectUpdate(Project_Deatails_Pojo obj1) {
	String result="";
	String usr=obj1.getUsername();
	String pwd=obj1.getPassword();
	String sql1="select * from emplogin2 where username=?  and emproleid='a' ";
	List<Map<String,Object>> hm=new ArrayList<Map<String,Object>>();
	hm=obj.queryForList(sql1,usr);
	String sql5="select * from emplogin2 where  password=? and emproleid='a' ";
	List<Map<String,Object>> hm1=new ArrayList<Map<String,Object>>();
	hm1=obj.queryForList(sql5,pwd);
	String projectname=obj1.getProject_name();
	String newproject=obj1.getNew_project();
	if(!hm.isEmpty()) {
		if(!hm1.isEmpty()) {
	String sql="select * from project_details where project_name=?";
	List<Map<String,Object>> copy=new ArrayList<Map<String,Object>>();
	copy=obj.queryForList(sql,projectname);
	DateTimeFormatter a=DateTimeFormatter.ofPattern("dd/MM/YYYY");
	LocalDateTime b=LocalDateTime.now();
	String date=a.format(b);
     if(!copy.isEmpty()) {
		for(Map ins:copy) {
			try {
			String descrption=(String)ins.get("description");
			String edate=(String)ins.get("end_date");
			String stdate=(String)ins.get("start_date");
            String nemp=(String)ins.get("No_of_emp");
            String prjmanager=(String)ins.get("project_manager");
			String hr=(String)ins.get("hr");
			String id=nemp+newproject;
			String asn=usr;
			String sql2="insert into project_details values(?,?,?,?,?,?,?,?,?)";
			obj.update(sql2,id,newproject,descrption,stdate,edate,nemp,prjmanager,hr,asn);
			String sql3="select * from prj_emp_details where name=?";
			List<Map<String,Object>> add=new ArrayList<Map<String,Object>>();
			add=obj.queryForList(sql3,projectname);
			for(Map upt:add) {
				String sql4="insert into prj_emp_details values(?,?,?,?,?,?,?,?,?)";
 obj.update(sql4,id,newproject,upt.get("emp_id"),upt.get("emp_usrname"),upt.get("phonenumber"),upt.get("email"),upt.get("join_date"),date,upt.get("assigned_by"));
			}
			
			result="inserted";
			}catch(Exception e) {
				result=""+e;
			}
		}
     }else {
    	 result="invalid old project name";
     }
		}else {
			result="invalid password";
		}
	}else {
		result="invalid username ";
	}
	return result;
}
}
