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
    private JTextField disc_name_text_field;
    private JCheckBox docxCheckBox;
    private JCheckBox xlsxCheckBox;
    private JCheckBox pdfCheckBox;
    private JCheckBox pptxCheckBox;
    private JScrollPane JScrollPane;
    private JButton DeleteBtn;
    private CheckBoxList fileCheckBoxList;

    public static int time_period;
    public static String disc_name = ":\\";
    public static List<String> doctype_list = new ArrayList<String>();

    public UNUSED_GUI() {

        fileCheckBoxList = new CheckBoxList(new CheckBoxListModel());
        JScrollPane.setViewportView(fileCheckBoxList);

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

        DeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete_data();
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
                        ((CheckBoxListModel) fileCheckBoxList.getModel()).addElement(new JCheckBox(file.toAbsolutePath().toString()));
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
        ((CheckBoxListModel) fileCheckBoxList.getModel()).clear();
        disc_name = ":\\";
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

    private void delete_data(){

        CheckBoxListModel model = (CheckBoxListModel) fileCheckBoxList.getModel();
        for (int i = model.size() - 1; i >= 0; i--) {
            JCheckBox checkBox = (JCheckBox) model.getElementAt(i);
            if (checkBox.isSelected()) {
                Path file_path = Paths.get(checkBox.getText());
                RESOURCES.delete_file(file_path);
                System.out.println(checkBox.getText() + " is Deleted \n");
                model.remove(i);
            }
        }
    }
}
