import java.io.*;

public class FileManager {
    private DataOutputStream f;
    private DataInputStream ifile;

    public   void SaveToFile(File file, String text) throws IOException {
        String s;
        if (file != null) {
             s = file.getAbsolutePath();
        }
        else {
            s = "Output.bin";
        }
        f= new DataOutputStream(new FileOutputStream(s));
        for(int i =0 ; i< text.length();++i){
            f.writeChar(text.charAt(i));

        }
    };
    public String Read(File file) throws IOException {
        String name = file.getAbsolutePath();
        String s="";
        ifile= new DataInputStream(new FileInputStream(name));//01001101010101
        while(true) {
            try {
                s +=ifile.readChar();
            }
            catch(EOFException eof) {
                break;
            }
        }
        return s;
    };
}