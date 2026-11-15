# CSA09 Assignment 5 – Java Data Persistence Solutions

This project contains working Java programs for the two code-complete tasks from the assignment:

- PassStore.java – file-based bus pass lookup with damaged-line skipping
- PassLoader.java – JDBC batch insert in a single transaction

## Java version

Tested with Java 17+

## Compile

```bash
javac PassStore.java PassLoader.java
```

## Run the file-store demonstration

```bash
java PassStore
```

Expected output:

```text
[P1003, 192311003, Divya, 7, 3800.0]
null
```

## Notes

- PassStore deliberately ignores malformed lines that do not contain exactly five comma-separated fields.
- PassLoader wraps every insert in a transaction and batches rows in groups of 1000.
- A rollback is issued if any record in the batch is invalid or the database operation fails.
