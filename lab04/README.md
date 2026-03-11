# Lab 04

## Overview
This lab focuses on improving the quality of unit tests using **code coverage** and **mutation testing**. The objective is to evaluate how thoroughly tests exercise the code and to strengthen them when weaknesses are discovered.

Coverage tools are used to measure how much of the code is executed during testing. This includes **line coverage** and **branch coverage**, which help identify untested parts of the program. Mutation testing is then used to verify the strength of the test suite by introducing small changes to the code and checking whether the tests detect them. :contentReference[oaicite:0]{index=0}

## Technologies
- Java
- JUnit
- IntelliJ IDEA
- Git / GitLab
- PITest

## Implementation
The lab involved several testing tasks:

- **Unit Testing**: Tested the methods provided in the repository.
- **Line Coverage**: Used IntelliJ’s coverage tools to achieve **100% line coverage**.
- **Branch Coverage**: Enabled branch tracing to ensure all conditional paths were tested.
- **Mutation Testing**: Generated a PITest report and improved tests to kill surviving mutants.
- **Additional Test Design**: Created tests to achieve full coverage for provided pseudocode and methods.

## Running Tests
Tests can be run in IntelliJ using the built-in **JUnit runner**. Code coverage can be viewed using the **Run with Coverage** feature, and mutation testing can be performed using the **PITest plugin**.

## What I Learned
- How to measure test quality using **code coverage**
- The difference between **line coverage and branch coverage**
- How **mutation testing** exposes weak tests
- How to improve tests to better detect faults
