package crud.service;

import crud.dto.UserRequestDto;
import crud.entity.User;

public interface UserRegisterService {
    boolean registerUser(UserRequestDto userRequestDto);
}
