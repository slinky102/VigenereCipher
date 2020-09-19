//Shannon Trinh
//Homework 2: Vigenere Cipher
import java.util.Arrays;
import java.util.Scanner;

public class VignereCipher
{
    protected static int[] whiteSpace;

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the string to be encrypted: ");
        String toEncrypt = scan.nextLine();
        System.out.println("Enter the key: ");
        String key = scan.nextLine();

        System.out.println("Your string is: " + toEncrypt);

        toEncrypt = toEncrypt.toUpperCase();
        key = key.toUpperCase();

        trackWhiteSpace(toEncrypt);

        toEncrypt = toEncrypt.replaceAll("\\s", "");

        key = extendKey(key, toEncrypt);

        String encrypted = encrypt(toEncrypt, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Your encrypted string is: " + encrypted);
        System.out.println("Your decrypted string is: " + decrypted);

    }

    protected static String encrypt(String toEncrypt, String key)
    {
        String encrypted = "";
        int index = 0;

        for(int i = 0; i < toEncrypt.length() ; i++)
        {
            index = (toEncrypt.charAt(i) + key.charAt(i))%26;
            index += 'A';
            encrypted += (char)index;
        }
        return encrypted;
    }

    protected static String decrypt(String toDecrypt, String key)
    {
        String decrypted = "";
        int index = 0;
        int count = 0;

        for(int i = 0; i < toDecrypt.length(); i++)
        {
            index = (toDecrypt.charAt(i) - key.charAt(i) + 26)%26;
            index += 'A';
            decrypted += (char)index;
        }

        decrypted = addWhiteSpace(decrypted);
        return decrypted;
    }

    protected static String extendKey(String key, String toEncrypt)
    {
        int index = toEncrypt.length();

        for(int i = 0; key.length() != toEncrypt.length(); i++)
        {
            if(index == i)
            {
                i = 0;
            }
            else
            {
                key += key.charAt(i);
            }
        }

        return key;
    }

    protected static void trackWhiteSpace(String string)
    {
        whiteSpace = new int[string.length()];
        int count = 0;

        for (int i = 0; i < string.length(); i++)
        {
            if (string.substring(i, i + 1).equals(" "))
            {
                whiteSpace[count] = i;
                count++;
            }
        }
    }

    protected static String addWhiteSpace(String str)
    {
        String newString = "";
        char[] arr = new char[str.length()];
        for(int i = 0; i < str.length(); i++)
        {
            arr[i] = str.charAt(i);
        }

        int index = 0;
        int count = 0;

        while(count < whiteSpace.length && index < arr.length)
        {
            if(index == (whiteSpace[count] - count))
            {
                newString += " ";
                count++;
            }
            newString += arr[index];
            index ++;
        }
        return newString;
    }
}


