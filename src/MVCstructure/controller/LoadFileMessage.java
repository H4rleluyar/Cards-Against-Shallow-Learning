package MVCstructure.controller;

/**
 * @author 404 Bits Not Found(Henry Lai, Henry Fan, Gabby Kim, Bill Huynh)
 */
public class LoadFileMessage implements Message{
    private String fileDir;

    public LoadFileMessage(String fileDir) { this.fileDir = fileDir; }

    public String getFileDir() { return fileDir; }
}
