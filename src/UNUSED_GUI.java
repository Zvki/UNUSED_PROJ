import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class UNUSED_GUI {
    private JTextField old_period_text_field;
    private JPanel UNUSED;
    private JButton start_program_btn;
    private JTextArea result_area;
    private JTextField disc_name_text_field;
    private JCheckBox docxCheckBox;
    private JCheckBox xlsxCheckBox;
    private JCheckBox pdfCheckBox;
    private JCheckBox pptxCheckBox;

    public static int time_period;
    public static String disc_name = ":\\";
    public static List<String> doctype_list = new ArrayList<String>();

    public UNUSED_GUI() {
        start_program_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            clear_data();
            add_doctype();
            time_period = Integer.parseInt(old_period_text_field.getText());
            disc_name = disc_name_text_field.getText() + disc_name;
            UNUSED_MAIN();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UNUSED_GUI");
        frame.setContentPane(new UNUSED_GUI().UNUSED);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void UNUSED_MAIN(){

        Path starting_directory = Paths.get(disc_name);

        try {
            Files.walkFileTree(starting_directory, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
                    if(RESOURCES.is_Target_File(file) && RESOURCES.is_Older_Than(file, time_period)){
                        result_area.append(file.toAbsolutePath() + "\n");
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.err.println("Failed to visit file " + file + ": " + exc);
                    return FileVisitResult.CONTINUE;
                }
            } );
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    private void clear_data(){
        doctype_list.clear();
        disc_name = ":\\";
        result_area.setText("");
    }

    private void add_doctype(){
        if(docxCheckBox.isSelected()){
            doctype_list.add(docxCheckBox.getText());
        }
        if(xlsxCheckBox.isSelected()){
            doctype_list.add(xlsxCheckBox.getText());
        }
        if(pdfCheckBox.isSelected()){
            doctype_list.add(pdfCheckBox.getText());
        }
        if(pptxCheckBox.isSelected()){
            doctype_list.add(pptxCheckBox.getText());
        }
    }
}
