package MVCstructure.controller;

public class LoadFileMessage implements Message{
    private String fileDir;

    public LoadFileMessage(String fileDir) { this.fileDir = fileDir; }

    public String getFileDir() { return fileDir; }
}
