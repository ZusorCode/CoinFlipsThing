/**
 * this is where autograded tests should live.
 */

public class Grader
{
    public static void main(String [] args)
    {
        Autograder grader = new Autograder();
        LogIt.init();

        for(int i = 1; i <= 3; i++)
        {
            CoinFlips p1 = new CoinFlips();
            CoinFlipsSolution p2 = new CoinFlipsSolution();
            Randomizer.getInstance().setSeed(i);
            p1.run();
            Randomizer.getInstance().setSeed(i);
            p2.run();
            String StudentOutput = grader.getOutput("CoinFlips");
            String SolutionOutput = grader.getOutput("CoinFlipsSolution");
            // track custom test
            // track custom test
            // replace any nonalphanumeric characters
            String normedStudentOutput = StudentOutput.toLowerCase().replace("", "");
            String normedSolutionOutput = SolutionOutput.toLowerCase().replace("", "");
            grader.assertEqual(
                    "testing output with seed " + i, normedStudentOutput,
                    normedSolutionOutput, "great!", "not quite.");
            grader.clearOutput();

        }
        System.out.println(grader);
    }
}