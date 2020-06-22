package patterns.command;

import patterns.command.action.LigthOn;

public class LigthOnCommand implements Command
{
    private LigthOn ligthOn;

    public LigthOnCommand(LigthOn ligthOn)
    {
        this.ligthOn = ligthOn;
    }

    public void execute()
    {
        ligthOn.on();
    }

    public void undo()
    {
        ligthOn.off();
    }
}
