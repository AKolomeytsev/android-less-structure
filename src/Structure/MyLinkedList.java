package Structure;
import java.io.EOFException;
import java.util.Collection;
import java.util.function.Consumer;

import static java.util.Objects.isNull;


public class MyLinkedList<T> {
    transient private int size;
    transient private MyNode<T> last;
    transient private MyNode<T> first;

    public MyLinkedList(){

    }
    public boolean add(T item){
        addToEnd(item);
        return true;
    }
    public MyNode<T> get(int index){
        return getNodeByIndex(index);
    }
    public int indexOf(T element) throws EOFException {
        return getIndexByElement(element);
    }
    private int getIndexByElement(T element) throws EOFException {
        int index = 0;
        for(MyNode<T> i=first;i!=null;i=i.next){
            if(i.item.equals(element))
                return index;
            index++;
        }
        throw new EOFException();
    }
    public boolean addLast(T item){
        addToEnd(item);
        return true;
    }
    public boolean addFirst(T item){
        addToStart(item);
        return true;
    }
    public boolean add(int index,T item){
        addBefore(index,item);
        return true;
    }
    public boolean remove(T element) throws EOFException {
        int index = getIndexByElement(element);
        if(index == 0){
            removeFirst();
        }else if(index == size()-1){
            removeLast();
        }else{
            removeByIndex(index);
        }
        return true;
    }
    public boolean remove(int index) throws EOFException {
        if(index == 0){
            removeFirst();
        }else if(index == size()-1){
            removeLast();
        }else{
            removeByIndex(index);
        }
        return true;
    }
    private void removeLast() {
        MyNode<T> lastElement = last.last;
        last = lastElement;
        last.last = null;
        size--;
    }
    private void removeFirst() {
        MyNode<T> nextElement = first.next;
        first = nextElement;
        first.last = null;
        size--;
    }

    private void removeByIndex(int index) {
        MyNode<T> currentElement = getNodeByIndex(index);
        MyNode<T> lastElement = currentElement.last;
        MyNode<T> nextElement = currentElement.next;
        lastElement.next = nextElement;
        nextElement.last = lastElement;
        currentElement = null;
        size--;
    }

    private void addBefore(int index, T item) throws ArrayIndexOutOfBoundsException {
        if(index>size()-1){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if(index==0){
            addToStart(item);
        }else if(index == size()-1){
            addToEnd(item);
        }else{
            MyNode<T> searchNode = getNodeByIndex(index);
            MyNode<T> NodeBeforeSearch = searchNode.last;
            MyNode<T> newNode = new MyNode<>(item,searchNode.last,searchNode);
            NodeBeforeSearch.next = newNode;
            searchNode.last = newNode;
            size++;
        }
    }
    private MyNode<T> getNodeByIndex(int index) {
        MyNode<T> currentItem;
        if(index<size()){
            currentItem = first;
            for(int i=0;i<index;i++){
                currentItem = currentItem.next;
            }
        }else{
            currentItem = last;
            for(int i = size()-1;i>index;i--){
                currentItem = currentItem.last;
            }
        }
        return currentItem;
    }
    private void addToStart(T item) {
        final MyNode<T> firstLink = first;
        final MyNode<T> nextLink = new MyNode<T>(item,null,firstLink);
        first = nextLink;
        if(firstLink == null){
            last = nextLink;
        }else{
            firstLink.last = nextLink;
        }
        size++;
    }
    private void addToEnd(T item){
        final MyNode<T> lastLink = last;
        final MyNode<T> nextLink = new MyNode<>(item,lastLink,null);
        last = nextLink;
        if(lastLink == null){
            first = nextLink;
        }else{
            lastLink.next = nextLink;
        }
        size++;
    }
    public int size(){
        return size;
    }
    public String toString(){
        MyNode<T> current = first;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<size;i++) {
            stringBuilder.append(current.item);
            stringBuilder.append("\n");
            current = current.next;
        }
        return stringBuilder.toString();
    }
}

class MyNode<T>{
    T item;
    MyNode<T> last;
    MyNode<T> next;

    public MyNode(T item, MyNode<T> last, MyNode<T> next) {
        this.item = item;
        this.last = last;
        this.next = next;
    }

    public String toString(){
        StringBuilder returnString = new StringBuilder();
        returnString.append("Текущий Node:" + "\n");
        try {
            returnString.append(" --Предыдущий элемент - " + last.item + "\n");
        }catch (NullPointerException e){
            returnString.append(" --Предыдущий элемент - null" + "\n");
        }
        try {
            returnString.append(" --Текущий элемент - " + item + "\n");
        }catch (NullPointerException e){
            returnString.append(" --Текущий элемент - null" + "\n");
        }
        try {
            returnString.append(" --Следующий элемент элемент - " + next.item + "\n");
        }catch (NullPointerException e){
            returnString.append(" --Следующий элемент - null" + "\n");
        }
        return returnString.toString();
    }
}
