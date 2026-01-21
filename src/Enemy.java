public class Enemy implements Fieldable{
    private  int rowIndex;
    private int columnIndex;

    @Override
    public String getSymbol() {
        return " % ";
    }

    public Enemy(int rowIndex, int columnIndex){
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }



    //getters and setterrs
    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }
}
