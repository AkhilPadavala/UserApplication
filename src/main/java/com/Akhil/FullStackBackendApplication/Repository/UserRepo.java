package com.Akhil.FullStackBackendApplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Akhil.FullStackBackendApplication.Model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	

}
