package com.wander.notes.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wander.notes.dao.User;
import com.wander.notes.dao.UserRepository;
import com.wander.notes.model.RegistrationApiRequest;
import com.wander.notes.service.definition.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private UserRepository userDao;
	
	@Override
	public int registerUser(RegistrationApiRequest request) throws Exception {
		List<String> authoritiesList = new ArrayList<String>();
		authoritiesList.add("ROLE_USER");
		User user = new User(request.getEmail(),new BCryptPasswordEncoder().encode(request.getPassword()),request.getName(),true,
				new Date(),authoritiesList);
		User checkUser = userDao.findByUsername(user.getUsername());
		if(checkUser != null) {
			return -2;//Username already exists
		}
		userDao.save(user);
		return 1;//Successfully registered
	}

}