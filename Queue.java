public class Queue{

    int front = 0;
    int rear = -1;
    int []queue;
    int size;


    Queue(int mxsize){

        size = mxsize;
        this.queue =  new int[mxsize];


    }

    public void enqueue(int data){
        if(rear == -1){

            rear = (rear +1) % size;

            queue[rear] = data;

            return;
        }
        if(front == (rear+1)%size){

            System.out.println("Queue is full");

        }
        else{
            queue[(rear+1)%size] = data;
            return;
        }
    }

    public void dequeue(){
        if(rear == -1){
            System.out.println("Queue is empty");
        }
        else{

            queue[front] = 0;
            front = (front+1)%size;
        }
    }

    public void print(){

        for(int i=0; i<queue.length;i++){

            System.out.print(queue[i]+ " ");
        }
    }

    public static void main(String[]args){

        Queue q = new Queue(5);


        q.enqueue(23);
        q.enqueue(21);
        q.enqueue(53);
        q.enqueue(73);

        q.dequeue();
        q.print();
    }

}