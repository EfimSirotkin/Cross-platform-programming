package epam.lab8;

import classes.CustomLinkedList;
import classes.ChainOfResponsibilities;
import classes.EnormousCollection;
import javassist.CannotCompileException;
import javassist.ClassPool;
import classes.BinaryTree;

import static classes.CustomLinkedList.printList;


public class Main {

        public static void main(String[] args) throws CannotCompileException, InterruptedException {
/*

        do {
            try {
                new EnormousCollection().populateList();
            } catch (Error e) {
                String a = e.getMessage();
                if (a.equals("Java heap space")) {
                    System.out.println("1) " + a);

                } else {
                    System.out.println("Something went wrong ");
                }
                break;
            }
        } while (true);
*/

            CustomLinkedList list = new CustomLinkedList();
            String abc = "abc";
            while(true){
                abc+=abc;
              /*  try {
                    list = CustomLinkedList.insert(list, (int)(Math.random()* 10000));
                }catch(Error e){
                    System.out.println("2) " + e.toString());
                    break;
                }*/
            }

        /*try {
            Main main = new Main();
            main.RecursionCorruption();
        } catch (Error e) {
            if (e.toString() == "java.lang.StackOverflowError") {
                System.out.println("4) " + e.toString());
            } else {
                System.out.println("Something went wrong ");
            }
        }

        try {
            ChainOfResponsibilities.work();
        } catch (Error e) {
            if (e.toString().equals("java.lang.StackOverflowError")) {
                System.out.println("5) " + e.toString());
            } else {
                System.out.println("Something went wrong ");
            }
        }
            try {
                System.out.println("Starting...");
                ClassPool cp = ClassPool.getDefault();
                for (int i = 0; i < 1_500_000; i++) {
                    if (i % 50000 == 0) {
                        System.out.println(">> Sleeping for " + i);
                        Thread.sleep(2000);
                    }
                    String className = "Metaspace" + i;
                    cp.makeClass(className).toClass();
                }
            }catch(Error e){
                System.out.println(e.toString());
            }*/
    }

    public void RecursionCorruption() {
        RecursionCorruption();
    }
}
