import java.util.*; 
import java.io.IOException;

//exception when contents of file to be parsed is empty

public class EmptyFileException extends IOException {

    public EmptyFileException(String errorMessage) {
        super(errorMessage);
    }
}