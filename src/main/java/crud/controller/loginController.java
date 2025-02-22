package crud.controller;

import crud.entity.User;
import crud.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class loginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 🔥 로그인 API (세션 기반)
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData,
                                     HttpServletRequest request, HttpServletResponse response) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        User user = userRepository.findByLoginId(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("🚨 로그인 실패: 사용자 없음 또는 비밀번호 불일치");
        }

        // ✅ Spring Security 인증 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // ✅ 세션 강제 생성 및 사용자 정보 저장
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // ✅ 인증 정보 저장
        session.setAttribute("user", user);
        System.out.println("✅ 로그인 성공! JSESSIONID: " + session.getId());

        // ✅ 응답 반환
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "Login successful");
        responseBody.put("user", user.getName());

        return responseBody;
    }


    // 🔥 로그아웃 API
    @PostMapping("/logout")
    public Map<String, String> logout(HttpSession session) {
        session.invalidate(); // ✅ 세션 무효화
        System.out.println("✅ 로그아웃 성공");

        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return response;
    }
}
