import java.util.Scanner;

public class ceaserciper{

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text:- ");
        String text = sc.nextLine();
        System.out.println("Enter key value:- ");
        int s = sc.nextInt();

        String encry = encrypt(text,s);
        System.out.println("plain text: "+encry);

        String decry = decrypt(encry,s);
        System.out.println("chiper text: "+decry);



    }

    public static String encrypt(String text, int s){
        
        StringBuilder encry = new StringBuilder();
        text = text.toUpperCase();

        for(int i=0; i<text.length(); i++){
            char ciphertext = text.charAt(i);
            if(Character.isLetter(ciphertext)){
                ciphertext = (char)((ciphertext - 'A' + s + 26)%26 + 'A');
            }
            encry.append(ciphertext);
        }
        return encry.toString();

    }

    public static String decrypt(String text, int s){
         StringBuffer decry = new StringBuffer();

         for(int i=0; i<text.length(); i++){
            char plaintext = encry.charAt(i);
            if(Character.isLettr(plaintext)){
                plaintext = (char)((plaintext - 'A' - s +26)%26+'A');

            }
            decry.append(plaintext);
         }
         return decry.toString();
         


    }
}