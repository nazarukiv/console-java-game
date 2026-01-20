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

    private void movePlayer(int deltaRowIndex, int deltaColumnIndex){
        int newRowIndex = rowIndex + deltaRowIndex;
        int newColumnIndex = columnIndex + deltaColumnIndex;

        if ((newRowIndex > 0) && (newRowIndex < field.getColumns()) &&
                (newColumnIndex>0) && (newColumnIndex < field.getRows()) &&
                !((field.getFieldable(newColumnIndex, newRowIndex)) instanceof Enemy)){
            if ((field.getFieldable(newColumnIndex, newRowIndex)) instanceof Flower) {
                Flower flower =(Flower) field.getFieldable(newColumnIndex, newRowIndex);
                game.setTransistorsGathered(flower.getTransistors());
                game.getFlowerArrayList().remove(flower);
                field.setFieldable(newColumnIndex, newRowIndex, this);
                field.setFieldable(columnIndex, rowIndex, new Empty());
                rowIndex = newRowIndex;
                columnIndex = newColumnIndex;
            }
            if ((field.getFieldable(newColumnIndex, newRowIndex)) instanceof Empty) {
                swapPlayer(newColumnIndex, newRowIndex);
            }
        }
    }
    private void swapPlayer(int newColumnIndex, int newRowIndex){
        field.setFieldable(newColumnIndex, newRowIndex, this);
        field.setFieldable(columnIndex, rowIndex, new Empty());
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
