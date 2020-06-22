package patterns.command;

import org.junit.jupiter.api.Test;
import patterns.command.action.Cooler;
import patterns.command.action.LigthOn;

class CommandTest
{
    @Test
    void commandTest(){
        LigthOn light = new LigthOn(5);
        Command lightOn = new LigthOnCommand(light);
        lightOn.execute();

        Cooler cooler = new Cooler();
        Command coolerOn = new CoolerCommand(cooler);
        coolerOn.execute();

        Command coolerModeChange = new CoolerSetModeCommand(cooler);
        cooler.setMode(Cooler.MODE.COOL);
        coolerModeChange.execute();
        cooler.setMode(Cooler.MODE.DRY);
        coolerModeChange.execute();
        cooler.setMode(Cooler.MODE.HOT);
        coolerModeChange.execute();

        coolerModeChange.undo();
        coolerModeChange.undo();
        coolerModeChange.undo();






    }
}
