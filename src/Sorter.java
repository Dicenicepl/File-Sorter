import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class Sorter {
    private final String path = "/home/dicenice/Desktop/Projects/sorterTest/";
    File directory = new File(path);

    public void sorting(byte choose) {
        if(directory.isDirectory()){
            File[] list = listFiles();
            for (File file : list){
                if (file.isFile()) {
                    switch (choose) {
                        case 0 -> sortByExtention(file);
                        case 1 -> sortByAlphabet(file);
                        case 2 -> sortByDateOfCreation(file);
                    }
                }
            }
        }
    }

    private void sortByExtention(File file){
        int index = file.getName().indexOf(".");
        String extension = file.getName().substring(index + 1);
        if (createDirectory(extension)) {
            System.out.println("Folder created");
        }
        String newPath = path + extension + "/";
        moveToFolder(file.toPath(), Path.of(newPath));
    }
    private void sortByDateOfCreation(File file){
        try {
            BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            String time = String.valueOf(attributes.creationTime());
            int toDelete = time.indexOf("T");
            time = time.substring(0, toDelete);

            if (createDirectory(time)) {
                System.out.println("Folder created");
            }
            String newPath = path + time + "/";
            moveToFolder(file.toPath(), Path.of(newPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void sortByAlphabet(File file){
        char firstLetter = file.getName().charAt(0);
        String newPath = path + firstLetter + "/";
        if (createDirectory(String.valueOf(firstLetter))) {
            System.out.println("Folder created");
        }
        moveToFolder(file.toPath(), Path.of(newPath));
    }


    //moving file from oldPath to newPath directory where directory name == fileName
    private void moveToFolder(Path oldPath, Path newPath){
        try {
            Files.move(oldPath, newPath.resolve(oldPath.getFileName()));
            System.out.println("MOVE TO FOLDER: Done");
        }catch (IOException e){
            System.out.println("MOVE TO FOLDER: Error");
        }
    }
    private boolean createDirectory(String name){
        File create = new File(path + name + "/");
        return create.mkdir();
    }
    private File[] listFiles(){
        return directory.listFiles();
    }

}
