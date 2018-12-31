import java.io.*;
import java.lang.*;

/**
 * This class intercepts output destined for System.out so it can be tracked.
 */
public class Interceptor extends PrintStream
{

    private Autograder prog;
    private static final int CLASS_STACK_POS = 3;

    /**
     * Setups up an interceptor for System.out
     * @param  out  The output stream being used (should be System.out)
     * @param  prog The autograder that is using this Incerceptor
     */
    public Interceptor(OutputStream out, Autograder prog)
    {
        super(out, true);
        this.prog = prog;
    }

    /**
     * This handles the general logic for printing to System.out. Based on the
     * `newLine` boolean, we decide whether we print or add a line break. After,
     * we determine which class it came from, based on the stacktrace. If the
     * class is `ConsoleProgram`, we look one level up on the stack trace.
     * @param output  The output to print to System.out and store
     * @param newLine Boolean for whether this is using print or println
     */
    private void addOutput(String output, boolean newLine)
    {
        if (newLine)
        {
            super.println(output);
            output += "\n";
        } else {
            super.print(output);
        }
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String className = stackTraceElements[CLASS_STACK_POS].getClassName();
        int stackPos = CLASS_STACK_POS;
        while (className == "ConsoleProgram")
        {
            stackPos += 1;
            className = stackTraceElements[stackPos].getClassName();
        }
        this.prog.addOutput(className, output);
    }

    /**
     * This overrides System.out.print. It prints out to System.out like normal.
     * Afterwards, it stores the output in the Autograder along with the class
     * name that is trying to print based on the stack trace (up one for
     * ConsoleProgram).
     * @param s String to be printed
     */
    @Override
    public void print(String s)
    {
        this.addOutput(s, false);
    }

    /**
     * This overrides System.out.print. It prints out to System.out like normal.
     * Afterwards, it stores the output in the Autograder along with the class
     * name that is trying to print based on the stack trace (up one for
     * ConsoleProgram).
     * @param x Boolean to be printed
     */
    @Override
    public void print(boolean x)
    {
        String output = Boolean.toString(x);
        this.addOutput(output, false);
    }

    /**
     * This overrides System.out.print. It prints out to System.out like normal.
     * Afterwards, it stores the output in the Autograder along with the class
     * name that is trying to print based on the stack trace (up one for
     * ConsoleProgram).
     * @param x Character to be printed
     */
    @Override
    public void print(char x)
    {
        String output = Character.toString(x);
        this.addOutput(output, false);
    }

    /**
     * This overrides System.out.print. It prints out to System.out like normal.
     * Afterwards, it stores the output in the Autograder along with the class
     * name that is trying to print based on the stack trace (up one for
     * ConsoleProgram).
     * @param x Character array to be printed
     */
    @Override
    public void print(char[] x)
    {
        String output = new String(x);
        this.addOutput(output, false);
    }

    /**
     * This overrides System.out.print. It prints out to System.out like normal.
     * Afterwards, it stores the output in the Autograder along with the class
     * name that is trying to print based on the stack trace (up one for
     * ConsoleProgram).
     * @param x Integer to be printed
     */
    @Override
    public void print(int x)
    {
        String output = Integer.toString(x);
        this.addOutput(output, false);
    }

    /**
     * This overrides System.out.print. It prints out to System.out like normal.
     * Afterwards, it stores the output in the Autograder along with the class
     * name that is trying to print based on the stack trace (up one for
     * ConsoleProgram).
     * @param x Long to be printed
     */
    @Override
    public void print(long x)
    {
        String output = Long.toString(x);
        this.addOutput(output, false);
    }

    /**
     * This overrides System.out.print. It prints out to System.out like normal.
     * Afterwards, it stores the output in the Autograder along with the class
     * name that is trying to print based on the stack trace (up one for
     * ConsoleProgram).
     * @param x Float to be printed
     */
    @Override
    public void print(float x)
    {
        String output = Float.toString(x);
        this.addOutput(output, false);
    }

    /**
     * This overrides System.out.print. It prints out to System.out like normal.
     * Afterwards, it stores the output in the Autograder along with the class
     * name that is trying to print based on the stack trace (up one for
     * ConsoleProgram).
     * @param x Double to be printed
     */
    @Override
    public void print(double x)
    {
        String output = Double.toString(x);
        this.addOutput(output, false);
    }

    /**
     * This overrides System.out.print. It prints out to System.out like normal.
     * Afterwards, it stores the output in the Autograder along with the class
     * name that is trying to print based on the stack trace (up one for
     * ConsoleProgram).
     * @param o Object to be printed
     */
    @Override
    public void print(Object o)
    {
        String output = o.toString();
        this.addOutput(output, false);
    }

    /**
     * This overrides System.out.println. It prints out to System.out like
     * normal. Afterwards, it stores the output in the Autograder along with
     * the class name that is trying to print based on the stack trace (up one
     * for ConsoleProgram).
     * @param s String to be printed
     */
    @Override
    public void println(String s)
    {
        this.addOutput(s, true);
    }

    /**
     * This overrides System.out.println. It prints out to System.out like
     * normal. Afterwards, it stores the output in the Autograder along with
     * the class name that is trying to print based on the stack trace (up one
     * for ConsoleProgram).
     */
    @Override
    public void println()
    {
        this.addOutput("", true);
    }

    /**
     * This overrides System.out.println. It prints out to System.out like
     * normal. Afterwards, it stores the output in the Autograder along with
     * the class name that is trying to print based on the stack trace (up one
     * for ConsoleProgram).
     * @param x Boolean to be printed
     */
    @Override
    public void println(boolean x)
    {
        String output = Boolean.toString(x);
        this.addOutput(output, true);
    }

    /**
     * This overrides System.out.println. It prints out to System.out like
     * normal. Afterwards, it stores the output in the Autograder along with
     * the class name that is trying to print based on the stack trace (up one
     * for ConsoleProgram).
     * @param x Character to be printed
     */
    @Override
    public void println(char x)
    {
        String output = Character.toString(x);
        this.addOutput(output, true);
    }

    /**
     * This overrides System.out.println. It prints out to System.out like
     * normal. Afterwards, it stores the output in the Autograder along with
     * the class name that is trying to print based on the stack trace (up one
     * for ConsoleProgram).
     * @param x Character array to be printed
     */
    @Override
    public void println(char[] x)
    {
        String output = new String(x);
        this.addOutput(output, true);
    }

    /**
     * This overrides System.out.println. It prints out to System.out like
     * normal. Afterwards, it stores the output in the Autograder along with
     * the class name that is trying to print based on the stack trace (up one
     * for ConsoleProgram).
     * @param x Integer to be printed
     */
    @Override
    public void println(int x)
    {
        String output = Integer.toString(x);
        this.addOutput(output, true);
    }

    /**
     * This overrides System.out.println. It prints out to System.out like
     * normal. Afterwards, it stores the output in the Autograder along with
     * the class name that is trying to print based on the stack trace (up one
     * for ConsoleProgram).
     * @param x Long to be printed
     */
    @Override
    public void println(long x)
    {
        String output = Long.toString(x);
        this.addOutput(output, true);
    }

    /**
     * This overrides System.out.println. It prints out to System.out like
     * normal. Afterwards, it stores the output in the Autograder along with
     * the class name that is trying to print based on the stack trace (up one
     * for ConsoleProgram).
     * @param x Float to be printed
     */
    @Override
    public void println(float x)
    {
        String output = Float.toString(x);
        this.addOutput(output, true);
    }

    /**
     * This overrides System.out.println. It prints out to System.out like
     * normal. Afterwards, it stores the output in the Autograder along with
     * the class name that is trying to print based on the stack trace (up one
     * for ConsoleProgram).
     * @param x Double to be printed
     */
    @Override
    public void println(double x)
    {
        String output = Double.toString(x);
        this.addOutput(output, true);
    }

    /**
     * This overrides System.out.println. It prints out to System.out like
     * normal. Afterwards, it stores the output in the Autograder along with
     * the class name that is trying to print based on the stack trace (up one
     * for ConsoleProgram).
     * @param o Object to be printed
     */
    @Override
    public void println(Object o)
    {
        String output = o.toString();
        this.addOutput(output, true);
    }
}