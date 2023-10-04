import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Data_baze_loader {
    public List<String> load_data_baze(String file_name){
        List<String> lines = null;
        try{
            Path path = Paths.get(file_name);
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
