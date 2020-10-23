
// Class that drives everything.
public class Main {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        //Take filename as input
        fileParser.parseFileInput(args[0]);        
    }
}