package cosc426.assign2part8;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.NumberFormat;

import static android.text.InputType.TYPE_CLASS_NUMBER;

public class InterfaceBuilder extends RelativeLayout{

    private EditText[][] board;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public InterfaceBuilder(Context context) {
        super(context);

        final int DP = (int) (getResources().getDisplayMetrics().density);
        //grid one will contain the board
        GridLayout gridOne = new GridLayout(context);
        gridOne.setId(GridLayout.generateViewId());
        gridOne.setColumnCount(9);
        gridOne.setRowCount(9);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(0, 0);
        params.width = 480 * DP;
        params.height = 480 * DP;
        params.topMargin = 30 * DP;
        params.addRule(CENTER_HORIZONTAL);
        gridOne.setLayoutParams(params);
        //the 9 x 9 playing board
        board = new EditText[9][9];
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                board[i][j] = new EditText(context);
                board[i][j].setText("");
                board[i][j].setTextColor(Color.parseColor("#28193c"));
                board[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                board[i][j].setGravity(Gravity.CENTER);
                board[i][j].setInputType(TYPE_CLASS_NUMBER);
                board[i][j].setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});
                GridLayout.LayoutParams params1 = new GridLayout.LayoutParams();
                params1.width = 53*DP;
                params1.height = 53*DP;
                params1.bottomMargin = 3;
                params1.rightMargin = 3;
                board[i][j].setLayoutParams(params1);
                gridOne.addView(board[i][j]);
            }
        }
        addView(gridOne);
    }
    public void drawInitalBoard(int[][] board){
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                if(board[i][j] == 0){
                    //sets the background of the open spaces
                    this.board[i][j].setText("");
                    this.board[i][j].setBackgroundColor(Color.parseColor("#afcece"));
                }else {
                    //sets the background and text of the filled spaces, also disables them so they cannot be changes
                    this.board[i][j].setText(board[i][j] + "");
                    this.board[i][j].setBackgroundColor(Color.parseColor("#a0b7b7"));
                    this.board[i][j].setEnabled(false);
                }
            }
        }
    }
    //sets the textchangehandlers
    public void setTextChangeHandler(TextWatcher textChangeHandler, int x, int y){
        board[x][y].addTextChangedListener(textChangeHandler);
    }
    //returns the input string
    public String getInput(int x, int y){ return board[x][y].getText().toString();}
    //used to clear a box/value
    public void clear(int x, int y){
        board[x][y].setText("");
    }
}