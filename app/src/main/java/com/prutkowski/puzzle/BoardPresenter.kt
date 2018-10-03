package com.prutkowski.puzzle

import com.prutkowski.puzzle.Exceptions.InvalidBoardDimensionsException
import com.prutkowski.puzzle.dtos.BoardMatrix
import com.prutkowski.puzzle.dtos.BoardSets
import com.prutkowski.puzzle.dtos.MatrixCoordinates
import com.prutkowski.puzzle.dtos.PuzzleHolder
import java.util.ArrayList
import kotlin.collections.LinkedHashMap

class BoardPresenter(var view: IBoardView?) : IBoardPresenter {

    var boardMatrix = BoardMatrix()

    override fun configureBoard(boardSets: BoardSets) {
        if (validateBoardSets(boardSets))
            throw InvalidBoardDimensionsException()

        var index = 0
        boardSets.imagesSet.forEach {
            boardMatrix.addPuzzleBoard(index, it.key, it.value)
            index++
        }

        view?.setupPuzzleHoldersClickListeners(boardMatrix.puzzleMatrix)
    }

    private fun validateBoardSets(boardSets: BoardSets) =
            boardSets.imagesSet.size != boardSets.dimension.x * boardSets.dimension.y

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
                view?.updateImagesBetweenHolders(clickedPuzzleHolder, newPuzzleHolder)
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