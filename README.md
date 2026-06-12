# AetherAI

AetherAI is an offline-first Android application built using Jetpack Compose, MVVM, Room, DataStore, WorkManager, Paging 3, Coroutines, StateFlow, and Hilt.

## Architecture

The project follows Clean Architecture principles:

```text
Presentation
    ↓
Domain
    ↓
Data
```

### Layers

#### Presentation

* Jetpack Compose UI
* ViewModels
* StateFlow based state management

#### Domain

* Models
* Repository Interfaces
* Business Logic

#### Data

* Room Database
* DataStore
* Repository Implementations
* WorkManager Sync

---

## Features

### Onboarding

* 3-step onboarding flow
* User profile collection
* Mock OTP verification (1234)
* Personality trait selection
* Data persistence using DataStore

### Home Screen

* Aura Circle built using Canvas API
* Audio amplitude visualization
* Keyboard input panel
* Swipe-up transition to chat history

### Chat History

* Room database storage
* Paging 3 support
* Flow-based updates

### State Machine

Message processing pipeline:

```text
Typing
↓
Validating
↓
Processing
↓
Responding
↓
Idle
```

Features:

* Cancellation support
* Timeout handling
* Error state

### Offline First

* Room database
* Flow queries
* WorkManager synchronization
* Sync status observable via StateFlow

---

## Tech Stack

* Kotlin
* Jetpack Compose
* Hilt
* Room
* DataStore
* Paging 3
* Coroutines
* StateFlow
* WorkManager

---

## Testing

Implemented tests:

* Happy Path Transition
* Cancellation Mid-Flow
* Timeout State Validation

---

## System Design

See:

```text
docs/system_design.md
```
