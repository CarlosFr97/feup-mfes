import java.io.IOException;

public class Utils{

     //Method to display the user's prompt string
    public static void printPrompt(String prompt) {
        System.out.print(prompt + " ");
        System.out.flush();
    }

    public static void clearScreen() {  
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    } 

    public static boolean isNumeric(String str){
        return str.matches("-?\\d+?");  //match a number
    }

    //Method to make sure no data is available in the
    //input stream
    public static void inputFlush() {
        int dummy;
        int bAvail;

        try {
        while ((System.in.available()) != 0)
            dummy = System.in.read();
        } catch (java.io.IOException e) {
        System.out.println("Input error");
        }
    }

     //********************************
     //  data input methods for
     //string, int, char, and double
     //********************************
    public static String inString(String prompt) {
        inputFlush();
        printPrompt(prompt);
        return inString();
    }

    public static String inPassword(String prompt) {
        inputFlush();
        printPrompt(prompt);
        return inPassword();
    }

    public static String inString() {
        int aChar;
        String s = "";
        boolean finished = false;

        while (!finished) {
        try {
            aChar = System.in.read();
            if (aChar < 0 || (char) aChar == '\n')
            finished = true;
            else if ((char) aChar != '\r')
            s = s + (char) aChar; // Enter into string
        }

        catch (java.io.IOException e) {
            System.out.println("Input error");
            finished = true;
        }
        }
        return s;
    }

    public static String inPassword() {
        char[] aChar = null;
        String s = "";
        boolean finished = false;

        while (!finished) {
            aChar = System.console().readPassword();
            finished = true;
        }
        
        return new String(aChar);
    }

    public static int inInt(String prompt) {
        while (true) {
        inputFlush();
        printPrompt(prompt);
        try {
            return Integer.valueOf(inString().trim()).intValue();
        }

        catch (NumberFormatException e) {
            System.out.println("Invalid input. Not an integer");
        }
        }
    }

    public static char inChar(String prompt) {
        int aChar = 0;

        inputFlush();
        printPrompt(prompt);

        try {
        aChar = System.in.read();
        }

        catch (java.io.IOException e) {
        System.out.println("Input error");
        }
        inputFlush();
        return (char) aChar;
    }


    public static double inDouble(String prompt) {
        while (true) {
          inputFlush();
          printPrompt(prompt);
          try {
            return Double.valueOf(inString().trim()).doubleValue();
          }
    
          catch (NumberFormatException e) {
            System.out
                .println("Invalid input. Not a floating point number");
          }
        }
      }


}