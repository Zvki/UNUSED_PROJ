import javax.swing.*;
import java.util.Vector;

public class CheckBoxListModel
        extends DefaultListModel {

    private static final long serialVersionUID = 7772455499540273507L;

    public CheckBoxListModel() {
        super();
    }

    public CheckBoxListModel(Object[] listData) {
        for (int i = 0; i < listData.length; i++)
            addElement(listData[i]);
    }

    public CheckBoxListModel(Vector listData) {
        for (int i = 0; i < listData.size(); i++)
            addElement(listData.get(i));
    }

    public void add(int index, Object element) {
        if (!(element instanceof CheckBoxList.CheckBoxListItem))
            super.add(index, new CheckBoxList.CheckBoxListItem(element));
        else
            super.add(index, element);
    }

    public void addElement(Object obj) {
        if (!(obj instanceof CheckBoxList.CheckBoxListItem))
            super.addElement(new CheckBoxList.CheckBoxListItem(obj));
        else
            super.addElement(obj);
    }

    public boolean contains(Object elem) {
        if (!(elem instanceof CheckBoxList.CheckBoxListItem))
            return super.contains(new CheckBoxList.CheckBoxListItem(elem));
        else
            return super.contains(elem);
    }

    public void copyInto(Object[] anArray) {
        if (anArray.length < getSize())
            throw new IndexOutOfBoundsException("Array not big enough!");

        for (int i = 0; i < getSize(); i++)
            anArray[i] = ((CheckBoxList.CheckBoxListItem) getElementAt(i)).getContent();
    }

    public Object elementAt(int index) {
        return ((CheckBoxList.CheckBoxListItem) super.elementAt(index)).getContent();
    }

    public Object firstElement() {
        return ((CheckBoxList.CheckBoxListItem) super.firstElement()).getContent();
    }

    public Object get(int index) {
        return ((CheckBoxList.CheckBoxListItem) super.get(index)).getContent();
    }

    public Object getElementAt(int index) {
        return ((CheckBoxList.CheckBoxListItem) super.getElementAt(index)).getContent();
    }

    public int size(){
        return super.getSize();
    }

    public int indexOf(Object elem) {
        if (!(elem instanceof CheckBoxList.CheckBoxListItem))
            return super.indexOf(new CheckBoxList.CheckBoxListItem(elem));
        else
            return super.indexOf(elem);
    }

    public int indexOf(Object elem, int index) {
        if (!(elem instanceof CheckBoxList.CheckBoxListItem))
            return super.indexOf(new CheckBoxList.CheckBoxListItem(elem), index);
        else
            return super.indexOf(elem, index);
    }

    public void insertElementAt(Object obj, int index) {
        if (!(obj instanceof CheckBoxList.CheckBoxListItem))
            super.insertElementAt(new CheckBoxList.CheckBoxListItem(obj), index);
        else
            super.insertElementAt(obj, index);
    }

    public Object lastElement() {
        return ((CheckBoxList.CheckBoxListItem) super.lastElement()).getContent();
    }

    public int lastIndexOf(Object elem) {
        if (!(elem instanceof CheckBoxList.CheckBoxListItem))
            return super.lastIndexOf(new CheckBoxList.CheckBoxListItem(elem));
        else
            return super.lastIndexOf(elem);
    }

    public int lastIndexOf(Object elem, int index) {
        if (!(elem instanceof CheckBoxList.CheckBoxListItem))
            return super.lastIndexOf(new CheckBoxList.CheckBoxListItem(elem), index);
        else
            return super.lastIndexOf(elem, index);
    }

    public Object remove(int index) {
        return ((CheckBoxList.CheckBoxListItem) super.remove(index)).getContent();
    }

    public boolean removeElement(Object obj) {
        if (!(obj instanceof CheckBoxList.CheckBoxListItem))
            return super.removeElement(new CheckBoxList.CheckBoxListItem(obj));
        else
            return super.removeElement(obj);
    }

    public Object set(int index, Object element) {
        if (!(element instanceof CheckBoxList.CheckBoxListItem))
            return ((CheckBoxList.CheckBoxListItem) super.set(index, new CheckBoxList.CheckBoxListItem(element))).getContent();
        else
            return ((CheckBoxList.CheckBoxListItem) super.set(index, element)).getContent();
    }

    public void setElementAt(Object obj, int index) {
        if (!(obj instanceof CheckBoxList.CheckBoxListItem))
            super.setElementAt(new CheckBoxList.CheckBoxListItem(obj), index);
        else
            super.setElementAt(obj, index);
    }

    public Object[] toArray() {
        Object[]      result;
        Object[]      internal;
        int       i;

        internal = super.toArray();
        result   = new Object[internal.length];

        for (i = 0; i < internal.length; i++)
            result[i] = ((CheckBoxList.CheckBoxListItem) internal[i]).getContent();

        return result;
    }

    public boolean getChecked(int index) {
        return ((CheckBoxList.CheckBoxListItem) super.getElementAt(index)).getChecked();
    }

    public void setChecked(int index, boolean checked) {
        ((CheckBoxList.CheckBoxListItem) super.getElementAt(index)).setChecked(checked);
    }
}
