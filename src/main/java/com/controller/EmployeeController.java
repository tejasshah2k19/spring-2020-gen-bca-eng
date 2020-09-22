package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.EmployeeBean;
import com.bean.ResponseBean;
import com.bean.SkillBean;
import com.dao.EmployeeDao;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeDao empDao;

	@PostMapping("/addEmployee")
	public ResponseBean<EmployeeBean> addEmployee(@RequestBody EmployeeBean employeeBean) {

		ResponseBean<EmployeeBean> response = new ResponseBean<>();

		System.out.println(employeeBean.getEname());

		System.out.println("skills -- employee ");

		int empId = empDao.addEmployee(employeeBean);

		for (SkillBean sb : employeeBean.getSkills()) {
			empDao.addSkillForEmployee(empId, sb.getSkillId());
			System.out.println(sb.getSkillId());
			System.out.println(sb.getSkillName());
		}
		employeeBean.setEmpId(empId);
		response.setData(employeeBean);
		response.setMsg("EmployeeAdded");
		response.setStatus(200);
		return response;
	}

	@PutMapping("/updateEmployee")
	public ResponseBean<EmployeeBean> updateEmployee(@RequestBody EmployeeBean employeeBean) {
		ResponseBean<EmployeeBean> resp = new ResponseBean<>();
		resp.setMsg("update done");
		resp.setStatus(200);

		empDao.updateEmployee(employeeBean);

		empDao.deleteSkillForEmployee(employeeBean.getEmpId());

		for (SkillBean sb : employeeBean.getSkills()) {
			empDao.addSkillForEmployee(employeeBean.getEmpId(), sb.getSkillId());
		}
		resp.setData(employeeBean);
		return resp;

	}

	@GetMapping("/employees")
	public ResponseBean<List<EmployeeBean>> getAllEmployees() {

		ResponseBean<List<EmployeeBean>> responseBean = new ResponseBean<>();

		List<EmployeeBean> employees = empDao.getAllEmployees();

		for (int i = 0; i < employees.size(); i++) {
			EmployeeBean eb = employees.get(i);
			List<SkillBean> skills = empDao.getSkillsOfEmpl(eb.getEmpId());
			eb.setSkills((ArrayList<SkillBean>) skills);
		}

		responseBean.setData(employees);
		responseBean.setMsg("List done");
		responseBean.setStatus(200);

		return responseBean;
	}

	@DeleteMapping("/employee/{empId}")
	public String deleteEmployee(@PathVariable("empId") int empId) {
		
		empDao.deleteSkillForEmployee(empId);
		empDao.deleteEmployee(empId);
		return "user deleted!!!";
	}


	public String removeEmpSession(){
		System.out.println("abcd");
		System.out.println("god is great ......");	
		return "";
	}
	
}
