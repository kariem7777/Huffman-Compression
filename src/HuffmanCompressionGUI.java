import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HuffmanCompressionGUI extends JFrame{
    JFileChooser fileChooser;
    private File binaryFile;
    private JTable hoffmanTable;
    private JButton decodeButton;
    private JButton encodeButton;
    private JTextField decompressedData;
    private JTextField compressedData;
    private JPanel HoffmanCompression;
    private JButton openFileButton;
    private JButton saveToFileButton;

    public HuffmanCompressionGUI() {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\temp"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Binary Files","bin"));

        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compressedData.setText(HuffmanOperations.Encode(decompressedData.getText()));
            }
        });
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decompressedData.setText(HuffmanOperations.Decode(compressedData.getText()));
            }
        });
        openFileButton.addActionListener(this::openFileButtonPerformed);
    }

    private void openFileButtonPerformed(ActionEvent e) {
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            binaryFile = fileChooser.getSelectedFile();
        }
    }
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("HuffmanCompressionGUI");
        frame.setContentPane(new HuffmanCompressionGUI().HoffmanCompression);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        file f= new file();
        String s="kariem";
        String sa="11100011111011011111000000000100111101010101010101001";
        String res = HuffmanOperations.Encode(s);
        System.out.println(res);
        file file = new file();
        file.saveTofile(binaryFile,);
//    res = huffmanoperations.decode(res);
        System.out.println(file.read());

    }
}
