import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HuffmanCompressionGUI extends JFrame{
    JFileChooser fileChooser;
    FileManager fileManager;
    private File binaryFile;
    private JTable hoffmanTable;
    private JButton decodeButton;
    private JButton encodeButton;
    private JTextField readData;
    private JPanel HoffmanCompression;
    private JButton openFileButton;
    private JButton saveToFileButton;

    public HuffmanCompressionGUI() {
        fileChooser = new JFileChooser();
        fileManager = new FileManager();
        fileChooser.setCurrentDirectory(new File("C:\\temp"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Binary Files","bin"));

        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readData.setText(HuffmanOperations.Encode(readData.getText()));
                LoadJTable();
            }
        });
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readData.setText(HuffmanOperations.Decode(readData.getText()));
            }
        });
        openFileButton.addActionListener(this::OpenFileButtonPerformed);
        saveToFileButton.addActionListener(this::SaveToFileButtonPerformed);
    }

    private void OpenFileButtonPerformed(ActionEvent e) {
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            binaryFile = fileChooser.getSelectedFile();
            try {readData.setText(fileManager.Read(binaryFile));} catch (IOException ioe) {

            }
            LoadJTable();
        }
    }
    private void SaveToFileButtonPerformed(ActionEvent e) {
        try {fileManager.SaveToFile(binaryFile,readData.getText(),HuffmanOperations.encodeTb);} catch (IOException ioe) {

        }
    }
    private void LoadJTable() {
        int size = HuffmanOperations.encodeTb.size();
        String[] header = {"Character", "Code"};
        String[][] data = new String[size][2];
        final int[] i = {0};
        HuffmanOperations.encodeTb.forEach((key, value) -> {
            String[] dataField = {String.valueOf(key),value};
            data[i[0]] = dataField;
            i[0]++;
        });
        DefaultTableModel defaultTableModel = new DefaultTableModel(data,header);
        hoffmanTable.setModel(defaultTableModel);
    }
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("HuffmanCompressionGUI");
        frame.setContentPane(new HuffmanCompressionGUI().HoffmanCompression);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
