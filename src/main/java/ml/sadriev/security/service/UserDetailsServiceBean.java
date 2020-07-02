package ml.sadriev.security.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import ml.sadriev.security.model.Role;
import ml.sadriev.security.model.Users;
import ml.sadriev.security.api.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceBean implements UserDetailsService {

    @Resource
    private UsersRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Users user = findByLogin(username);
        if (user == null) throw new UsernameNotFoundException("User not found.");
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPasswordHash());
        final List<Role> userRoles = user.getRoles();
        final List<String> roles = new ArrayList<>();
        for (Role role: userRoles) roles.add(role.toString());
        builder.roles(roles.toArray(new String[] {}));
        return builder.build();
    }
    private Users findByLogin(String username) {
        return userRepository.findUserByNickName(username);
    }
}