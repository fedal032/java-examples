package patterns.command.action;

public class Cooler
{
    public enum MODE
    {
        OFF,
        COOL,
        DRY,
        HOT
    }

    private MODE mode = MODE.OFF;

    public void on(){
        System.out.println("mode is " + mode.name());
        System.out.println("cooler on");
    }

    public void off(){
        System.out.println("cooler off");
    }

    public MODE getMode()
    {
        return mode;
    }

    public void setMode(MODE mode)
    {
        System.out.println("Mode changed: the old is " + this.mode.name() + ", the new is " + mode.name());
        this.mode = mode;
    }
}
