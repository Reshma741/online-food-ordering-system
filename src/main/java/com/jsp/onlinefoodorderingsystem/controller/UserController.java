package com.jsp.onlinefoodorderingsystem.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.onlinefoodorderingsystem.entity.User;
import com.jsp.onlinefoodorderingsystem.responseStructure.ResponseStructure;
import com.jsp.onlinefoodorderingsystem.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<User>> saveUser( @Valid @RequestBody User user){
		ResponseStructure<User> apiresponse= new ResponseStructure<User>(HttpStatus.ACCEPTED.value(), "user created", userService.saveUser(user));
		return new ResponseEntity<>(apiresponse,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable Integer id){
		ResponseStructure<User> apiresponse= new ResponseStructure<User>(HttpStatus.OK.value(), "User with id: "+id+"is found", userService.getUser(id));
		return ResponseEntity.ok(apiresponse);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers(){
		ResponseStructure<List<User>> apiresponse= new ResponseStructure<List<User>>(HttpStatus.OK.value(), "All users found", userService.getAllUsers());
		return  ResponseEntity.ok(apiresponse);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<User>> updateUserById(@PathVariable Integer id, @RequestBody User user){
		ResponseStructure<User> apiresponse=new ResponseStructure<User>(HttpStatus.OK.value(), "User with "+id+"is updated", userService.updateUser(user, id));
		return ResponseEntity.ok(apiresponse);
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@PatchMapping("/uplaodimage/user/{id}")
	public ResponseEntity<ResponseStructure<String>> uplaodImage(@RequestParam MultipartFile image,@PathVariable Integer id) throws IOException{
		String response=userService.uplaodImage(image, id);
		ResponseStructure<String> rs= new ResponseStructure<String>(HttpStatus.OK.value() , "Image is uploded", response);
		return new ResponseEntity<>(rs,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/{id}/user/getImage")
	public ResponseEntity<byte[]> getImage(@PathVariable Integer id){
		byte[] image=userService.getImage(id);
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(image);
	}
	
     
}
