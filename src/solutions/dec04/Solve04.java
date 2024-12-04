package solutions.dec04;

import java.util.List;

public class Solve04 {
    int boardWidth;
    int boardHeight;
    int spaceToLeftEdge;
    int spaceToRightEdge;
    int spaceToUpperEdge;
    int spaceToBottomEdge;
    int counter;

    public int solveA(List<String> input){
        initialiseBoard(input);
        spaceToLeftEdge = 2;
        spaceToRightEdge = boardWidth - 2;
        spaceToUpperEdge = 2;
        spaceToBottomEdge = boardHeight - 2;
        for(int y = 0; y < input.size(); y++){
            for(int x = 0; x < input.get(y).length(); x++){
                if(input.get(y).charAt(x) == 'X'){
                    checkForXmas(y, x, input);
                }
            }
        }
        return counter;
    }


    public int solveB(List<String> input){
        spaceToLeftEdge = 1;
        spaceToRightEdge = boardWidth - 1;
        spaceToUpperEdge = 1;
        spaceToBottomEdge = boardHeight - 1;
        initialiseBoard(input);
        for(int y = 0; y < input.size(); y++){
            for(int x = 0; x < input.get(y).length(); x++){
                if(input.get(y).charAt(x) == 'A'){
                    checkForCrossMas(y, x, input);
                }
            }
        }
        return counter++;
    }

    private void initialiseBoard(List<String> input) {
        counter = 0;
        boardWidth = input.get(0).length() - 1;
        boardHeight = input.size() - 1;
    }

    public void checkForXmas(int y, int x, List<String> board){
        if(y > spaceToUpperEdge){
            checkDirection(y-1, x, y-2, x, y-3, x, board);
        }
        if(y < spaceToBottomEdge){
            checkDirection(y+1, x, y+2, x, y+3, x, board);
        }
        if(x > spaceToLeftEdge){
            checkDirection(y, x-1, y, x-2, y, x-3, board);
        }
        if(x < spaceToRightEdge){
            checkDirection(y, x+1, y, x+2, y, x+3, board);
        }
        if(x > spaceToLeftEdge && y > spaceToUpperEdge){
            checkDirection(y-1, x-1, y-2, x-2, y-3, x-3, board);
        }
        if(x > spaceToLeftEdge && y < spaceToBottomEdge){
            checkDirection(y+1, x-1, y+2, x-2, y+3, x-3, board);
        }
        if(x < spaceToRightEdge && y > spaceToUpperEdge){
            checkDirection(y-1, x+1, y-2, x+2, y-3, x+3, board);
        }
        if(x < spaceToRightEdge && y < spaceToBottomEdge){
            checkDirection(y+1, x+1, y+2, x+2, y+3, x+3, board);
        }
    }

    public void checkForCrossMas(int y, int x, List<String> board){
        if(y >= spaceToUpperEdge && y < boardHeight && x >= spaceToLeftEdge && x < boardWidth){
            if(board.get(y-1).charAt(x-1) == 'M' && board.get(y+1).charAt(x+1) == 'S'
                    && ((board.get(y-1).charAt(x+1) == 'M' && board.get(y+1).charAt(x-1) == 'S')
                        || (board.get(y+1).charAt(x-1) == 'M' && board.get(y-1).charAt(x+1) == 'S'))){
                counter++;
            }else if(board.get(y-1).charAt(x-1) == 'S' && board.get(y+1).charAt(x+1) == 'M'
                    && ((board.get(y-1).charAt(x+1) == 'S' && board.get(y+1).charAt(x-1) == 'M')
                        || (board.get(y+1).charAt(x-1) == 'S' && board.get(y-1).charAt(x+1) == 'M'))){
                counter++;
            }
        }
    }

    private void checkDirection(int yFirst, int xFirst, int ySecond, int xSecond,
                                   int yThird, int xThird, List<String> board){
        if(board.get(yFirst).charAt(xFirst) == 'M'
                && board.get(ySecond).charAt(xSecond) == 'A'
                && board.get(yThird).charAt(xThird) == 'S'){
            counter++;
        }
    }
}