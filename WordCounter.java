import java.util.*; 

public class WordCounter {
    protected StringBuffer text;
    protected String stopword;
    protected int count;

    public WordCounter(StringBuffer text, String stopword, int count) {
        this.text = text;
        this.stopword = stopword;
        this.count = count;
    }
    //Use regular expression
    public int processText(StringBuffer text, String stopword, int count) {
        //if stopword is not found -> raise InvalidStopwordException
        //if stopword = null -> count all words in the file
        //if count < 5 -> raise TooSmallText exception
        
        //use regular expression
        Pattern regex = Pattern.compile("your regular expression here");
        Matcher regexMatcher = regex.matcher(text);
        while (regexMatcher.find()) {
            System.out.println("I just found the word: " + regexMatcher.group());
        } 
    }

    public StringBuffer processFile(String ??) {
        //converts file path to a stringbuffer which is returned
        //if file cannot open -> prompt to reenter filename until correct
        //if file is empty -> raise EmptyFileException 
    }
}

public static void main(String[] args) {
    //option 1: processFile
    //option 2: processText
    //If invalid option then allow to choose again
    //Then checks if second command line specifys a stopword
    //Then calls methods in processText, outputs number of words counted
    //if the file is empty display the message with path of file
    //if stopword was not found allow chance to re-specify stopword
}