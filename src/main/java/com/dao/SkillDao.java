package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.SkillBean;

@Repository
public class SkillDao {
	@Autowired
	JdbcTemplate stmt;

	public void insertSkill(SkillBean skillBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into skill (skill_name ) values (?)", skillBean.getSkillName());

	}

	public SkillBean deleteSkill(int skillId) {
		SkillBean skillBean = null;
		skillBean = getSkillById(skillId);
		if (skillBean != null)
			stmt.update("delete from skill where skillid = ?", skillId);
		return skillBean;
	}

	public SkillBean getSkillById(int skillId) {

		SkillBean skillBean = null;
		try {
			skillBean = stmt.queryForObject("select * from skill where skillid = ? ", new Object[] { skillId },
					BeanPropertyRowMapper.newInstance(SkillBean.class));
		} catch (Exception e) {
			System.out.println("skill not found " + e.getMessage());
		}
		return skillBean;
	}
}
