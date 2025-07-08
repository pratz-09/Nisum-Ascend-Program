package com.nisum.demo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.autoconfigure.ldap.EmbeddedLdap;

@TestConfiguration
@com.nisum.demo.EmbeddedLdap(
    baseDn = "dc=springframework,dc=org",
    port = 8389,
    ldif = "classpath:test-ldap.ldif"
)
public class LdapTestConfig {
    // No beans needed, annotation does the work
}