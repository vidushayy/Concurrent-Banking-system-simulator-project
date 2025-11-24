Concurrent Banking System Simulator

Project Overview

This project implements a console-based simulation of a banking system, specifically designed to test and demonstrate concurrent transaction processing. It utilizes multithreading in Java to simulate multiple independent "customers" simultaneously accessing and modifying shared account balances.

The core purpose of this simulator is to ensure data integrity by preventing race conditions through explicit thread synchronization techniques, proving that financial transactions remain atomic and consistent even under heavy load.

Features

The system fulfills the following functional requirements:

Account Management: Allows creation of bank accounts with unique IDs and initial balances.

Transaction Processing: Supports synchronized deposit and withdraw operations.

Concurrency Testing: Utilizes the Runnable interface and multiple Thread objects to simulate simultaneous user access.

Minimum Balance Constraint: Withdrawal logic checks against a minimum required balance ($100.00) and enforces this rule using a custom checked exception.

Exception Handling: Uses the custom checked exception InsufficientFundsException to gracefully handle failed withdrawals.

Data Persistence: Supports saving and loading the entire state of the bank (all accounts) to a local file using Java Object Serialization.

Logging: Outputs transaction attempts, successes, and failures clearly to the console for verification.

Technical Highlights (Concurrency & Design)

Area

Component

Implementation Detail

Thread Safety

Account methods (deposit, withdraw)

Secured using the synchronized keyword to enforce mutual exclusion on the account object's intrinsic lock. This prevents "lost updates" and guarantees transaction atomicity.

Resource Management

Bank collection

Uses ConcurrentHashMap to store accounts, ensuring thread-safe access to the collection structure itself.

Architecture

Classes

Adheres to OOP principles, separating the Model (Account), Controller (Bank), and Concurrent Task (CustomerTask).

Error Handling

Business Logic

Custom InsufficientFundsException forces developers to handle foreseeable financial rule violations, increasing reliability.

Getting Started

Prerequisites

To run this project, you need:

Java Development Kit (JDK) 17 or higher.

A Java IDE (like IntelliJ IDEA, Eclipse, or VS Code) or the ability to compile and run Java from the command line.

How to Run the Simulation

Clone the Repository:

git clone [https://github.com/vidushayy/Concurrent-Banking-system-simulator-project.git](https://github.com/vidushayy/Concurrent-Banking-system-simulator-project.git)
cd Concurrent-Banking-system-simulator-project


Compile and Run (via Command Line):
Assuming your source files are in a standard structure (e.g., src/):

# Compile
javac src/*.java 

# Run the main application (replace 'MainApp' with your main class name)
java MainApp 


Monitor Output:
The application will create accounts, start multiple threads, and log interleaved deposit and withdrawal operations to the console. After the threads complete, it will display the final validated balances and save the state to accounts.dat.

Documentation and Report

For a deep dive into the system's design, architecture, rationale, and formal testing approach, please refer to the complete project report:

Detailed Project Report (PDF) (This refers to the file generated in the previous step.)

Repository Link: https://github.com/vidushayy/Concurrent-Banking-system-simulator-project
