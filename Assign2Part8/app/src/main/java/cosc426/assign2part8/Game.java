package cosc426.assign2part8;

import java.util.Random;

public class Game {
    public int[][] board = {};
    private int x,y;
    private int input;

    public Game(){
        //uses the sudoku file to generate a board
        Sudoku s = new Sudoku();
        board = s.generate();
    }
    //sets values
    public void set(int inputValue, int x, int y){
        this.input = inputValue;
        this.x = x;
        this.y = y;
    }
    public boolean check(int inputValue, int x, int y){
        //checks row and column
        for(int i = 0 ; i < 9 ; i++){
            if(board[x][i] == inputValue){
                board[x][y] = 0;
                return false;
            }
            if(board[i][y] == inputValue) {
                board[x][y] = 0;
                return false;
            }
        }
        //checks the 3 x 3 square
        for(int i = 0 ; i < 3 ; i++){
            if(board[x+i][y] == inputValue) {
                board[x][y] = 0;
                return false;
            }
            if(board[x][y+i] == inputValue){
                board[x][y] = 0;
                return false;
            }
            if(board[x+i][y+i] == inputValue){
                board[x][y] = 0;
                return false;
            }
        }
        //if none return false from above then it will return true and set the given value to that box
        board[x][y] = inputValue;
        return true;
    }
    //returns the board
    public int[][] getBoard(){
        return  board;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}