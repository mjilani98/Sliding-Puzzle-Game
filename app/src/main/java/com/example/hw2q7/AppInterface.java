package com.example.hw2q7;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AppInterface extends RelativeLayout
{

    //the size of the board
    private final int BOARD_SIZE = 3;

    //goal board text view
    private TextView[][] goalBoard;
    //grid for the goal board
    private GridLayout grid;

    //initial board text view
    private TextView[][] initialBoard;
    //grid for the initial board
    private GridLayout initialGrid;

    //Buttons
    private Button upButton;
    private Button downButton;
    private Button rightButton;
    private Button leftButton;



    //constructor
    public AppInterface(Context context, OnClickListener buttonHandler)
    {
        super(context);

        //for dimensions
        final int DP = (int)(getResources().getDisplayMetrics().density);

        //create initial board
        //get initial board from generator
        Generator generator = new Generator();
        char[][] initBoard = generator.generateInitialBoard();

        //create a grid layout inside relative layout
        initialGrid = new GridLayout(context);
        initialGrid.setId(GridLayout.generateViewId());
        initialGrid.setRowCount(BOARD_SIZE);
        initialGrid.setColumnCount(BOARD_SIZE);

        //setting layouts for the grid
        LayoutParams initialGridParams = new LayoutParams
                (
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
        initialGridParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        initialGrid.setLayoutParams(initialGridParams);

        //creating a goal board
        initialBoard = new TextView[BOARD_SIZE][BOARD_SIZE];

        for(int x = 0 ;  x < BOARD_SIZE ; x++)
        {
            for(int y =0  ; y < BOARD_SIZE ; y++)
            {
                initialBoard[x][y] = new TextView(context);
                initialBoard[x][y].setBackgroundColor(Color.parseColor("#87CEEB"));
                initialBoard[x][y].setText(" "); //Here
                initialBoard[x][y].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                initialBoard[x][y].setGravity(Gravity.CENTER);
                initialBoard[x][y].setTextSize(TypedValue.COMPLEX_UNIT_SP,25 );
                initialBoard[x][y].setTextColor(Color.parseColor("#FF000000"));
                GridLayout.LayoutParams params = new GridLayout.LayoutParams( );
                params.width = 80*DP;
                params.height  = 80*DP;
                params.rowSpec = GridLayout.spec(x, 1);
                params.columnSpec = GridLayout.spec(y, 1);
                params.topMargin = params.bottomMargin = 1;
                params.leftMargin = params.rightMargin = 1;


                if (x == 0) params.topMargin = 400;
                initialBoard[x][y].setLayoutParams(params);
                initialGrid.addView(initialBoard[x][y]);


            }
        }

        addView(initialGrid);
        //end initial board




        //create goal board
        //create a grid layout inside relative layout
        grid = new GridLayout(context);
        grid.setId(GridLayout.generateViewId());
        grid.setRowCount(BOARD_SIZE);
        grid.setColumnCount(BOARD_SIZE);

        //setting layouts for the grid
        LayoutParams gridParams = new LayoutParams
                (
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
        gridParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        gridParams.addRule(RelativeLayout.BELOW,initialGrid.getId());

        //setting the grid layouts to the grid
        grid.setLayoutParams(gridParams);

        //creating a goal board
        goalBoard = new TextView[BOARD_SIZE][BOARD_SIZE];
        int goalBoardNums = 1; //numbers that will be printed on the board
        for(int x = 0 ;  x < BOARD_SIZE ; x++)
        {
            for(int y =0  ; y < BOARD_SIZE ; y++)
            {
                goalBoard[x][y] = new TextView(context);
                goalBoard[x][y].setBackgroundColor(Color.parseColor("#00A86B"));
                goalBoard[x][y].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                goalBoard[x][y].setGravity(Gravity.CENTER);
                goalBoard[x][y].setTextSize(TypedValue.COMPLEX_UNIT_SP,25 );
                goalBoard[x][y].setTextColor(Color.parseColor("#FF000000"));
                GridLayout.LayoutParams params = new GridLayout.LayoutParams( );
                params.width = 80*DP;
                params.height  = 80*DP;
                params.rowSpec = GridLayout.spec(x, 1);
                params.columnSpec = GridLayout.spec(y, 1);
                params.topMargin = params.bottomMargin = 1;
                params.leftMargin = params.rightMargin = 1;

                if (x == 0) params.topMargin = 200;
                goalBoard[x][y].setLayoutParams(params);
                grid.addView(goalBoard[x][y]);

                goalBoardNums += 1;
            }
        }

        addView(grid);
        //end of goal board


        //creating and displaying buttons
        drawButtons(context,buttonHandler);

        //setting the background screen color
        setBackgroundColor(Color.parseColor("#DDDDDD"));

    } // end of constructor

    //method to display the buttons on the screen
    private void drawButtons(Context context,OnClickListener buttonHandler)
    {
        final int DP = (int)(getResources().getDisplayMetrics().density);

        //layouts for the buttons


        //Up button
        upButton = new Button(context);
        upButton.setId(Button.generateViewId());
        upButton.setText("Up");
        upButton.setTextColor(Color.parseColor("#FF000000"));
        upButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        upButton.setBackgroundColor(Color.parseColor("#E9967A"));
        upButton.setOnClickListener(buttonHandler);
        //layouts for the up button
        RelativeLayout.LayoutParams upButtonLayout = new RelativeLayout.LayoutParams(0,0);
        upButtonLayout.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        upButtonLayout.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        upButtonLayout.topMargin = 80 * DP;
        upButtonLayout.addRule(RelativeLayout.BELOW,grid.getId());
        upButtonLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        upButton.setLayoutParams(upButtonLayout);
        addView(upButton);

        //Down button
        downButton = new Button(context);
        downButton.setId(Button.generateViewId());
        downButton.setText("Down");
        downButton.setTextColor(Color.parseColor("#FF000000"));
        downButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        downButton.setBackgroundColor(Color.parseColor("#E9967A"));
        downButton.setOnClickListener(buttonHandler);
        //setting layouts for the down button
        RelativeLayout.LayoutParams downButtonLayout = new RelativeLayout.LayoutParams(0,0);
        downButtonLayout.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        downButtonLayout.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        downButtonLayout.topMargin = 20 * DP;
        downButtonLayout.addRule(RelativeLayout.BELOW,upButton.getId());
        downButtonLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        downButton.setLayoutParams(downButtonLayout);
        addView(downButton);

        //left Button
        leftButton = new Button(context);
        leftButton.setId(Button.generateViewId());
        leftButton.setText("Left");
        leftButton.setTextColor(Color.parseColor("#FF000000"));
        leftButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        leftButton.setBackgroundColor(Color.parseColor("#E9967A"));
        leftButton.setOnClickListener(buttonHandler);
        //setting the layouts for the left button
        RelativeLayout.LayoutParams leftButtonLayout = new RelativeLayout.LayoutParams(0,0);
        leftButtonLayout.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        leftButtonLayout.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        leftButtonLayout.topMargin = 120 * DP;
        leftButtonLayout.rightMargin = 20 * DP;
        leftButtonLayout.addRule(RelativeLayout.LEFT_OF,upButton.getId());
        leftButtonLayout.addRule(RelativeLayout.BELOW,grid.getId());
        leftButtonLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        leftButton.setLayoutParams(leftButtonLayout);
        addView(leftButton);

        //right button
        rightButton = new Button(context);
        rightButton.setId(Button.generateViewId());
        rightButton.setText("Right");
        rightButton.setTextColor(Color.parseColor("#FF000000"));
        rightButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        rightButton.setBackgroundColor(Color.parseColor("#E9967A"));
        rightButton.setOnClickListener(buttonHandler);
        //setting layouts for the right button
        RelativeLayout.LayoutParams rightButtonLayout = new RelativeLayout.LayoutParams(0,0);
        rightButtonLayout.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        rightButtonLayout.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        rightButtonLayout.topMargin = 120 * DP;
        rightButtonLayout.leftMargin = 20 * DP;
        rightButtonLayout.addRule(RelativeLayout.RIGHT_OF,upButton.getId());
        rightButtonLayout.addRule(RelativeLayout.BELOW,grid.getId());
        rightButtonLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rightButton.setLayoutParams(rightButtonLayout);
        addView(rightButton);

    }

    //method to display the current board to the screen
    public void drawCurrentBoard(char[][] currentBoard)
    {
        for(int x = 0 ;  x < BOARD_SIZE ; x++)
        {
            for (int y = 0; y < BOARD_SIZE; y++)
            {
                initialBoard[x][y].setText(""+currentBoard[x][y]);
            }
        }
    }


    //method to display the goal board to the screen
    public void drawGoalBoard(char[][] goal)
    {
        for(int x = 0 ;  x < BOARD_SIZE ; x++)
        {
            for (int y = 0; y < BOARD_SIZE; y++)
            {
                goalBoard[x][y].setText(""+goal[x][y]);
            }
        }
    }

    //method to return the button id
    public int findButton(Button button)
    {
        int id= 0 ;
        //if up button is clicked, return 1
        if(button.getId() == upButton.getId())
        {
            id = 1;
        }
        //if down button is clicked, return 2
        if(button.getId() == downButton.getId())
        {
            id = 2;
        }
        //if left button is clicked, return 3
        if(button.getId() == leftButton.getId())
        {
            id = 3;
        }
        //if right button is clicked, return 4
        if(button.getId() == rightButton.getId())
        {
            id = 4;
        }

        return id;
    }

}
