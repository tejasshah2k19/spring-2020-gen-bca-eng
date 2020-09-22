package com.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {

	public static ArrayList<UserBean> users = new ArrayList<>();

	public void insertUser(UserBean userBean) {
		users.add(userBean);
	}

	public ArrayList<UserBean> listUsers() {
		return users;
	}

	public void updateUser(UserBean userBean) {
		// TODO Auto-generated method stub
		
	}

}
