import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleProgram
{

    /**
     * Private variables for ConsoleProgram
     */
    private Scanner scanner;
    private ArrayList<String> inputs;
    private int curInputIndex = 0;

    /**
     * Setups a new Scanner for System.in and the inputs variable for storring
     * inputs.
     */
    public ConsoleProgram()
    {
        scanner = new Scanner(System.in);
        inputs = new ArrayList<String>();
    }

    /**
     * This is a run method for the class. It is intentionally empty as it gets
     * overwritten by the subclass.
     *
     * All code to be run by a student or user should go in the overridden run
     * method.
     */
    public void run()
    {
    }

    /**
     * Allows you to set all inputs at once as strings.
     * @param inputStrings Array of strings to be inputs
     */
    public void setInputs(String[] inputStrings)
    {
        inputs = new ArrayList<String>(Arrays.asList(inputStrings));
    }

    /**
     * Allows you to add an input to your inputs.
     * @param s String to add
     */
    public void addInput(String s)
    {
        if (inputs == null)
        {
            inputs = new ArrayList<String>();
        }
        inputs.add(s);
    }

    /**
     * ConsoleProgram can also be run as the main class. However this is not
     * necessary.
     * @param args class name to run should be the first argument
     */
    public static void main(String[] args){

        if(args.length == 0){
            System.out.println("Please provide the name of the main class as an argument.");
            return;
        }

        String mainClassName = args[0];

        try{
            Class mainClass = Class.forName(mainClassName);
            Object obj = mainClass.newInstance();
            ConsoleProgram program = (ConsoleProgram)obj;
            program.run();
        } catch (IllegalAccessException ex) {
            System.out.println("Error in program. Make sure you extend ConsoleProgram");
        } catch (InstantiationException ex) {
            System.out.println("Error in program. Make sure you extend ConsoleProgram");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error in program. Make sure you extend ConsoleProgram");
        }
    }

    /**
     * This is a dual purpose function. It both allows the user to get a
     * string input, as well as, get the next input for all other readers.
     * In all cases, it will return the next input as a string, either to the
     * user or another reader.
     * @param  prompt String prompt to show user
     * @return        inputed string
     */
    public String readLine(String prompt){
        System.out.print(prompt);

        if (curInputIndex >= inputs.size())
        {
            System.out.println();
            return null;
        }

        String next = inputs.get(curInputIndex);
        System.out.println(next);
        curInputIndex++;
        return next;
    }

    /**
     * Allow the user to get a boolean.
     * @param  prompt String prompt to show user
     * @return        inputed boolean
     */
    public boolean readBoolean(String prompt){

        while(true){
            String input = readLine(prompt);

            if (input == null) {
                return false;
            }

            if(input.equalsIgnoreCase("true")){
                return true;
            }

            if(input.equalsIgnoreCase("false")){
                return false;
            }
        }
    }

    /**
     * Allow the user to get a double.
     * @param  prompt String prompt to show user
     * @return        inputed double
     */
    public double readDouble(String prompt){

        while(true){
            String input = readLine(prompt);

            if (input == null) {
                return -1.0;
            }

            try {
                double n = Double.valueOf(input).doubleValue();
                return n;
            } catch (NumberFormatException e){

            }
        }
    }

    /**
     * Allow the user to get an integer.
     * @param  prompt String prompt to show user
     * @return        inputed integer
     */
    public int readInt(String prompt){

        while(true){
            String input = readLine(prompt);

            if (input == null) {
                return -1;
            }

            try {
                int n = Integer.parseInt(input);
                return n;
            } catch (NumberFormatException e){

            }
        }
    }

    /**
     * Allows us to use a shorthand version for System.out.println()
     */
    public void println() {
        System.out.println();
    }

    /**
     * Allows us to use a shorthand version for System.out.println(String s)
     * @param s String to print
     */
    public void println(String s) {
        System.out.println(s);
    }

    /**
     * Allows us to use a shorthand version for System.out.println(boolean x)
     * @param x Boolean to print
     */
    public void println(boolean x) {
        System.out.println(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.println(char x)
     * @param x Character to print
     */
    public void println(char x) {
        System.out.println(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.println(char[] x)
     * @param x Character arry to print
     */
    public void println(char[] x) {
        System.out.println(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.println(int x)
     * @param x Integer to print
     */
    public void println(int x) {
        System.out.println(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.println(long x)
     * @param x Long to print
     */
    public void println(long x) {
        System.out.println(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.println(float x)
     * @param x Float to print
     */
    public void println(float x) {
        System.out.println(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.println(double x)
     * @param x Double to print
     */
    public void println(double x) {
        System.out.println(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.println(Object o)
     * @param o Object to print
     */
    public void println(Object o) {
        System.out.println(o);
    }

    /**
     * Allows us to use a shorthand version for System.out.print()
     */
    public void print() {
        System.out.print("");
    }

    /**
     * Allows us to use a shorthand version for System.out.print(String s)
     * @param s String to print with a new line
     */
    public void print(String s) {
        System.out.print(s);
    }

    /**
     * Allows us to use a shorthand version for System.out.print(boolean x)
     * @param x Boolean to print with a new line
     */
    public void print(boolean x) {
        System.out.print(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.print(char x)
     * @param x Character to print with a new line
     */
    public void print(char x) {
        System.out.print(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.print(char[] x)
     * @param x Character arry to print with a new line
     */
    public void print(char[] x) {
        System.out.print(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.print(int x)
     * @param x Integer to print with a new line
     */
    public void print(int x) {
        System.out.print(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.print(long x)
     * @param x Long to print with a new line
     */
    public void print(long x) {
        System.out.print(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.print(float x)
     * @param x Float to print with a new line
     */
    public void print(float x) {
        System.out.print(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.print(double x)
     * @param x Double to print with a new line
     */
    public void print(double x) {
        System.out.print(x);
    }

    /**
     * Allows us to use a shorthand version for System.out.print(Object o)
     * @param o Object to print with a new line
     */
    public void print(Object o) {
        System.out.print(o);
    }
}