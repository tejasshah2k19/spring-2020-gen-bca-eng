package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.UserDao;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;

	// signup ==> insert db
	@PostMapping("/signup")
	public ResponseBean<UserBean> signup(UserBean userBean) {
		System.out.println("signup via post called....");
		System.out.println(userBean.getEmail());
		userDao.insertUser(userBean);
		// return "UserAdded";
		// return userBean;
		// return new ResponseEntity<UserBean>(userBean, null, HttpStatus.CREATED);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(userBean);
		responseBean.setMsg("Signup done");
		responseBean.setStatus(201);
		return responseBean;
	}

	@GetMapping("/users")
	public ResponseBean<ArrayList<UserBean>> getUsers() {

		ResponseBean<ArrayList<UserBean>> responseBean = new ResponseBean<>();

		responseBean.setData(userDao.listUsers());
		responseBean.setMsg("list of users");
		responseBean.setStatus(200);
		return responseBean;
	}

	@PutMapping("/user")
	public ResponseBean<UserBean> updateUser(UserBean userBean) {
		System.out.println("update called....");
		System.out.println(userBean.getEmail());
		userDao.updateUser(userBean);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(userBean);
		responseBean.setMsg("user successfully modified");
		responseBean.setStatus(201);
		return responseBean;
	}

	@PostMapping("/abcd")
	public void abcd() {
		System.out.println("abcd call....");
	}

}
