package patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Menu implements MenuComponent
{
    private String name;
    List<MenuComponent> components = new ArrayList<>();

    public Menu(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void print()
    {
        System.out.println(String.format(" ==== %s ====", getName()));
        for (MenuComponent component: components)
        {
            component.print();
        }
    }

    @Override
    public void add(MenuComponent component)
    {
        components.add(component);
    }
}
