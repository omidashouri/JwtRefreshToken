package ir.omidashouri.service;

import ir.omidashouri.domain.RoleEntity;
import ir.omidashouri.domain.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity saveUser(UserEntity user);
    RoleEntity saveRole(RoleEntity role);
    void addRoleToUser(String username, String roleName);
    UserEntity getUser(String username);
    List<UserEntity> getUsers();

}
