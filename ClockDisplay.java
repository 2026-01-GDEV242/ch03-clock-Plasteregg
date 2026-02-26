
/**
 * The ClockDisplay class implements a digital clock display for a
 * American-style 12 hour clock. The clock shows hours and minutes. The 
 * range keeps within the 24 hour system but displays as the american system it also keeps track of the AM and PM
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Andrew Wright
 * @version 2016.02.29
 */
public class ClockDisplay
{
    private NumberDisplay hours;   // 0-23 internally
    private NumberDisplay minutes; // 0-59
    private String displayString;  // the string shown to user

    /**
     * Default constructor: 12:00 AM
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24); // 24-hour internal
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor with specific time (0-23 hours)
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * Increment the clock by one minute
     */
    public void timeTick()
    {
        minutes.increment();
        if (minutes.getValue() == 0) { // minute rolled over
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the clock (24-hour input)
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour % 24);  // ensure valid 0-23
        minutes.setValue(minute % 60);
        updateDisplay();
    }

    /**
     * Get current time as 12-hour formatted string with AM/PM
     */
    public String getTime()
    {
        return displayString;
    }

    /**
     * Update the display string in 12-hour format
     */
    private void updateDisplay()
    {
        int displayHour = hours.getValue();
        boolean isAM = true;

        if (displayHour == 0) {         // midnight
            displayHour = 12;
            isAM = true;
        } else if (displayHour == 12) { // noon
            displayHour = 12;
            isAM = false;
        } else if (displayHour > 12) {
            displayHour = displayHour - 12;
            isAM = false;
        }

        displayString = displayHour + ":" + minutes.getDisplayValue() + (isAM ? " AM" : " PM");
    }
}