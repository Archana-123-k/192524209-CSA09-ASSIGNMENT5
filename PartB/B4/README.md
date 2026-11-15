# B4 - Final Engineering Decision

## Recommended strategy
For 12 campuses and high concurrent access, a database is the best long-term choice because it provides:
- transaction safety
- concurrency control
- indexing and searching
- centralized validation and reporting

## Why not text files or serialization
Text files are simple but poor for concurrent updates and large reads.
Serialization is convenient for local persistence but is not suitable for concurrent multi-user production systems.

## Decision
Use MySQL or another relational database with transactional inserts and indexed search on pass ID and route.
