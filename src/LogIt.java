import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

public class LogIt extends PrintStream {

    private static long startTime = -1;

    public static final int NONE = 0;
    public static final int ERROR = 1;
    public static final int WARN = 2;
    public static final int INFO = 3;
    public static final int DEBUG = 4;
    public static final int ALL = 5;

    private static int logLevel = ALL;
    private static boolean showExtraInfo = true;

    private int outType = INFO;

    private LogIt(OutputStream out, int outType) {
        super(out);
        this.outType = outType;
    }

    public void printlnOverride(String text) {
        if (outType == INFO)
            if (startTime == -1)
                System.err.println("LogIt Class not Intitialized or error with System Clock!");
            else if (showExtraInfo) {
                StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
                String clas = javaToClassName(ste.getFileName());
                int line = ste.getLineNumber();
                super.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                        (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                        ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                        + " INFO/" + Thread.currentThread().getName() + " @ " + clas + ":" + line + " : " + text);
            } else
                super.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                        (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                        ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                        + " INFO/" + Thread.currentThread().getName() + " : " + text);
        else if (outType == ERROR)
            if (startTime == -1)
                super.println("LogIt Class not Intitialized or error with System Clock!");
            else if (showExtraInfo) {
                StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
                String clas = javaToClassName(ste.getFileName());
                int line = ste.getLineNumber();
                super.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                        (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                        ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                        + " EROR/" + Thread.currentThread().getName() + " @ " + clas + ":" + line + " : " + text);
            } else
                super.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                        (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                        ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                        + " EROR/" + Thread.currentThread().getName() + " : " + text);
    }

    @Override
    public void println(String text) {
        printlnOverride(text);
    }

    @Override
    public void println(boolean x) {
        printlnOverride(x + "");
    }

    @Override
    public void println(char x) {
        printlnOverride(x + "");
    }

    @Override
    public void println(char[] x) {
        printlnOverride(new String(x));
    }

    @Override
    public void println(double x) {
        printlnOverride(x + "");
    }

    @Override
    public void println(float x) {
        printlnOverride(x + "");
    }

    @Override
    public void println(int x) {
        printlnOverride(x + "");
    }

    @Override
    public void println(long x) {
        printlnOverride(x + "");
    }

    @Override
    public void println(Object x) {
        printlnOverride(x.toString());
    }

    public static void init() {
        resetStartTime();
        System.setOut(new LogIt(System.out, INFO));
        // System.setErr(new LogIt(System.err, ERROR));
    }

    public static void info(String text) {
        if (logLevel < INFO)
            return;

        if (startTime == -1)
            System.err.println("LogIt Class not Intitialized or error with System Clock!");
        else if (showExtraInfo) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
            String clas = javaToClassName(ste.getFileName());
            int line = ste.getLineNumber();
            System.out.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " INFO/" + Thread.currentThread().getName() + " @ " + clas + ":" + line + " : " + text);
        } else
            System.out.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " INFO/" + Thread.currentThread().getName() + " : " + text);
    }

    public static void debug(String text) {
        if (logLevel < DEBUG)
            return;

        if (startTime == -1)
            System.err.println("LogIt Class not Intitialized or error with System Clock!");
        else if (showExtraInfo) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
            String clas = javaToClassName(ste.getFileName());
            int line = ste.getLineNumber();
            System.out.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " DBUG/" + Thread.currentThread().getName() + " @ " + clas + ":" + line + " : " + text);
        } else
            System.out.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " DBUG/" + Thread.currentThread().getName() + " : " + text);
    }

    public static void err(String text) {
        if (logLevel < ERROR)
            return;

        if (startTime == -1)
            System.err.println("LogIt Class not Intitialized or error with System Clock!");
        else if (showExtraInfo) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
            String clas = javaToClassName(ste.getFileName());
            int line = ste.getLineNumber();
            System.err.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " EROR/" + Thread.currentThread().getName() + " @ " + clas + ":" + line + " : " + text);
        } else
            System.err.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " EROR/" + Thread.currentThread().getName() + " : " + text);
    }

    public static void warn(String text) {
        if (logLevel < WARN)
            return;

        if (startTime == -1)
            System.err.println("LogIt Class not Intitialized or error with System Clock!");
        else if (showExtraInfo) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
            String clas = javaToClassName(ste.getFileName());
            int line = ste.getLineNumber();
            System.err.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " WARN/" + Thread.currentThread().getName() + " @ " + clas + ":" + line + " : " + text);
        } else
            System.err.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " WARN/" + Thread.currentThread().getName() + " : " + text);
    }

    public static void info(String text, StackTraceElement[] stack, int backtrace) {
        if (logLevel < INFO)
            return;

        if (startTime == -1)
            System.err.println("LogIt Class not Intitialized or error with System Clock!");
        else if (showExtraInfo) {
            StackTraceElement ste = stack[backtrace];
            String clas = javaToClassName(ste.getFileName());
            int line = ste.getLineNumber();
            System.out.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " INFO/" + Thread.currentThread().getName() + " @ " + clas + ":" + line + " : " + text);
        } else
            System.out.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " INFO/" + Thread.currentThread().getName() + " : " + text);
    }

    public static void debug(String text, StackTraceElement[] stack, int backtrace) {
        if (logLevel < DEBUG)
            return;

        if (startTime == -1)
            System.err.println("LogIt Class not Intitialized or error with System Clock!");
        else if (showExtraInfo) {
            StackTraceElement ste = stack[backtrace];
            String clas = javaToClassName(ste.getFileName());
            int line = ste.getLineNumber();
            System.out.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " DBUG/" + Thread.currentThread().getName() + " @ " + clas + ":" + line + " : " + text);
        } else
            System.out.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " DBUG/" + Thread.currentThread().getName() + " : " + text);
    }

    public static void err(String text, StackTraceElement[] stack, int backtrace) {
        if (logLevel < ERROR)
            return;

        if (startTime == -1)
            System.err.println("LogIt Class not Intitialized or error with System Clock!");
        else if (showExtraInfo) {
            StackTraceElement ste = stack[backtrace];
            String clas = javaToClassName(ste.getFileName());
            int line = ste.getLineNumber();
            System.err.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " EROR/" + Thread.currentThread().getName() + " @ " + clas + ":" + line + " : " + text);
        } else
            System.err.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " EROR/" + Thread.currentThread().getName() + " : " + text);
    }

    public static void warn(String text, StackTraceElement[] stack, int backtrace) {
        if (logLevel < WARN)
            return;

        if (startTime == -1)
            System.err.println("LogIt Class not Intitialized or error with System Clock!");
        else if (showExtraInfo) {
            StackTraceElement ste = stack[backtrace];
            String clas = javaToClassName(ste.getFileName());
            int line = ste.getLineNumber();
            System.err.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " WARN/" + Thread.currentThread().getName() + " @ " + clas + ":" + line + " : " + text);
        } else
            System.err.println(String.format("%02d:%02d:%02d:%03d", (int) (((System.currentTimeMillis() - startTime) / (1000 * 60 * 60)) % 24),
                    (int) (((System.currentTimeMillis() - startTime) / (1000 * 60)) % 60),
                    ((int) ((System.currentTimeMillis() - startTime) / 1000) % 60), ((int) ((System.currentTimeMillis() - startTime)) % 1000))
                    + " WARN/" + Thread.currentThread().getName() + " : " + text);
    }

    public static long getStartTime() {
        return startTime;
    }

    public static void resetStartTime() {
        LogIt.startTime = System.currentTimeMillis();
    }

    public static boolean isShowExtraInfo() {
        return showExtraInfo;
    }

    public static void setShowExtraInfo(boolean showExtraInfo) {
        LogIt.showExtraInfo = showExtraInfo;
    }

    public static void setLogLevel(int level) {
        if ((level >= NONE) && (level <= ALL))
            logLevel = level;

    }

    public static int getLogLevel() {
        return logLevel;
    }

    public static float getTimeDelta() {
        return (System.currentTimeMillis() - startTime) / 1000f;
    }

    public static void addCurrentThreadErrorHandler() {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String stacktrace = sw.toString();
                LogIt.err(stacktrace, e.getStackTrace(), 0);
            }
        });
    }

    public static void addAllErrorHandlers() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        Thread[] threads = threadSet.toArray(new Thread[threadSet.size()]);

        for (int i = 0; i < threads.length; i++)
            threads[i].setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    String stacktrace = sw.toString();
                    LogIt.err(stacktrace, e.getStackTrace(), 0);
                }
            });
    }

    private static String javaToClassName(String java) {
        String cla = "";
        for (int i = 0; i < java.length(); i++) {
            if (java.charAt(i) == '.')
                break;
            cla += java.charAt(i);
        }
        return cla;
    }
}
