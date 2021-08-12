# spring-enum-path-security-bug

This project demonstrates a bug found in the Security/Web parts of spring.

Tests are written that demonstrates the issue. The tests should all be passing, but one fails as an endpoint returns
`400 BAD REQUEST` instead of the expected `403 FORBIDDEN`.
