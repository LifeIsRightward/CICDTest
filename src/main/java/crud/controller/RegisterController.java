package crud.controller;

import crud.dto.UserRequestDto;
import crud.service.UserRegisterService;
import crud.service.UserRegisterServiceImpl;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping
    public boolean registerUserFN(@RequestBody UserRequestDto userRequestDto) {
        System.out.println("registerUserFN 실행은 됨 [디버그]");
        return userRegisterService.registerUser(userRequestDto);
    }

}
