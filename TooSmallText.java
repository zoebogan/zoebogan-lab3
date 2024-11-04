import java.util.*; 

//exception when the length of text is less than 5 words

public class TooSmallText extends Exception {

    public TooSmallText(String errorMessage) {
        super(errorMessage);
    }
    
}