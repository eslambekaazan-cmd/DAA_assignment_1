DAA Assignment 1: Divide and Conquer Algorithms

Repository for Assignment 1 (Divide and Conquer & Asymptotic Notations).

Features & Optimizations
- **MergeSort:** Single reusable buffer array and Insertion Sort cutoff for subarrays N <= 15.
- **QuickSort:** Randomized pivot selection, 3-way partitioning for duplicates, and smaller-side recursion to bound depth to O(log n).
- **QuickSelect:** Finds the k-th smallest element in O(n) average time using 3-way partition.

Benchmark & Results
Benchmarks run on sizes N = 1000, 10000, 100000, 1000000 across random, sorted, and duplicates inputs. Each test is repeated 5 times, storing the median in results.csv.

Included Plots:
- **time_vs_n.png** - Execution time vs N
- **depth_vs_n.png** - Recursion depth vs N
- **ratio_vs_n.png** - Empirical comparison ratio vs N

Full analysis and Master Theorem proofs are in **REPORT.md**.

Project Structure
- **src/main/java/** - Core algorithms, Metrics counter, and Main benchmark runner
- **src/test/java/** - JUnit 5 tests for correctness, edge cases, and stack depth
- **results.csv** - Benchmark outputs
- **REPORT.md** - Theoretical analysis report
- ***.png** - Generated benchmark charts

How to Run
Requirements: Java 17+, Maven.

- **Run tests:** mvn test
- **Run benchmark:** mvn exec:java -Dexec.mainClass="Main" (or run Main.java in IntelliJ IDEA)

---
**Course:** Design and Analysis of Algorithms  
**Release Tag:** v1.0