package ru.spbau.group202.sharkova.hw7treeset;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * This test class checks correctness of BinaryTreeSet methods.
 */
public class BinaryTreeSetTest {

    @Test
    public void testAdd() {
        BinaryTreeSet<Double> bts = new BinaryTreeSet<>();

        assertEquals(true, bts.add(3.14));
        assertEquals(false, bts.add(3.14));
        assertEquals(true, bts.add(3.1));
        assertEquals(false, bts.add(3.1));
        assertEquals(true, bts.add(2.0));
        assertEquals(false, bts.add(2.0));
    }

    @Test
    public void testContains() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.add(1);
        bts.add(2);
        bts.add(3);
        assertEquals(true, bts.contains(1));
        assertEquals(true, bts.contains(2));
        assertEquals(true, bts.contains(3));
        assertEquals(false, bts.contains(4));
        bts.add(3);
        assertEquals(true, bts.contains(1));
        assertEquals(true, bts.contains(2));
        assertEquals(true, bts.contains(3));
        assertEquals(false, bts.contains(4));
    }

    @Test
    public void testAddAll() {
        ArrayList<Character> list = new ArrayList<>();
        list.add('a');
        list.add('c');
        list.add('x');
        list.add(' ');
        list.add('4');

        BinaryTreeSet<Character> bts = new BinaryTreeSet<>();
        bts.add('x');
        assertEquals(true, bts.addAll(list));
        assertEquals(true, bts.contains('a'));
        assertEquals(true, bts.contains('c'));
        assertEquals(true, bts.contains('x'));
        assertEquals(true, bts.contains(' '));
        assertEquals(true, bts.contains('4'));
        assertEquals(5, bts.size());
        assertEquals(false, bts.addAll(list));
        assertEquals(5, bts.size());
    }

    @Test
    public void testFirst() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.add(1);
        bts.add(2);
        bts.add(3);
        assertEquals(new Integer(1), bts.first());
    }

    @Test
    public void testLast() {
        BinaryTreeSet<String> bts = new BinaryTreeSet<>();
        bts.add("b");
        bts.add("a");
        bts.add("ba");
        bts.add("aa");
        bts.add("aab");
        assertEquals("ba", bts.last());
    }

    @Test
    public void testLowerInteger() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.add(1);
        bts.add(2);
        bts.add(3);
        assertEquals(new Integer(1), bts.lower(2));
        assertEquals(null, bts.lower(1));
        assertEquals(new Integer(2), bts.lower(3));
    }

    @Test
    public void testLowerString() {
        BinaryTreeSet<String> bts = new BinaryTreeSet<>();
        bts.add("dda");
        bts.add("e");
        bts.add("daca");
        bts.add("ca");
        bts.add("dacb");
        bts.add("df");
        bts.add("ea");
        bts.add("aaa");
        bts.add("cb");
        bts.add("ddc");
        bts.add("dfk");
        bts.add("eaa");
        bts.add("a");
        bts.add("aab");
        bts.add("ddb");

        assertEquals("dacb", bts.lower("dacc"));
        assertEquals(null, bts.lower("a"));
        assertEquals("a", bts.lower("aa"));
        assertEquals("a", bts.lower("aaa"));
        assertEquals("aaa", bts.lower("aab"));
        assertEquals("aab", bts.lower("aac"));
        assertEquals("dacb", bts.lower("dd"));
        assertEquals("dda", bts.lower("ddb"));
        assertEquals("dacb", bts.lower("dda"));
        assertEquals("eaa", bts.lower("z"));
        assertEquals("ddc", bts.lower("df"));
        assertEquals("ddc", bts.lower("de"));
        assertEquals("dfk", bts.lower("dg"));
        assertEquals("dfk", bts.lower("dfm"));
        assertEquals("ea", bts.lower("eaa"));
        assertEquals("eaa", bts.lower("eaaa"));
    }

    @Test
    public void testHigherInteger() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.add(1);
        bts.add(2);
        bts.add(3);
        assertEquals(new Integer(2), bts.higher(1));
        assertEquals(null, bts.higher(3));
        assertEquals(new Integer(3), bts.higher(2));
    }

    @Test
    public void testHigherString() {
        BinaryTreeSet<String> bts = new BinaryTreeSet<>();
        bts.add("dda");
        bts.add("e");
        bts.add("daca");
        bts.add("ca");
        bts.add("dacb");
        bts.add("df");
        bts.add("ea");
        bts.add("aaa");
        bts.add("cb");
        bts.add("ddc");
        bts.add("dfk");
        bts.add("dfka");
        bts.add("dfa");
        bts.add("eaa");
        bts.add("a");
        bts.add("aab");
        bts.add("ddb");

        assertEquals("dacb", bts.higher("daca"));
        assertEquals("ddb", bts.higher("dda"));
        assertEquals(null, bts.higher("eaa"));
        assertEquals("aab", bts.higher("aaa"));
        assertEquals("dfa", bts.higher("df"));
        assertEquals("cb", bts.higher("ca"));
        assertEquals("df", bts.higher("ddc"));
        assertEquals("dda", bts.higher("dacb"));
        assertEquals("dda", bts.higher("dd"));
        assertEquals("cb", bts.higher("cab"));
    }

    @Test
    public void testFloorString() {
        BinaryTreeSet<String> bts = new BinaryTreeSet<>();
        bts.add("dda");
        bts.add("e");
        bts.add("daca");
        bts.add("ca");
        bts.add("dacb");
        bts.add("df");
        bts.add("ea");
        bts.add("aaa");
        bts.add("cb");
        bts.add("ddc");
        bts.add("dfk");
        bts.add("eaa");
        bts.add("aa");
        bts.add("aab");
        bts.add("ddb");

        assertEquals("dda", bts.floor("dda"));
        assertEquals("dacb", bts.floor("dd"));
        assertEquals("eaa", bts.floor("eaa"));
        assertEquals("eaa", bts.floor("eab"));
        assertEquals("cb", bts.floor("dac"));
        assertEquals("daca", bts.floor("daca"));
        assertEquals("aa", bts.floor("aa"));
        assertEquals(null, bts.floor("a"));
        assertEquals("aab", bts.floor("b"));
    }

    @Test
    public void testCeilingString() {
        BinaryTreeSet<String> bts = new BinaryTreeSet<>();
        bts.add("dda");
        bts.add("e");
        bts.add("daca");
        bts.add("ca");
        bts.add("dacb");
        bts.add("df");
        bts.add("ea");
        bts.add("aaa");
        bts.add("cb");
        bts.add("ddc");
        bts.add("dfk");
        bts.add("eaa");
        bts.add("a");
        bts.add("aab");
        bts.add("ddb");

        assertEquals("dda", bts.ceiling("dda"));
        assertEquals("dda", bts.ceiling("dd"));
        assertEquals("df", bts.ceiling("ddca"));
        assertEquals("ddc", bts.ceiling("ddc"));
        assertEquals(null, bts.ceiling("eaaa"));
        assertEquals("eaa", bts.ceiling("eaa"));
        assertEquals("e", bts.ceiling("e"));
        assertEquals("ddc", bts.ceiling("ddbc"));
        assertEquals("df", bts.ceiling("ddcc"));
    }

    @Test
    public void testContainsAll() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add(i);
        }

        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.add(5);
        bts.add(2);
        bts.add(8);
        bts.add(1);
        bts.add(3);
        bts.add(7);
        bts.add(0);
        bts.add(4);
        bts.add(6);

        assertEquals(false, bts.containsAll(list));

        bts.add(9);
        assertEquals(true, bts.containsAll(list));

        bts.add(10);
        assertEquals(true, bts.containsAll(list));
    }

    @Test
    public void testRemove() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();

        bts.remove(1);

        bts.add(6);
        bts.add(3);
        bts.add(8);
        bts.add(2);
        bts.add(5);
        bts.add(7);
        bts.add(9);

        assertEquals(true, bts.remove(2));
        assertEquals(false, bts.contains(2));
        bts.add(2);

        assertEquals(true, bts.remove(5));
        assertEquals(false, bts.contains(5));
        bts.add(5);

        assertEquals(true, bts.remove(7));
        assertEquals(false, bts.contains(7));
        bts.add(7);

        assertEquals(true, bts.remove(9));
        assertEquals(false, bts.contains(9));
        bts.add(9);

        assertEquals(true, bts.remove(3));
        assertEquals(false, bts.contains(3));
        bts.add(3);

        assertEquals(true, bts.remove(8));
        assertEquals(false, bts.contains(8));
        bts.add(8);

        assertEquals(true, bts.remove(6));
        assertEquals(false, bts.contains(6));
        bts.add(6);

        bts.add(1);
        assertEquals(true, bts.remove(2));
        assertEquals(false, bts.contains(2));
        bts.add(2);

        assertEquals(true, bts.remove(5));
        assertEquals(false, bts.contains(5));

        assertEquals(false, bts.remove(5));
        assertEquals(false, bts.remove(0));
        assertEquals(false, bts.remove(10));
    }

    @Test
    public void testSize() {
        BinaryTreeSet<Double> bts = new BinaryTreeSet<>();
        assertEquals(0, bts.size());

        bts.add(3.14);
        bts.add(42.0);
        bts.add(3.1);
        bts.add(0.79);
        bts.add(2.3);
        bts.add(7.111);
        assertEquals(6, bts.size());
        bts.add(3.14);
        bts.add(42.0);
        bts.add(3.1);
        bts.add(0.79);
        bts.add(2.3);
        bts.add(7.111);
        assertEquals(6, bts.size());

        bts.remove(3.14);
        assertEquals(5, bts.size());
        bts.remove(3.14);
        assertEquals(5, bts.size());
    }

    @Test
    public void testIsEmpty() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        assertEquals(true, bts.isEmpty());

        bts.add(5);
        assertEquals(false, bts.isEmpty());
        bts.add(2);
        bts.add(7);
        bts.add(5);
        assertEquals(false, bts.isEmpty());
        bts.remove(5);
        assertEquals(false, bts.isEmpty());
        bts.remove(2);
        assertEquals(false, bts.isEmpty());
        bts.remove(7);
        assertEquals(true, bts.isEmpty());
        bts.remove(5);
        bts.remove(2);
        bts.remove(7);
        assertEquals(true, bts.isEmpty());
    }

    @Test
    public void testClear() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.clear();
        assertEquals(true, bts.isEmpty());
        assertEquals(0, bts.size());

        bts.add(5);
        bts.add(3);
        bts.add(8);
        bts.add(1);
        bts.add(4);
        bts.add(1);
        bts.add(2);
        assertEquals(false, bts.isEmpty());
        assertEquals(6, bts.size());

        bts.clear();
        assertEquals(true, bts.isEmpty());
        assertEquals(0, bts.size());
    }

    @Test
    public void testRemoveAll() {
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("aa");
        list.add("bcd");
        list.add("b");
        list.add("ac");

        BinaryTreeSet<String> bts = new BinaryTreeSet<>();
        bts.add("abc");
        bts.add("aa");
        bts.add("bcd");
        bts.add("b");
        bts.add("ac");
        assertEquals(false, bts.isEmpty());
        assertEquals(5, bts.size());

        assertEquals(true, bts.removeAll(list));
        assertEquals(true, bts.isEmpty());
        assertEquals(0, bts.size());

        bts.add("abc");
        bts.add("a");
        bts.add("aa");
        bts.add("bcd");
        bts.add("bc");
        bts.add("b");
        bts.add("cc");
        bts.add("ac");

        assertEquals(true, bts.removeAll(list));
        assertEquals(false, bts.isEmpty());
        assertEquals(3, bts.size());

        bts.add("abc");
        assertEquals(true, bts.removeAll(list));
        assertEquals(false, bts.isEmpty());
        assertEquals(3, bts.size());

        bts.add("acc");
        assertEquals(false, bts.removeAll(list));
        assertEquals(false, bts.isEmpty());
        assertEquals(4, bts.size());

        bts.remove("acc");
        assertEquals(false, bts.removeAll(list));
        assertEquals(false, bts.isEmpty());
        assertEquals(3, bts.size());
    }

    @Test
    public void testIterator() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.add(6);
        bts.add(3);
        bts.add(8);
        bts.add(2);
        bts.add(5);
        bts.add(7);
        bts.add(9);

        ArrayList<Integer> values = new ArrayList<>(bts);

        assertEquals(new Integer(2), values.get(0));
        assertEquals(new Integer(3), values.get(1));
        assertEquals(new Integer(5), values.get(2));
        assertEquals(new Integer(6), values.get(3));
        assertEquals(new Integer(7), values.get(4));
        assertEquals(new Integer(8), values.get(5));
        assertEquals(new Integer(9), values.get(6));
    }

    @Test
    public void testRetainAll() {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(5);
        list.add(3);
        list.add(2);

        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.addAll(list);
        bts.add(6);
        bts.add(3);
        bts.add(7);
        bts.add(8);

        bts.retainAll(list);
        assertEquals(bts.size(), 4);
        assertEquals(true, bts.contains(9));
        assertEquals(true, bts.contains(5));
        assertEquals(true, bts.contains(3));
        assertEquals(true, bts.contains(2));
        assertEquals(false, bts.contains(6));
        assertEquals(false, bts.contains(7));
        assertEquals(false, bts.contains(8));
    }

    @Test
    public void testDescendingIterator() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.add(6);
        bts.add(3);
        bts.add(8);
        bts.add(2);
        bts.add(5);
        bts.add(7);
        bts.add(9);

        ArrayList<Integer> values = new ArrayList<>();
        for (Iterator<Integer> i = bts.descendingIterator(); i.hasNext(); ) {
            values.add(i.next());
        }

        assertEquals(new Integer(9), values.get(0));
        assertEquals(new Integer(8), values.get(1));
        assertEquals(new Integer(7), values.get(2));
        assertEquals(new Integer(6), values.get(3));
        assertEquals(new Integer(5), values.get(4));
        assertEquals(new Integer(3), values.get(5));
        assertEquals(new Integer(2), values.get(6));
    }

    @Test
    public void testDescendingSet() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.add(6);
        bts.add(3);
        bts.add(8);
        bts.add(2);
        bts.add(5);
        bts.add(7);
        bts.add(9);

        MyTreeSet<Integer> descendingSet = bts.descendingSet();
        assertEquals(bts.first(), descendingSet.last());
        assertEquals(bts.last(), descendingSet.first());

        ArrayList<Integer> values = new ArrayList<>(bts);

        ArrayList<Integer> descendingSetValues = new ArrayList<>(descendingSet);

        Collections.reverse(descendingSetValues);
        assertEquals(values, descendingSetValues);

        MyTreeSet<Integer> ascendingSet = descendingSet.descendingSet();
        assertEquals(bts.first(), ascendingSet.first());
        assertEquals(bts.last(), ascendingSet.last());

        ArrayList<Integer> ascendingSetValues = new ArrayList<>(ascendingSet);
        assertEquals(values, ascendingSetValues);
    }

    /**
     * This test checks correctness of removal
     * from a set which is a result of descendingSet() method.
     */
    @Test
    public void testRemoveFromDescendingSet() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.add(6);
        bts.add(3);
        bts.add(8);
        bts.add(2);
        bts.add(5);
        bts.add(7);
        bts.add(9);

        MyTreeSet<Integer> descSet = bts.descendingSet();
        assertEquals(true, descSet.remove(6));
        assertEquals(false, descSet.contains(6));
        assertEquals(6, descSet.size());
        assertEquals(true, descSet.contains(3));
        assertEquals(true, descSet.contains(8));
        assertEquals(true, descSet.contains(2));
        assertEquals(true, descSet.contains(5));
        assertEquals(true, descSet.contains(7));
        assertEquals(true, descSet.contains(9));

        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(9);
        assertEquals(true, descSet.removeAll(list));
        assertEquals(4, descSet.size());
        assertEquals(true, descSet.contains(3));
        assertEquals(true, descSet.contains(8));
        assertEquals(false, descSet.contains(2));
        assertEquals(true, descSet.contains(5));
        assertEquals(true, descSet.contains(7));
        assertEquals(false, descSet.contains(9));

        list.add(3);
        list.add(8);
        assertEquals(true, descSet.retainAll(list));
        assertEquals(2, descSet.size());
        assertEquals(true, descSet.contains(3));
        assertEquals(true, descSet.contains(8));
        assertEquals(false, descSet.contains(5));
        assertEquals(false, descSet.contains(7));

        MyTreeSet<Integer> ascSet = descSet.descendingSet();
        assertEquals(new Integer(3), ascSet.first());
        assertEquals(new Integer(8), ascSet.last());
        assertEquals(2, ascSet.size());
        assertEquals(false, ascSet.contains(5));
        assertEquals(false, ascSet.contains(7));

        ascSet.clear();
        assertEquals(true, ascSet.isEmpty());
        assertEquals(false, ascSet.contains(3));
        assertEquals(false, ascSet.contains(8));
    }

    /**
     * This method checks whether the behavior of toArray() method
     * of standard class AbstractSet matches the expected
     * if used with the provided BinaryTreeSet implementation.
     * toArray() version: Object[] toArray()
     */
    @Test
    public void testToArrayObject() {
        BinaryTreeSet<String> bts = new BinaryTreeSet<>();
        bts.add("abb");
        bts.add("fgh");
        bts.add("fggh");
        bts.add("a");
        bts.add("cab");

        Object[] array = bts.toArray();
        assertEquals("a", array[0]);
        assertEquals("abb", array[1]);
        assertEquals("cab", array[2]);
        assertEquals("fggh", array[3]);
        assertEquals("fgh", array[4]);
    }

    /**
     * This method checks whether the behavior of toArray() method
     * of standard class AbstractSet matches the expected
     * if used with the provided BinaryTreeSet implementation.
     * toArray() version: T[] toArray(T[] arr)
     */
    @Test
    public void testToArrayTyped() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>();
        bts.add(6);
        bts.add(3);
        bts.add(8);
        bts.add(2);
        bts.add(5);
        bts.add(7);
        bts.add(9);

        Integer[] array = new Integer[10];
        array = bts.toArray(array);
        assertEquals(new Integer(2), array[0]);
        assertEquals(new Integer(3), array[1]);
        assertEquals(new Integer(5), array[2]);
        assertEquals(new Integer(6), array[3]);
        assertEquals(new Integer(7), array[4]);
        assertEquals(new Integer(8), array[5]);
        assertEquals(new Integer(9), array[6]);
        assertEquals(null, array[7]);

        Integer[] smallArray = new Integer[2];
        smallArray = bts.toArray(smallArray);
        assertEquals(smallArray.length, 7);
        assertEquals(new Integer(2), smallArray[0]);
        assertEquals(new Integer(3), smallArray[1]);
        assertEquals(new Integer(5), smallArray[2]);
        assertEquals(new Integer(6), smallArray[3]);
        assertEquals(new Integer(7), smallArray[4]);
        assertEquals(new Integer(8), smallArray[5]);
        assertEquals(new Integer(9), smallArray[6]);
     }

     /**
      * This method checks correctness of custom comparator usage
      * by BinaryTreeSet class.
      */
    @Test
    public void testCustomComparator() {
        BinaryTreeSet<String> bts = new BinaryTreeSet<>((x, y) -> (y.length() - x.length()));
        // this comparator only works with strings of different lengths
        bts.add("z");
        bts.add("aaza");
        bts.add("ab");
        bts.add("235sa");
        bts.add("tyu");

        assertEquals(5, bts.size());

        assertEquals("z", bts.last());
        assertEquals("235sa", bts.first());

        Object[] array = bts.toArray();
        assertEquals("235sa", array[0]);
        assertEquals("aaza", array[1]);
        assertEquals("tyu", array[2]);
        assertEquals("ab", array[3]);
        assertEquals("z", array[4]);

        assertEquals(true, bts.remove("tyu"));
        assertEquals(4, bts.size());
    }

    @Test
    public void testCustomComparatorWithNull() {
        BinaryTreeSet<Integer> bts = new BinaryTreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                if (integer == null) {
                    if (t1 == null) {
                        return 0;
                    }

                    return new Integer(0).compareTo(t1);
                }

                if (t1 == null) {
                    return integer.compareTo(0);
                }

                return integer.compareTo(t1);
            }
        });

        bts.add(4);
        bts.add(2);
        bts.add(5);
        bts.add(null); // null is treated as 0
        bts.add(9);

        assertEquals(null, bts.first());
        assertEquals(null, bts.lower(2));
        // technically, the following is incorrect, yet expected
        assertEquals(null, bts.lower(null));
    }
}