# Week 2: Observer Pattern Summary

## What is the Observer Pattern?

The Observer Pattern is a behavioural design pattern that allows one object (Subject) to notify other objects (Observers) whenever its state changes.

## Main Components

1. Subject – Maintains a list of observers.
2. Observer – Defines the update method.
3. ConcreteSubject – Stores data and notifies observers.
4. ConcreteObserver – Receives notifications and responds to changes.

## Advantages

- Reduces tight coupling.
- Supports multiple observers.
- Makes systems easier to maintain.
- Allows observers to be added or removed dynamically.
- Improves flexibility and reusability.

## Event Notification Mechanisms in Java

- Custom Observer Pattern
- java.util.Observer (deprecated)
- PropertyChangeListener
- JavaFX Bindings

## Conclusion

The Observer Pattern is important in event-driven programming because it allows objects to communicate through notifications. It makes applications more flexible, organised and easier to maintain.
