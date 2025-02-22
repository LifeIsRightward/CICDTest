package crud.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String major;

    public boolean checkPassword() {
        if(password != null && password.length() < 3) {
            System.out.println("패스워드 3자리 이하면 오류 찍혀요~");
            return false;
        }else{
            return true;
        }
    }
}
