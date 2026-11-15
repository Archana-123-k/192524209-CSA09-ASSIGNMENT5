# B2 - Adversarial Input and Defensive Design

## Unsafe login example
```java
String sql = "SELECT * FROM users WHERE id='" + id + "' AND pwd='" + pwd + "'";
```

A malicious input like:
```text
id = admin' --
pwd = anything
```
can bypass password validation.

## Safer version
Use `PreparedStatement` so the query is parameterized and the database handles escaping safely.

## Damaged bus-pass file examples
- wrong number of fields
- non-numeric amount
- empty line

The file store should skip malformed lines instead of crashing or returning corrupt data.
