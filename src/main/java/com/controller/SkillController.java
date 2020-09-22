package com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.SkillBean;
import com.dao.SkillDao;

@RestController
public class SkillController {
	@Autowired
	SkillDao skillDao;

	@PostMapping("/skill")
	public ResponseBean<SkillBean> addSkill(@Valid @RequestBody SkillBean skillBean) {
	System.out.println("===in==");
	skillDao.insertSkill(skillBean);

		ResponseBean<SkillBean> responseBean = new ResponseBean<>();
		responseBean.setData(skillBean);
		responseBean.setMsg("Skill Added");
		responseBean.setStatus(200);
		return responseBean;
	}

	@DeleteMapping("/skill/{skillId}")
	public ResponseBean<SkillBean> removeSkill(@PathVariable("skillId") int skillId) {
		SkillBean skillBean = skillDao.deleteSkill(skillId);

		ResponseBean<SkillBean> responseBean = new ResponseBean<>();
		responseBean.setData(skillBean);
		if (skillBean != null)
			responseBean.setMsg("skill deleted");
		else
			responseBean.setMsg("skill not found");
		responseBean.setStatus(200);
		return responseBean;
	}

}
