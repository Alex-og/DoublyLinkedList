package model;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class AlternativeLinkedListTest {

    @Test
    public void shouldReturnEmptyList() {
        Linked<String> actual = new AlternativeLinkedList<>();
        Linked<String> expected = new AlternativeLinkedList<>();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEBecauseAddNull() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.add(null);
        assertNull(actual);
    }

    @Test
    public void shouldAddThreeStrings() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.add("1");
        actual.add("2");
        actual.add("3");
        Linked<String> expected = new AlternativeLinkedList<>(List.of("1", "2", "3"));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddThreeInteger() {
        Linked<Integer> actual = new AlternativeLinkedList<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);
        Linked<Integer> expected = new AlternativeLinkedList<>(List.of(1, 2, 3));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyListBecauseClear() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.add("1");
        actual.add("2");
        actual.add("3");
        actual.clear();
        Linked<Integer> expected = new AlternativeLinkedList<>();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testAddFirst() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.addFirst("1");
        actual.addFirst("2");
        actual.addFirst("3");
        Linked<String> expected = new AlternativeLinkedList<>(List.of("3", "2", "1"));
        assertEquals(expected, actual);
    }

    @Test
    public void testAddLast() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.addLast("1");
        actual.addLast("2");
        actual.addLast("3");
        Linked<String> expected = new AlternativeLinkedList<>(List.of("1", "2", "3"));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldInsertIntoMiddleOfList() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.add("1");
        actual.add("2");
        actual.add("3");
        actual.add(1, "8");
        Linked<String> expected = new AlternativeLinkedList<>(List.of("1", "8", "2", "3"));
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void shouldNPEBecauseInsertNull() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.add("1");
        actual.add("2");
        actual.add("3");
        actual.add(1, null);
        assertNull(actual);
    }

    @Test
    public void shouldGetByIndex() {
        Linked<Integer> actual = new AlternativeLinkedList<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);
        Integer expected = 2;
        assertEquals(expected, actual.get(1));
        Integer expectedFirst = 1;
        assertEquals(expectedFirst, actual.getFirst());
        Integer expectedLast = 3;
        assertEquals(expectedLast, actual.getLast());
    }

    @Test
    public void testContainsTrue() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.addFirst("1");
        actual.addFirst("2");
        actual.addFirst("3");
        assertTrue(actual.contains("3"));
    }

    @Test
    public void testContainsFalse() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.addFirst("1");
        actual.addFirst("2");
        actual.addFirst("3");
        assertFalse(actual.contains("4"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsNPE() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.addFirst("1");
        actual.addFirst("2");
        actual.addFirst("3");
        assertNull(actual.contains(null));
    }

    @Test
    public void shouldRemove() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.add("1");
        actual.add("2");
        actual.add("3");
        actual.add("4");
        actual.remove(2);
        Linked<String> expectedByIndex = new AlternativeLinkedList<>(List.of("1", "2", "4"));
        assertEquals(expectedByIndex, actual);
        actual.removeFirst();
        Linked<String> expectedRemoveFirst = new AlternativeLinkedList<>(List.of("2", "4"));
        assertEquals(expectedRemoveFirst, actual);
        actual.removeLast();
        Linked<String> expectedRemoveLast = new AlternativeLinkedList<>(List.of("2"));
        assertEquals(expectedRemoveLast, actual);
    }

    @Test
    public void testOddCount() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.add("1");
        actual.add("2");
        actual.add("3");
        actual.add("4");
        actual.add("5");
        String expectedMiddle = "3";
        String expectedAfterMiddle = "4";
        assertEquals(expectedMiddle, actual.get(actual.size() / 2));
        assertEquals(expectedAfterMiddle, actual.get(actual.size() / 2 + 1));
    }

    @Test
    public void shouldHasNextFalse() {
        Linked<String> actual = new AlternativeLinkedList<>();
        Iterator<String> iterator = actual.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldHasNextTrue() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.add("1");
        Iterator<String> iterator = actual.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("1", iterator.next());
    }

    @Test
    public void shouldHasNextDescFalse() {
        Linked<String> actual = new AlternativeLinkedList<>();
        Iterator<String> iterator = actual.descendingIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldHasNextDescTrue() {
        Linked<String> actual = new AlternativeLinkedList<>();
        actual.add("1");
        actual.add("2");
        Iterator<String> iterator = actual.descendingIterator();
        assertTrue(iterator.hasNext());
        assertEquals("2", iterator.next());
    }
}