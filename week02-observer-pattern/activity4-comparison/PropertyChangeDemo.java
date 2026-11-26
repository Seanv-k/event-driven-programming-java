import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PropertyChangeDemo {

    static class Person {
        private String name;

        private PropertyChangeSupport support =
                new PropertyChangeSupport(this);

        public void addPropertyChangeListener(
                PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }

        public void setName(String newName) {
            String oldName = this.name;
            this.name = newName;

            support.firePropertyChange(
                    "name", oldName, newName);
        }
    }

    public static void main(String[] args) {

        Person person = new Person();

        person.addPropertyChangeListener(event -> {
            System.out.println("Listener 1: "
                    + event.getPropertyName()
                    + " changed from "
                    + event.getOldValue()
                    + " to "
                    + event.getNewValue());
        });

        person.addPropertyChangeListener(event -> {
            System.out.println("Listener 2: A change was detected!");
        });

        person.setName("Alice");
        person.setName("Bob");
    }
}
