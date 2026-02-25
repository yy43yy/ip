import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String folder, String fileName){
        this.filePath= Paths.get(folder,fileName);
    }

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
