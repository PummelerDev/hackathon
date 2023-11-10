package com.hackathon.backend.service;

import static java.lang.String.format;

import com.hackathon.backend.domain.entity.User;
import com.hackathon.backend.domain.request.UserCreateAndUpdateRequest;
import com.hackathon.backend.mapper.UserMapper;
import com.hackathon.backend.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public User getById(long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(format("User with id %d not found", id)));
  }

  public void create(UserCreateAndUpdateRequest request) {
    User user = userMapper.mapToUser(request);
    userRepository.save(user);
  }

  public void updateById(long id, UserCreateAndUpdateRequest request) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(format("User with id %d not found", id)));
    user.setFirstname(request.getFirstname());
    user.setLastname(request.getLastname());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    userRepository.save(user);
  }

  public void deleteById(long id) {
    userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(format("User with id %d not found", id)));
    userRepository.deleteById(id);
  }

  public User getTestUser() {
    return User.builder()
        .id(1L)
        .email("test.email@test.com")
        .firstname("first")
        .lastname("last")
        .password("password")
        .build();
  }
}
