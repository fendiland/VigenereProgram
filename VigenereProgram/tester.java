import edu.duke.*;

public class tester {
    
    public void testCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(cc.encrypt(message));
        System.out.println(cc.encryptLetter('c'));
        String encrypted = cc.encrypt(message);
        System.out.println(cc.decrypt(encrypted));
    }
    
    public void testCaesarCracker() {
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println(cc.getKey(encrypted));
    }
    
    public void testVigenereCipher() {
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = vc.encrypt(message);
        System.out.println(encrypted);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
    public void testVigenereBreaker() {
        VigenereBreaker vb = new VigenereBreaker();
        
        //FileResource fr = new FileResource();
        vb.breakVigenere();
    }
}
