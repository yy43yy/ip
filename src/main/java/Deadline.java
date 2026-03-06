import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**Represent a task with specified deadlines*/
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a deadline task with the given description and due date.
     *
     * @param description The task description.
     * @param by The due date in yyyy-MM-dd format.
     * @throws DukeException If the date format is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use date format yyyy-MM-dd, e.g. 2019-12-02");
        }
    }
    /**return the deadline date*/
    public LocalDate getBy() {
        return by;
    }
    /** set the deadline
     * @param by the due date*/
    public void setBy(String by) throws DukeException {
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use date format yyyy-MM-dd, e.g. 2019-12-02");
        }
    }
    /** return the deadline task string as specified format*/
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + getBy().format(outputFormatter) + ")";
    }
    /** return the deadline task string as another specified format*/
    public String toStorageString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + getBy();
    }
}