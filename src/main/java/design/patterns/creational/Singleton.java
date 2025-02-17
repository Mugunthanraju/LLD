package design.patterns.creational;

/*
Singleton is a creational design pattern that lets you ensure that a class
has only one instance, while providing a global access point to this
instance.
*/

/*
> Should be used when a class must have a single instance variable.
> Disables all means of creating objects of a class expect for the special
static creation method.
> Returns existing instance if it has already been created.
> Its code needs to be adapted to handle multi threads.
 */

public class Singleton {

    private static Singleton instance;
    private String data;

    private Singleton(String data) {
        this.data = data;
    }

    public static Singleton getInstance(String data) {
        if (instance == null)
            instance = new Singleton(data);

        return instance;
    }

    // Above is the basic Singleton pattern example
    // Below is to make robust Singleton pattern class

    /*

        private static volatile Singleton instance;
        private String data;

        private Singleton(String data) {
            this.data = data;
        }

        public static Singleton getInstance(String data) {
            Singleton result = instance;
            if (result == null){
                synchronized (Singleton.class) {
                    result = instance;
                    if (result == null) {
                        instance = result = new Singleton(data);
                    }
                }
            }
            return result;
        }
     */
}