import Structure.MyArrayList;
import Structure.MyLinkedList;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws EOFException {
        MyLinkedList<String> list = new MyLinkedList();
        MyArrayList<String> list1 = new MyArrayList<>();
        list.add("String 1");
        list.addFirst("String 2");
        list.addLast("String 3");
        list.addFirst("String 4");
        list.addLast("String 5");
        list.addFirst("String 6");
        list.add(5,"String Before");
        
        System.out.println(list.toString());
        String find = String.valueOf(list.get(0));
        System.out.println(find);

        list.remove("String 4");
        list.remove("String 6");
        list.remove("String Before");
        System.out.println(list.toString());

        list1.add("String 1");
        list1.add("String 2");
        list1.add(1,"String 3");
        list1.add("String 4");
        list1.add("String 5");
        list1.add(0,"String 6");
        list1.get(1);
        list1.printCustomList();

        LinkedList<String> strings = new LinkedList<>();
        strings.add("a1");
        strings.addLast("a2");
        strings.addFirst("a3");
        strings.get(1);
        strings.remove("a1");
        strings.remove(1);

        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("s1");
        strings1.add("s2");
        strings1.add(1,"s3");
        strings1.get(2);
        strings1.remove("s2");
        strings1.remove(0);

    }
}
