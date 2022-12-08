package com.org.app.util;

import com.org.app.customui.MyEditorKit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.LookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TableRendererUtil {

    public static final int CELL_ALIGN_LEFT = 2;
    public static final int CELL_ALIGN_CENTER = 0;
    public static final int CELL_ALIGN_RIGHT = 4;
    public int headerHeight = 35;
    private JTable jTable;
    private Vector tableHeader;
    private Object[] columNames;

    public TableRendererUtil(JTable jTable) {
        this.jTable = jTable;    
        
//        Lay header cua table
        tableHeader = new Vector();
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            tableHeader.add(jTable.getColumnName(i));
        }
        
        this.setDataVector(new Vector());
    }
    


    public TableRendererUtil(JTable jTable, Class[] columnClass) {
        this.jTable = new JTable() {
            private static final long serialVersionUID = 1L;
            @Override
            public Class<?> getColumnClass(int column) {
//                return columnClass[column];
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
        jTable = this.jTable;
    }
    
    
    
    public void setCellEditable(boolean isEditable) {
        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditable;
            }
        };
        tableModel.setDataVector(new Vector(), this.tableHeader);
        this.jTable.setModel(tableModel);
    }
    
    public void setCellEditable(int columnIndex) {
        this.jTable.getModel().isCellEditable(-1, columnIndex);
    }

    public void setDataVector(Vector tableData) {
        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        tableModel.setDataVector(tableData, this.tableHeader);
        this.jTable.setModel(tableModel);
    }

    public void changeHeaderStyle(Color background) {
        JTableHeader jTableHeader = this.jTable.getTableHeader();
        
        // canh giua man hinh
        ((DefaultTableCellRenderer) jTableHeader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) jTableHeader.getDefaultRenderer())
                .setVerticalAlignment(JLabel.CENTER);
        // chieu cao header
        jTableHeader.setBackground(background);
        
        jTableHeader.setForeground(Color.decode("#000")); // change the Foreground
        this.jTable.setFillsViewportHeight(true);
        this.jTable.setBackground(Color.WHITE);
        jTableHeader.setDefaultRenderer(new MultiLineTableHeaderRenderer(40));
        jTableHeader.setPreferredSize(new Dimension(0, headerHeight));
        jTableHeader.setFont(new Font("Open sans", Font.BOLD, 13)); // font name style size
        
    }
    
    public void setHeaderHeight(int height) {
        headerHeight = height;
    }
    
    public void wrapHeader() {
        DefaultTableModel model = (DefaultTableModel) this.jTable.getModel();
        for(Object headerv : tableHeader){
            String h = headerv.toString();
            h = String.format("<html>%s</html>", h).toString();
            headerv = (Object) h;
        }
    }

    public void setColumnAlignment(int columnIndex, int cellAlignment) {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(cellAlignment);
        this.jTable.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
    }

    public void setColoumnWidthByPersent(int columnIndex, int persent) {
        double tableWidth = this.jTable.getPreferredSize().getWidth();
        this.jTable.getColumnModel().getColumn(columnIndex).setPreferredWidth((int) (tableWidth * persent / 100));
    }
    
    public void setColumnAlignment(int[] columnsIndex, int present) {
        for (int i = 0; i < columnsIndex.length; i++) {
            setColumnAlignment(columnsIndex[i], present);
            
        }
    }
    
    public void setRowHeightByPresent(int size) {
        this.jTable.setRowHeight(size);
    }
    
    class MultiLineTableHeaderRenderer extends JTextPane implements TableCellRenderer
{
        int height = 0;
    public  MultiLineTableHeaderRenderer(int headerHeight) {
        this();
        height = headerHeight;
    }
  public MultiLineTableHeaderRenderer() {
      this.setEditorKit(new MyEditorKit());
      StyledDocument documentStyle = this.getStyledDocument();
SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
    
documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
    setEditable(false);
//    setLineWrap(true);
    setOpaque(false);
    setFocusable(false);
//    setWrapStyleWord(true);
    
    height = getPreferredSize().height;
    LookAndFeel.installBorder(this, "TableHeader.cellBorder");
  }
  
  private int getHeaderHeight() {
      return height;
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    int width = table.getColumnModel().getColumn(column).getWidth();
    setText((String)value);
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(width, getPreferredSize().height + 20);
    return this;
  }
}
}
