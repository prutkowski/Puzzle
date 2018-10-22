package com.prutkowski.puzzle.ui.board

import com.prutkowski.puzzle.dtos.BoardMatrix
import com.prutkowski.puzzle.dtos.BoardInput
import com.prutkowski.puzzle.dtos.MatrixCoordinates
import com.prutkowski.puzzle.dtos.PuzzleHolder
import com.prutkowski.puzzle.exceptions.InvalidBoardDimensionsException

class BoardPresenter(var view: IBoardView?) : IBoardPresenter {

    lateinit var boardMatrix: BoardMatrix
    lateinit var emptyHolderCoordinates: MatrixCoordinates

    override fun configureBoard(boardInput: BoardInput) {
        if (!validBoardSets(boardInput))
            throw InvalidBoardDimensionsException()

        boardMatrix = BoardMatrix(boardInput.dimension)
        emptyHolderCoordinates = boardInput.emptyDefaultPosition
        boardInput.imagesSet.forEach {
            boardMatrix.addPuzzleHolder(it.key, it.value)
        }

        view?.setupPuzzleHoldersClickListeners(boardMatrix.puzzleMatrix)
    }

    private fun validBoardSets(boardInput: BoardInput): Boolean {
        return boardInput.imagesSet.size == boardInput.dimension.x * boardInput.dimension.y
    }

    override fun resolvePuzzleHolderOnClick(clickedPuzzleHolderId: Int) {
        val clickedPuzzleHolder = boardMatrix.getPuzzleHolder(clickedPuzzleHolderId) ?: return
        if (!puzzleCanMove(clickedPuzzleHolder))
            return

        val newPuzzleHolder = boardMatrix.getPuzzleHolder(emptyHolderCoordinates)
        emptyHolderCoordinates = clickedPuzzleHolder.coordinates

        if (newPuzzleHolder != null) {
            val foo = clickedPuzzleHolder.currImage
            clickedPuzzleHolder.currImage = null
            newPuzzleHolder.currImage = foo
            view?.switchImages(clickedPuzzleHolder, newPuzzleHolder)
        }
    }

    private fun puzzleCanMove(clickedPuzzleHolder: PuzzleHolder): Boolean {
        val currentCoordinates = boardMatrix.getCoordinates(clickedPuzzleHolder)

        return currentCoordinates != null
                && ((currentCoordinates.row == emptyHolderCoordinates.row && (currentCoordinates.column + 1 == emptyHolderCoordinates.column || currentCoordinates.column - 1 == emptyHolderCoordinates.column))
                || (currentCoordinates.column == emptyHolderCoordinates.column && (currentCoordinates.row + 1 == emptyHolderCoordinates.row || currentCoordinates.row - 1 == emptyHolderCoordinates.row)))
    }

    private fun getNextPuzzleHolderPosition(clickedCoordinates: MatrixCoordinates): PuzzleHolder? {
        val newCoordinates = getNewCoordinates(clickedCoordinates)

        return boardMatrix.getPuzzleHolder(newCoordinates)
    }

    override fun getNewCoordinates(clickedCoordinates: MatrixCoordinates): MatrixCoordinates {
        val newCoordinates = clickedCoordinates.copy()
        if (clickedCoordinates.column < boardMatrix.dimension.y - 1) {
            newCoordinates.column++
        } else
            newCoordinates.column--

        return newCoordinates
    }

    override fun onDestroy() {
        view = null
    }
}