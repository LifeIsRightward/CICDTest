package crud.service;

import crud.entity.User;
import crud.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;

    // 로그인을 성공하면 onAuthenticationSuccess를 호출한다.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // 로그인 성공 시 UserDetail을 가져옴
        User userEntity = userRepository.findByName(userDetails.getUsername());

        System.out.println("커스텀 인증 성공 핸들러");
        // ✅ 세션에 사용자 정보 저장 (기존 기능 유지)
        request.getSession().setAttribute("user", userEntity);

        // ✅ JSON 응답을 반환하여 무한 리디렉트 방지
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "Login successful");
        responseData.put("user", userEntity.getName()); // 사용자 이름 반환 (필요한 정보 추가 가능)
        responseData.put("role", userEntity.getRole()); // 사용자 역할 추가

        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(responseData)); // JSON 변환 후 반환
        writer.flush();
        writer.close();




    }
}
