package SIIT.Final.Project.service;


import SIIT.Final.Project.model.User;

public interface UserDetailsService {
    void save(User user);

    User findByUsername(String username);
}