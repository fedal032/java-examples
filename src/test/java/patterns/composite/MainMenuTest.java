package patterns.composite;

import org.junit.jupiter.api.Test;

class MainMenuTest
{
    @Test
    void testMenuComposite(){
        MainMenu mainMenu = new MainMenu("all menus");
        mainMenu.print();
    }

}