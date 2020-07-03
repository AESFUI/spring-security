package ml.sadriev.security;

import javax.annotation.Resource;
import ml.sadriev.security.api.service.UsersService;
import ml.sadriev.security.model.Users;
import org.springframework.stereotype.Component;

@Component
public class Testing {

    @Resource
    private UsersService usersService;

    public void start() {

    }



    private void getNewAdmin(Users user) {
        user.setNickName("admin");
        user.setPasswordHash("qwerty");
    }

    private Users getNewUser(String nickName) {
        Users user = new Users();
        user.setNickName(nickName);
        user.setPasswordHash("12345");
        return user;
    }
}
