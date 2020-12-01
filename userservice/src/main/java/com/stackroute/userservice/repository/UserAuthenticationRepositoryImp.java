package com.stackroute.userservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stackroute.userservice.model.User;

@Repository
@Transactional
@EnableTransactionManagement
public class UserAuthenticationRepositoryImp implements UserAuthenticationRepositoryCustom {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		Query q = entityManager.unwrap(Session.class).createQuery("from User where username = :username and password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		List<User> user =  q.getResultList();
		return user.get(0);
	}
	

	@Override
	public List<User> findByUsername(String username) {
		Query q = entityManager.unwrap(Session.class).createQuery("from User where username = :username");
		q.setParameter("username", username);
		List<User> user =  q.getResultList();
		return user;
	}



}
