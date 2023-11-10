package com.hackathon.backend.controller;

import static org.springframework.http.HttpStatus.OK;

import com.hackathon.backend.domain.entity.User;
import com.hackathon.backend.domain.request.UserCreateAndUpdateRequest;
import com.hackathon.backend.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/test")
  public ResponseEntity<User> getTestUser() {
    User user = userService.getTestUser();
    return new ResponseEntity<>(user, OK);
  }

  @GetMapping
  public ResponseEntity<List<User>> getUserList() {
    List<User> users = userService.getUsers();
    return new ResponseEntity<>(users, OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getById(@PathVariable long id) {
    User user = userService.getById(id);
    return new ResponseEntity<>(user, OK);
  }

  @PostMapping
  public void create(@RequestBody UserCreateAndUpdateRequest request) {
    userService.create(request);
  }

  @PutMapping("/{id}")
  public void updateById(@PathVariable long id, UserCreateAndUpdateRequest request) {
    userService.updateById(id, request);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable long id) {
    userService.deleteById(id);
  }
}
