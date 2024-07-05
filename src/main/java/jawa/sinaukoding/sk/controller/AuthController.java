package jawa.sinaukoding.sk.controller;

import jawa.sinaukoding.sk.model.Response;
import jawa.sinaukoding.sk.model.request.LoginReq;
import jawa.sinaukoding.sk.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/login")
    public Response<Object> login(@RequestBody LoginReq req) {
        return userService.login(req);
    }
}
