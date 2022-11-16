import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    char mostCommon;
    public VigenereBreaker() {
        mostCommon = 'e';
    }
    
    public VigenereBreaker(char c) {
        mostCommon = c;
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder(message);
        String sb2 = sb.toString();
        //int startSlice = sb.charAt(whichSlice);
        //int endSlice = sb.charAt(totalSlices);
        String sb3 = "";
        for(int i = whichSlice; i < sb2.length(); i = i + totalSlices) {
            char c = sb2.charAt(i);
            sb3 = sb3 + c;
        }
        return sb3;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            int value = cc.getKey(slice);
            //System.out.println(cc.decrypt(slice));
            key[i] = value;
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fs = new FileResource();
        String encrypted = fs.asString();
        
        //HashSet<String> dictionary = readDictionary(fr);
        
        //System.out.println(countWords(message, dictionary));
        //String encrypted = fr.asString();
        //int[] key = tryKeyLength(encrypted, 38, 'e');
        //VigenereCipher vc = new VigenereCipher(key);
        //System.out.println(Arrays.toString(key));
        //System.out.println(vc.decrypt(encrypted));
        //breakForLanguage(encrypted, dictionary);
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for(int i = 0; i < languages.length; i++) {
            String lang = languages[i];
            FileResource fr = new FileResource("dictionaries/" + lang);
            HashSet<String> words = readDictionary(fr);
            map.put(lang, words);
        }
        String decrypted = breakForAllLangs(encrypted, map);
        System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        //fr = new FileResource();
        HashSet<String> dict = new HashSet<String>();
        for(String s : fr.lines()) {
            s = s.toLowerCase();
            dict.add(s);
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String>dictionary) {
        int count = 0;
        //String[] words = new String[count];
        String[] split = message.split("\\W+");
        for(String s : split) {
            s = s.toLowerCase();
            if(dictionary.contains(s)) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String>dictionary) {
       int max = 0;
       String decryp = "";
       int keyLength = 0;
       int words = 0;
       String decrypted = "";
       //int[] klength = new int[100];
       int[] klength2 = new int[100];
       for(int i = 1; i <= 100; i++) {
           int[] klength = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
           for(int j = 0; j < klength.length; j++){
           VigenereCipher vc = new VigenereCipher(klength);
           decrypted = vc.decrypt(encrypted);
           words = countWords(decrypted, dictionary);
           }
           if(words > max) {
           max = words;
           keyLength = klength.length;
           decryp = decrypted;
           //System.out.print(decrypted);
           klength2 = klength;
           //System.out.println(klength.length);
           //System.out.println(max);
           }
           }
       System.out.println(max);
       System.out.println(keyLength);
       System.out.println(Arrays.toString(klength2));
       System.out.println("This is the decrypted message: " + decryp);
       return decryp;
       }
       
    public char mostCommonCharIn(HashSet<String>dictionary) {
        HashMap<Character, Integer>map = new HashMap<Character, Integer>();
        int max = 0;
        char maxCh = '\0';
        for(String words : dictionary) {
            words = words.toLowerCase();
            for(char ch : words.toCharArray()) {
                //Character.toLowerCase(ch);
                if(!map.containsKey(ch)) {
                    map.put(ch, 1);
                } else {
                    map.put(ch, map.get(ch) +1);
                }
                for(char sh : map.keySet()) {
                    //Character.toLowerCase(ch);
                    int value = map.get(sh);
                    if(value > max) {
                        max = value;
                        maxCh = sh;
                    }
                    
                }
            }
        }
        
        return maxCh;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>>languages) {
        String decrypted = "";
        for(String lang : languages.keySet()) {          
            System.out.println("the language is: " + lang);
            decrypted = breakForLanguage(encrypted, languages.get(lang));
        }
        return decrypted;
    }
    }

