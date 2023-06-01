package com.example.ldap.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;

import javax.naming.Name;

@Entry(
        base = "ou=people",
        objectClasses = {"top", "person", "organizationalPerson", "inetOrgPerson"}
)
@Setter
@Getter
@ToString
public class User {
    @Id
    private Name id;
    @Attribute(name = "cn")
    private String fullName;
    @Attribute(name = "sn")
    private String name;
    @Attribute(name = "uid")
    @DnAttribute(value = "uid", index = 1)
    private String uid;
    private @Attribute(name = "userPassword") String password;
}
