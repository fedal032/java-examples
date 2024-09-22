package yandex;
import org.junit.jupiter.api.Test;
import patterns.command.Command;
import patterns.command.CoolerCommand;
import patterns.command.CoolerSetModeCommand;
import patterns.command.LigthOnCommand;
import patterns.command.action.Cooler;
import patterns.command.action.LigthOn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class SchoolTest
{
    @Test
    int task1()
    {
        String J = "ab";
        String S = "aabbccd";

        if (S.length() == 0)
            return 0;

        Set<Character> charsSetS = S.chars()
                .mapToObj(e->(char)e).collect(Collectors.toSet());
        int c = 0;
        for (char j : J.toCharArray())
        {
            if (charsSetS.contains(j))
                c++;
        }

        return c;
    }

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

        List<String> strings = Arrays.asList("one", "one", "two");

        List<String> list = doSmth(strings);
        List<String> listOpt = doSmthOpt(strings);

    }


    private static <T> List<T> doSmth(List<T> l) {
        List<T> u = new ArrayList<>();

        l.forEach(e -> {
            if (!u.contains(e)) {
                u.add(e);
            }
        });
        return u;
    }

    private static <T> List<T> doSmthOpt(List<T> l) {
        return new ArrayList<>(new HashSet<>(l));
    }


}
