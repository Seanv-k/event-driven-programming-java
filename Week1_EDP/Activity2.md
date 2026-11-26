# Activity 2: Build the Event Loop

## Questions and Answers

**1. What happened to the other events while you were stuck on the slow handler?**

The other events remained in the event queue waiting to be processed. They could not be handled until the current event finished.

**2. What would a user see if this happened in a real application?**

The user would experience a frozen or unresponsive application. Buttons might stop responding, and the window might not respond to user interactions.

**3. How could this problem be fixed?**

The problem can be fixed by moving long-running operations to a background thread so that the Event Dispatch Thread remains available to process other events.

## Reflection

The event queue stores events that are waiting to be processed by the application. The event loop continuously checks the queue and processes events one at a time. When an event occurs, it is placed in the queue until the event loop handles it. Each event is processed by its corresponding event handler. The event loop should never be blocked because this prevents other events from being processed. Blocking the event loop can make an application freeze or become unresponsive. Therefore, long-running operations should be handled in background threads.