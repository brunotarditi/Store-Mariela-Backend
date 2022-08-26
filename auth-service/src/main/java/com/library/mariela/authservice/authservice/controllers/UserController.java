package com.library.mariela.authservice.authservice.controllers;


import com.library.mariela.authservice.authservice.dtos.*;
import com.library.mariela.authservice.authservice.entities.User;
import com.library.mariela.authservice.authservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto){
        TokenDto tokenDto = userService.login(loginUserDto);
        if (tokenDto == null)
            return new ResponseEntity<>(new Message("Por favor revise el usuario o la contraseña"), HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<?> create(@RequestBody NewUserDto newUserDto){
        User user = userService.save(newUserDto);
        if (user == null)
            return new ResponseEntity<>(new Message("El usuario ya existe"), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String userName){
        UserDto userDto = this.userService.getUserByUserName(userName);
        if (userDto == null)
            return new ResponseEntity<>(new Message("Usuario no encontrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
