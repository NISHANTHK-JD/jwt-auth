package com.example.demo.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>
{

	User findByUsernameAndPassword(String username, String password);
	List<User> findByUsername(String username);
	@Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
	User validateUser(String username, String password);

	@Query("SELECT u FROM User u WHERE u.petName = :petName AND u.username = :username")
	User findBypetNameAnduserName(String petName,String username);
	User findByPetName(String petName);
}
