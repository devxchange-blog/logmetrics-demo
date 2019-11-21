package io.devxchange.logmetrics.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.devxchange.logmetrics.demo.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author io.devxchange
 *
 */
@Service("user")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();
	}

	public List<User> findAllUsers() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering findAllUsers()");
			logger.debug("exiting findAllUsers()");
			logger.debug("returning: " + users);
		}
		return users;
	}

	public User findById(long id) {
		if (logger.isDebugEnabled()) {
			logger.debug("entering findById(long)");
			logger.debug("id: " + id);
		}
		for (User user : users) {
			if (user.getId() == id) {
				if (logger.isDebugEnabled()) {
					logger.debug("exiting findById()");
					logger.debug("returning: " + user);
				}
				return user;
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("exiting findById()");
			logger.debug("returning: " + null);
		}
		return null;
	}

	public User findByName(String name) {
		if (logger.isDebugEnabled()) {
			logger.debug("entering findByName(String)");
			logger.debug("name: \"" + name + "\"");
		}
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				if (logger.isDebugEnabled()) {
					logger.debug("exiting findByName()");
					logger.debug("returning: " + user);
				}
				return user;
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("exiting findByName()");
			logger.debug("returning: " + null);
		}
		return null;
	}

	public void saveUser(User user) {
		if (logger.isDebugEnabled()) {
			logger.debug("entering saveUser(User)");
			logger.debug("user: " + user);
		}
		user.setId(counter.incrementAndGet());
		users.add(user);
		if (logger.isDebugEnabled()) {
			logger.debug("exiting saveUser()");
		}
	}

	public void updateUser(User user) {
		if (logger.isDebugEnabled()) {
			logger.debug("entering updateUser(User)");
			logger.debug("user: " + user);
		}
		int index = users.indexOf(user);
		users.set(index, user);
		if (logger.isDebugEnabled()) {
			logger.debug("exiting updateUser()");
		}
	}

	public void deleteUserById(long id) {

		if (logger.isDebugEnabled()) {
			logger.debug("entering deleteUserById(long)");
			logger.debug("id: " + id);
		}
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("exiting deleteUserById()");
		}
	}

	public boolean isUserExist(User user) {
		if (logger.isDebugEnabled()) {
			logger.debug("entering isUserExist(User)");
			logger.debug("user: " + user);
			logger.debug("exiting isUserExist()");
		}
		return findByName(user.getName()) != null;
	}

	private static List<User> populateDummyUsers() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering populateDummyUsers()");
		}
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "karthik", 29, 115000));
		if (logger.isDebugEnabled()) {
			logger.debug("exiting populateDummyUsers()");
			logger.debug("returning: " + users);
		}
		return users;
	}

	public void deleteAllUsers() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering deleteAllUsers()");
		}
		users.clear();
		if (logger.isDebugEnabled()) {
			logger.debug("exiting deleteAllUsers()");
		}
	}
}
