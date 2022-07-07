package com.github.ifsantos.resourceserver.api;

import static java.util.stream.Collectors.toList;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.ifsantos.resourceserver.dto.UserDTO;
import com.github.ifsantos.resourceserver.model.User;
import com.github.ifsantos.resourceserver.service.IUserService;



@RestController
@RequestMapping("api/user")
public class UserController {
    
    private IUserService svc;

    public UserController(IUserService svc) {
        this.svc = svc;
    }

    @GetMapping(value="/{id}")
    public UserDTO getUser(@PathVariable("{id}") Long id) {
        User user = svc.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toDTO(user);
    }
    
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) {
        User entity = svc.save(toEntity(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(entity));
    }
    
    @GetMapping
    public Iterable<UserDTO> listUser() {
        return StreamSupport
            .stream(svc.list().spliterator(),false)
            .map(this::toDTO)
            .collect(toList());
    }
    
    public UserDTO toDTO(User u){
        return new UserDTO(u.getID(), u.getName());
    }
    public User toEntity(UserDTO u){
        return new User(u.getId(), u.getName());
    }

}
