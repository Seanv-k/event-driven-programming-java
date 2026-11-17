# Activity 4: Debug the Event Handler

## Bug 1: Blocking the Event Dispatch Thread (EDT)

Problem:
The program used `Thread.sleep(5000)` inside an ActionListener. This blocked the Event Dispatch Thread and made the GUI temporarily unresponsive.

Solution:
Moved the long-running task into a background thread using `new Thread()`. Used `SwingUtilities.invokeLater()` to update the GUI safely.

Concept learned:
Long-running operations should not run on the Event Dispatch Thread.

## Bug 2: Missing Listener

Problem:
The button had no ActionListener, so clicking it did not produce any response.

Solution:
Added an ActionListener using `button.addActionListener()` to respond to button clicks.

Concept learned:
Event listeners detect events and allow the program to respond to them.

## Bug 3: Wrong Thread

Problem:
The program updated a Swing label directly from a background thread.

Solution:
Used `SwingUtilities.invokeLater()` to perform the label update on the Event Dispatch Thread.

Concept learned:
Swing GUI components should be updated on the Event Dispatch Thread.

## Bug 4: Memory Leak

Problem:
Buttons were removed from the panel, but they remained stored in the `createdButtons` ArrayList.

Solution:
Added `createdButtons.clear()` when removing the buttons.

Concept learned:
Removing components from a GUI does not automatically remove references to them from other data structures.

## Conclusion

This activity helped me understand common problems in event-driven programming. I learned how to handle long-running tasks, attach event listeners, update GUI components safely, and manage memory properly. These concepts help developers create responsive and reliable applications.
