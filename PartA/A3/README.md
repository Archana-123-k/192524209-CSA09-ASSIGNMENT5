# A3 - Discover the Data-Access Design Pattern

## Common pattern
The repeated steps across StudentDB, CourseDB, and MarksDB are:
- open connection
- build SQL
- execute query
- map ResultSet to objects
- close resources

The differing parts are:
- table name
- SQL fields
- object type
- row mapping logic

## Generic interface
```java
public interface Dao<T, K> {
    void insert(T entity);
    T findById(K id);
    List<T> findAll();
    void update(T entity);
    void delete(K id);
}
```

## StudentDao example
```java
public class StudentDao implements Dao<Student, Integer> { ... }
```

## Object-to-table mapping
`Student` fields map to columns in `student` table:
- studentId -> PRIMARY KEY
- name -> name
- regNo -> reg_no
- department -> department
- email -> email
