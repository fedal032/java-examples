package patterns.command;

import patterns.command.action.Cooler;

import java.util.Stack;

public class CoolerSetModeCommand implements Command
{
    private final Cooler cooler;
    private Stack<Cooler.MODE> modes = new Stack<Cooler.MODE>();


    public CoolerSetModeCommand(Cooler cooler)
    {
        this.cooler = cooler;
        modes.push(cooler.getMode());
    }


    public void execute()
    {
        System.out.println("the new mode is " + cooler.getMode());
        modes.push(cooler.getMode());
    }

    public void undo()
    {
        System.out.println("undo current mode " + cooler.getMode());
        cooler.setMode(modes.pop());
        System.out.println("current mode is " + cooler.getMode());
    }
}
