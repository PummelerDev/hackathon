package com.hackathon.backend.mapper;

import com.hackathon.backend.domain.entity.User;
import com.hackathon.backend.domain.request.UserCreateAndUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User mapToUser(UserCreateAndUpdateRequest request) {
    return User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(request.getPassword())
        .build();
  }
}
