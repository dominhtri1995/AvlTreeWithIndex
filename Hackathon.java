/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*add(Object obj)
*add(int index, Object obj)
*remove(int index)
*set(int index, Object obj)
*get(int index)
 */
package hackathon;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author TriDo
 */
public class Hackathon<AnyType> implements java.util.List {

    AvlTree<AnyType> tree = new AvlTree<AnyType>();
    int size = 0;

    public void printTree() {
        tree.printTree();
    }

    @Override
    public boolean add(Object e) {
        tree.insert(size, (AnyType) e);
        size++;
        return true;
    }

    @Override
    public void add(int index, Object element) {
        int result = tree.insert(index, (AnyType) element);
        if (result == -1) {
            throw new IndexOutOfBoundsException("Index out of bound. Please check the index");
        }
        size++;

    }

    @Override
    public Object get(int index) {
        Object result = tree.get(index);
        if (result == null) {
            throw new IndexOutOfBoundsException("Index out of bound. Please check the index");
        }
        return result;
    }

    @Override
    public Object set(int index, Object element) {

        Object result = tree.findAndReplace(index, (AnyType) element);
        if (result != null) {
            return element;
        } else {
            throw new IndexOutOfBoundsException("Index out of bound. Please check the index");
        }
    }

    @Override
    public Object remove(int index) {
        int result = tree.remove(index);
        if (result == -1) {
            throw new IndexOutOfBoundsException("Index out of bound. Please check the index");
        } else {
            size--;
            return index;
        }
    }

    // No need to implement ///////////////////////////
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Hackathon h = new Hackathon();
//        h.add(1);
//        h.add(2);
//        h.add(3);
//        h.add(4);
//        h.add(5);
//        h.add(6);
//        h.add(7);
//        h.add(8);
//        h.add(9);
//        h.add(10);
        h.add(0);
        h.add(0.5);
        for (int i = 1; i <= 10; i++) {
            h.add(1, i);
//            System.out.println(h.get(1));
        }
        for (int i = 1; i <= 10; i++) {
            System.out.println(h.get(1));
            h.remove(1);
//            h.printTree();
//            System.out.println("*********\n\n");
        }
//        System.out.println(h.get(2));
//        System.out.println(h.get(4));
//        System.out.println(h.get(1));

    }

}
