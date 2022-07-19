package com.activity.four.controller;

import com.activity.four.response.Response;
import com.activity.four.model.Users;
import com.activity.four.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getUsers() {
        return usersService.getUsers();
    }

    @PostMapping
    public Response addUser(@RequestBody Users user) {
        usersService.addUser(user);
        return new Response("user " + user.getId() + " inserted", Boolean.TRUE);
    }

    @DeleteMapping(path = "/{userId}")
    public Response deleteUser(@PathVariable("userId") Long userId) {
        usersService.deleteUser(userId);
        return new Response("user " + userId + " deleted", Boolean.TRUE);
    }

    @PutMapping(path = "/{userId}")
    public Response updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String role) {
        usersService.updateUser(userId, username, password, role);
        return new Response("user " + userId + " updated", Boolean.TRUE);
    }

}
