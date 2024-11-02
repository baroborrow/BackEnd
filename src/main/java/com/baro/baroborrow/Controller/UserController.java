package com.baro.baroborrow.Controller;


import com.baro.baroborrow.DTO.KakaoLoginDTO;
import com.baro.baroborrow.DTO.LoginCheckDTO;
import com.baro.baroborrow.DTO.LoginCheckSendDTO;
import com.baro.baroborrow.DTO.RegisterDTO;
import com.baro.baroborrow.Domain.User;
import com.baro.baroborrow.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getUsers() throws Exception{
        List<User> list = userService.getUsers();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/login-check")
    public LoginCheckSendDTO checkLogin(@RequestBody LoginCheckDTO loginCheckDTO) throws Exception{
        List<User> list = userService.getUsers();
        for(User user : list){
            if(user.getUser_id().equals(loginCheckDTO.getMail_address())){
                if(user.getPassword().equals(loginCheckDTO.getPassword())){
                    return new LoginCheckSendDTO(true,user.getUser_id());
                }
            }
        }
        return new LoginCheckSendDTO(false,null);
    }

    @PostMapping("/register")
    public LoginCheckSendDTO registerUser(@RequestBody RegisterDTO registerDTO) throws Exception{
        List<User> list = userService.getUsers();
        for(User user : list){
            if(user.getUser_id().equals(registerDTO.getMail_address())){
                return new LoginCheckSendDTO(false,null);
            }

            //카카오 비교
        }
        User user = new User(registerDTO.getMail_address(),registerDTO.getKakao_id(),registerDTO.getPassword(), registerDTO.getUsername());
        userService.addUser(user);
        return new LoginCheckSendDTO(true,user.getUser_id());
    }

    @PostMapping("/kakao-login")
    public LoginCheckSendDTO kakaoLogin(@RequestBody KakaoLoginDTO kakaoLoginDTO) throws Exception{
        List<User> list = userService.getUsers();
        for(User user : list){
            if(user.getUser_id() != null){
                if(user.getKakao_id().equals(kakaoLoginDTO.getKakao_id())){
                    return new LoginCheckSendDTO(true,user.getUser_id());
                }
            }
        }
        return new LoginCheckSendDTO(false,null);
    }

    @DeleteMapping("/delete-user/{user_id}")
    public void deleteUser(@PathVariable String user_id) throws Exception{
        userService.deleteUser(user_id);
    }

    @GetMapping("/find-user/{id}")
    public ResponseEntity<User> findUser(@PathVariable String id) throws Exception{
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

}
