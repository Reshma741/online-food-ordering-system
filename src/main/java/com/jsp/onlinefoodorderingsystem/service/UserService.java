package com.jsp.onlinefoodorderingsystem.service;



import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jsp.onlinefoodorderingsystem.entity.User;



public interface UserService {
	
	User saveUser(User user);
	
	User getUser(Integer id);
	
	List<User> getAllUsers();
	
	User updateUser(User user,Integer id);
	
	void deleteUser(Integer id);
	
	String uplaodImage(MultipartFile file,Integer id) throws  IOException;
	
	byte[] getImage(Integer id);

}
