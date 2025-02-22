package crud.service;

import crud.entity.User;
import crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("🔥 loadUserByUsername() 호출됨 - 입력된 username: '" + username + "'");

        if (username == null || username.trim().isEmpty()) {
            System.out.println("🚨 오류: 빈 username 값이 전달됨!");
            throw new UsernameNotFoundException("잘못된 사용자 입력입니다.");
        }

        User userEntity = userRepository.findByLoginId(username);
        if (userEntity == null) {
            System.out.println("🚨 사용자 없음: " + username);
            throw new UsernameNotFoundException("등록된 사용자가 없습니다.");
        }

        System.out.println("✅ 사용자 인증 성공: " + username);
        return new CustomUserDetails(userEntity);
    }

}
