package patterns.composite;

public interface MenuComponent
{
    default void print()
    {
        throw new UnsupportedOperationException();
    }

    default void add(MenuComponent component)
    {
        throw new UnsupportedOperationException();
    }

    default boolean getChild()
    {
        throw new UnsupportedOperationException();
    }

    String getName();
}
