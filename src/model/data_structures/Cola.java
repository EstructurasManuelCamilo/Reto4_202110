package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Cola <T extends Comparable <T>> implements Iterable<T>
{
    private Node1<T> first;    // beginning of queue
    private Node1<T> last;     // end of queue
    private int n;             // number of elements on queue

    private static class Node1<Item> 
    {
        private Item item;
        private Node1<Item> next;
    }
    
    public Cola() 
    {
        first = null;
        last  = null;
        n = 0;
    }
    
    public boolean isEmpty() 
    {
        return first == null;
    }
    
    public int size() 
    {
        return n;
    }
    
    public T peek() 
    {
        if (isEmpty()) throw new NoSuchElementException("Desbordamiento de cola");
        return first.item;
    }
    
    public void enqueue(T item) 
    {
        Node1<T> oldlast = last;
        last = new Node1<T>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }
    
    public T dequeue() 
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        T item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
    
    public String toString() 
    {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    } 
    
    public Iterator<T> iterator()  
    {
        return new LinkedIterator(first);  
    }

    private class LinkedIterator implements Iterator<T> 
    {
        private Node1<T> current;

        public LinkedIterator(Node1<T> first) 
        {
            current = first;
        }

        public boolean hasNext()  
        { 
        	return current != null;
        }
        public void remove()      
        {
        	throw new UnsupportedOperationException();  
        }

        public T next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next; 
            return item;
        }
    } 
}
