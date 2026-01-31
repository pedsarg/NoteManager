package Notes.service;

import Notes.domain.Note;
import Notes.util.DateFormatter;
import Notes.util.NameNormalizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NoteService {

    public void createNote(Path folderPath, Note note){
        String normalizedTitle = NameNormalizer.normalize(note.getTitle());

        Path notePath = folderPath.resolve(normalizedTitle + ".txt");

        //Exception
        if (noteExists(folderPath, normalizedTitle)){
            return;
        };

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(notePath.toFile()))){
            
            bw.write("#Title: " + note.getTitle());
            bw.newLine();
            
            bw.write("#CreatedAt: " + DateFormatter.format(note.getCreatedAt()));
            bw.newLine();
            
            bw.write("#UpdatedAt: " + DateFormatter.format(note.getUpdatedAt()));
            bw.newLine();
            
            bw.write("#Tags: ");
            if(note.getTags() != null && !note.getTags().isEmpty()){
                for(String tag: note.getTags()){
                    bw.write(NameNormalizer.normalize(tag) + ";");
                }
            }
            bw.newLine();

            if(note.getContent() != null){
                for(String line:note.getContent()){
                    bw.write(line);
                    bw.newLine();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }              
    }

    private List<String> processTags(String tagsLine){
        if(tagsLine == null || tagsLine.isBlank()){
            return new ArrayList<>();
        }

        String[] parts = tagsLine.split(";");

        List<String> tags = new ArrayList<>();

        for(String tag:parts){
            String cleanTag = tag.trim();
            if(!cleanTag.isEmpty()){
                tags.add(cleanTag);
            }
        }
        return tags;
    }

    public Note readNote(Path folderPath, String title){
        String normalizedTitle = NameNormalizer.normalize(title);

        Path notePath = folderPath.resolve(normalizedTitle + ".txt");

        //Warning!
        if (!noteExists(folderPath, normalizedTitle)){
            return null; //Exception
        };

        try(BufferedReader br = new BufferedReader(new FileReader(notePath.toFile()))){
            Note note = new Note();
            String line = "";
            List<String> content = new ArrayList<>();

            note.setTitle(br.readLine());

            note.setCreatedAt(DateFormatter.parse(br.readLine()));
            note.setUpdatedAt(DateFormatter.parse(br.readLine()));

            String tagsLine = br.readLine();
            note.setTags(processTags(tagsLine));

            while ((line = br.readLine()) != null) {
              line = br.readLine();
              if(line != null && !line.startsWith("#")){
                content.add(line);
              }
            }

            note.setContent(content);  

            return note;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // public void overWriteNote(Path path, Note note){

    // }

    // public void appendContent(Path path, String title, String content){

    // }

    // public void deleteNote(Path path, String title){

    // }

    // public void renameNote(Path path, String oldTitle, String newTitle){

    // }

    public boolean noteExists(Path folderPath, String title){
        String Standardtitle = NameNormalizer.normalize((title));

        Path notePath = folderPath.resolve(Standardtitle + ".txt");
        
        return Files.exists(notePath);
    }
}
