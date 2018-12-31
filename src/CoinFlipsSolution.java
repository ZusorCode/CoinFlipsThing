public class CoinFlipsSolution extends ConsoleProgram
{
    public static final int flips = 100;

    public void run()
    {
        int heads = 0;
        int tails = 0;
        for(int i = 0; i < flips; i++)
        {
            if(Randomizer.nextBoolean())
            {
                System.out.println("heads");
                heads++;
            }
            else
            {
                System.out.println("tails");
                tails++;
            }
        }

        System.out.println("heads " + heads);
        System.out.println("tails " + tails);
        System.out.println("% heads " + heads / (double)flips);
        System.out.println("% tails " + tails / (double)flips);

    }
}