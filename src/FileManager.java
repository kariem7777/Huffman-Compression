import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;

    public void SaveToBinaryFile(String text) throws IOException {
        String s;
        s = "Output.bin";
        fileOutputStream = new FileOutputStream(s);
        int previousTextLength = text.length();
        if (text.length() % 8 != 0) {
            for (int i = 0; i < text.length() % 8; i++) {
                text = "0" + text;
            }
        }
        String[] substrings = new String[text.length()/8];
        fileOutputStream.write(previousTextLength);
        fileOutputStream.write(text.length()/8);
        for (int i = 0; i < text.length() / 8; i++) {
            int count = i*8;
            substrings[i] = text.substring(count,count+8);
        }
        for(int i = 0; i < text.length() / 8; i++) {
            int DecimalSubstring = Integer.parseInt(substrings[i], 2);
            fileOutputStream.write(DecimalSubstring);
        }
        HuffmanOperations.charFreq.forEach((key,value) -> {
            try {
                fileOutputStream.write(key);
                fileOutputStream.write(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
     };
    public void SaveToTxtFile(String text) {
        String s = "Output.txt";

        try {
            FileWriter writer = new FileWriter(s);
            writer.write(text);
            writer.close();
            System.out.println("Text successfully written to " + s);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public String Read(File file) throws IOException {
        fileInputStream = new FileInputStream(file.getAbsolutePath());
        int actualCodeLength = fileInputStream.read();
        int numberOfBytes = fileInputStream.read();
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < numberOfBytes; i++) {
            //the following code ensures that the function doesn't neglect any trailing zeroes
            encodedText.append(String.format("%8s", Integer.toBinaryString(fileInputStream.read())).replace(' ', '0'));

        }

        int binaryLength = encodedText.length();
        int trailingZeros = binaryLength - actualCodeLength;
        encodedText = new StringBuilder(encodedText.substring(trailingZeros));
        int FreqKey;
        while ((FreqKey = fileInputStream.read()) != -1) {
            int value = fileInputStream.read();
            HuffmanOperations.charFreq.put((char) FreqKey,value);
        }
        node tree = HuffmanOperations.FormHuffmanTree();
        HuffmanOperations.generateTb(tree,"");
        return encodedText.toString();
    };
}