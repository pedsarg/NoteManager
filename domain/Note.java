package Notes.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Note {
    private String title;
    private List<String> content;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private List<String> tags;

    public Note(){}
    
    public Note(String title, List<String> content,List<String> tags){
        this.title = title;
        this.content = new ArrayList<>(content);
        this.tags = new ArrayList<>(tags);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Note(String title, List<String> content){
        this.title = title;
        this.content = new ArrayList<>(content);
        this.tags = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
        setUpdatedAt();
    }

    public List<String> getContent(){
        return content;
    }

    public void setContent(List<String> content){
        this.content = new ArrayList<>(content);
        setUpdatedAt();
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public List<String> getTags(){
        return tags;
    }

    public void setTags(List<String> tags){
        this.tags = new ArrayList<>(tags);
        setUpdatedAt();
    }   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Title: ").append(title).append("\n");
        sb.append("Created: ").append(createdAt).append("\n");
        sb.append("Updated: ").append(updatedAt).append("\n");
        sb.append("Tags: ").append(tags).append("\n\n");

        for (String line : content) {
            sb.append(line).append("\n");
        }

        return sb.toString();
    }
}
