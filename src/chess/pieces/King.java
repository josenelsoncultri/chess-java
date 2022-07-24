package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);
        //Above
        checkValidMove(matrix, p, -1, 0);
        //Below
        checkValidMove(matrix, p, 1, 0);
        //Left
        checkValidMove(matrix, p, 0, -1);
        //Right
        checkValidMove(matrix, p, 0, 1);
        //NW
        checkValidMove(matrix, p, -1, -1);
        //NE
        checkValidMove(matrix, p, -1, 1);
        //SW
        checkValidMove(matrix, p, 1, -1);
        //SE
        checkValidMove(matrix, p, 1, 1);

        return matrix;
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return (p == null || p.getColor() != getColor());
    }

    private void checkValidMove(boolean[][] matrix, Position p, int incrementRow, int incrementColumn) {
        p.setValues(position.getRow() + incrementRow, position.getColumn() + incrementColumn);
        if (getBoard().positionExists(p) && canMove(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }
    }
}
