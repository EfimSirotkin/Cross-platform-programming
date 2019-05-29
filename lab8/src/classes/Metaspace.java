package classes;

import javassist.CannotCompileException;

import java.util.ArrayList;
import java.util.List;

public class Metaspace {
    //static javassist.ClassPool cp = javassist.ClassPool.getDefault();

   private static List<Class> classes = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            try {
                Class<?> aClass = Metaspace.class.getClassLoader().loadClass("classes.Metaspace");
                classes.add(aClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void work() throws CannotCompileException {

    }
}
