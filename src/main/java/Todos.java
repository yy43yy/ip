/**
 * Represents a todo task with only a description.
 */

public class Todos extends Task{
    /**
     * Creates a todo task with the given description.
     *
     * @param description The description of the todo task.
     */

    public Todos(String description){
        super(description);
    }

    /**
     * Returns the string representation of this todo task for display.
     *
     * @return A formatted string showing the todo task.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of this todo task for file storage.
     *
     * @return A string formatted for saving this todo task to a file.
     */
    @Override
    public String toStorageString() {
        return "T | " + (isDone?"1" :"0") +" | " + description;
    }
}
