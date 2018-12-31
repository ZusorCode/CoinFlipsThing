/**
 * this is where autograded tests should live.
 */
import java.util.*;

public class Grader
{
    public static void main(String [] args)
    {
        Autograder grader = new Autograder();

        for(int i = 1; i <= 3; i++)
        {
            CoinFlips p1 = new CoinFlips();
            CoinFlipsSolution p2 = new CoinFlipsSolution();
            Randomizer.getInstance().setSeed(i);
            p1.run();
            Randomizer.getInstance().setSeed(i);
            p2.run();
            String studentoutput = grader.getOutput("coinflips");
            String solutionoutput = grader.getOutput("coinflipssolution");
            // track custom test
            // track custom test
            // replace any nonalphanumeric characters
            String normedStudentOutput = studentoutput.toLowerCase().replace("", "");
            String normedSolutionOutput = solutionoutput.toLowerCase().replace("", "");
            grader.assertEqual(
                    "testing output with seed " + i, normedStudentOutput,
                    normedSolutionOutput, "great!", "not quite.");
            grader.clearOutput();

        }
        System.out.println(grader);
    }
}