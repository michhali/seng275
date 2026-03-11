# Lab 06

## Overview
This lab focuses on **testing classes in isolation** using mocking techniques. In real applications, classes often depend on other objects, which makes it difficult to test them independently. Mocking allows these dependencies to be replaced with controlled test doubles so that the behavior of the class under test can be verified. :contentReference[oaicite:0]{index=0}

The lab introduces the **Mockito framework**, which is used to create mock objects, define their behavior, and verify how they are used during tests.

## Technologies
- Java
- JUnit
- Mockito
- AssertJ
- Git / GitLab

## Implementation
The lab involved several testing tasks:

- **Mocking Dependencies**: Replaced external objects with mock versions to isolate the class under test.
- **Stubbing Behavior**: Configured mock objects to return specific values when certain methods were called.
- **Interaction Verification**: Used Mockito verification features to confirm that expected methods were called.
- **Invoice Filtering Tests**: Tested the `InvoiceFilter` class by mocking the `IssuedInvoices` dependency.
- **Todo Application Tests**: Tested methods of the `TodoApplication` class using mocked dependencies.

## Running Tests
Tests can be executed in IntelliJ using the **JUnit runner**. Mockito automatically provides the mock objects required for the tests.

## What I Learned
- How to isolate classes during testing using **mock objects**
- The difference between **dummies, stubs, and mocks**
- How to verify interactions between objects
- How mocking frameworks improve unit testing of complex systems
