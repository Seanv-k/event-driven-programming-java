# Smart Home Controller

## 1. System Overview

The Smart Home Controller is an event-driven application that allows a user to control household devices through a graphical interface.

## 2. Event Sources

- Light button
- Fan button
- Door lock button
- Temperature control

## 3. Events

- Light button clicked
- Fan button clicked
- Door lock button clicked
- Temperature changed

## 4. Event Listeners

ActionListeners detect when the user interacts with the buttons. A change listener detects temperature adjustments.

## 5. Event Handlers

- Light handler: switches the light ON or OFF.
- Fan handler: switches the fan ON or OFF.
- Door handler: locks or unlocks the door.
- Temperature handler: updates the selected temperature.

## 6. System State

The interface displays the current state of each device so the user can see whether it is ON, OFF, LOCKED, or UNLOCKED.

## 7. Conclusion

The Smart Home Controller uses event-driven programming because the system responds to user actions. Event listeners detect interactions, while event handlers perform the required actions and update the interface.