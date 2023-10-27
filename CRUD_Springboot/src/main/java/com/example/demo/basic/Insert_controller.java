package com.example.demo.basic;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
class myException extends Exception{
	myException(String msg){
		super(msg);
	}
}
@RestController
public class Insert_controller {
@Autowired
Insert_service is;
@PostMapping("insert")

public Map insert(@RequestBody Insert_pojo b1) {
	HashMap<String,String> hm=new HashMap();
	try {
	int data=is.insert1(b1);
	if(data>0) {
		hm.put("sucess", "data inseted");
	}else {
		hm.put("failed", "data not  inseted");
	}
	}catch(Exception e) {
		hm.put("exception", e.toString());
	}
	return hm;
}

//@PostMapping("insert1")
//public String insert1(@RequestBody pojo b1) {
//	int data=is.insert2(b1);
//	if(data>0) {
//		return "data inserted";
//	}else {
//		return "not inserted";
//	}
@PostMapping("update")
public Map  update(@RequestBody Insert_pojo b1) {
	HashMap<String,String> hm=new HashMap();

	try {
		int data=is.update(b1);
	if(data>0) {
		hm.put("success", "data updated");
		}
	else {
		hm.put("failed","data not update");
	}
	}catch(Exception e) {
		hm.put("exception", e.toString());
	}
	return hm;
}
@PostMapping("delete")
public HashSet delete(@RequestBody Insert_pojo b1) throws Exception  {
//	
    HashSet<String> hm=new HashSet();
//	int i=0;
    try {
	int data=is.delete(b1);
	if(data>0) {
		hm.add("successfully deleted");
		
	}else  {
		hm.add("not delete");
		throw new myException("invalid");
			}
	}catch(Exception e) {
    	hm.add(e.getMessage());
    	  }
	return hm;
	}
@PostMapping("/select")
public List findAll(){
   return is.selectAll();
}
@GetMapping("s")
public List name(@RequestBody Insert_pojo a) throws myException {
	List i= is.selectById(a);
	return i;
	
}
@PostMapping("lik")
public List like1(@RequestBody Insert_pojo a) {
	List i=is.like(a);
	return i;
}

}
