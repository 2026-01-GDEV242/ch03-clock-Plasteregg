
/**
 * The ClockDisplay class implements a digital clock display for a
 * American-style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00 AM (midnight) to 12:00PM this also shows off the AM and PM system by rolling over
 * 
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
    private int hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private boolean isAM;            // true=AM False=PM
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = 12;
        minutes = new NumberDisplay(60);
        isAM=true;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
 public void timeTick()
    {
        minutes.increment();

        if (minutes.getValue() == 0) { // minute rollover
            hours++;

            if (hours > 12) {          // 13 -> 1
                hours = 1;
            }

            if (hours == 12) {         // flip AM/PM at 12
                isAM = !isAM;
            }
        }

        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        minutes.setValue(minute);

        if (hour == 0) {        // midnight
            hours = 12;
            isAM = true;
        } else if (hour == 12) { // noon
            hours = 12;
            isAM = false;
        } else if (hour > 12) { // 13-23
            hours = hour - 12;
            isAM = false;
        } else {                 // 1-11
            hours = hour;
            isAM = true;
        }

        updateDisplay();
    }


    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayString = hours + ":" + minutes.getDisplayValue() + (isAM ? " AM" : " PM");
    }
}
