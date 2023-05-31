package com.example.ldap.repositories;

import com.example.ldap.entities.Group;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends LdapRepository<Group> {
    Group findGroupByName(String name);
}
