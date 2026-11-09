# Week 2: Observer Pattern – Self-Assessment

## Learning Outcome 1: Design and Implement the Observer Pattern

1. What are the four main participants in the Observer Pattern?

Answer: Subject, Observer, ConcreteSubject and ConcreteObserver.

2. Write a Java Subject interface.

Answer:
```java
public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
```

3. Why does the Subject need a list of observers?

Answer: To keep track of registered observers so it can notify them when its state changes.

## Learning Outcome 2: Evaluate Legacy java.util.Observer

4. List three problems with java.util.Observable.

Answer:
- It requires inheritance.
- Notification order is unspecified.
- It uses a confusing setChanged() method.

5. Why was this API deprecated?

Answer: Because it has design limitations, poor flexibility and is not thread-safe.

6. What is the Pull Model and why is it harder to use?

Answer: The observer requests data from the Subject using getter methods. This requires extra method calls and makes communication less direct.

## Learning Outcome 3: Create a Custom Event Notification System

7. What methods must a custom Subject interface provide?

Answer: addObserver(), removeObserver() and notifyObservers().

8. How does the Subject notify all observers?

Answer: It loops through its observer list and calls each observer's update method.

9. How do you prevent a memory leak when observers are no longer needed?

Answer: Remove observers when they are no longer needed.

## Learning Outcome 4: Compare Different Mechanisms

10. Name four event notification mechanisms in Java.

Answer:
- Custom Observer Pattern
- java.util.Observer
- PropertyChangeListener
- JavaFX Bindings

11. Which one is best for JavaFX?

Answer: JavaFX Bindings.

12. Which one is deprecated?

Answer: java.util.Observer and java.util.Observable.