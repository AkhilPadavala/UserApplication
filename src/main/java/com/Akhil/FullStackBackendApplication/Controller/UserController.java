package com.Akhil.FullStackBackendApplication.Controller;

//import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Akhil.FullStackBackendApplication.Model.User;
import com.Akhil.FullStackBackendApplication.Repository.UserRepo;
import com.Akhil.FullStackBackendApplication.Service.UserService;

//import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@CrossOrigin("http://localhost:3002")
public class UserController {
	
	@Autowired
	UserService service;
	@Autowired
	UserRepo repo;
	
	@PostMapping("add")
	public ResponseEntity<User> addUser(@RequestBody User u)
	{
		User u1= service.addUser(u);
		if(u1!=null)
		{
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	@GetMapping("get")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> u=service.getAllUsers();
		if(u.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{
			return ResponseEntity.of(Optional.of(u));
		}
		
	}
	@GetMapping("get/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id)
	{
		User u=service.getUserById(id);
		if(u!=null)
		{
			return ResponseEntity.of(Optional.of(u));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
//	
	@PutMapping("update")
	public ResponseEntity<User> updateUser(@RequestBody User u)
	{
		User u1=service.update(u);
		if(u1!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	@PutMapping("update/{id}")
	public Optional<User> updateUser(@RequestBody User u,@PathVariable int id)
	{
		return repo.findById(id)
				.map(user->{
					user.setUsername(u.getUsername());
					user.setName(u.getName());
					user.setEmail(u.getEmail());
					return repo.save(user);
					
				});
		
	}
	
	
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id)
	{
		boolean res=service.deleteUser(id);
		if(res)
		{
			return ResponseEntity.of(Optional.of("Deleted Successfully"));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	

}
