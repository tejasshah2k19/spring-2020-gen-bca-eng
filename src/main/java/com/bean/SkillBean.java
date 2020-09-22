package com.bean;

import javax.validation.constraints.NotNull;

public class SkillBean {
	int skillId;
	
	@NotNull
	String skillName;
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	
}
