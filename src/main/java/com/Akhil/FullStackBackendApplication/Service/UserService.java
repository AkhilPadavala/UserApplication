package com.Akhil.FullStackBackendApplication.Service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Akhil.FullStackBackendApplication.Model.User;
import com.Akhil.FullStackBackendApplication.Repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo repo;
	
	public User addUser(User u)
	{
		return repo.save(u);	
	}
	
	public List<User> getAllUsers()
	{
		return repo.findAll();
		
	}
	
	public User getUserById(int id)
	{
		
		return repo.findById(id).get();
	}
	
	public User update(User u)
	{
		return  repo.save(u);
		 
	}
	
	public boolean deleteUser(int id)
	{
		try {
		repo.deleteById(id);
		return true;
	}
		catch(Exception e)
		{
			return false;
		}

	}

//	public Optional<User> updateUserById(int id) {
//		return repo.findById(id);
//	}
}
