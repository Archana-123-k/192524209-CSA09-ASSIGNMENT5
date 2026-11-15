# A4 - What Should Be Serialized?

## What to persist
Fields such as `name`, `regNo`, and room history should be serialized.

Fields that must not be persisted include:
- database `Connection`
- live session references
- caches or transient runtime data

These should be marked `transient`.

## Object graph
When serializing a `Hostel`, all reachable objects such as `Room` and `Resident` are serialized recursively unless marked transient.

## Versioning
If a field is added after old files already exist, deserialization may fail or result in default values depending on `serialVersionUID` and compatibility.

A stable `serialVersionUID` prevents accidental invalidation of old serialized data.
