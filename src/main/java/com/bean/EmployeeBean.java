package com.bean;

import java.util.ArrayList;

public class EmployeeBean {

	int empId;
	String ename;
	int salary;
	
	ArrayList<SkillBean> skills = new ArrayList<>();

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public ArrayList<SkillBean> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<SkillBean> skills) {
		this.skills = skills;
	}
	
 	
	
	

}
