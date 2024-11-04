import java.util.*; 

//Exception raised when stopword is not found in text

public class InvalidStopwordException extends Exception {

    public InvalidStopwordException(String errorMessage) {
        super(errorMessage); 
    }

}