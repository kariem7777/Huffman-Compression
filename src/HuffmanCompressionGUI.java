import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HuffmanCompressionGUI extends JFrame{
    JFileChooser encodeFileChooser;
    JFileChooser decodeFileChooser;
    FileManager fileManager;
    private JButton decodeButton;
    private JButton encodeButton;
    private JPanel HoffmanCompression;

    public HuffmanCompressionGUI() {
        encodeFileChooser = new JFileChooser();
        fileManager = new FileManager();
        encodeFileChooser.setCurrentDirectory(new File("C:\\temp"));
        encodeFileChooser.setFileFilter(new FileNameExtensionFilter("Text Files","txt"));

        decodeFileChooser = new JFileChooser();
        decodeFileChooser.setCurrentDirectory(new File("C:\\temp"));
        decodeFileChooser.setFileFilter(new FileNameExtensionFilter("Binary Files", "bin"));
        encodeButton.addActionListener(this::EncodeButtonPerformed);
        decodeButton.addActionListener(this::DecodeButtonPerformed);

    }

    private void EncodeButtonPerformed(ActionEvent e) {
        File textFile;
        int returnValue = encodeFileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            textFile = encodeFileChooser.getSelectedFile();
            Path filePath = Path.of(textFile.getPath());
            try {
                String content = Files.readString(filePath);
                String encodedText = HuffmanOperations.Encode(content);
                fileManager.SaveToBinaryFile(encodedText);
            } catch (IOException ioe) {
                System.out.println("Failed To Encode");
            }
        }
    }

    private void DecodeButtonPerformed(ActionEvent e) {
        File binaryFile;
        int returnValue = decodeFileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            binaryFile = decodeFileChooser.getSelectedFile();
            Path filePath = Path.of(binaryFile.getPath());
            try {
                String content = fileManager.Read(binaryFile);
                String decodedText = HuffmanOperations.Decode(content);
                fileManager.SaveToTxtFile(decodedText);
            } catch (IOException ioe) {
                System.out.println("Failed To Decode");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("HuffmanCompressionGUI");
        frame.setContentPane(new HuffmanCompressionGUI().HoffmanCompression);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
