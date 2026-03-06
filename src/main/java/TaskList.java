import java.util.ArrayList;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list using an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Finds all tasks whose descriptions contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */

    public ArrayList<Task> findTasks(String keyword){
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks){
            if(task.getDescription().contains(keyword)){
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */

    public void addTask(Task task){
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */

    public Task removeTask(int index){
        return tasks.remove(index);
    }
    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the given index.
     */

    public Task get(int index){
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */

    public int size(){
        return tasks.size();
    }
    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark.
     */

    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to unmark.
     */

    public void unmarkTask(int index) {
        tasks.get(index).unmarkDone();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return The list of tasks.
     */

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
