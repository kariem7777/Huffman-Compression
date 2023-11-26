import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private DataOutputStream f;
    private DataInputStream ifile;

    public   void SaveToFile(File file, String text, Map<Character,String> tb) throws IOException {
        String s;
        if (file != null) {
             s = file.getAbsolutePath();
        }
        else {
            s = "Output.bin";
        }
        f= new DataOutputStream(new FileOutputStream(s));
        f.writeUTF(text);
        tb.forEach((key, value) -> {
            try {
                f.writeChar(key);
                f.writeUTF(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    };
    public String Read(File file) throws IOException {
        String name = file.getAbsolutePath();
        String s="";
        ifile= new DataInputStream(new FileInputStream(name));//01001101010101
//        while(true) {
//            try {
//                s +=ifile.readChar();
//            }
//            catch(EOFException eof) {
//                break;
//            }
//        }
        s +=ifile.readUTF();
        Map<Character, String> eencodetb= new HashMap<>();
        Map<String, Character> ddecodetb= new HashMap<>();
        char c;
        String tmp;
        while(true){
            try {
                c=ifile.readChar();
                tmp= ifile.readUTF();
                eencodetb.put(c,tmp);
                ddecodetb.put(tmp,c);
            }
            catch(EOFException eof) {
                break;
            }
        }
        HuffmanOperations.encodeTb=eencodetb;
        HuffmanOperations.decodeTb=ddecodetb;


        return s;
    };
}