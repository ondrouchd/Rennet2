package cz.luweko.rennet.calculator;

/**
 * Utility class for temperature conversions commonly used in cheese making.
 */
public final class TemperatureConverter {

    private TemperatureConverter() {
        // Utility class
    }

    /**
     * Converts Celsius to Fahrenheit.
     *
     * @param celsius temperature in Celsius
     * @return temperature in Fahrenheit
     */
    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32.0;
    }

    /**
     * Converts Fahrenheit to Celsius.
     *
     * @param fahrenheit temperature in Fahrenheit
     * @return temperature in Celsius
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }

    /**
     * Checks if the given temperature is within a safe range for cheese making.
     * Typical cheese-making temperatures range from 20°C to 55°C.
     *
     * @param celsius temperature in Celsius
     * @return true if the temperature is within the safe range
     */
    public static boolean isSafeTemperature(double celsius) {
        return celsius >= 20.0 && celsius <= 55.0;
    }
}
