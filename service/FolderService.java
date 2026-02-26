package Notes.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Notes.util.NameNormalizer;

public class FolderService {

    /*
        FolderService:
            Mover    
            Copiar
            Existe?
    */

    public void createFolder(Path rootPath, String folderName){
        String normalizedFolderName = NameNormalizer.normalize(folderName);
        Path folderPath = rootPath.resolve(normalizedFolderName);

        try{
            Files.createDirectories(folderPath);
        }catch(Exception e){
            System.out.println("Error creating folder: " + e.getMessage());
        }
    }

    public void deleteFolder(Path folderPath){
        
        if(!Files.exists(folderPath)){
            throw new RuntimeException("Folder does not exist.");
        }
        try{
            Files.walk(folderPath).sorted(Comparator.reverseOrder()).forEach(path -> {
                try{
                    Files.delete(path);
                }catch(IOException e){
                    throw new RuntimeException("Error deleting: " + path, e);
                }
            });
        }catch(IOException e){
            throw new RuntimeException("Error walking through folder.", e);
        }
    }

    public void renameFolder(Path folderPath, String oldFolderName,String newFolderName){
        if(!Files.exists(folderPath)){
            throw new RuntimeException("Folder does not exist.");
        }

        String normalizedOldFolderName = NameNormalizer.normalize(oldFolderName);
        String normalizedFolderName = NameNormalizer.normalize(newFolderName);
        
        Path oldFolderPath = folderPath.getParent().resolve(normalizedOldFolderName);
        Path newFolderPath = folderPath.getParent().resolve(normalizedFolderName);

        if(!Files.exists(oldFolderPath)){
            throw new RuntimeException("Folder with the old name does not exist.");
        }

        if(Files.exists(newFolderPath)){
            throw new RuntimeException("Folder with the new name already exists.");
        }

        try{
            Files.move(oldFolderPath, newFolderPath);
        }catch(IOException e){
            throw new RuntimeException("Error renaming folder.", e);
        }
    }

    public List<String> listFolders(Path rootPath){
        List<String> folders = new ArrayList<>();
        
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(rootPath)){
            for(Path path : stream){
                if(Files.isDirectory(path)){
                    folders.add(path.getFileName().toString());
                }
            }
        }catch(IOException e){
            throw new RuntimeException("Error listing folders.", e);
        }

        return folders;
    }
}
