package Structure;

import java.util.Arrays;

public class MyArrayList<T> {
    private T[] container;
    private int initSize;

    public MyArrayList() {
        initSize = 0;
        this.container = (T[]) new Object[initSize];
    }

    public boolean add(T item) {
        int newIndex = changeDimension();
        container[newIndex - 1] = item;
        return true;
    }

    public boolean add(int index, T item) {
        T[] lefArray = (T[]) new Object[0];
        T[] rightArray = (T[]) new Object[0];
        if(index>=0) {
            if (index < container.length) {
                if (index == 0) {
                    rightArray = Arrays.copyOf(copyArray(0, container.length), container.length);
                    container = (T[]) new Object[1];
                    container[0] = item;
                    for (int i = 0; i < rightArray.length; i++) {
                        changeDimension();
                        container[i + 1] = rightArray[i];
                    }
                } else {
                    lefArray = Arrays.copyOf(copyArray(0, index), index + 1);
                    rightArray = Arrays.copyOf(copyArray(index, container.length), container.length - index);
                    changeDimension();
                    for (int i = 0; i < container.length; i++) {
                        if (i < index) {
                            container[i] = lefArray[i];
                        } else if (i > index) {
                            container[i] = rightArray[(i - (index + 1))];
                        } else {
                            container[index] = item;
                        }
                    }
                }
            } else {
                if (index > container.length) {
                    throw new IndexesAreIncorrectException();
                } else {
                    lefArray = Arrays.copyOf(copyArray(0, container.length), container.length);
                    container = Arrays.copyOf(lefArray, index + 1);
                    container[index] = item;
                }
            }

        }else{
            throw new IndexesAreIncorrectException();
        }
        return true;
    }

    public T set(int index, T item) {
        if(index>0 && index<container.length){
            container[index] = item;
        }else{
            throw new IndexesAreIncorrectException();
        }
        return item;
    }

    public T removeItem(T item) throws NotFoundElemnetException {
        int index = indexOf(item);
        if(index!=-1){
            T[] lefArray = (T[]) new Object[0];
            T[] rightArray = (T[]) new Object[0];
            if(index == 0){
                rightArray = Arrays.copyOf(copyArray(1, container.length), container.length-1);
                container = Arrays.copyOf(rightArray, rightArray.length);
            }else if(index == container.length-1){
                lefArray = Arrays.copyOf(copyArray(0, container.length-1), container.length-1);
                container = Arrays.copyOf(lefArray, lefArray.length);
            }else{
                lefArray = Arrays.copyOf(copyArray(0, index), index);
                rightArray = Arrays.copyOf(copyArray(index+1, container.length), container.length - (index+1));
                container = (T[]) new Object[(lefArray.length+rightArray.length)];
                int j =0;
                for (int i = 0; i < container.length; i++) {
                    if (i < index) {
                        container[i] = lefArray[i];
                    } else {
                        container[i] = rightArray[j];
                        j++;
                    }
                }
            }
        }else{
            throw new NotFoundElemnetException();
        }
        return item;
    }

    public T remove(int index) {
        T item = null;
        if(index!=-1 && index<container.length){
            item = container[index];
            T[] lefArray = (T[])new Object[0];
            T[] rightArray = (T[])new Object[0];
            if(index == 0){
                rightArray = Arrays.copyOf(copyArray(1, container.length), container.length-1);
                container = Arrays.copyOf(rightArray, rightArray.length);
            }else if(index == container.length-1){
                lefArray = Arrays.copyOf(copyArray(0, container.length-1), container.length-1);
                container = Arrays.copyOf(lefArray, lefArray.length);
            }else{
                lefArray = Arrays.copyOf(copyArray(0, index), index);
                rightArray = Arrays.copyOf(copyArray(index+1, container.length), container.length - index);
                container = (T[]) new Object[(lefArray.length+rightArray.length)-1];
                for (int i = 0; i < container.length; i++) {
                    if (i < index) {
                        container[i] = lefArray[i];
                    } else {
                        container[i] = rightArray[i-2];
                    }
                }
            }
        }else{
            throw new IndexesAreIncorrectException();
        }
        return item;
    }

    public int indexOf(T item) {
        int index = -1;
        for (int i=0;i< container.length;i++){
            if(container[i] == item){
                index = i;
                break;
            }
        }
        return index;
    }

    public int lastIndexOf(T item) {
        int index = -1;
        for (int i=container.length-1;i>=0;i--){
            if(container[i]==item){
                index = i;
                break;
            }
        }
        return index;
    }

    public T get(int index) {
        T item = null;
        if(index>-1 && index<container.length){
            item = container[index];
        }else{
            throw new IndexesAreIncorrectException();
        }
        return item;
    }

    public boolean equals(T[] otherList) throws ListCannotBeEmptyException {
        boolean flag = true;
        if(otherList!=null) {
            if (container.length == otherList.length) {
                for (int i = 0; i < container.length; i++) {
                    if (container[i]!=otherList[i])
                        flag = false;
                }
            } else {
                flag = false;
            }
        }else{
            throw new ListCannotBeEmptyException();
        }
        return flag;
    }

    public int size() {
        return container.length;
    }

    public boolean isEmpty() {
        return size()==0?true:false;
    }

    public void clear() {
        container =  (T[]) new Object[0];
    }

    public T[] toArray() {
        T[] newArray = (T[]) new Object[container.length];
        for (int i = 0;i<container.length;i++){
            newArray[i] = container[i];
        }
        return newArray;
    }

    private int changeDimension(){
        if(container.length == size() && size()!=0){
            T[] tempArray = copyArray(0,container.length);
            container = (T[]) new Object[0];
            container = Arrays.copyOf(tempArray,tempArray.length+1);
        }else{
            container = (T[]) new Object[1];
        }
        return container.length;
    }

    private T[] copyArray(int firstIndex, int lastIndex){
        T[] newArray = (T[]) new Object[lastIndex - firstIndex];
        int newIndex = 0;
        if (firstIndex<lastIndex) {
            for (int i = firstIndex;i<lastIndex;i++){
                newArray[newIndex] = container[i];
                newIndex++;
            }
        }else{
            throw new IndexesAreIncorrectException();
        }
        return newArray;
    }

    public void printCustomList(){
        System.out.println(Arrays.toString(container));
    }
}
