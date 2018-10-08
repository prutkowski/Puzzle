package com.prutkowski.puzzle.board

import com.prutkowski.puzzle.board.ui.IBoardView
import com.prutkowski.puzzle.exceptions.InvalidBoardDimensionsException
import com.prutkowski.puzzle.dtos.BoardMatrix
import com.prutkowski.puzzle.dtos.BoardSets
import com.prutkowski.puzzle.dtos.MatrixCoordinates
import com.prutkowski.puzzle.dtos.PuzzleHolder

class BoardPresenter(var view: IBoardView?) : IBoardPresenter {

    var boardMatrix = BoardMatrix()

    override fun configureBoard(boardSets: BoardSets?) {
        if (validBoardSets(boardSets))
            throw InvalidBoardDimensionsException()

        var index = 0
        boardSets!!.imagesSet.forEach {
            boardMatrix.addPuzzleBoard(index, it.key, it.value)
            index++
        }

        view?.setupPuzzleHoldersClickListeners(boardMatrix.puzzleMatrix)
    }

    private fun validBoardSets(boardSets: BoardSets?): Boolean {
        return boardSets != null && boardSets.imagesSet.size == boardSets.dimension.x * boardSets.dimension.y
    }

    /*
     * TODO two dimmensional board
     */
    override fun resolvePuzzleHolderOnClick(clickedPuzzleHolderId: Int) {
        val clickedPuzzleHolder = boardMatrix.getPuzzleHolder(clickedPuzzleHolderId) ?: return
        if (!clickedPuzzleHolder.isEmptyHolder())
            return

        val clickedCoordinates = boardMatrix.getCoordinates(clickedPuzzleHolder)

        if (clickedCoordinates != null) {
            val newPuzzleHolder = getNextPuzzleHolderPosition(clickedCoordinates)

            if (newPuzzleHolder != null) {
                val foo = clickedPuzzleHolder.currentDrawableId
                clickedPuzzleHolder.currentDrawableId = newPuzzleHolder.currentDrawableId
                newPuzzleHolder.currentDrawableId = foo
                view?.switchImages(clickedPuzzleHolder, newPuzzleHolder)
            }
        }
    }

    private fun getNextPuzzleHolderPosition(clickedCoordinates: MatrixCoordinates): PuzzleHolder? {
        val newCoordinates = getNewCoordinates(clickedCoordinates)

        return boardMatrix.getPuzzleHolder(newCoordinates)
    }

    override fun getNewCoordinates(clickedCoordinates: MatrixCoordinates): MatrixCoordinates {
        val newCoordinates = clickedCoordinates.copy()
        if (clickedCoordinates.column < boardMatrix.puzzleMatrix.size - 1) {
            newCoordinates.column++
        } else
            newCoordinates.column--

        return newCoordinates
    }

    override fun onDestroy() {
        view = null
    }
}