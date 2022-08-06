package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        //NW
        checkValidMove(matrix, p, -1, -1);

        //NE
        checkValidMove(matrix, p, -1, 1);

        //SE
        checkValidMove(matrix, p, 1, 1);

        //SW
        checkValidMove(matrix, p, 1, -1);

        return matrix;
    }

    private void checkValidMove(boolean[][] matrix, Position p, int incrementRow, int incrementColumn) {
        p.setValues(position.getRow() + incrementRow, position.getColumn() + incrementColumn);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;

            p.setRow(p.getRow() + incrementRow);
            p.setColumn(p.getColumn() + incrementColumn);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }
    }
}
