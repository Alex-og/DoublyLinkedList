package model;

public interface Linked<T> extends Iterable<T>, DescendingIterator<T> {
    void addFirst(T t);
    void addLast(T t);
    void add(T t);
    void add(int index, T t);
    T removeFirst();
    T removeLast();
    T remove(int index);
    int size();
    T get(int index);
    T getFirst();
    T getLast();
    int indexOf(T t);
    boolean contains(T t);
    void clear();
    boolean isEmpty();
}
