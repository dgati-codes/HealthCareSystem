package io.dave.service;

import java.util.Optional;

import io.dave.entity.User;

public interface IUserService {

	Long saveUser(User user);
	Optional<User> findByUsername(String username);
	void updateUserPwd(String pwd,Long userId);
}
