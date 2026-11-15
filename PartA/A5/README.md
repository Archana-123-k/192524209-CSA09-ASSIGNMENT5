# A5 - Design for Correctness and Safety

## Transaction pseudocode
```text
BEGIN TRANSACTION
UPDATE wallet SET balance = balance - 500 WHERE id = 'student'
UPDATE wallet SET balance = balance + 500 WHERE id = 'canteen'
IF any update fails THEN
    ROLLBACK
ELSE
    COMMIT
END IF
```

## SQL injection example
A query like:
`SELECT * FROM users WHERE id='admin' AND pwd=' ' OR '1'='1'`
can bypass the password check.

## Safer design
Use `PreparedStatement` with bind variables:
```java
PreparedStatement ps = con.prepareStatement(
    "SELECT * FROM users WHERE id = ? AND pwd = ?");
ps.setString(1, id);
ps.setString(2, pwd);
```
