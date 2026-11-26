# Limitations of Java's Legacy Observer Pattern

1. Deprecated: Observable and Observer were deprecated in Java 9.

2. Inheritance limitation: A class must extend Observable, meaning it cannot extend another class.

3. Encapsulation: Observable provides limited control over how notifications are managed.

4. Type safety: The update() method uses Object, which can require type casting.

5. Limited flexibility: The built-in implementation provides less control than a custom Observer pattern.

## Conclusion

The legacy Observer pattern is useful for understanding event notification, but a custom Observer implementation provides greater flexibility and control.