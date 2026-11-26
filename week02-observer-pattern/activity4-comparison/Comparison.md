# Comparison of Event Notification Mechanisms in Java

| Feature | Custom Observer | java.util.Observer | PropertyChangeListener | JavaFX Bindings |
|---|---|---|---|---|
| Push or Pull? | Both possible | Pull | Push (carries old and new values) | Push |
| Is it deprecated? | No | Yes (Java 9+) | No | No |
| Requires inheritance? | No (interface) | Yes (class) | No | No |
| Thread-safe? | Depends on implementation | No | Yes (with the right setup) | Yes |
| Auto-generates boilerplate? | No | No | Yes (via PropertyChangeSupport) | Yes |
| Best for | Custom systems | Legacy code | JavaBeans / Swing | JavaFX apps |
| Can have multiple listeners? | Yes | Yes | Yes | Yes |
| Can be typed to send any data? | Yes | No (Object) | Yes | Yes |
| Recommended today? | Yes | No | Only for legacy | Yes |

## Which One Should You Use?

- For a plain Java project with custom needs: Custom Observer.
- For a JavaFX application: JavaFX Bindings.
- For legacy code using JavaBeans: PropertyChangeListener.
- Avoid java.util.Observer because it is deprecated.