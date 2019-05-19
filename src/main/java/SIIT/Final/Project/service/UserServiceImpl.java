package SIIT.Final.Project.service;

import SIIT.Final.Project.model.User;
import SIIT.Final.Project.repository.RoleRep;
import SIIT.Final.Project.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRep userRep;
    @Autowired
    private RoleRep roleRep;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRep.findAll()));
        userRep.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRep.findByUsername(username);
    }
}