package patterns.command;

import patterns.command.action.Cooler;

public class CoolerCommand implements Command
{
    private Cooler cooler;

    public CoolerCommand(Cooler cooler)
    {
        this.cooler = cooler;
    }

    public void execute()
    {
        cooler.on();
    }

    public void undo()
    {
        cooler.off();
    }
}
