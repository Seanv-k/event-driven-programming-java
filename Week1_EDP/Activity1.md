# Activity 1: Spot the Components

| System | Event Source | Event | Listener | Handler/Action |
|---|---|---|---|---|
| ATM Machine | Keypad button | Button pressed | ATM software | Verify PIN entered by the user |
| Microwave Oven | Door sensor | Door opened | Microwave controller | Stop heating immediately |
| Traffic Light | Timer | Timer expires | Traffic light controller | Change the traffic light to the next colour |
| Video Game | Keyboard | Key pressed | Game input listener | Move the game character |
| Elevator | Floor selection button | Floor button pressed | Elevator controller | Move the elevator to the selected floor |
| Smartphone | Touchscreen | Screen tapped | Touch event listener | Open the selected application |
| Online Shopping | Add to Cart button | Button clicked | Shopping website | Add the selected product to the shopping cart |
| Security Alarm | Motion sensor | Movement detected | Alarm controller | Activate the alarm and notify the user |

## Step 4: Self-Check

### 1. What is the difference between an event source and an event listener?

An event source is the component that generates an event, such as a button or sensor. An event listener is the component that waits for and detects that event so that the system can respond.

### 2. Can one event source have multiple listeners? Give an example.

Yes. One event source can have multiple listeners.

For example, when a user clicks a button, one listener can display a message while another listener can update a counter.

### 3. Can one listener handle events from multiple sources? Give an example.

Yes. One listener can handle events from multiple sources.

For example, a keyboard listener can detect different key presses, such as A, B, C and Enter, and respond accordingly.