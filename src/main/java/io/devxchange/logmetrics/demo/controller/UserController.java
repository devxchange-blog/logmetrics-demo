package io.devxchange.logmetrics.demo.controller;

import java.util.List;

import io.devxchange.logmetrics.demo.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.devxchange.logmetrics.demo.service.UserService;

/**
 *
 * @author io.devxchange
 *
 */
@RestController
@RequestMapping("/user-service/api/v1/")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	UserService userService;

	@RequestMapping(value = "user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		if (logger.isDebugEnabled()) {
			logger.debug("entering getUser(long)");
			logger.debug("id: " + id);
		}
		System.out.println("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			if (logger.isDebugEnabled()) {
				logger.debug("exiting getUser()");
				logger.debug("returning: " + new ResponseEntity<User>(HttpStatus.NOT_FOUND));
			}
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("exiting getUser()");
			logger.debug("returning: " + new ResponseEntity<User>(user, HttpStatus.OK));
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering listAllUsers()");
		}
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("exiting listAllUsers()");
				logger.debug("returning: " + new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT));
			}
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("exiting listAllUsers()");
			logger.debug("returning: " + new ResponseEntity<List<User>>(users, HttpStatus.OK));
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		if (logger.isDebugEnabled()) {
			logger.debug("entering createUser(User,UriComponentsBuilder)");
			logger.debug("user: " + user);
			logger.debug("ucBuilder: " + ucBuilder);
		}
		System.out.println("Creating User " + user.getName());

		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getName() + " already exist");
			if (logger.isDebugEnabled()) {
				logger.debug("exiting createUser()");
				logger.debug("returning: " + new ResponseEntity<Void>(HttpStatus.CONFLICT));
			}
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("user/{id}").buildAndExpand(user.getId()).toUri());
		if (logger.isDebugEnabled()) {
			logger.debug("exiting createUser()");
			logger.debug("returning: " + new ResponseEntity<Void>(headers, HttpStatus.CREATED));
		}
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		if (logger.isDebugEnabled()) {
			logger.debug("entering updateUser(long,User)");
			logger.debug("id: " + id);
			logger.debug("user: " + user);
		}
		System.out.println("Updating User " + id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			System.out.println("User with id " + id + " not found");
			if (logger.isDebugEnabled()) {
				logger.debug("exiting updateUser()");
				logger.debug("returning: " + new ResponseEntity<User>(HttpStatus.NOT_FOUND));
			}
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		if (logger.isDebugEnabled()) {
			logger.debug("exiting updateUser()");
			logger.debug("returning: " + new ResponseEntity<User>(currentUser, HttpStatus.OK));
		}
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	@RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		if (logger.isDebugEnabled()) {
			logger.debug("entering deleteUser(long)");
			logger.debug("id: " + id);
		}
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			if (logger.isDebugEnabled()) {
				logger.debug("exiting deleteUser()");
				logger.debug("returning: " + new ResponseEntity<User>(HttpStatus.NOT_FOUND));
			}
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		if (logger.isDebugEnabled()) {
			logger.debug("exiting deleteUser()");
			logger.debug("returning: " + new ResponseEntity<User>(HttpStatus.NO_CONTENT));
		}
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering deleteAllUsers()");
		}
		System.out.println("Deleting All Users");

		userService.deleteAllUsers();
		if (logger.isDebugEnabled()) {
			logger.debug("exiting deleteAllUsers()");
			logger.debug("returning: " + new ResponseEntity<User>(HttpStatus.NO_CONTENT));
		}
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}
