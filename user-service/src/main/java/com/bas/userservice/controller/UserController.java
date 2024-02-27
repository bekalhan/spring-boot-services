package com.bas.userservice.controller;

import com.bas.userservice.dto.UserDTO;
import com.bas.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    public String testMethod(@RequestHeader("currUser") String user){
        return "hello "+user;
    }
    @PostMapping("addUser")
    public ResponseEntity<String>addUserDetails(@RequestBody @Valid UserDTO userDTO,@RequestHeader("currUser") String username){
        return new ResponseEntity<>(userService.addUserDetails(userDTO,username), HttpStatus.CREATED);
    }

   /* @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> allUsers = userService.findAll();
        return  ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "userId") Integer userId){
        UserDTO user = userService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDto){
        UserDTO user = userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDto){
        UserDTO user = userService.update(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDto, @PathVariable(value="userId") Integer userId){
        UserDTO user = userService.update(userId,userDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable(value="userId") Integer userId){
        this.userService.deleteById(userId);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable(value = "username") String username){
        return ResponseEntity.ok(this.userService.findByUsername(username));
    }*/
}
