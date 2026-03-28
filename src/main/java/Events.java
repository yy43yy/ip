/**
 * Represents an event task with a description, start time, and end time.
 */
public class Events extends Task{
    protected String from;
    protected String to;

    /**
     * Creates an event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */

    public Events(String description,String from, String to){
        super(description);
        this.from=from;
        this.to=to;
    }

    /**
     * Returns the start time of this event.
     *
     * @return The start time of the event.
     */

    public String getFrom(){
        return from;
    }
    /**
     * Returns the end time of this event.
     *
     * @return The end time of the event.
     */

    public String getTo(){
        return to;
    }

    /**
     * Updates the start time of this event.
     *
     * @param from The new start time of the event.
     */

    public void setFrom(String from){
        this.from= from;
    }

    /**
     * Updates the end time of this event.
     *
     * @param to The new end time of the event.
     */

    public void setTo(String to){
        this.to= to;
    }

    /**
     * Returns the string representation of this event for display.
     *
     * @return A formatted string showing the event details.
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
    /**
     * Returns the string representation of this event for file storage.
     *
     * @return A string formatted for saving this event to a file.
     */

    public String toStorageString() {
        return "E | " + (isDone?"1" :"0") +" | " + description +" | " +getFrom()+ " | "+ getTo();
    }
}

