
package com.example.ldap.services;

import com.example.ldap.entities.Group;
import com.example.ldap.entities.User;
import com.example.ldap.repositories.GroupRepository;
import com.example.ldap.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private GroupRepository groupRepository;

    public User createUser(final String fullName, final String userName, final String password, final String role) {
        final User user = new User();
        user.setUid(userName);
        user.setName(fullName);
        user.setFullName(fullName);
        user.setPassword(password);
        userRepository.save(user);

        addGroupToUser(userName, role);

        return user;
    }

    public User changePassword(final String userName, final String oldPassword, final String newPassword) {
        final User user = this.fetchUser(userName);
        //TODO: Validate old password
        user.setPassword(newPassword);
        userRepository.save(user);
        return user;
    }

    public User fetchUser(final String userName) {
        return userRepository.findByUid(userName);
    }

    public List<String> fetchAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(User::toString)
                .collect(Collectors.toList());
    }

    private void addGroupToUser(final String userName, final String role) {
        final Name userDn = LdapNameBuilder
                .newInstance()
                .add("dc", "org")
                .add("dc", "springframework")
                .add("ou", "people")
                .add("uid", userName)
                .build();

        final Group group = groupRepository.findGroupByName(role);
        group.getMembers().add(userDn);
        groupRepository.save(group);
    }
}
