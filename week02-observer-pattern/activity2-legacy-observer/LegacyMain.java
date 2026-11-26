public class LegacyMain {

    public static void main(String[] args) {

        // Create the subject
        LegacyWeatherData weatherData = new LegacyWeatherData();

        // Create the observer
        LegacyDisplay display = new LegacyDisplay();

        // Register the observer
        weatherData.addObserver(display);

        // Send weather measurements
        weatherData.setMeasurements(25.0f, 65.0f, 1013.0f);
        weatherData.setMeasurements(28.0f, 70.0f, 1012.0f);
        weatherData.setMeasurements(22.0f, 60.0f, 1015.0f);
    }
}