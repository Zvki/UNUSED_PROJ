import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.NoSuchElementException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;


public class CheckBoxList
        extends JList {

    private static final long serialVersionUID = -4359573373359270258L;

    protected static class CheckBoxListItem {

        private boolean m_Checked = false;

        private Object m_Content = null;

        public CheckBoxListItem(Object o) {
            this(o, false);
        }

        public CheckBoxListItem(Object o, boolean checked) {
            m_Checked = checked;
            m_Content = o;
        }

        public Object getContent() {
            return m_Content;
        }

        public void setChecked(boolean value) {
            m_Checked = value;
        }

        public boolean getChecked() {
            return m_Checked;
        }

        public String toString() {
            return m_Content.toString();
        }

        public boolean equals(Object o) {
            if (!(o instanceof CheckBoxListItem))
                throw new IllegalArgumentException("Must be a CheckBoxListItem!");

            return getContent().equals(((CheckBoxListItem) o).getContent());
        }
    }

    private static class CheckBoxListRenderer extends JCheckBox implements ListCellRenderer<JCheckBox> {
        @Override
        public Component getListCellRendererComponent(JList<? extends JCheckBox> list, JCheckBox value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setEnabled(list.isEnabled());
            setSelected(value.isSelected());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setText(value.getText()); // Display the text of the JCheckBox
            return this;
        }
    }


    public CheckBoxList() {
        this(null);
    }

    public CheckBoxList(CheckBoxListModel model) {
        super(model);

        setCellRenderer(new CheckBoxListRenderer());

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = locationToIndex(e.getPoint());

                if (index != -1) {
                    JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    repaint();
                }
            }
        });
    }

    public void setModel(ListModel model) {
        if (!(model instanceof CheckBoxListModel))
            throw new IllegalArgumentException("Model must be an instance of CheckBoxListModel!");

        super.setModel(model);
    }

//    public void setListData(Object[] listData) {//
//    setModel(new CheckBoxListModel(listData));
//    }

    public void setListData(Vector listData) {
        setModel(new CheckBoxListModel(listData));
    }

    public boolean getChecked(int index) {
        return ((CheckBoxListModel) getModel()).getChecked(index);
    }

    public void setChecked(int index, boolean checked) {
        ((CheckBoxListModel) getModel()).setChecked(index, checked);
    }

    public int[] getCheckedIndices() {
        Vector  list;
        int[]   result;
        int     i;

        // traverse over model
        list = new Vector();
        for (i = 0; i < getModel().getSize(); i++) {
            if (getChecked(i))
                list.add(i);
        }

        // generate result array
        result = new int[list.size()];
        for (i = 0; i < list.size(); i++) {
            result[i] = ((Integer) list.get(i)).intValue();
        }

        return result;
    }

}
