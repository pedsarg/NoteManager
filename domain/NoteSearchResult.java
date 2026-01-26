package Notes.domain;

import java.nio.file.Path;

public class NoteSearchResult {
    private Path path;
    private int lineNumber;
    private String lineContent;
    private String noteTitle;

    public NoteSearchResult(Path path, int lineNumber, String lineContent, String noteTitle) {
        this.path = path;
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
        this.noteTitle = noteTitle;
    }

    public Path getPath() {
        return path;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLineContent() {
        return lineContent;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    @Override
    public String toString() {
        return "[" + noteTitle + "] (" + path + ") linha " + lineNumber + ": " + lineContent;
    }
}