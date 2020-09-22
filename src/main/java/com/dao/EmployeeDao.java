package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.EmployeeBean;
import com.bean.SkillBean;

@Repository
public class EmployeeDao {

	@Autowired
	JdbcTemplate stmt;

	public int addEmployee(EmployeeBean employeeBean) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String insertQ = "insert into employee (ename,salary) values (?,?)";

		stmt.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement pstmt = con.prepareStatement(insertQ, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, employeeBean.getEname());
				pstmt.setInt(2, employeeBean.getSalary());

				return pstmt;
			}
		}, keyHolder);

		int empId = (Integer) keyHolder.getKeys().get("empid");
		return empId;
	}

	public void addSkillForEmployee(int empId, int skillId) {
		stmt.update("insert into empskill (empid,skillid) values (?,?)", empId, skillId);
	}

	public void updateEmployee(EmployeeBean employeeBean) {
		stmt.update("update employee set ename = ? , salary = ? where empid = ?", employeeBean.getEname(),
				employeeBean.getSalary(), employeeBean.getEmpId());

	}

	public void deleteSkillForEmployee(int empId) {
		stmt.update("delete from empskill where empid = ?", empId);
	}

	public List<EmployeeBean> getAllEmployees() {
		List<EmployeeBean> employees = stmt.query("select * from employee",
				BeanPropertyRowMapper.newInstance(EmployeeBean.class));
		return employees;
	}

	public List<SkillBean> getSkillsOfEmpl(int empId) {

		List<SkillBean> skills = stmt.query("select s.skill_name,s.skillid from skill s ,empskill es where es.empid = "
				+ empId + " and s.skillid = es.skillid ", BeanPropertyRowMapper.newInstance(SkillBean.class));
		return skills;
	}

	public void deleteEmployee(int empId) {
		stmt.update("delete from employee where empid = ?",empId);
	}
}
