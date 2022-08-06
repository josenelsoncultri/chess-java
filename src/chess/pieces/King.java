package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);

        this.chessMatch = chessMatch;
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

        //#SpecialMoves: Castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            //Special move: Castling kingside rook
            Position kingRook = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(kingRook)) {
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
                    matrix[position.getRow()][position.getColumn() + 2] = true;
                }
            }

            //Special move: Castling queenside rook
            Position queenRook = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(queenRook)) {
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
                    matrix[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }

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

    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return (p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0);
    }
}
