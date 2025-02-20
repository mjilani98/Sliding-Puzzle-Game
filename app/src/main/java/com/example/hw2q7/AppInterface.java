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

    private Button upButton;
    private Button downButton;
    private Button rightButton;
    private Button leftButton;



    //constructor
    public AppInterface(Context context, OnClickListener buttonHandler)
    {
        super(context);

        //draw the initial board
        drawInitialBoard(context);

        //draw the goad board
        drawGoalBoard(context);

        //Draw Buttons
        drawButtons(context,buttonHandler);


        //setting the background screen color
        setBackgroundColor(Color.parseColor("#DDDDDD"));

    } // end of constructor

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


    }

    private void drawInitialBoard(Context context)
    {
        final int DP = (int)(getResources().getDisplayMetrics().density);

        //create a grid layout inside relative layout
        initialGrid = new GridLayout(context);
        initialGrid.setId(GridLayout.generateViewId());
        initialGrid.setRowCount(BOARD_SIZE);
        initialGrid.setColumnCount(BOARD_SIZE);

        //setting layouts for the grid
        LayoutParams gridParams = new LayoutParams
                (
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
        gridParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        initialGrid.setLayoutParams(gridParams);

        //creating a goal board
        initialBoard = new TextView[BOARD_SIZE][BOARD_SIZE];
        int goalBoardNums = 1; //numbers that will be printed on the board
        for(int x = 0 ;  x < BOARD_SIZE ; x++)
        {
            for(int y =0  ; y < BOARD_SIZE ; y++)
            {
                initialBoard[x][y] = new TextView(context);
                initialBoard[x][y].setBackgroundColor(Color.parseColor("#87CEEB"));
                initialBoard[x][y].setText(""+goalBoardNums);
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

                goalBoardNums += 1;
            }
        }
        initialBoard[BOARD_SIZE-1][BOARD_SIZE-1].setText("");

        addView(initialGrid);
    }


    public void drawGoalBoard(Context context)
    {
        final int DP = (int)(getResources().getDisplayMetrics().density);

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
                goalBoard[x][y].setText(""+goalBoardNums);
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
        goalBoard[BOARD_SIZE-1][BOARD_SIZE-1].setText("");

        addView(grid);
    }


}
