import static java.lang.System.*;

public class driver {
    public static void main (String[] args) {
        CList<Integer> list = new CList<>();
        list.append(1);
        list.insert(3);
        list.prev();
        list.insert(2);
        list.moveToStart();
        list.prev();
        list.insert(4);
        list.append(5);
        list.next();
        list.append(-6);
        list.moveToPos(-3);
        list.insert(10);
        System.out.println(list.length());
        printList(list);
    }

    public static void printList (CList<Integer> list) {
        list.moveToStart();
        for (int i = 0; i < list.length(); i++) {
            System.out.print(list.getValue() + ", ");
            list.next();
        }
        System.out.println();
    }
}

