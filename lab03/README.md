# Lab 03

## Overview
This lab focuses on **Domain Testing** and **Specification Testing** using Java and JUnit. The goal is to understand how to design effective tests by analyzing the input domain of a function and identifying important boundary conditions.

Domain testing helps reduce the number of tests needed by dividing inputs into **equivalence classes** and testing representative values from each class. Boundary testing is used to detect errors that occur near the limits of valid input values. :contentReference[oaicite:0]{index=0}

The lab also introduces **Specification Testing**, where tests are written based on the documented behavior of a method without examining its implementation. :contentReference[oaicite:1]{index=1}

## Technologies
- Java
- JUnit
- IntelliJ IDEA
- Git / GitLab

## Implementation
The lab involved several testing tasks:

- **Boundary Testing**: Wrote tests for methods in `Boundary.java` by identifying ON, OFF, IN, and OUT points and selecting representative values from each equivalence class.
- **Specification Testing**: Designed tests for methods in `Specification.java` using only the method specifications.
- **Bug Identification**: Used tests to discover a defect in the `messageIsValid` implementation and documented it in a bug report.
- **Roman Numeral Converter**: Implemented and tested a program that converts Roman numerals to integers while detecting invalid inputs.
- **GameBoard Domain Testing**: Created tests for a 6×6 board to verify whether player coordinates fall inside or outside the board boundaries.

## Running Tests
Tests can be run in IntelliJ using the built-in **JUnit runner**. All tests should pass once the implementations and fixes are complete.

## What I Learned
- How to perform **domain and boundary testing**
- How to design tests using **equivalence classes**
- How to apply **specification-based testing**
- How tests can help detect and document software bugs
