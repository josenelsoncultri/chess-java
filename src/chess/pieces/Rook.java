package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        //Above
        checkValidMove(matrix, p, -1, 0);

        //Left
        checkValidMove(matrix, p, 0, -1);

        //Right
        checkValidMove(matrix, p, 0, 1);

        //Below
        checkValidMove(matrix, p, 1, 0);

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
