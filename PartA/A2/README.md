# A2 - Don’t Read What You Don’t Need

## Prediction
A 50 MB file read one byte at a time using `FileInputStream.read()` causes a very high number of OS-level read calls, which makes the program slow.

## Buffered redesign
Use `BufferedInputStream` or `BufferedReader` to read a block of bytes into memory and process them in a loop.

This reduces the number of system calls and increases throughput significantly.

## When buffering is not useful
If the file is already in memory or the workload is very small, buffering gives little benefit.

## Alternative concept
Using `Files.readAllLines()` reads the whole file into memory at once, which is acceptable for moderate files but less memory-efficient for large datasets.
