public class Stack{
    int[] arr = new int[5];
    int top = -1;

    public void push(int data){
        if(top == 4){
            System.out.println("Stack is full");

        }
        else{
            arr[++top] = data;  
        }

    }

    public void pop(){
        if(top == -1){
            System.out.println("Stack is empty");
        }
        else{
            arr[top] = 0;
            top--;
        }
    }

    public void peek(){

        if(top == -1){

            System.out.println("Stac is empty");

        }
        else{
            System.out.println("peek value "+arr[top]);
        }


    }
    public void print(){
        for(int i=0; i<arr.length;i++){
            System.out.print(arr[i]+ " "); 
        }
    }

    public static void main(String[] args){

        Stack st = new Stack();
        st.push(6);
        st.push(8);
        st.push(2);

        st.pop();

        st.push(3);st.push(23);
        st.print();

    }
}