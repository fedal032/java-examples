package patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class MainMenu
{
    private String name;
    private List<MenuComponent> items = new ArrayList<>();

    public MainMenu(String name)
    {
        this.name = name;
        items.add(createMenu("Bar menu"));
        items.add(createRestMenu("Rest menu"));
    }

    private MenuComponent createMenu(String name)
    {
        MenuComponent menu = new Menu(name);
        menu.add(new MenuItem("Beer", 12.));
        menu.add(new MenuItem("Chips", 7.));
        return menu;
    }

    private MenuComponent createRestMenu(String name)
    {
        MenuComponent restMenu = new Menu(name);
        restMenu.add(new MenuItem("Salad", 12.));
        restMenu.add(new MenuItem("Stake", 7.));
        restMenu.add(createMenu("Desert"));
        return restMenu;
    }

    private MenuComponent createDesertMenu(String name)
    {
        MenuComponent menu = new Menu(name);
        menu.add(new MenuItem("Pavlova", 12.));
        menu.add(new MenuItem("Napoleon", 7.));
        return menu;
    }

    public List<MenuComponent> getItems()
    {
        return items;
    }

    public void print()
    {
        System.out.println("===== MENU: " + name + " =======");
        for (MenuComponent i : items)
        {
            i.print();
        }
    }

}
