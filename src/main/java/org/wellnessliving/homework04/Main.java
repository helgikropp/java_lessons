package org.wellnessliving.homework04;

import org.wellnessliving.homework04.entity.Role;
import org.wellnessliving.homework04.entity.User;
import org.wellnessliving.homework04.repository.RoleRepositoryImpl;
import org.wellnessliving.homework04.repository.UserRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RoleRepositoryImpl roleRepository = new RoleRepositoryImpl();
        Role roleOwner = new Role("Owner");
        Role roleAdmin = new Role("Admin");
        Role roleManager = new Role("Manager");
        Role roleUser = new Role("User");
        Role roleGuest = new Role("Guest");

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user01 = new User();
        user01.setEmail("test01@test.com");
        user01.setName("NewUser01");
        user01.setRoles(List.of(roleOwner, roleAdmin));
        userRepository.create(user01);

        User user02 = new User();
        user02.setEmail("test02@test.com");
        user02.setName("NewUser02");
        user02.setRoles(List.of(roleAdmin, roleManager));
        userRepository.create(user02);

        User user03 = new User();
        user03.setEmail("test03@test.com");
        user03.setName("NewUser03");
        user03.setRoles(List.of(roleManager, roleUser, roleGuest));
        userRepository.create(user03);

        Role roleSaved = roleRepository.findById(5L).orElseThrow(() -> new RuntimeException("404 Not found"));
        System.out.println(roleSaved);

        User userSaved = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("404 Not found"));
        System.out.println(userSaved);
        userSaved.getRoles().forEach(role -> System.out.println(role.getName()));

        userSaved.setEmail("testtest@test.com");
        userRepository.update(userSaved);
        userSaved = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("404 Not found"));
        System.out.println(userSaved);
    }
}
