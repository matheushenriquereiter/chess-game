package io.github.matheushenriquereiter.entities.board;

import io.github.matheushenriquereiter.entities.pieces.*;
import io.github.matheushenriquereiter.enums.PieceColor;
import io.github.matheushenriquereiter.enums.SquareColor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chessboard extends JFrame {
    private final Square[][] squares = new Square[8][8];
    private final Piece[][] whitePieces = createDefaultPieces(PieceColor.BLACK);
    private final Piece[][] blackPieces = createDefaultPieces(PieceColor.WHITE);
    private PieceColor playerTurn = PieceColor.WHITE;
    private Square selectedSquare;
    private Piece selectedPiece;

    public Chessboard() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        createBoard();

        setUpChessPieces(whitePieces, blackPieces);

        setVisible(true);
        pack();
    }

    public static boolean isWithinBounds(int row, int column) {
        return row >= 0 && row <= 7 && column >= 0 && column <= 7;
    }

    public void setUpChessPieces(Piece[][] whitePieces, Piece[][] blackPieces) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= 7; j++) {
                squares[i][j].setPiece(whitePieces[i][j]);
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 7; j >= 0; j--) {
                squares[squares[0].length - i - 1][j].setPiece(blackPieces[i][j]);
            }
        }
    }

    public Piece[][] createDefaultPieces(PieceColor color) {
        return new Piece[][]{
                {
                        new Rook(color),
                        new Knight(color),
                        new Bishop(color),
                        new Queen(color),
                        new King(color),
                        new Bishop(color),
                        new Knight(color),
                        new Rook(color)
                },
                {
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color)
                }
        };
    }

    public void createBoard() {
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        JPanel numberPanel = new JPanel(new GridLayout(8, 1));
        JPanel topPanel = new JPanel(new GridLayout(1, 8));
        JPanel rightPanel = new JPanel(new GridLayout(8, 1));
        JPanel letterPanel = new JPanel(new GridLayout(1, 8));

        for (int i = 0; i <= 7; i++) {
            JLabel rightLabel = new JLabel();
            rightLabel.setPreferredSize(new Dimension(20, 20));
            rightPanel.add(rightLabel);

            JLabel numberLabel = new JLabel(String.valueOf(8 - i));
            numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
            numberLabel.setFont(new Font("Arial", Font.BOLD, 14));
            numberLabel.setPreferredSize(new Dimension(60, 100));
            numberPanel.add(numberLabel);

            for (int j = 0; j <= 7; j++) {
                Square square = new Square(i, j);
                square.addActionListener(_ -> onSquareClicked(square));
                squares[i][j] = square;
                boardPanel.add(square);
            }
        }

        for (int i = 0; i <= 7; i++) {
            JLabel topLabel = new JLabel();
            topLabel.setPreferredSize(new Dimension(20, 20));
            topPanel.add(topLabel);

            char letter = (char) ('A' + i);
            JLabel letterLabel = new JLabel(String.valueOf(letter));
            letterLabel.setFont(new Font("Arial", Font.BOLD, 14));
            letterLabel.setHorizontalAlignment(SwingConstants.CENTER);
            letterLabel.setPreferredSize(new Dimension(100, 60));
            letterPanel.add(letterLabel);
        }

        add(boardPanel, BorderLayout.CENTER);
        add(numberPanel, BorderLayout.WEST);

        JPanel bottomWrapper = new JPanel(new BorderLayout());
        JPanel bottomLeftCorner = new JPanel();
        bottomLeftCorner.setPreferredSize(new Dimension(60, 60));

        JPanel bottomRightCorner = new JPanel();
        bottomRightCorner.setPreferredSize(new Dimension(20, 60));

        bottomWrapper.add(bottomLeftCorner, BorderLayout.WEST);
        bottomWrapper.add(letterPanel, BorderLayout.CENTER);
        bottomWrapper.add(bottomRightCorner, BorderLayout.EAST);

        JPanel topWrapper = new JPanel(new BorderLayout());
        JPanel topLeftCorner = new JPanel();
        topLeftCorner.setPreferredSize(new Dimension(60, 20));

        JPanel topRightCorner = new JPanel();
        topRightCorner.setPreferredSize(new Dimension(20, 20));

        topWrapper.add(topLeftCorner, BorderLayout.WEST);
        topWrapper.add(topPanel, BorderLayout.CENTER);
        topWrapper.add(topRightCorner, BorderLayout.EAST);

        JPanel rightWrapper = new JPanel(new BorderLayout());
        JPanel rightTopCorner = new JPanel();
        rightTopCorner.setPreferredSize(new Dimension(20, 20));
        JPanel rightBottomCorner = new JPanel();
        rightBottomCorner.setPreferredSize(new Dimension(20, 60));

        rightWrapper.add(rightTopCorner, BorderLayout.NORTH);
        rightWrapper.add(rightPanel, BorderLayout.CENTER);
        rightWrapper.add(rightBottomCorner, BorderLayout.SOUTH);

        add(rightWrapper, BorderLayout.EAST);
        add(topWrapper, BorderLayout.NORTH);
        add(bottomWrapper, BorderLayout.SOUTH);
    }

    public void onSquareClicked(Square clickedSquare) {
        clearSelectedSquares();

        if (clickedSquare.hasPiece() && playerTurn == clickedSquare.getPiece().getColor()) {
            selectedSquare = clickedSquare;
            selectedPiece = clickedSquare.getPiece();

            paintLegalMovementsSquares(selectedSquare, selectedPiece);
            selectedSquare.setBackground(SquareColor.LIGHT_SELECTED.color);

            return;
        }

        if (selectedPiece != null && isMovementLegal(clickedSquare, selectedSquare, selectedPiece)) {
            if (selectedPiece instanceof Pawn pawn) {
                pawn.isFirstMove = false;
            }

            if (selectedPiece instanceof King king) {
                king.isFirstMove = false;
            }

            if (selectedPiece instanceof Rook rook) {
                rook.isFirstMove = false;
            }

            if (clickedSquare.hasPiece()) {
                clickedSquare.removePiece();
            }

            clickedSquare.setPiece(selectedPiece);
            selectedSquare.removePiece();

            if (isPawnPromotable(clickedSquare)) {
                promotePawn(clickedSquare);
            }

            if (selectedPiece instanceof King && Math.abs(clickedSquare.getColumn() - selectedSquare.getColumn()) == 2) {
                int row = clickedSquare.getRow();
                int col = clickedSquare.getColumn();

                if (col == 6) {
                    Square rookSquare = squares[row][7];
                    Piece rook = rookSquare.getPiece();
                    squares[row][5].setPiece(rook);
                    rookSquare.removePiece();
                }

                if (col == 2) {
                    Square rookSquare = squares[row][0];
                    Piece rook = rookSquare.getPiece();
                    squares[row][3].setPiece(rook);
                    rookSquare.removePiece();
                }
            }

            selectedSquare = null;
            selectedPiece = null;
            changePlayerTurn();

            if (isCheckmate()) {
                JOptionPane.showMessageDialog(this, "Winning team: %s Pieces".formatted(playerTurn == PieceColor.WHITE ? "Black" : "White"), "Game Over", JOptionPane.PLAIN_MESSAGE);

                resetGame();
            }

            if (!hasAvailableMoves()) {
                JOptionPane.showMessageDialog(this, "Stalemate", "Game Over", JOptionPane.PLAIN_MESSAGE);

                resetGame();
            }
        }
    }

    public void promotePawn(Square clickedSquare) {
        Object[] options = {"Queen", "Rook", "Bishop", "Knight"};
        List<Piece> possibleOptions = new ArrayList<>(Arrays.asList(
                new Queen(playerTurn),
                new Rook(playerTurn),
                new Bishop(playerTurn),
                new Knight(playerTurn)
        ));

        int pieceIndex = JOptionPane.showOptionDialog(
                this,
                "Choose option:",
                "Promote Pawn",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (pieceIndex == JOptionPane.CLOSED_OPTION) {
            clickedSquare.setPiece(possibleOptions.getFirst());
        } else {
            clickedSquare.setPiece(possibleOptions.get(pieceIndex));
        }
    }

    public void resetGame() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                squares[i][j].removePiece();
            }
        }

        playerTurn = PieceColor.WHITE;
        setUpChessPieces(whitePieces, blackPieces);
    }

    public boolean isPawnPromotable(Square clickedSquare) {
        return selectedPiece instanceof Pawn && (clickedSquare.getRow() == 0 || clickedSquare.getRow() == 7);
    }

    public void changePlayerTurn() {
        playerTurn = playerTurn == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public void clearSelectedSquares() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = squares[i][j];

                if (!square.hasPiece()) {
                    square.setIcon(null);
                }

                square.setBackground(square.getSquareColor());
            }
        }
    }

    public void paintLegalMovementsSquares(Square selectedSquare, Piece selectedPiece) {
        List<Position> legalMovements = getLegalMovements(selectedSquare, selectedPiece);
        List<Square> squaresToPaint = new ArrayList<>();

        for (Position legalMovement : legalMovements) {
            int row = legalMovement.row();
            int col = legalMovement.column();

            squaresToPaint.add(squares[row][col]);
        }

        for (Square squareToPaint : squaresToPaint) {
            squareToPaint.paintLegalSquare();
        }
    }

    public boolean isMovementLegal(Square square, Square selectedSquare, Piece selectedPiece) {
        List<Position> legalMovements = getLegalMovements(selectedSquare, selectedPiece);

        for (Position legalMovement : legalMovements) {
            if (square.getRow() == legalMovement.row() && square.getColumn() == legalMovement.column()) {
                return true;
            }
        }

        return false;
    }

    public List<Position> getLegalMovements(Square selectedSquare, Piece selectedPiece) {
        List<Position> possibleMovements = selectedPiece.getPossibleMovements(selectedSquare.getRow(), selectedSquare.getColumn(), squares);
        List<Position> legalMovements = new ArrayList<>();

        if (selectedPiece instanceof King king && king.isFirstMove) {
            int row = selectedSquare.getRow();
            int column = selectedSquare.getColumn();

            if (!king.isInCheck(row, column, squares)) {
                if (squares[row][7].hasPiece() && squares[row][7].getPiece() instanceof Rook rightRook && rightRook.isFirstMove) {
                    if (!squares[row][column + 1].hasPiece() && !squares[row][column + 2].hasPiece()) {
                        if (!king.isInCheck(row, column + 1, squares) && !king.isInCheck(row, column + 2, squares)) {
                            possibleMovements.add(new Position(row, column + 2));
                        }
                    }
                }

                if (squares[row][0].hasPiece() && squares[row][0].getPiece() instanceof Rook leftRook && leftRook.isFirstMove) {
                    if (!squares[row][column - 1].hasPiece() && !squares[row][column - 2].hasPiece() && !squares[row][column - 3].hasPiece()) {
                        if (!king.isInCheck(row, column - 1, squares) && !king.isInCheck(row, column - 2, squares)) {
                            possibleMovements.add(new Position(row, column - 2));
                        }
                    }
                }
            }
        }

        selectedSquare.removePiece();

        for (Position possibleMovement : possibleMovements) {
            Square possibleMovementSquare = squares[possibleMovement.row()][possibleMovement.column()];
            Piece squarePreviousPiece = possibleMovementSquare.getPiece();

            possibleMovementSquare.setPiece(selectedPiece);
            Square kingSquare = getCurrentTurnKingSquare();
            King currentTurnKing = (King) kingSquare.getPiece();

            if (currentTurnKing.isInCheck(kingSquare.getRow(), kingSquare.getColumn(), squares)) {
                possibleMovementSquare.setPiece(squarePreviousPiece);
                continue;
            }

            legalMovements.add(new Position(possibleMovement.row(), possibleMovement.column()));
            possibleMovementSquare.setPiece(squarePreviousPiece);
        }

        selectedSquare.setPiece(selectedPiece);
        return legalMovements;
    }

    public Square getCurrentTurnKingSquare() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = squares[i][j];

                if (square.hasPiece() && square.getPiece() instanceof King && square.getPiece().getColor() == playerTurn) {
                    return square;
                }
            }
        }

        return null;
    }

    public boolean isCheckmate() {
        Square kingSquare = getCurrentTurnKingSquare();
        King currentTurnKing = (King) kingSquare.getPiece();

        if (currentTurnKing.isInCheck(kingSquare.getRow(), kingSquare.getColumn(), squares)) {
            return !hasAvailableMoves();
        }

        return false;
    }

    public boolean hasAvailableMoves() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = squares[i][j];

                if (square.hasPiece() && square.getPiece().getColor() == playerTurn) {
                    List<Position> legalMovements = getLegalMovements(square, square.getPiece());

                    if (!legalMovements.isEmpty()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
