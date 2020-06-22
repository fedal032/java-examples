package patterns.singleton;


import org.junit.jupiter.api.Test;

class SingletonTest
{

    @Test
    void getInstanceConcurrecnySafe()
    {
        Singleton instance = Singleton.getInstanceConcurrecnySafe();
        if (instance != null){
            System.out.println("instance was created");
        }
    }
}