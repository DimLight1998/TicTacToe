package gui_tictactoc.server;

/**
 * Created on 2017/04/26.
 */
public class GameControl {
    private int belong[][];


    GameControl() {
        belong = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                belong[i][j] = 0;
            }
        }
    }


    void setBelong(int row, int column, int belong) {
        this.belong[row][column] = belong;
    }


    int getBelong(int row, int column) {
        return belong[row][column];
    }


    int getWinner() {
        // check diagonal
        if ((belong[0][0] == 1) && (belong[1][1] == 1) && (belong[2][2] == 1)) {
            return 1;
        } else if ((belong[0][0] == -1) && (belong[1][1] == -1) && (belong[2][2] == -1)) {
            return -1;
        }

        if ((belong[0][2] == 1) && (belong[1][1] == 1) && (belong[2][0] == 1)) {
            return 1;
        } else if ((belong[0][2] == -1) && (belong[1][1] == -1) && (belong[2][0] == -1)) {
            return -1;
        }

        // check row
        for (int i = 0; i < 3; i++) {
            int ret = 0;

            if ((belong[i][0] == 1) && (belong[i][1] == 1) && (belong[i][2] == 1)) {
                ret = 1;
            }

            if ((belong[i][0] == -1) && (belong[i][1] == -1) && (belong[i][2] == -1)) {
                ret = -1;
            }

            if (ret != 0) {
                return ret;
            }
        }

        // check column
        for (int i = 0; i < 3; i++) {
            int ret = 0;

            if ((belong[0][i] == 1) && (belong[1][i] == 1) && (belong[2][i] == 1)) {
                ret = 1;
            }

            if ((belong[0][i] == -1) && (belong[1][i] == -1) && (belong[2][i] == -1)) {
                ret = -1;
            }

            if (ret != 0) {
                return ret;
            }
        }

        return 0;
    }
}