package com.example.ldap.repositories;

import com.example.ldap.entities.User;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends LdapRepository<User> {
    User findByUid(String uid);
}
