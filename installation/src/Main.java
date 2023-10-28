
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        StringBuilder logOperation = new StringBuilder();
        createDir("C://Games", Arrays.asList("src", "res", "savegames", "temp"), logOperation);
        createDir("C://Games/src", Arrays.asList("main", "test"), logOperation);
        createFile("C://Games/src/main", Arrays.asList("Main.java", "Utils.java"), logOperation);
        createDir("C://Games/res", Arrays.asList("drawables", "vectors", "icons"), logOperation);
        createFile("C://Games/temp", Arrays.asList("temp.txt"), logOperation);
        System.out.println(logOperation);
        try (FileWriter writer = new FileWriter("C://Games/temp/temp.txt", false)) {
            writer.write(logOperation.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }




    }

    public static void createDir(String nameDir, List<String> namesDirs, StringBuilder logOperation){
        namesDirs.stream()
                .filter(dir -> new File(nameDir + "//" + dir).mkdirs())
                .forEach(dir -> logOperation.append(dir + " созданна успешно\n"));
    }

    public static void createFile(String nameDir, List<String> nameFiles, StringBuilder logOperation) {
        nameFiles.stream()
                .filter(file -> {
                    try {
                        return new File(nameDir, file).createNewFile();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                })
                .forEach(dir -> logOperation.append(dir + " созданна успешно\n"));
    }
}
