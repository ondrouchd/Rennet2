# Rennet v1.0 — Architecture Review

## Overview

**Rennet** (Sýrařův rádce / Cheese-maker's Advisor) is a Java SE console application
designed to assist home cheese makers with calculations and recipe planning.

> **Note:** The original source code is hosted on Azure DevOps at
> `https://luweko.visualstudio.com/Rennet/_versionControl` and is not publicly accessible.
> This document describes the reconstructed reference implementation in the
> `java-reference/` directory based on the project name, description ("Syraruv radce"),
> and standard cheese-making domain knowledge.

## Application Structure

```
java-reference/
├── pom.xml                              # Maven build configuration (Java 11+)
└── src/main/java/cz/luweko/rennet/
    ├── Main.java                        # Entry point
    ├── model/
    │   ├── CheeseType.java              # Cheese type definitions with production parameters
    │   ├── MilkType.java                # Milk type definitions with fat content
    │   └── Recipe.java                  # Complete recipe with all calculated values
    ├── calculator/
    │   ├── RennetCalculator.java        # Core rennet dosage and yield calculations
    │   └── TemperatureConverter.java    # Temperature conversion utilities
    └── ui/
        └── ConsoleUI.java              # Interactive console user interface
```

## Domain Model

### MilkType
Enumerates the common milk types used in cheese making:
- **Cow** (Kravské) — ~3.5% fat, the standard baseline
- **Goat** (Kozí) — ~4.0% fat
- **Sheep** (Ovčí) — ~6.5% fat
- **Buffalo** (Buvolí) — ~7.5% fat

### CheeseType
Defines cheese varieties with their production parameters:
- **Optimal temperature** (°C) for coagulation
- **Coagulation time** (minutes)
- **Rennet strength factor** — multiplier for standard dosage

Supported types: Fresh, Camembert, Gouda, Cheddar, Parmesan, Mozzarella, Feta, Brie.

### Recipe
A value object that aggregates all calculated production parameters for a specific
cheese-making session, including rennet dosage, temperature, timing, and expected yield.

## Core Calculations

### Rennet Dosage
```
rennet_ml = milk_liters × 0.2 × cheese_strength_factor × (milk_fat% / 3.5)
```
- Base dosage: **0.2 ml** of liquid rennet (1:10000 strength) per liter of milk
- Adjusted by cheese type (e.g., Parmesan needs 1.2× more rennet)
- Adjusted by milk fat content (higher fat = proportionally more rennet)

### Yield Estimation
```
yield_kg = milk_liters × 0.10 × cheese_type_multiplier × (milk_fat% / 3.5)
```
- Base yield: ~10% of milk volume (1 kg cheese per 10 liters of cow's milk)
- Soft cheeses yield more (higher moisture): Fresh = 1.5×, Mozzarella = 1.3×
- Hard cheeses yield less (lower moisture): Parmesan = 0.8×

## User Interface

The v1.0 application provides a console-based menu with:
1. **Recipe Calculator** — select milk type, cheese type, and volume to get a
   complete recipe with rennet dosage, temperature, timing, and expected yield
2. **Cheese Types Overview** — reference table of all supported cheese types
3. **Temperature Converter** — °C ↔ °F conversion with cheese-making safety check

## Building and Running

```bash
cd java-reference
mvn clean package
java -jar target/rennet-1.0.0.jar
```

Or compile directly:
```bash
cd java-reference/src/main/java
javac cz/luweko/rennet/Main.java
java cz.luweko.rennet.Main
```

## Migration Notes for Rennet2 (Dart/Flutter)

When porting to Dart/Flutter for the Google Play Store release:

1. **Domain model** → Dart classes with named constructors and `toString()` overrides
2. **Calculator logic** → Pure Dart functions, easily testable
3. **Console UI** → Replace with Flutter Material Design widgets
4. **Persistence** → Add local storage (SharedPreferences or SQLite) for saved recipes
5. **Localization** → Use Flutter's `intl` package for Czech/English support
6. **Additional features** for v2:
   - Timer widget with notifications for coagulation
   - Recipe history and favorites
   - Photo documentation of cheese-making process
   - Community recipe sharing
