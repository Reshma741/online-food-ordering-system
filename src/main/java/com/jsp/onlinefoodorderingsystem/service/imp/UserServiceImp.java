package com.jsp.onlinefoodorderingsystem.service.imp;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.onlinefoodorderingsystem.entity.User;
import com.jsp.onlinefoodorderingsystem.repository.UserRepository;
import com.jsp.onlinefoodorderingsystem.service.UserService;


@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}


	@Override
	public User getUser(Integer id) {
		
		return userRepository.findById(id).orElseThrow(()->new NoSuchElementException());
	}

    
	
	@Cacheable(value="user_cache")
	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

     
	
	@CachePut(value="user_cache",key="#id")
	@Override
	public User updateUser(User user, Integer id) {
		  User exsiting= getUser(id);
		 exsiting.setName(user.getName());
		 exsiting.setEmail(user.getEmail());
		 exsiting.setContactNumber(user.getContactNumber());
		 exsiting.setAddress(user.getAddress());
		 exsiting.setGender(user.getGender());
		return userRepository.save(exsiting) ;
	}

   
	
	@CacheEvict(value="user_cache",key="#id")
	@Override
	public void deleteUser(Integer id) {
		
		userRepository.deleteById(id);
		
	}

	
	@CacheEvict(value="user_cache" ,allEntries=true)
	@Scheduled(fixedRate=120000)// evict cache every 2 minutes
	public void evictAllCache() {
		System.out.println("evicting all entries from user cache");
	}

	@Override
	public String uplaodImage(MultipartFile file, Integer id) throws IOException {
		byte[] image= file.getBytes();
		User user= getUser(id);
		user.setImage(image);
		userRepository.save(user);
		return "Image is uploaded" ;
	}
	
	
	@Override
	public byte[] getImage(Integer id) {
		User user= getUser(id);
		byte[] image=user.getImage();
		if(image==null||image.length==0) {
			throw new NoSuchElementException("user with id"+id +"doesnot have any image");
			
		}
		return image;
	}
	

}
