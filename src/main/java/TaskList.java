import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> findTasks(String keyword){
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks){
            if(task.getDescription().contains(keyword)){
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
    public void addTask(Task task){
        tasks.add(task);
    }
    public Task removeTask(int index){
        return tasks.remove(index);
    }

    public Task get(int index){
        return tasks.get(index);
    }

    public int size(){
        return tasks.size();
    }
    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmarkDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
