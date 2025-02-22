package crud.service;

import crud.dto.UserRequestDto;
import crud.entity.User;
import crud.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    public UserRegisterServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }



    @Override
    public boolean registerUser(UserRequestDto userRequestDto) {
        System.out.println("userRequestDto: " + userRequestDto);
        if (userRepository.existsByname(userRequestDto.getName())) {
            System.out.println("User already exists");
            return false;
        }

        if (!userRequestDto.checkPassword()) {
            System.out.println("Wrong password");
            return false;
        }


        // ModelMapper -> DTO와 Entity를 변환
        User userEntity = new ModelMapper().map(userRequestDto, User.class);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        // Admin인 경우에는 별도로 처리하는 로직이 있어야 할 듯 하다.
        userEntity.setRole("USER");
        System.out.println("userEntity: "  + userEntity);

        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
