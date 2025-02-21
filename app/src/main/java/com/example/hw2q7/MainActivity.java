package com.example.hw2q7;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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


            //check if the player has won the game
            if(game.checkWin())
            {
                showDialogBox();
            }
            
        }
    }
    private void showDialogBox()
    {
        //create a dialog box
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(this);

        //set message on dialog box
        dialogBox.setMessage("Game ended , do you want to play again");

        //create an event handler for the dialog box
        DialogListener handler = new DialogListener();

        //add event handler to the dialog box
        dialogBox.setPositiveButton("Yes",handler);
        dialogBox.setNegativeButton("No",handler);
        dialogBox.setNeutralButton("Cancel",handler);
        dialogBox.show();
    }

    private class DialogListener implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int id)
        {
            //if the player wants to play again
            if(id == 1)
            {
                //create a new game
                game = new Game();
                //set the board to the new game
                char[][] newBoard = game.getCurrentBoard();
                anInterface.drawCurrentBoard(newBoard);
            }
            //if the player wants to end the game
            else if (id == 2)
            {
                //destroy the app
                MainActivity.this.finish();
            }
            else ;
        }
    }


}