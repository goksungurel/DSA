class Node{
    int priority;
    String name;
    Node next;


    public Node(int priority, String name){
        this.priority = priority;
        this.name = name;
    }

    public Node(int priority, String name, Node next){
        this.priority = priority;
        this.name = name;
        this.next = next;
    }

    // Getters & Setters

    public int getPriority(){
        return this.priority;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Node getNext(){
        return this.next;
    }

    public void setNext(Node next){

        this.next = next;
    }

}

public class PriorityQueue {

    Node headNode;
    int size;


    public PriorityQueue(){
        headNode = null;
        size = 0;
    }

    // Getters & Setters

    public Node getHeadNode(){
        return headNode;
    }

    public void setHeadNode(Node newHeadNode){

        headNode = newHeadNode;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int newSize){
        size = newSize;
    }


    public void printQueue(){

        System.out.print("Size of the queue is: " + getSize() + "\n");

        Node cursor = getHeadNode();

        System.out.print("[headNode] ---> ");

        while(cursor != null){
            System.out.print("[" + cursor.getPriority() + ", " + cursor.getName() + "] ---> ");
            cursor = cursor.getNext();
        }

        System.out.print("NULL");
        System.out.println("\n\n");
    }


    // TODO:
    public boolean enqueue(int priority, String name){
        if(priority<=0{
            return false;
        }
        Node newNode=new Node(priority ,name,null);
      
        if(headNode==null){
            headNode=newNode;
            size++;
            return true;
        }
        if (priority < headNode.getPriority()) {
            newNode.setNext(headNode);
            headNode=newNode;
            size++;
            return true;
        }
        Node cur=headNode;
        while(cur.getNext() !=null && cur.getNext().getPriority()<=priority)){
            cur=cur.getNext();
        }
        newNode.setNext(cur.getNext());
        cur.setNext(newnNode);
        size++;
        return true;

        /**
         * IMPLEMENT THIS METHOD
         */

        return true;
    }


    // TODO:
    public String dequeue(){
        if(headNode==null) return "E";
        String removedName=headNode.getName();
        headNode=headNode.getNext();
        size--;
        return removedName;

        /**
         * IMPLEMENT THIS METHOD
         */

        return "";
    }


    // TODO:
    public int findPlace(String name){

        if(headNode==null || name==null) return -1;

        Node current=headNode;
        int index=0;

        while(current!=null){
            if(current.getName().equals(name)){
                return index;
            }
            current=current.getNext();
            index++;
        }

        /**
         * IMPLEMENT THIS METHOD
         */

        return -2;
    }


    // ------------------------------
    // 4) promote(String name)
    // Upgrades priority of FIRST node with given name and relocates it:
    // - Priority is improved by decreasing its value by 1 (higher priority).
    // - headNode cannot be upgraded any further (i.e., cannot promote head).
    // - If the node already has the HIGHEST PRIORITY (== head's priority),
    //   relocate it as the NEW HEAD (move to front) without changing its priority.
    // Returns true on success, false otherwise.
    // ------------------------------
    public boolean promote(String name) {
        if (headNode == null || name == null) return false;

        // If the head is the target, cannot upgrade further per spec
        if (name.equals(headNode.getName())) {
            return false; // headNode cannot be promoted further
        }

        // Find first occurrence: keep prev to unlink
        Node prev = null;
        Node cur = headNode;
        while (cur != null && !name.equals(cur.getName())) {
            prev = cur;
            cur = cur.getNext();
        }
        if (cur == null) return false; // not found

        // Unlink 'cur' from its current position
        // (prev cannot be null here because we returned false for head earlier)
        prev.setNext(cur.getNext());
        cur.setNext(null);

        // Case A: Node already has highest priority (== head priority)
        // => move to front, do NOT change priority; becomes new head
        if (cur.getPriority() == headNode.getPriority()) {
            cur.setNext(headNode);
            headNode = cur;
            return true;
        }

        // Case B: Decrease priority by 1 (minimum allowed is 1)
        int newPriority = cur.getPriority() - 1;
        if (newPriority < 1) newPriority = 1;
        cur.setPriority(newPriority);

        // Reinsert cur into proper place (stable among equals)
        if (cur.getPriority() < headNode.getPriority()) {
            // becomes new head
            cur.setNext(headNode);
            headNode = cur;
            return true;
        } else {
            Node scan = headNode;
            while (scan.getNext() != null && scan.getNext().getPriority() <= cur.getPriority()) {
                scan = scan.getNext();
            }
            cur.setNext(scan.getNext());
            scan.setNext(cur);
            return true;
        }
    }

    // ------------------------------
    // Helper: printQueue()
    // Prints size and all elements from head to end in a simple format.
    // (You can adjust to match the exact expected format if specified.)
    // ------------------------------
    public void printQueue() {
        System.out.println("Size = " + size);
        Node cur = headNode;
        StringBuilder sb = new StringBuilder();
        sb.append("[headNode] -> ");
        while (cur != null) {
            sb.append("[").append(cur.getPriority()).append(", ").append(cur.getName()).append("] -> ");
            cur = cur.getNext();
        }
        sb.append("NULL");
        System.out.println(sb.toString());
    }
}












