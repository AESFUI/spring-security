package ml.sadriev.security.service;

import java.util.Collections;
import javax.annotation.Resource;
import ml.sadriev.security.api.repository.UsersRepository;
import ml.sadriev.security.api.service.UsersService;
import ml.sadriev.security.enums.RoleType;
import ml.sadriev.security.model.Role;
import ml.sadriev.security.model.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersRepository usersRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Users findUserByNickName(String nickName) {
        return usersRepository.findUserByNickName(nickName);
    }

    @Transactional
    public void createUser(final String nickName, final String password, final RoleType roleType) {
        Users user = new Users();
        user.setNickName(nickName);
        user.setPasswordHash(passwordEncoder.encode(password));

        Role role = new Role();
        role.setRoleEnum(roleType);
        role.setUser(user);
        user.setRoles(Collections.singletonList(role));

        usersRepository.save(user);
    }
}
