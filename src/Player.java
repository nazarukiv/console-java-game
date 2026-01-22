public class Player implements Fieldable{

    //commands for moves
    private static final String MOVE_LEFT = "a";
    private static final String MOVE_RIGHT = "d";
    private static final String MOVE_UP = "w";
    private static final String MOVE_DOWN= "s";
    private static final String NO_MOVE = "z";



    private int rowIndex;
    private int columnIndex;
    private Field field;
    private Game game;

    @Override
    public String getSymbol() {
        return " @ ";
    }

    public Player(int rowIndex, int columnIndex, Field field, Game game){
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.game = game;
        this.field =game.getField();
        field.setFieldable(rowIndex, columnIndex, this);
    }


    public void makeMove(String command){
        switch (command){
            case MOVE_LEFT:
                movePlayer(0, -1);
                break;

            case MOVE_RIGHT:
                movePlayer(0, 1);
                break;
            case MOVE_UP:
                movePlayer(-1, 0);
                break;

            case MOVE_DOWN:
                movePlayer(1, 0);
                break;

            case NO_MOVE:
                break;

            default:
                showError(command);
                break;
        }
    }

    private void movePlayer(int dRow, int dCol) {
        int newRow = rowIndex + dRow;
        int newCol = columnIndex + dCol;

        if (!field.isInside(newRow, newCol)) return;

        Fieldable target = field.getFieldable(newRow, newCol);

        if (target instanceof Enemy) {
            System.out.println("blocked by enemy");
            return;
        }

        if (target instanceof Flower) {
            Flower f = (Flower) target;
            game.setTransistorsGathered(f.getTransistors());
            game.getFlowerArrayList().remove(f);
        }

        field.setFieldable(rowIndex, columnIndex, new Empty());
        field.setFieldable(newRow, newCol, this);

        rowIndex = newRow;
        columnIndex = newCol;
    }

    private void swapPlayer(int newRowIndex, int newColumnIndex){
        field.setFieldable(rowIndex, columnIndex, new Empty());
        field.setFieldable(newRowIndex, newColumnIndex, this);
        rowIndex = newRowIndex;
        columnIndex = newColumnIndex;
    }

    private void showError(String command){
        System.out.println("Sorry, there is no " + command +
                " command, please verify and try again.");
    }

    //getters and setters
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
