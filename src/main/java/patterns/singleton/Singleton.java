package patterns.singleton;

public class Singleton
{
    //мнговенная инициализация, которая безопасна для многопоточки
    //private static Singleton patterns.singleton = new Singleton();

    private static Singleton instance;

    // Синхронизация метода, может значительно снизить производительность
    //public static synchronized Singleton getInstance()
    public static Singleton getInstance()
    {
        //ленивая инициализация, небезопасна при многопоточке
        if(instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

    // Ленивая инициализация в многопточной среде
    //private static volatile Singleton instance;
    public static Singleton getInstanceConcurrecnySafe()
    {
        //ленивая инициализация, небезопасна при многопоточке
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    private Singleton() {
    }
}
