package com.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.Operator;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport; 
import com.service.OperatorService;

@Component("testaction")
public class TestAction extends ActionSupport {

	@Autowired
	private OperatorService service;
	private List<Operator> list;
	
	public List<Operator> getList() {
		return list;
	}

	public void setList(List<Operator> list) {
		this.list = list;
	}

	public String test()
	{
		System.out.println("-----test----");
		  list=(List<Operator>)service.queryAll("Operator");
		 
		  System.out.println("-------"+list.size());
		  if(list.size()>0)
		  {
			    Gson g=new Gson();
				String str=g.toJson(list);
				System.out.println(str);
		  }
		return "test";
	}
	
	 
	public static void main(String[] args) {
		//提取字符串中的数字
		String[] dist={"asdfasdf123sdafasdf456sfsf"}; 
		for (String li : dist) {
			   String[] nums = li.split("\\D+");
			   for (String num : nums) {
			    System.out.print(num + "\t");
			   } 
	    } 
	}
}
