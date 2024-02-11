import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Sorter {
    private final String path = "/home/dicenice/Desktop/Projects/sorterTest/";
    File directory = new File(path);

    public void sorting(){
        if(directory.isDirectory()){
            File[] list = listFiles();
            for (File file : list){
                if (file.isFile()) {
                    int index = file.getName().indexOf(".");
                    String extension = file.getName().substring(index + 1);
                    if (createDirectory(extension)) {
                        System.out.println("Folder created");
                    }
                    String newPath = path + extension + "/";
                    moveToFolder(file.toPath(), Path.of(newPath));
                }
            }
        }
    }
    private boolean createDirectory(String name){
        File create = new File(path + name + "/");
        return create.mkdir();
    }
    private void moveToFolder(Path oldPath, Path newPath){
        try {
            Files.move(oldPath, newPath.resolve(oldPath.getFileName()));
            System.out.println("Done");
        }catch (IOException e){
            System.out.println("Error");
        }
    }
    private File[] listFiles(){
        return directory.listFiles();
    }

}
