import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PlayfairCipher_ICT342 {

    private static char[][] generateKeyMatrix(String keyword) {
        keyword = keyword.toUpperCase().replace('J', 'I');
        Set<Character> seen = new HashSet<>();
        char[] keyMatrix = new char[25];
        int index = 0;

        for (char c : keyword.toCharArray()) {
            if (!seen.contains(c) && Character.isLetter(c)) {
                seen.add(c);
                keyMatrix[index++] = c;
            }
        }

        for (char c : "ABCDEFGHIKLMNOPQRSTUVWXYZ".toCharArray()) {
            if (!seen.contains(c)) {
                seen.add(c);
                keyMatrix[index++] = c;
            }
        }

        char[][] keyMatrix5x5 = new char[5][5];
        for (int i = 0; i < 25; i++) {
            keyMatrix5x5[i / 5][i % 5] = keyMatrix[i];
        }

        return keyMatrix5x5;
    }

    private static int[] findPosition(char c, char[][] keyMatrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private static String prepareText(String text) {
        text = text.toUpperCase().replace('J', 'I').replaceAll("[^A-Z]", "");
        StringBuilder preparedText = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            preparedText.append(text.charAt(i));
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                preparedText.append('X');
                preparedText.append(text.charAt(i + 1));
            } else if (i + 1 < text.length()) {
                preparedText.append(text.charAt(i + 1));
            }
        }
        if (preparedText.length() % 2 != 0) {
            preparedText.append('X');
        }
        return preparedText.toString();
    }

    private static String encrypt(String plaintext, char[][] keyMatrix) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = prepareText(plaintext);

        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] pos1 = findPosition(plaintext.charAt(i), keyMatrix);
            int[] pos2 = findPosition(plaintext.charAt(i + 1), keyMatrix);

            if (pos1[0] == pos2[0]) {
                ciphertext.append(keyMatrix[pos1[0]][(pos1[1] + 1) % 5]);
                ciphertext.append(keyMatrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) {
                ciphertext.append(keyMatrix[(pos1[0] + 1) % 5][pos1[1]]);
                ciphertext.append(keyMatrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else {
                ciphertext.append(keyMatrix[pos1[0]][pos2[1]]);
                ciphertext.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return ciphertext.toString();
    }

    private static String decrypt(String ciphertext, char[][] keyMatrix) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            int[] pos1 = findPosition(ciphertext.charAt(i), keyMatrix);
            int[] pos2 = findPosition(ciphertext.charAt(i + 1), keyMatrix);

            if (pos1[0] == pos2[0]) {
                plaintext.append(keyMatrix[pos1[0]][(pos1[1] + 4) % 5]);
                plaintext.append(keyMatrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) {
                plaintext.append(keyMatrix[(pos1[0] + 4) % 5][pos1[1]]);
                plaintext.append(keyMatrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else {
                plaintext.append(keyMatrix[pos1[0]][pos2[1]]);
                plaintext.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return plaintext.toString();
    }

    private static void displayKeyMatrix(char[][] keyMatrix) {
        for (char[] row : keyMatrix) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    private static String cleanDecryptedText(String plaintext) {
        return plaintext.replace("X", "");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Choose operation: 1) Encrypt 2) Decrypt");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();
        
        char[][] keyMatrix = generateKeyMatrix(keyword);
        System.out.println("Key Matrix:");
        displayKeyMatrix(keyMatrix);
        
        if (choice == 1) {
            System.out.print("Enter plaintext: ");
            String plaintext = scanner.nextLine();
            String ciphertext = encrypt(plaintext, keyMatrix);
            System.out.println("Encrypted Text: " + ciphertext);
        } else if (choice == 2) {
            System.out.print("Enter ciphertext: ");
            String ciphertext = scanner.nextLine();
            String decryptedText = decrypt(ciphertext, keyMatrix);
            String cleanedText = cleanDecryptedText(decryptedText);
            System.out.println("Decrypted Text: " + cleanedText);
        } else {
            System.out.println("Invalid choice.");
        }
        
        scanner.close();
    }
}
