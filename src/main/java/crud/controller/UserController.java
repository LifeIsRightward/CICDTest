package crud.controller;

import crud.entity.User;
import crud.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

//    @GetMapping
//    public ResponseEntity<?> getUsers(HttpServletRequest request) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        System.out.println("🔍 현재 인증된 사용자: " + authentication.getName());
//        System.out.println("🔍 현재 사용자 권한: " + authentication.getAuthorities());
//
//        HttpSession session = request.getSession(false); // ❗ 현재 세션 확인
//        if (session != null) {
//            System.out.println("✅ 세션 존재함: " + session.getId());
//        } else {
//            System.out.println("🚨 세션이 존재하지 않음 (JSESSIONID가 유효하지 않음)");
//        }
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("🚨 인증되지 않은 요청입니다.");
//        }
//
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }


    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println("디버그 Post 컨트롤러");
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

}
