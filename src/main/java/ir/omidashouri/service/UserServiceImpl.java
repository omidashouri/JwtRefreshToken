package ir.omidashouri.service;

import ir.omidashouri.domain.RoleEntity;
import ir.omidashouri.domain.UserEntity;
import ir.omidashouri.repository.RoleRepository;
import ir.omidashouri.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity ==null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else{
            log.error("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        log.info("saving new user {} to the database.",user.getName());
        return userRepository.save(user);
    }

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        log.info("saving new role {} to the database.", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding role {} to user {}", roleName, username);
        UserEntity user = userRepository.findByUsername(username);
        RoleEntity role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserEntity getUser(String username) {
        log.info("fetching user {} ", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserEntity> getUsers() {
        log.info("fetching all users ");
        return userRepository.findAll();
    }


}
