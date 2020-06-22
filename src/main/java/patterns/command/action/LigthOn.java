package patterns.command.action;

public class LigthOn
{
    private final int lamps;

    public LigthOn(int lamps)
    {
        this.lamps = lamps;
    }

    public void on(){
        System.out.println(lamps + " lamps on");
    }

    public void off(){
        System.out.println("light off");
    }
}
