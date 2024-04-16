public class array{

    static void insertarray(int[] arr,int a){

        arr[12]=a;

    }

    static void searcharray(int[] arr,int a){

    
    for(int i=0; i<arr.length;i++){
        if(arr[i] == a){

            System.out.println("found array:- "+arr[i]);
            return;

        }
    }
    }
    static void deletearray(int[]arr,int a){
        for(int i=0; i<arr.length;i++){

            if(arr[i]==a){
                for(int j=0; j<arr.length-1;j++){
                    arr[j] = arr[j+1];
                }

                arr[arr.length-1]=0;
            }
        
        }

    }

    static void print(int[] arr){

        for(int i=0; i<arr.length;i++){
            System.out.println("array:- "+arr[i]+ " ");
        }
    }

    public static void main(String[] args){

        int[] arr={17,10,84,27,84,87,33,76,11,98,0,0};

        insertarray(arr,15);
        searcharray(arr,33);
        deletearray(arr,27);
        print(arr);


    }
}