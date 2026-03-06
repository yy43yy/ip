import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading tasks from and saving tasks to the storage file.
 */

public class Storage {
    private final Path filePath;

    /**
     * Creates a Storage object using the given folder and file name.
     *
     * @param folder The folder where the file is stored.
     * @param fileName The name of the storage file.
     */

    public Storage(String folder, String fileName){
        this.filePath= Paths.get(folder,fileName);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws DukeException If the file cannot be read.
     */

    public ArrayList<Task> load() throws DukeException{
        try{
            if(!Files.exists(filePath)){
                return new ArrayList<>();
            }

            List<String> lines = Files.readAllLines(filePath);
            ArrayList<Task> tasks = new ArrayList<>();

            for(String line:lines){
                tasks.add(parseLine(line));
            }
            return tasks;
        }catch(IOException e){
            throw new DukeException("Failed to load tasks: " + e.getMessage());
        }
    }

    /**
     * Saves the given task list to the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws DukeException If the tasks cannot be written to the file.
     */

    public void writeToFile(ArrayList<Task> tasks) throws DukeException{
        try{
            Path parent = filePath.getParent();
            if(parent!=null){
                Files.createDirectories(parent);
            }
            ArrayList<String> lines = new ArrayList<>();
            for(Task task:tasks){
                lines.add(task.toStorageString());
            }
            Files.write(filePath,lines,StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        }catch (IOException e){
            throw new DukeException("Fail to save task: " +e.getMessage());
        }
    }
    /**
     * Converts a line from the storage file into a Task object.
     *
     * @param line A single line read from the storage file.
     * @return The corresponding Task object.
     * @throws DukeException If the task type is invalid or the line format is incorrect.
     */
    private Task parseLine(String line) throws DukeException{
        String[] parts = line.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].equals("1");
        String detail = parts[2].trim();

        Task task;

        switch (type){
        case "T":
            task = new Todos(detail);
            break;
        case "D":
            task = new Deadline(detail,parts[3].trim());
            break;
        case "E":
            task = new Events(detail,parts[3].trim(),parts[4].trim());
            break;
        default:
            throw new DukeException("Unknown task type" + type);
        }

        if (isDone){
            task.markDone();
        }
        return task;

    }



}
