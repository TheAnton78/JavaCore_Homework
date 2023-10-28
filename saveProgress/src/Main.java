import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        GameProgress save1 = new GameProgress(93, 10,13,204.5);
        GameProgress save2 = new GameProgress(60, 15,24,354.87);
        GameProgress save3 = new GameProgress(85, 9,15,254.35);
        List<String> gameProgresses = Arrays.asList("C://Games/savegames/save1.txt", "C://Games/savegames/save2.txt", "C://Games/savegames/save3.txt");
        saveGame("C://Games/savegames/save1.txt", save1);
        saveGame("C://Games/savegames/save2.txt", save2);
        saveGame("C://Games/savegames/save3.txt", save3);
        zipFiles("C://Games/savegames/zip.zip", gameProgresses);
        deleteFile(gameProgresses);
        openZip("C://Games/savegames/zip.zip","C://Games/savegames");
        openProgress("C://Games/savegames/save3.txt");


    }

    public static void saveGame(String fileName, GameProgress gameProgress){

        try(FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipName, List<String> filesNames){
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipName))){
            for(String file : filesNames){
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                }catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void deleteFile(List<String> namesFiles){
        namesFiles.stream()
                .forEach(file -> new File(file).delete());
    }

    public static void openZip(String zipName, String dirName) {
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream(zipName))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }


    public static void openProgress(String fileName){
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);;
    }
}

