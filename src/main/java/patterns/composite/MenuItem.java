package patterns.composite;

public class MenuItem implements MenuComponent
{
    private String name;
    private Double cost;

    public MenuItem(String name, Double cost)
    {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public void print()
    {
        System.out.println(String.format("Name: %s, cost: %f", getName(), cost));
    }

    @Override
    public boolean getChild()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
