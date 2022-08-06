package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        checkValidMove(matrix, p, -1, -2);

        checkValidMove(matrix, p, -2, -1);

        checkValidMove(matrix, p, -2, 1);

        checkValidMove(matrix, p, -1, 2);

        checkValidMove(matrix, p, 1, 2);

        checkValidMove(matrix, p, 2, 1);

        checkValidMove(matrix, p, 2, -1);

        checkValidMove(matrix, p, 1, -2);

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
