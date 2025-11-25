/**
 * The DateTime class represents a specific point in time with year, month, day, hour, and minute components.
 * It provides methods for displaying and retrieving date and time information in different formats.
 * 
 * <p>This class is used throughout the expense tracking system to timestamp budgets and expenses,
 * enabling temporal analysis of financial data.
 * 
 * @author Magdaluyo, Alaine Carlo R.
 * @author Cruz, Zachary Andre A.
 * @version 0.2
 * 
 */
public class DateTime {
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    
    /**
     * Constructs a new DateTime object with the specified components.
     * 
     * @param year the year as a string (e.g., "2025")
     * @param month the month as a string (e.g., "11")
     * @param day the day as a string (e.g., "15")
     * @param hour the hour as a string in 24-hour format (e.g., "14")
     * @param minute the minute as a string (e.g., "30")
     */
    public DateTime(String year, String month, String day, String hour, String minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
    
    /**
     * Displays the date in MM/DD/YYYY format to the console.
     * For example: "11/15/2025"
     */
    public void displayDate() {
        System.out.println(month + "/" + day + "/" + year);
    }
    
    /**
     * Displays the time in HH:MM format to the console.
     * For example: "14:30"
     */
    public void displayTime() {
        System.out.println(hour + ":" + minute);
    }
    
    // Getters for individual components
    
    /**
     * Returns the year component of the date.
     * 
     * @return the year as a String
     */
    public String getYear() {
        return year;
    }
    
    /**
     * Returns the month component of the date.
     * 
     * @return the month as a String
     */
    public String getMonth() {
        return month;
    }
    
    /**
     * Returns the day component of the date.
     * 
     * @return the day as a String
     */
    public String getDay() {
        return day;
    }
    
    /**
     * Returns the hour component of the time.
     * 
     * @return the hour as a String in 24-hour format
     */
    public String getHour() {
        return hour;
    }
    
    /**
     * Returns the minute component of the time.
     * 
     * @return the minute as a String
     */
    public String getMinute() {
        return minute;
    }
    
    /**
     * Returns the date in MM/DD/YYYY format as a String.
     * 
     * @return the formatted date string (e.g., "11/15/2025")
     */
    public String getDateString() {
        return month + "/" + day + "/" + year;
    }
    
    /**
     * Returns the time in HH:MM format as a String.
     * 
     * @return the formatted time string (e.g., "14:30")
     */
    public String getTimeString() {
        return hour + ":" + minute;
    }
    
    /**
     * Returns the full datetime in MM/DD/YYYY HH:MM format as a String.
     * 
     * @return the formatted datetime string (e.g., "11/15/2025 14:30")
     */
    public String getDateTimeString() {
        return getDateString() + " " + getTimeString();
    }
}