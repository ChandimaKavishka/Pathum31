
import java.util.Scanner;


public class encryptionandDecryption{

    int rails;

    public encryptionandDecryption(int rails){

        this.rails= rails;

    }

    String decryption(String text){
        char[] decrypt = new char[text.length()];
        int n =0;

        for(int k=0; k<rails; k++){
            int index = k;
            boolean down = true;

            while(index < text.length()){
                
                decrypt[index] = text.charAt(n++);

                if(k==0 || k==rails - 1){
                    index = index +2 * rails-1;

                }else if(down){
                    index = index +2 * rails-k-1;
                    down =! down;
                }
                else{
                    index = index +2 * k;
                    down =! down;
                }
                
            }
           
        }
        
        return new String(decrypt);

    }

    String encryption(String text){

        char[] encrypt = new char[text.length()];
        int n=0;

        for(int k=0; k<rails; k++) {
          int  index = k;
          boolean down = true;

          while(index<text.length()){

            encrypt[n++] = text.charAt(index);
            if(k==0 || k==rails -1){
                index = index +2 * rails-1;

            }
            else if(down){
                index = index +2 * rails-k-1;
                down =!down;
            }
            else{
                index = index +2 * k;
                down =! down;
            }
          } 

        }
        return new String (encrypt);


    }


    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("plaint text: ");
        String text = sc.nextLine();
        System.out.println("valus: ");
        int rails = sc.nextInt();

        encryptionandDecryption railfence = new encryptionandDecryption(rails);
        
        
        String encrypt = railfence.encryption(text);
        System.out.println("encrptedd text :- "+encrypt);

        String decrypt = railfence.
        decryption(encrypt);
        System.out.println("decreted text : "+ decrypt);

    }
}