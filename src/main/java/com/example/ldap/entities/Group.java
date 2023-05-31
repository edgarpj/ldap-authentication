package com.example.ldap.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.Set;

@Entry(objectClasses = {"top", "groupOfUniqueNames"}, base = "ou=groups")
@Setter
@Getter
public class Group {
    @Id
    private Name dn;

    @Attribute(name = "cn")
    @DnAttribute("cn")
    private String name;

    @Attribute(name = "uniqueMember")
    private Set<Name> members;
}
