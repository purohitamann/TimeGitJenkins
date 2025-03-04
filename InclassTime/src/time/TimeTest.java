package time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("Starting all tests...");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("All tests finished.");
    }

    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Starting a test...");
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("Test finished.");
    }

    // Test for good inputs
//    @Test
//    public void testGetTotalSecondsGood() {
//        int seconds = Time.getTotalSeconds("13:13:13:13");
//        assertEquals(47593, seconds, "The seconds were not calculated properly for a valid input.");
//    }

    // Test for bad inputs
    
    @Test
    public void testGetTotalSecondsBad() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            Time.getTotalSeconds("10:00");
        }, "Expected an exception for an invalid time format.");
        
        assertThrows(NumberFormatException.class, () -> {
            Time.getTotalSeconds("invalid:time:format");
        }, "Expected an exception for a non-numeric input.");
        
        assertThrows(NullPointerException.class, () -> {
            Time.getTotalSeconds(null);
        }, "Expected an exception for a null input.");
    }

    // Test for boundary inputs
//    @Test
//    public void testGetTotalSecondsBoundary() {
//        assertEquals(0, Time.getTotalSeconds("00:00:00:00"), "Boundary test for start of day failed.");
//        assertEquals(86399, Time.getTotalSeconds("23:59:59:59"), "Boundary test for end of day failed.");
//    }
//    
//    @Test
//    public void testGetMilliseconds() {
//    	assertEquals(5, Time.getMilliseconds("12:05:05:05"));
//    }
//    
    void testGetTotalMillisecondsGood(String candidate, int expectedMilliseconds) {
        int totalMilliseconds = Time.getTotalMilliseconds(candidate);
        assertEquals(expectedMilliseconds, totalMilliseconds, "Total milliseconds calculation is incorrect.");
    }	
    
//    @Test
//    public void testInvalidFormat() {
//    	int milliseconds = Time.getMilliseconds("12:05:05:05");
//    	assertEquals(5, milliseconds, "Milliseconds were not extracted correctly");
//    }
//    
    @Test
    public void testNonNumericInput() {
        // Test invalid non-numeric input
        assertThrows(NumberFormatException.class, () -> Time.getMilliseconds("12:05:05:xx"));
    }
    
    // Good input for getSeconds
    @Test
    void testGetSecondsGood() {
        int seconds = Time.getSeconds("05:05:05:05");
        assertEquals(5, seconds, "The seconds part was not extracted properly for a valid input.");
    }

    // Bad input for getSeconds
    @Test
    void testGetSecondsBad() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            Time.getSeconds("10:00");
        }, "Expected an exception for an invalid time format.");
    }

    // Boundary input for getSeconds
    @Test
    void testGetSecondsBoundary() {
        int seconds = Time.getSeconds("00:00:59");
        assertEquals(59, seconds, "Boundary test for seconds at the maximum value failed.");
    }

    // Good input for getTotalMinutes
    @Test
    void testGetTotalMinutesGood() {
        int minutes = Time.getTotalMinutes("05:05:05:05");
        assertEquals(5, minutes, "The total minutes were not calculated properly for a valid input.");
    }

    // Bad input for getTotalMinutes
    @Test
    void testGetTotalMinutesBad() {
        assertThrows(NumberFormatException.class, () -> {
            Time.getTotalMinutes("05:XX:00:00");
        }, "Expected an exception for an invalid time format.");
    }

    // Boundary input for getTotalMinutes
    @Test
    void testGetTotalMinutesBoundary() {
        int minutes = Time.getTotalMinutes("10:10:10:10");
        assertEquals(10, minutes, "Boundary test for minutes at the end of the day failed.");
    }


    // Good input for getTotalHours
    @ParameterizedTest
    @ValueSource(strings = {"05:00:00:500 ", "05:15:15", "05:59:59"})
    void testGetTotalHoursGood(String candidate) {
        int hours = Time.getTotalHours(candidate);
        assertEquals(5, hours, "The total hours were not calculated properly for a valid input.");
    }

    // Bad input for getTotalHours
    @Test
    void testGetTotalHoursBad() {
        assertThrows(NumberFormatException.class, () -> {
            Time.getTotalHours("XX:00:00:00");
        }, "Expected an exception for an invalid time format.");
    }

    // Boundary input for getTotalHours
    @Test
    void testGetTotalHoursBoundary() {
        int hoursStart = Time.getTotalHours("00:00:00");
        assertEquals(0, hoursStart, "Boundary test for hours at the start of the day failed.");
        
        int hoursEnd = Time.getTotalHours("23:59:59");
        assertEquals(23, hoursEnd, "Boundary test for hours at the end of the day failed.");
    }
}
