# Comparison: Tightly Coupled vs Observer Pattern

| Feature | Tightly Coupled System | Observer Pattern |
|---|---|---|
| Adding a new display | Modify DataModel | Just register a new observer |
| Testing DataModel | Difficult because it depends on display classes | Easy because it works independently |
| Removing a display | Modify DataModel | Simply remove the observer |
| Communication | Direct method calls | Through an interface |
| Flexibility | Less flexible | More flexible |

## Conclusion

The tightly coupled system makes DataModel depend directly on display classes.

The Observer Pattern reduces this dependency by allowing DataModel to communicate through the DataObserver interface.

This makes the system easier to maintain, test and extend.