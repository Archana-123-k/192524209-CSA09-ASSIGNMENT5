# A1 - Design a File Format

## Record layout for text file
Fields stored in order:
1. passId
2. regNo
3. name
4. routeNo
5. validityDate
6. amountPaid

Format:
`passId|regNo|name|routeNo|validityDate|amountPaid`

Delimiter: `|`

If a field contains the delimiter, escape it as `\|` or use CSV quoting rules.

## Binary record design
A fixed-width binary layout may use:
- passId: 20 bytes
- regNo: 20 bytes
- name: 60 bytes
- routeNo: 4 bytes (int)
- validityDate: 10 bytes (yyyy-MM-dd)
- amountPaid: 8 bytes (double)

## Operations
- addPass: append a valid record
- findByPassId: scan the file and compare IDs
- listByRoute: read each record and filter on route

## Damaged data handling
- corrupted line detected by field count mismatch or invalid numeric parsing
- missing file handled by creating a new file or returning a friendly error
- incomplete write prevented by validating the final record and using atomic replacement or journaled writes
