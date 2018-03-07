//------------------------------------------------------------------------
// Tyler MacIntosh, tmacinto, CMPS 12M, 2.26.18
// List.java
// Java generic implementation of List ADT
//------------------------------------------------------------------------

public class List<T>{

        // private inner Node class
        @SuppressWarnings({ "unchecked", "hiding" })
        private class Node<T>{
                //fields
                T item;
                Node<T> next;

                Node(Object newItem){
                        item = (T) newItem;
                        next = null;
                }
        }

        // Fields for the List class
        private Node<T> head;
        private int numItems;

        // List()
        // constructor for the List class
        public List(){
                head = null;
                numItems = 0;
        }

        // private helper function -------------------------------------------

        // find()
        // returns a reference to the Node at position index in this List
        private Node<T> find(int index) {
                Node<T> N = head;
                for(int i = 1; i < index; i++) {
                        N = N.next;
                }
                return N;
        }

        // isEmpty
        //pre: none
        //post: returns true if this List is empty, false otherwise
        public boolean isEmpty(){
                return(numItems == 0);
        }

        // size
        //pre: none
        //post: returns the number of elements in this List
        public int size() {
                return numItems;
        }

        // get
        // pre: 1<= index <= size()
        // post: returns item at position index
        public T get(int index) throws ListIndexOutOfBoundsException{
                if( index < 1 || index > numItems ) {
                        throw new ListIndexOutOfBoundsException(
                                "get(): invalid index: " + index);
                }
                List<T>.Node<T> N = find(index);
                return N.item;
        }

        // add
        // inserts newItem in this List at position index
        // pre: 1 <= index <= size()+1
        // post: !=isEmpty(), items to the right of newItem are renumbered
        public void add(int index, Object newItem) throws ListIndexOutOfBoundsException{
                if( index<1 || index>(numItems+1) ) {
                        throw new ListIndexOutOfBoundsException(
                                "add(): invalid index: " + index);
                }
                if(index==1) {
                        List<T>.Node<T> N = new Node<T>(newItem);
                        N.next = head;
                        head = N;
                }else {
                        List<T>.Node<T> P = find(index-1);
                        List<T>.Node<T> C = P.next;
                        P.next = new Node<T>(newItem);
                        P = P.next;
                        P.next = C;
                }
                numItems++;
        }

        // remove
        // deletes item from position index
        // pre: 1 <= index <= size()
        // post: items to the right of deleted item are renumbered
        public void remove(int index) throws ListIndexOutOfBoundsException{
                if( index<1 || index>numItems ) {
                        throw new ListIndexOutOfBoundsException(
                                "remove(): invalid index: " + index);
                }
                if(index==1) {
                        List<T>.Node<T> N = head;
                        head = head.next;
                        N.next = null;
                }else {
                        List<T>.Node<T> P = find(index-1);
                        List<T>.Node<T> N = P.next;
                        P.next = N.next;
                        N.next = null;
                }
                numItems--;
        }

        // removeAll
        // pre: none
        // post: isEmpty()
        public void removeAll() {
                head = null;
                numItems = 0;
        }

        // toString()
        // returns a string representation of this List
        public String toString(){
                StringBuffer sb = new StringBuffer();
                List<T>.Node<T> N = head;
                for( ; N!=null; N=N.next){
                        sb.append(N.item).append(" ");
                }
                return new String(sb);
        }
}
