package dominio;

public class JewelQuest {
    private final Board board;

    public JewelQuest(){board = new Board();}

    public JewelQuest(int width, int length){board = new Board(width,length);}

    public JewelQuest(String[][] elements){board = new Board(elements);}

    public boolean moveElement(int x, int y, char direction){return board.moveElement(x,y,direction);}

    public int getScore(){return board.getScore();}

    public boolean getStatus(){
        boolean win = true;
        for (int[] integers:board.getCompleted()){
            for (int integer: integers){
                if (integer == 0) {
                    win = false;
                    break;
                }
            }
        }
        return win;
    }

    public Board getBoard(){return board;}



}
