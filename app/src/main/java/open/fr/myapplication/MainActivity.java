package open.fr.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    char markPlayer = 'X';
    char[][] board;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layout);

        initBoard();
    }

    public void initBoard() {

        board = new char[3][3];

        for (int i = 0; i < board.length; i++) {

            LinearLayout linearLayout = new LinearLayout(getApplicationContext());
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.CENTER);
            layout.addView(linearLayout);

            for (int j = 0; j < board.length; j++) {

                final Button button = new Button(this);
                button.setWidth(340);
                button.setHeight(190);

                linearLayout.addView(button);

                final int rowPosition = i;
                final int columnPosition = j;

                // empty board at the start
                board[i][j] = '-';
                button.setText("");

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        board[rowPosition][columnPosition] = markPlayer;
                        button.setText(String.valueOf(markPlayer));

                        if (isWinner()) {

                            showToastEndGame("WINNER is "+markPlayer);

                        } else {

                            if (isBoardFull()) {

                                showToastEndGame("NO WINNER");

                            }
                        }

                        if (markPlayer == 'X') {
                            markPlayer = '0';
                        } else {
                            markPlayer = 'X';
                        }
                    }
                });
            }
        }
    }

    public void showToastEndGame(String message){

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

        // delay to see the result before reset
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.removeAllViews();
                initBoard();
            }
        }, 1000);

    }


    public boolean isWinner() {
        if (checkRows() || checkColumn() || checkDiagonal()) {
            return true;
        }
        return false;
    }

    //  optimisable
    public boolean checkRows() {

        if (board[0][0] != '-' && board[0][0] == board[0][1] && board[0][0] == board[0][2]) {
            return true;
        }

        if (board[1][0] != '-' && board[1][0] == board[1][1] && board[1][0] == board[1][2]) {
            return true;
        }

        if (board[2][0] != '-' && board[2][0] ==board[2][1] && board[2][0] == board[2][2]) {
            return true;
        }

        return false;

    }

    //  optimisable
    public boolean checkColumn() {

        if (board[0][0] != '-' && board[0][0] == board[1][0] && board[0][0] == board[2][0]) {
            return true;
        }

        if (board[0][1] != '-' && board[0][1] == board[1][1] && board[0][1] == board[2][1]) {
            return true;
        }

        if (board[0][2] != '-' && board[0][2] == board[1][2] && board[0][2] == board[2][2]) {
            return true;
        }

        return false;

    }

    //  optimisable
    public boolean checkDiagonal() {

        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true;
        }

        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return true;
        }

        return false;

    }

    // check if board is full ( no winner )
    public boolean isBoardFull() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }

        return true;
    }


}
