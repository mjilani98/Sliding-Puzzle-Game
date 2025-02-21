package com.example.hw2q7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private AppInterface anInterface;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //creating a game
        game = new Game();

        //creating an object of the button handler
        ButtonHandler buttonHandler = new ButtonHandler();

        //creating a screen
        anInterface = new AppInterface(this,buttonHandler);

        //setting up the screen
        setContentView(anInterface);

        //get the initial board and display it to the view
        char[][] initialBoard = game.getCurrentBoard();
        anInterface.drawCurrentBoard(initialBoard);

        //get the goal board and display it to the view
        char[][] goalBoard = game.getGoalBoard();
        anInterface.drawGoalBoard(goalBoard);

    }

    private class ButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {

            //get the button id
            int id = 0 ;
            id = anInterface.findButton((Button)v);

            if(id == 1) //up
            {
                game.up();
                anInterface.drawCurrentBoard(game.getCurrentBoard());

            }
            if(id == 2) //down
            {
                game.down();
                anInterface.drawCurrentBoard(game.getCurrentBoard());
            }
            if(id == 3) //left
            {
                game.left();
                anInterface.drawCurrentBoard(game.getCurrentBoard());
            }
            if(id == 4) //right
            {
                game.right();
                anInterface.drawCurrentBoard(game.getCurrentBoard());
            }
            
        }
    }


}