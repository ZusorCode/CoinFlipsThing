import java.util.*;
import java.lang.*;

/**
 * This class can be used to track other instances of classes. All that is
 * required is to have said class extend ObjectTracker. Then anytime, a instance
 * of said class is created, it will be tracked by ObjectTracker.
 */
public class ObjectTracker{

    private static HashMap<String, ArrayList<Object>> objTracker = new HashMap<String, ArrayList<Object>>();
    private static final int CALLER_INDEX = 3;

    /**
     * This will print out the names of all classes currently being tracked as
     * well as the class instances that are being tracked for those classes.
     */
    public static void printObjectTracker()
    {
        for(String className: objTracker.keySet())
        {
            System.out.println("Objects for " + className);
            ArrayList<Object> objs = objTracker.get(className);
            for(Object o: objs)
            {
                System.out.println("\t(" + o.getClass() + ") " + o);
            }
        }
    }


    /**
     * This will retreive all class instances for a given class name.
     * @param  className String of class name you want all tracked objects for.
     * @return           ArrayList of instances of the class you are trying to
     *                   get the tracked class objects for.
     */
    public static ArrayList<Object> getObjectsForClass(String className)
    {
        return objTracker.get(className);
    }

    /**
     * This adds an object to be tracked for a given class name. For the most
     * part, this object will be called automatically upon instance creation.
     * However, you can add extra objects if you like.
     * @param  className String of the class name
     * @param  o         Object to be tracked.
     */
    public static void addObjectForClass(String className, Object o)
    {
        ArrayList<Object> list = objTracker.get(className);
        if(list == null)
        {
            list = new ArrayList<Object>();
        }
        list.add(o);
        objTracker.put(className, list);
    }

    /**
     * This is the main logic for ObjectTracker. It will get called and created
     * everytime a instance is created of a class that extends ObjectTracker.
     */
    public ObjectTracker()
    {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement e = stackTraceElements[CALLER_INDEX];
        String className = e.getClassName();
        System.out.println(className);

        addObjectForClass(className, this);
    }
}