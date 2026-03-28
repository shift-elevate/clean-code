# Clean Code in Java 🧹

An educational Maven project demonstrating common code smells and their refactoring solutions in Java with practical, real-world examples.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Prerequisites](#prerequisites) 
- [Project Structure](#project-structure)
- [Quick Start](#quick-start)
- [Available Code Smells](#available-code-smells)
- [Running Instructions](#running-instructions)
- [Testing](#testing)
- [Contributing](#contributing)

## 🎯 Project Overview

This project serves as an educational resource for learning clean code principles through hands-on examples. Each code smell is demonstrated with:

- ✅ Before and after code comparisons
- ✅ Interactive console demonstrations
- ✅ Unit tests showing improvements

## 📋 Available Code Smells

### Size & Complexity Problems
- **Long Method**: Extract Method refactoring to break down complex methods
- **Long Parameter List**: Introduce Parameter Object refactoring to group related parameters
- **Magic Numbers**: Replace Magic Number with Named Constant refactoring to improve readability
- **Large Class**: Extract Class refactoring to break down large classes into focused, single-responsibility classes
- **Data Clumps**: Extract Class refactoring to group related data that always appear together into meaningful objects
- **Hardcoded Strings**: Replace Magic String with Named Constant refactoring to centralize string management using typesafe enums
- **Primitive Obsession**: Replace Primitive with Object refactoring to create rich domain models with meaningful types

### OOP Anti-Patterns
- **Switch Statements**: Replace Conditional with Polymorphism to eliminate switch statements
- **Temporary Field**: Extract Class refactoring to organize instance variables only used in certain circumstances into dedicated classes

### Dependency Problems
- **Feature Envy**: Move Method refactoring to place methods with the data they operate on
- **Circular Dependencies**: Dependency Inversion refactoring to break circular dependencies with interfaces

## 🔧 Prerequisites

- **Java 21** (OpenJDK 21.0.7+)
- **Maven 3.9+**
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)

### Installing Java 21

```bash
# Using SDKMAN (recommended)
sdk install java 21.0.7-tem

# For this project only (recommended approach)
# The project includes .sdkmanrc file for automatic environment switching
sdk env

# Or manually switch for this session only
sdk use java 21.0.7-tem

# Verify installation
java --version
```

## 📁 Project Structure

```
clean-code/
└── src/main/java/com/cleancode/
    ├── domain/
    ├── sizecomplexity/
    │   ├── longmethod/
    │   ├── longparameterlist/
    │   ├── magicnumbers/
    │   ├── largeclass/
    │   ├── dataclumps/
    │   ├── hardcodedstrings/
    │   └── primitiveobsession/
    ├── oopantipatterns/
    │   ├── switchstatements/
    │   └── temporaryfield/
    ├── dependencyproblems/
    │   ├── featureenvy/
    │   └── circulardependencies/
    └── Main.java
```

**Code Smell Organization:**
- Each code smell has its own dedicated package
- Clear separation between problematic and refactored code
- Shared domain models in `com.cleancode.domain` package
- Comprehensive test coverage for each smell

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/shift-elevate/clean-code.git
cd clean-code
```

### 2. Setup Environment (Automatic with SDKMAN)

The project includes a `.sdkmanrc` file that automatically configures Java 21 and Maven when you enter the directory:

```bash
# Activate the project environment (Java 21 + Maven)
sdk env

# This will automatically switch to:
# - Java 21.0.7-tem
# - Maven 3.9.10
```

### 3. Build the Project

```bash
mvn clean compile
```

### 4. Run the Main Application

```bash
mvn exec:java -Dexec.mainClass="com.cleancode.Main"
```

This will launch the main demonstration showing all implemented code smells and their refactored solutions.

## 📚 Running Instructions

### 🎯 **Recommended: Run Main Application for Complete Demo**

The best way to see all code smells and refactoring solutions in action:

```bash
# Run the main application with all demonstrations
mvn exec:java -Dexec.mainClass="com.cleancode.Main"

# Or compile and run directly
mvn clean compile
java -cp target/classes com.cleancode.Main
```

### 📦 **Advanced: Build and Run JAR**

For standalone execution:

```bash
# Build executable JAR
mvn clean package

# Run the JAR
java -jar target/clean-code-java-1.0.0.jar
```

## 🧪 Testing

### **Run All Tests**

```bash
mvn test
```

### **Run Individual Code Smell Demonstrations**

```bash
# Long Method Code Smell
mvn exec:java@long-method

# Switch Statements Code Smell
mvn exec:java@switch-statements

# Primitive Obsession Code Smell
mvn exec:java@primitive-obsession

# Long Parameter List Code Smell
mvn exec:java@long-parameter-list

# Magic Numbers Code Smell
mvn exec:java@magic-numbers

# Large Class Code Smell
mvn exec:java@large-class

# Data Clumps Code Smell
mvn exec:java@data-clumps

# Hardcoded Strings Code Smell
mvn exec:java@hardcoded-strings

# Feature Envy Code Smell
mvn exec:java@feature-envy

# Circular Dependencies Code Smell
mvn exec:java@circular-dependencies

# Temporary Field Code Smell
mvn exec:java@temporary-field
```

**Note**: Each execution ID runs only the specific code smell demonstration, not all of them.

### **Run All Code Smells Together**

```bash
# Run the main application with all demonstrations
mvn exec:java -Dexec.mainClass="com.cleancode.Main"
```

### **Run Specific Test Class**

```bash
# Long Method Code Smell
mvn test -Dtest=LongMethodTest

# Long Parameter List Code Smell
mvn test -Dtest=LongParameterListTest

# Magic Numbers Code Smell
mvn test -Dtest=MagicNumbersTest

# Large Class Code Smell
mvn test -Dtest=LargeClassTest

# Data Clumps Code Smell
mvn test -Dtest=DataClumpsTest

# Hardcoded Strings Code Smell
mvn test -Dtest=HardcodedStringsTest

# Switch Statements Code Smell
mvn test -Dtest=SwitchStatementsTest

# Primitive Obsession Code Smell
mvn test -Dtest=PrimitiveObsessionTest

# Feature Envy Code Smell
mvn test -Dtest=FeatureEnvyTest

# Circular Dependencies Code Smell
mvn test -Dtest=CircularDependenciesTest

# Temporary Field Code Smell
mvn test -Dtest=TemporaryFieldTest
```

## 🐛 Troubleshooting

### **Common Issues**

**Java Version Issues**:
```bash
# Check Java version
java --version

# Ensure Java 21 is active
sdk current java
```

**Maven Compilation Issues**:
```bash
# Clean and rebuild
mvn clean compile

# Skip tests if needed
mvn compile -DskipTests
```

**Class Path Issues**:
```bash
# Ensure proper classpath when running
java -cp target/classes com.cleancode.Main
```
