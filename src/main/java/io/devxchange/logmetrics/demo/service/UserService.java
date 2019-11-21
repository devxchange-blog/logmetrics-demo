package io.devxchange.logmetrics.demo.service;

import java.util.List;

import io.devxchange.logmetrics.demo.model.User;

/**
 *
 * @author io.devxchange
 *
 */
public interface UserService {

	User findById(long id);
	User findByName(String name);
	void saveUser(User user);
	void updateUser(User user);
	void deleteUserById(long id);
	List<User> findAllUsers();
	void deleteAllUsers();
	public boolean isUserExist(User user);

}
