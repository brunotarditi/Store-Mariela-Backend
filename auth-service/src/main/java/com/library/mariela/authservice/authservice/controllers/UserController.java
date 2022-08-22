package com.library.mariela.authservice.authservice.controllers;


import com.library.mariela.authservice.authservice.dtos.LoginUserDto;
import com.library.mariela.authservice.authservice.dtos.NewUserDto;
import com.library.mariela.authservice.authservice.dtos.RequestDto;
import com.library.mariela.authservice.authservice.dtos.TokenDto;
import com.library.mariela.authservice.authservice.entities.User;
import com.library.mariela.authservice.authservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginUserDto loginUserDto){
        TokenDto tokenDto = userService.login(loginUserDto);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto dto){
        TokenDto tokenDto = userService.validate(token, dto);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);

    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody NewUserDto newUserDto){
        User user = userService.save(newUserDto);
        if (user == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(user);
    }
}
