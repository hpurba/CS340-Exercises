//1. What design principles does this code violate?
//      Single responsibility, decomposition, and dependencies.
//2. Without actually doing so, explain how you would refactor this code to improve its design.
//      I would split this up into several classes and arrange responsibilities for constants, constructors,
//      and methods that are closely related. This would involve making several classes.

public class JTable extends JComponent
        implements Accessible, CellEditorListener,
        ListSelectionListener, Scrollable,
        TableColumnModelListener, TableModelListener
{
    // Constants
    public static final int AUTO_RESIZE_ALL_COLUMNS
    public static final int AUTO_RESIZE_LAST_COLUMN
    public static final int AUTO_RESIZE_NEXT_COLUMN
    public static final int AUTO_RESIZE_OFF
    public static final int AUTO_RESIZE_SUBSEQUENT_COLUMNS

    // Constructors
    public JTable()
    public JTable(TableModel, TableColumnModel)
    public JTable(TableModel, TableColumnModel, ListSelectionModel)
    public JTable(int, int)
    public JTable(Object[][], Object[][])
    public JTable(java.util.Vector, java.util.Vector)

    // Methods
    public void addColumn(TableColumn column)
    public void addColumnSelectionInterval(int start, int finish)
    public void addNotify()
    public void addRowSelectionInterval(int start, int finish)
    public void clearSelection()
    public void columnAdded(TableColumnModelEvent event)
    public void columnAtPoint(Point p)
    public void columnMarginChanged(ChangeEvent event)
    public void columnMoved(TableColumnModelEvent event)
    public void columnRemoved(TableColumnModelEvent event)
    public void columnSelectionChanged(ListSelectionEvent event)
    public void convertColumnIndexToModel(int viewColumn)
    public void convertColumnIndexToView(int modelColumn)
    public void createDefaultColumnsFromModel()
    public boolean editCellAt(int row, int column)
    public boolean editCellAt(int row, int column, EventObject event)
    public void editingCanceled(ChangeEvent event)
    public void editingStopped(ChangeEvent event)
    public AccessibleContext getAccessibleContext()
    public boolean getAutoCreateColumnsFromModel()
    public int getAutoResizeMode()
    public TableCellEditor getCellEditor()
    public TableCellEditor getCellEditor(int row, int column)
    public Rectangle getCellRect(int row, int column, boolean includeSpacing)
    public boolean getCellSelectionEnabled()
    public TableColumn getColumn(Object object)
    public Class getColumnClass(int column)
    public int getColumnCount()
    public TableColumnModel getColumnModel()
    public String getColumnName(int column)
    public Boolean getColumnSelectionAllowed()
    public TableCellEditor getDefaultEditor(Class class)
    public TableCellRenderer getDefaultRenderer(Class class)
    public int getEditingColumn()
    public int getEditingRow()
    public Component getEditorComponent()
    public Color getGridColor()
    public Dimension getIntercellSpacing()
    public TableModel getModel()

    public Dimension getPreferredScrollableViewportSize()
    public int getRowCount()
    public int getRowHeight()
    public int getRowMargin()
    public Boolean getRowSelectionAllowed()

    public int getScrollableBlockIncrement(
            Rectangle visible, int orientation, int direction)
    public Boolean getScrollableTracksViewportHeight()
    public Boolean getScrollableTracksViewportWidth()
    public int getScrollableUnitIncrement(
            Rectangle visible, int orientation, int direction)
    public int getSelectedColumn()
    public int getSelectedColumnCount()
    public int[] getSelectedColumns()
    public int getSelectedRow()
    public int getSelectedRowCount()
    public int[] getSelectedRows()
    public Color getSelectionBackground()
    public Color getSelectionForeground()
    public ListSelectionModel getSelectionModel()
    public Boolean getShowHorizontalLines()
    public Boolean getShowVerticalLines()
    public JTableHeader getTableHeader()
    public String getToolTipText(MouseEvent event)
    public TableUI getUI()
    public String getUIClassID()
    public Object getValueAt(int row, int column)
    public Boolean isCellEditable(int row, int column)
    public Boolean isCellSelected(int row, int column)
    public Boolean isColumnSelected(int column)
    public Boolean isEditing()
    public boolean isManagingFocus()
    public Boolean isRowSelected(int row)
    public void moveColumn(int column, int newColumn)
    public Component prepareEditor(TableCellEditor editor,
                                   int row, int column)
    public Component prepareRenderer(TableCellRenderer renderer,
                                     int row, int column)
    public void removeColumn(TableColumn column)
    public void removeColumnSelectionInterval(int column1, int column2)
    public void removeEditor()
    public void removeRowSelectionInterval(int row1, int row2)
    public void reshape(int x, int y, int width, int height)
    public int rowAtPoint(Point point)
    public void selectAll()
    public void setAutoCreateColumnsFromModel(Boolean doAutoCreate)
    public void setAutoResizeModel(int mode)
    public void setCellEditor(TableCellEditor editor)

    public void setCellSelectionEnabled(Boolean maySelect)
    public void setColumnModel(TableColumnModel model)
    public void setColumnSelectionAllowed(Boolean maySelect)
    public void setColumnSelectionInterval(int column1, int column2)
    public void setDefaultEditor(Class class, TableCellEditor editor)
    public void setDefaultRenderer(Class class, TableCellRenderer renderer)
    public void setEditingColumn(int column)
    public void setEditingRow(int row)
    public void setGridColor(Color color)
    public void setIntercellSpacing(Dimention dim)
    public void setModel(TableModel model)
    public void setPreferredScrollableViewportSize(Dimension dim)
    public void setRowHeight(int height)
    public void setRowMargin(int margin)
    public void setRowSelectionAllowed(Boolean maySelect)
    public void setSelectionBackground(Color background)
    public void setSelectionForeground(Color foreground)
    public void setSelectionMode(int mode)
    public void setSelectionModel(ListSelectionModel model)
    public void setShowGrid(Boolean showing)
    public void setShowHorizontalLines(Boolean b)
    public void setShowVerticalLines(Boolean b)
    public void setTableHeader(JTableHeader header)
    public void setUI(TableUI ui)
    public void setValueAt(Object value, int row, int column)
    public void sizeColumnsToFit(int resizingColumn)
    public void tableChanged(TableModelEvent event)
    public void updateUI()
    public void valueChanged(ListSelectionEvent)

// plus 22 protected variables

// plus 10 protected methods

// plus 97 methods inherited from JComponent (and fields etc.)

// plus about 35 methods from Container

// plus about 85 methods from Component

// plus 11 methods from Object
}