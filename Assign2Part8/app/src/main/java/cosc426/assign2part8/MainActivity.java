package cosc426.assign2part8;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {
    private Game game;
    private InterfaceBuilder appInterface;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initalizes game & creates the board, sets interface
        game = new Game();
        appInterface = new InterfaceBuilder(this);
        setContentView(appInterface);
        //displays the board on the interface
        appInterface.drawInitalBoard(game.getBoard());
        //sets the texhChangeHandlers depending on x & y
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                TextChangeHandler temp = new TextChangeHandler(i,j);
                appInterface.setTextChangeHandler(temp,i,j);
            }
        }
    }
    private class TextChangeHandler implements TextWatcher{
        private int x;
        private int y;

        public TextChangeHandler(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public void afterTextChanged(Editable s) {
            String input = appInterface.getInput(x,y);
            //conditional statements
            if(input.equals("")) {
                game.set(0, x, y);
            }else if(input.equals("0")) {
                game.set(0, x, y);
                appInterface.clear(x,y);
            }else{
                int value = Integer.parseInt(input);
                if(game.check(value,x,y)){ //checks whether the value can go there or not
                    game.set(value,x,y);
                }else{
                    game.set(0,x,y);
                    appInterface.clear(x,y);
                }
            }
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {  }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {  }
    }
}
