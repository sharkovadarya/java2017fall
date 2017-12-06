package ru.spbau.group202.sharkova.hw9streams;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        ArrayList<String> paths = new ArrayList<>();
        paths.add("src/test/resources/ru/spbau/group202/sharkova/hw9streams/file1.txt");
        paths.add("src/test/resources/ru/spbau/group202/sharkova/hw9streams/file2.txt");
        paths.add("src/test/resources/ru/spbau/group202/sharkova/hw9streams/file3.txt");

        List<String> list = SecondPartTasks.findQuotes(paths, "a");
        assertEquals(13, list.size());
        assertEquals(false, list.contains("21"));
        assertEquals(false, list.contains("121"));
        assertEquals(false, list.contains("\n"));

        list = SecondPartTasks.findQuotes(paths, "aa");
        assertEquals(1, list.size());
        assertEquals(true, list.contains("aa"));

        list = SecondPartTasks.findQuotes(paths, "abcde");
        assertEquals(4, list.size());
        assertEquals(true, list.contains("abcdefg"));
        assertEquals(true, list.contains("abcdefgh"));
        assertEquals(true, list.contains(" . abcdefg../."));
        list.remove("abcdefg");
        assertEquals(true, list.contains("abcdefg"));
        list.remove("abcdefg");
        assertEquals(false, list.contains("abcdefg"));
    }

    @Test
    public void testPiDividedBy4() {
        for (int i = 0; i < 25; i++) {
            assertEquals(Math.PI / 4, SecondPartTasks.piDividedBy4(), 1e-2);
        }
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> compositions = new HashMap<>();
        compositions.put("Victor Hugo", Arrays.asList("So long as there shall exist, " +
                "by virtue of law and custom, decrees of damnation pronounced by society, " +
                "artificially creating hells amid the civilization of earth, " +
                "and adding the element of human fate to divine destiny;",
                "so long as the three great problems of the century — " +
                "the degradation of man through pauperism, " +
                "the corruption of woman through hunger,",
                "the crippling of children through lack of light—are unsolved;",
                "so long as social asphyxia is possible in any part of the world;",
                "—in other words, and with a still wider significance, " +
                "so long as ignorance and poverty exist on earth, " +
                "books of the nature of Les Misérables cannot fail to be of use."));
        compositions.put("Блок", Arrays.asList("Ночь.", "Улица.",
                "Фонарь.", "Аптека.", "Бессмысленный и тусклый свет.",
                "Живи ещё хоть четверть века — Всё будет так. "));
        compositions.put("Ayn Rand", Arrays.asList("Roark threw aside the sketch of " +
                "the graceful facade with the fluted pilasters, the broken pediments, " +
                "the Roman fasces over the windows and the two eagles of Empire by the " +
                "entrance.",  "He picked up the plans. "));
        assertEquals("Victor Hugo", SecondPartTasks.findPrinter(compositions));
    }

    @Test
    public void testCalculateGlobalOrder() {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 30);
        map1.put("a", 21);
        map1.put("aA", 10);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("A", 59);
        map2.put("a", 2);
        map2.put("AA", 11);
        Map<String, Integer> map3 = new HashMap<>();
        map3.put("AA", 16);
        ArrayList<Map<String, Integer>> orders = new ArrayList<>();
        orders.add(map1);
        orders.add(map2);
        orders.add(map3);

        Map<String, Integer> globalOrder = SecondPartTasks.calculateGlobalOrder(orders);
        assertEquals(4, globalOrder.keySet().size());
        assertEquals(new Integer(89), globalOrder.get("A"));
        assertEquals(new Integer(23), globalOrder.get("a"));
        assertEquals(new Integer(10), globalOrder.get("aA"));
        assertEquals(new Integer(27), globalOrder.get("AA"));
    }
}