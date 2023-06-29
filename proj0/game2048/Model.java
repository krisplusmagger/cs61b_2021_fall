package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author Kris
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */

    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.我的任务，让tile move

        int size = this.size();
        int empty_row, empty_col, key_row, key_col, key_value;

        int secondKeyRow = size;
        /** 4 4 2
         *  4   2
         *  4 4
         *  4 4 4
         *
         *  */
        this.board.setViewingPerspective(side);
        //如果有相邻合并项, 检查可以合并的位置, 先合并还是先移动？先合并。从上向下检查，向上合并
       for(int col = 0; col < size; col++) {
           for(int row = size - 1; row >= 0; row--) {
               empty_row = CheckFirstEmpty(row, col);// return first,empty_col
               key_row =  CheckFirstValue(row, col);// return first key_col
               if( empty_row != size && key_row != size && empty_row > key_row) {
                   //直接移动到空白位置,这是第一种情况，空格在前面情况
                   Tile t = this.board.tile(col, key_row);
                   this.board.move(col, empty_row, t);
                   changed = true;
                   row += 1;//移动到空白位置后，再从空白位置为起点查找
               }
               else if(key_row != size)//  第二种情况，空格在后面
               {
                    secondKeyRow = findTheSameVal(key_row, col);//若有相同的值，返回对应的row
                    if(secondKeyRow != size) {
                        //情况是后面有相同的值, 进行合并,若没有，就继续向下检查
                        this.score = this.score + (this.board.tile(col, secondKeyRow).value() * 2);
                        Tile t = this.board.tile(col, secondKeyRow);
                        this.board.move(col, key_row, t);
                        //this.board.tile(col, secondKeyRow).move(col, key_row);
                        changed = true;
                    }
               }
           }
       }
        this.board.setViewingPerspective(Side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }
    /** To find out the first empty row, if not, return size*/
    private int CheckFirstEmpty(int row, int col) {

        int size = this.size();
        for(; row >= 0 ; row--) {
            if(this.board.tile(col, row) == null) {
                return row;
            }
        }
        return size;
    }
    /**To find out the first valid row(has value), if not return size  */
    private int CheckFirstValue(int row, int col) {

        int size = this.size();
        for(; row >= 0; row--) {
            if(this.board.tile(col, row) != null) {
                return row;
            }
        }
        return size;
    }
    /** find the same value, return the corresponding row, if not found ,return size*/
    private int findTheSameVal(int key_row, int col) {

        int key_val = this.board.tile(col, key_row).value();
        key_row -= 1;
        for(; key_row >= 0; key_row -- ){
            if(this.board.tile(col, key_row)!= null && this.board.tile(col, key_row).value() == key_val) {
                return key_row;
            }
        }
        return this.board.size();

    }

    /**To check one specific one col to see if it has one empty place, if find one,
     * it will return the empty place promptly, if not find, return 0 , 它不让你移动多次，必须一次找到位置
     **/
    private int checkCol(int col, int row)
    {
        Tile t;
        int empty_row = 0;
        for(; row < this.size(); row++) {
            if(this.board.tile(col, row) == null) {
                empty_row = row;
                return empty_row;
            }
        }
        return empty_row;
    }
    /**检查某列中是否可以有合并的项, 并返回可以此列中最上面合并行的位置 */
    private int CheckRowAdjacentValues(int col) {

        int valid_mergering_row = 0;
        for(int row = 0; row < this.board.size() - 1; row++) {

            if(this.board.tile(col, row) != null && this.board.tile(col, row).value() == this.board.tile(col, row + 1).value() ){
                valid_mergering_row = row + 1;
            }
        }
        return valid_mergering_row;
    }
    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {

        int size = b.size();
        for( int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(b.tile(row, col) == null) return  true;
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        //row是行，col是列
        /** 思路是先固定行，再遍历列*/
        int size = b.size();
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                if(b.tile(col, row) != null && b.tile(col, row).value() == MAX_PIECE) return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        if(emptySpaceExists(b)) return true;
        if(checkAdjacentValues(b)) return true;

        return false;
    }
    public static boolean checkAdjacentValues(Board b) {
        int size = b.size();
        /** Check adjacent row行  tiles */
        for(int col = 0; col < size; col++) {
            for( int row = 0; row < size - 1; row++) {
                if(b.tile(col, row).value() == b.tile(col, row + 1).value()) return true;
            }
        }
        /** Check adjacent col列 tiles */
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size - 1; col ++) {
                if (b.tile(col, row).value() == b.tile(col + 1, row).value()) return true;
            }
        }

        return false;

    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
