import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use date format yyyy-MM-dd, e.g. 2019-12-02");
        }
    }

    public LocalDate getBy() {
        return by;
    }

    public void setBy(String by) throws DukeException {
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use date format yyyy-MM-dd, e.g. 2019-12-02");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + getBy().format(outputFormatter) + ")";
    }

    public String toStorageString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + getBy();
    }
}