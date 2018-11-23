
[![](https://img.shields.io/badge/Medium-Read-lightgrey.svg)](https://medium.com/@itzgeoff/fine-tuning-oauth2-in-spring-boot-3f1eff1ea4c0)

This application tests out Spring Boot's OAuth2 support for pre-defined providers. It also
configures the `RestOperations` used during user info retrieval to ensure the `text/javascript`
response from Facebook is processed as JSON.

## Application properties

As described in [this section](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-security.html#boot-features-security-oauth2-client)
you will need to provide the client ID and secret for one or more of the providers, such as

```
spring.security.oauth2.client.registration.facebook.client-id
spring.security.oauth2.client.registration.facebook.client-secret
```