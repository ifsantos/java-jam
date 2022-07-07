# Authentication server: Keycloack embedded in Springboot app


### References
- [Implementation guide](https://www.baeldung.com/keycloak-embedded-in-spring-boot-app)
- [GIT repository example](https://github.com/Baeldung/spring-security-oauth/tree/master/oauth-rest/oauth-authorization-server)
- [Keycloak issue #10113: Update H2 database](https://github.com/keycloak/keycloak/issues/10113)
- [H2 database vulnerabilites and discussions related to 1.4 version (the only one working with Keycloak, so far)](https://mvnrepository.com/artifact/com.h2database/h2/1.4.200)
  - [CVE-2021-23463](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2021-23463)
  - <https://github.com/h2database/h2database/issues/3195>
  - <https://github.com/mysql/mysql-connector-j/commit/4993d5735fd84a46e7d949ad1bcaa0e9bb039824>
  - [CVE-2021-42392](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2021-42392)
  - [CVE-2022-23221](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2022-23221)
  - <https://packetstormsecurity.com/files/165676/H2-Database-Console-Remote-Code-Execution.html>