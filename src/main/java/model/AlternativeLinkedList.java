package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class AlternativeLinkedList<T> implements Linked<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public AlternativeLinkedList() {
        this.first = new Node<>(null, null, last);
        this.last = new Node<>(null, first, null);
    }

    public AlternativeLinkedList(Collection<? extends T> c) {
        this();
        addAll(c);
    }

    @Override
    public void addFirst(T t) {
        if (t == null) throw new NullPointerException();
        Node<T> insertNode = first;
        Node<T> afterInsertNode = first.getNext();
        first = new Node<>(null, null, insertNode);
        insertNode.setData(t);
        insertNode.setPrev(first);
        insertNode.setNext(afterInsertNode);
        if (isEmpty()) {
            last.setPrev(insertNode);
        }
        size++;
    }

    @Override
    public void addLast(T t) {
        if (t == null) throw new NullPointerException();
        Node<T> insertNode = last;
        Node<T> beforeInsertNode = last.getPrev();
        last = new Node<>(null, insertNode, null);
        insertNode.setData(t);
        insertNode.setNext(last);
        insertNode.setPrev(beforeInsertNode);
        if (isEmpty()) {
            first.setNext(insertNode);
        }
        size++;
    }

    @Override
    public void add(T t) {
        addLast(t);
    }

    @Override
    public void add(int index, T t) {
        if (t == null) throw new NullPointerException();
        if (index == size) {
            addLast(t);
        } if (index == 0) {
            addFirst(t);
        } else {
            Node<T> indexNode = necessaryNode(index);
            Node<T> insertNode = new Node<>(t, indexNode.getPrev(), indexNode);
            indexNode.getPrev().setNext(insertNode);
            indexNode.setPrev(insertNode);
            size++;
        }
    }

    public void addAll(Collection<? extends T> c) {
        if (c == null) throw new NullPointerException();
        c.stream().forEach(this::add);
    }

    @Override
    public T removeFirst() {
        Node<T> nextNode = first.getNext().getNext();
        T data = first.getNext().getData();
        first.setNext(nextNode);
        size--;
        return data;
    }

    @Override
    public T removeLast() {
        Node<T> beforeLastNode = last.getPrev().getPrev();
        beforeLastNode.setNext(last);
        T data = last.getPrev().getData();
        last.setPrev(beforeLastNode);
        size--;
        return data;
    }

    @Override
    public T remove(int index) {
        T data;
        if (index == size - 1) {
            data = removeLast();
        }
        if (index == 0) {
            data = removeFirst();
        } else {
            Node<T> necessNode = necessaryNode(index);
            data = necessNode.getData();
            necessNode.getPrev().setNext(necessNode.getNext());
            necessNode.getNext().setPrev(necessNode.getPrev());
            size--;
        }
        return data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        return necessaryNode(index).getData();
    }

    @Override
    public T getFirst() {
        return first.getNext().getData();
    }

    @Override
    public T getLast() {
        return last.getPrev().getData();
    }

    @Override
    public int indexOf(T t) {
        return necessaryIndex(t);
    }

    @Override
    public boolean contains(T t) {
        if (t == null) throw new NullPointerException();
        return necessaryIndex(t) >= 0;
    }

    @Override
    public void clear() {
        for (Node<T> x = first.getNext(); x != null;) {
            Node<T> next = x.getNext();
            x.setData(null);
            x.setPrev(null);
            x.setNext(null);
            x = next;
        }
        first.setNext(last);
        last.setPrev(first);
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node<T> necessaryNode(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        Node<T> target;
        if (index < size / 2) {
            target = first.getNext();
            for (int i = 0; i < index; i++) {
                target = target.getNext();
            }
        } else {
            target = last.getPrev();
            for (int i = size - 1; i > index; i--) {
                target = target.getPrev();
            }
        }
        return target;
    }

    private int necessaryIndex(T t) {
        Node<T> target = first.getNext();
        T data = null;
            for (int i = 0; i < size; i++) {
                data = target.getData();
                if (t.equals(data)) {
                    return i;
                }
                target = target.getNext();
            }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int counter = 0;
            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public T next() {
                return get(counter++);
            }
        };
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            int counter = size - 1;
            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public T next() {
                return get(counter--);
            }
        };
    }


    private static class Node<T> {
        private T data;
        private Node<T> prevNode;
        private Node<T> nextNode;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prevNode = prev;
            this.nextNode = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getPrev() {
            return prevNode;
        }

        public void setPrev(Node<T> prev) {
            this.prevNode = prev;
        }

        public Node<T> getNext() {
            return nextNode;
        }

        public void setNext(Node<T> next) {
            this.nextNode = next;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlternativeLinkedList)) return false;
        AlternativeLinkedList<?> that = (AlternativeLinkedList<?>) o;
        return size == that.size &&
                Objects.equals(getFirst(), that.getFirst()) &&
                Objects.equals(getLast(), that.getLast());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getLast(), size);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
                    for (T node : this) {
                        if (this.indexOf(node) == size - 1) {
                            builder.append(node);
                        } else {
                            builder.append(node).append(", ");
                        }
                    }
                    builder.append("]");
                    return builder.toString();
        }
    }
}
