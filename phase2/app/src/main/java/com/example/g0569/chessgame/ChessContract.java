package com.example.g0569.chessgame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.utils.Coordinate;

/** The interface Chess contract. */
public interface ChessContract {
  /** The interface View. */
  interface View extends BaseView<Presenter> {
    /** Init view. */
    void initView();

    /**
     * Find touched grid coordinate int.
     *
     * @param coordinate the coordinate
     * @return the index code of touched Grid coordinate.
     */
    int findTouchedGridCoordinate(Coordinate coordinate);

    /**
     * Draw chess piece.
     *
     * @param coordinate the coordinate
     * @param type the type
     */
    void drawChessPiece(Coordinate coordinate, String type);

    /**
     * Show ending dialogue.
     *
     * @param title the title
     * @param text the text
     * @param buttonHint the button hint
     */
    void showEndingDialogue(String title, String text, String buttonHint);
  }

  /** The interface Presenter. */
  interface Presenter extends BasePresenter {
    /** Draw chess piece. */
    void drawChessPiece();

    /**
     * Start auto fight boolean.
     *
     * @return the boolean
     */
    boolean startAutoFight();

    /**
     * Translate from Grid coordinate to view coordinate coordinate.
     *
     * @param coordinate the coordinate
     * @return the coordinate
     */
    Coordinate gridCoordinateToViewCoordinate(Coordinate coordinate);

    /**
     * Place player chess.
     *
     * @param coordinate the coordinate
     */
    void placePlayerChess(Coordinate coordinate);

    /**
     * Translate from View coordinate to inventory coordinate coordinate.
     *
     * @param coordinate the coordinate
     * @return the coordinate
     */
    Coordinate viewCoordinateToInventoryCoordinate(Coordinate coordinate);

    /**
     * Translate from View coordinate to board coordinate coordinate.
     *
     * @param coordinate the coordinate
     * @return the coordinate
     */
    Coordinate viewCoordinateToBoardCoordinate(Coordinate coordinate);

    /**
     * Sets selected chess piece data.
     *
     * @param coordinate the coordinate
     * @return the selected chess piece data
     */
    String setSelectedChessPieceData(Coordinate coordinate);

    /**
     * Sets game over result.
     *
     * @param winGame whether player win the game.
     */
    void showGameOverResult(boolean winGame);

    /**
     * Show whether the position has been taken.
     *
     * @param coordinate the coordinate
     * @return the position has been taken
     */
    boolean showPositionHasBeenTaken(Coordinate coordinate);

    /** Reset chess piece on the board back to inventory. */
    void resetChessPiece();
  }
}
