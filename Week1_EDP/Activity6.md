# Activity 6: Final Self-Assessment

## Part 1: Self-Assessment

1. What is event-driven programming?
   - A programming approach where program execution responds to events.

2. What is an event?
   - An action or occurrence detected by a program, such as a button click.

3. What is an event source?
   - A component that generates events.

4. What is an event listener?
   - A component that detects and receives events.

5. What is an event handler?
   - Code that executes when an event occurs.

6. What is the Event Dispatch Thread (EDT)?
   - The thread responsible for processing GUI events and updating Swing components.

7. Why should long-running tasks not execute on the EDT?
   - They can make the graphical interface unresponsive.

8. How can a background task update a Swing component safely?
   - By using SwingUtilities.invokeLater().

9. What is an ActionListener?
   - A Java interface that handles action events such as button clicks.

10. What is the difference between procedural and event-driven programming?
    - Procedural programming follows a sequence of instructions, while event-driven programming responds to events.

11. Why are event listeners important?
    - They allow programs to detect user interactions.

12. What is a memory leak?
    - A situation where objects that are no longer needed remain referenced and cannot be collected.

13. How did you fix the memory leak in Activity 4?
    - By clearing the createdButtons ArrayList when removing buttons.

14. What is Java Swing?
    - A Java library used to create graphical user interfaces.

15. Give one example of an event-driven system.
    - An ATM that responds to keypad and card-reader events.

## Part 2: Summary

During this practical, I learned the basic concepts of event-driven programming. I identified event sources, events, listeners, and handlers in everyday systems. I also compared procedural programming with event-driven programming by creating two calculator applications.

I learned how to use Java Swing components such as buttons, labels, and text fields. I practised using ActionListeners to respond to user interactions. I also fixed common event-handling problems, including blocking the Event Dispatch Thread, missing listeners, updating Swing components from the wrong thread, and memory leaks.

Finally, I designed and developed a Smart Home Controller that responds to user actions. This practical improved my understanding of how interactive applications work and how event-driven programming is used to develop responsive graphical applications.