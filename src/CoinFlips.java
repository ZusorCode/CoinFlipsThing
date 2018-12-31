import sun.misc.Unsafe;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class CoinFlips extends ConsoleProgram
{
    public static final int flips = 100;

    public void run() {
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, null, this.getClass().getSimpleName() + "Solution");
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File(".").toURI().toURL() });
            Class<?> cls = Class.forName(this.getClass().getSimpleName() + "Solution", false, classLoader);
            ((ConsoleProgram) cls.newInstance()).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}