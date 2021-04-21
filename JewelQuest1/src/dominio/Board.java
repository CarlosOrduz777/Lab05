package dominio;

public class Board {
    private int widht;
    private int height;
    private String[][] completed;
    int score;

    public Board(int widht, int height){
        this.widht = widht;
        this.height = height;
        completed = new String[widht][height];
        for(int i = 0; i<completed.length;i++){
            for(int j=0;j<completed[0].length;j++){
                completed[i][j] = null;
            }
        }

    }


    /**
     *
     * @param x x direction
     * @param y y direction
     * @param direction u for up movement, r for right movement
     *                  l for left movemnt, d for dowm movement
     */
    public boolean moveElement(int x,int y,char direction){
        String element = completed[x][y];
        boolean succesfulMove = false;
        switch (direction) {
            case 'u' -> {
                String secondElement = completed[x - 1][y];
                completed[x][y] = secondElement;
                completed[x - 1][y] = element;
                if(!(checkWinByPositionRow(x,y) && checkWinByPositionRow(x-1,y) && checkWinByPositionColumn(x,y) && checkWinByPositionColumn(x-1,y))){
                    completed[x][y] = element;
                    completed[x-1][y] = secondElement;
                    succesfulMove = false;
                }else{
                    checkWinColumn(y);
                    succesfulMove =true;
                }

            }
            case 'd' -> {
                String elementDown = completed[x + 1][y];
                completed[x][y] = elementDown;
                completed[x + 1][y] = element;
                if(!(checkWinByPositionRow(x,y) && checkWinByPositionRow(x+1,y) && checkWinByPositionColumn(x,y) && checkWinByPositionColumn(x+1,y))){
                    completed[x][y] = element;
                    completed[x-1][y] = elementDown;
                    succesfulMove = false;
                }else {
                    checkWinColumn(y);
                    succesfulMove = true;
                }
            }
            case 'l' -> {
                String elementLeft = completed[x][y - 1];
                completed[x][y] = elementLeft;
                completed[x][y - 1] = element;
                if(!(checkWinByPositionRow(x,y) && checkWinByPositionRow(x,y-1) && checkWinByPositionColumn(x,y) && checkWinByPositionColumn(x,y-1))){
                    completed[x][y] = element;
                    completed[x-1][y] = elementLeft;
                    succesfulMove = false;
                }else {
                    succesfulMove = true;
                }
            }
            case 'r' -> {
                String elementRight = completed[x][y + 1];
                completed[x][y] = elementRight;
                completed[x][y + 1] = element;
                if(!(checkWinByPositionRow(x,y) && checkWinByPositionRow(x,y+1) && checkWinByPositionColumn(x,y) && checkWinByPositionColumn(x,y+1))){
                    completed[x][y] = element;
                    completed[x-1][y] = elementRight;
                    succesfulMove = false;
                }else {
                    succesfulMove = true;
                }
            }
        }
        return succesfulMove;
    }

    /**
     * Remove an element from the board
     * @param x position x of the element
     * @param y position y of the element
     */
    public void removeElement(int x, int y){
        completed[x][y] = null;
    }

    /**
     * look for winner element in a complete column
     * @param column that we are looking for winner pieces
     */
    public void checkWinColumn(int column){
        for(int i =0;i<height;i++){
            checkWinByPositionColumn(i,column);
            checkWinByPositionRow(i,column);
        }
    }



    /**
     * Check if there are winner pieces given a position
     * @param x position x in the board
     * @param y position y in the board
     */
    public boolean checkWinByPositionColumn(int x, int y){
        if(completed[x-1][y].equals(completed[x][y]) && completed[x+1][y].equals(completed[x][y])){
            removeElement(x-1,y);
            removeElement(x,y);
            removeElement(x+1,y);
            return true;
        }
        return false;
    }

    public boolean checkWinByPositionRow(int x, int y){
        if(completed[x][y-1].equals(completed[x][y]) && completed[x][y+1].equals(completed[x][y])){
            removeElement(x,y-1);
            removeElement(x,y);
            removeElement(x,y+1);
            return true;
        }
        return false;
    }

    public void refreshColumn(int column){

    }


}
