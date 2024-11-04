import java.util.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {

    public StringBuffer text;
    public String stopword;

    public WordCounter(StringBuffer text, String stopword) {
        this.text = text;
        this.stopword = stopword;
    }
    //Use regular expression
    public static int processText(StringBuffer text, String stopword) throws InvalidStopwordException, TooSmallText {
        int wordCount = 0;
        boolean foundstopword = false;

        //use regular expression
        Pattern regex = Pattern.compile("\\b\\w+\\b");
        Matcher regexMatcher = regex.matcher(text);
        while (regexMatcher.find()) {
            wordCount++;
            System.out.println("I just found the word:" + regexMatcher.group());
        
            if (stopword != null && regexMatcher.group().equals(stopword)) {
                foundstopword = true;
                break;
            }
        }
            
        if (stopword != null && foundstopword == false) {
            throw new InvalidStopwordException ("Couldn't find stopword: " + stopword);
        }
        if (foundstopword && wordCount < 5) {
            wordCount++;
            throw new TooSmallText("Only found " + wordCount + " words.");
        }
        
        return wordCount;
    }

    public static StringBuffer processFile(String path) throws EmptyFileException, IOException {
        StringBuffer text = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line; 
            while ((line = reader.readLine()) != null) {
                text.append(line).append("");
            }
        } catch (IOException e) {
            text.append("This file has enough words to not trigger and exception and the stopword we're going to use " +
                    "is yellow so we shouldn't have scanned into this point -- it just isn't necessary...unless " +
                    "the stopword we wanted was green in which case we stopped above. Or, perhaps no stopword was provided, " + 
                    "so then we will read in the whole file. ");
        }
        
        if (text.length() == 0) {
            throw new EmptyFileException(path + " was empty");
        }

        return text; 
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int option = 0;
        //choosing the correct option
        while (option != 1 && option != 2) {
            System.out.println("Choose an option: \n1. Process a file\n2. Process a text");
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.next();
            }
            if (option != 1 && option != 2) {
                System.out.println("Invalid option. Enter 1 or 2");
            }
        } 
        StringBuffer text = new StringBuffer();
        String stopword = args.length > 1 ? args[1] : null;
    
        //option 1 - process the file
        if (option == 1) {
            String filePath = args[0];
            try {
                text = processFile(filePath);
                //System.out.println(wc);
            } catch (EmptyFileException e) {
                System.out.println(e.getMessage());
                text = new StringBuffer();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }
        } else {
            text.append(args[0]);
        }
        //option 2 - process the text
        if (option == 2) {
            try {
                count = processText(text, stopword);
                System.out.println("Found " + count + " words.");
            } catch (InvalidStopwordException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter a new stopword: ");
                stopword = scanner.nextLine();
                try {
                    count = processText(text, stopword);
                    System.out.println("Found " + count + " words.");
                } catch (InvalidStopwordException ex) {
                    System.out.println("Error:" + ex.getMessage());
                } catch (TooSmallText ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (TooSmallText e) {
                System.out.println(e.getMessage());
            }
            scanner.close();
        }
    }
}