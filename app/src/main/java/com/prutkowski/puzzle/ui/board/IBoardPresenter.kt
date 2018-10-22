package com.prutkowski.puzzle.ui.board

import com.prutkowski.puzzle.dtos.BoardInput
import com.prutkowski.puzzle.dtos.MatrixCoordinates

interface IBoardPresenter {
    fun configureBoard(boardInput: BoardInput)
    fun resolvePuzzleHolderOnClick(clickedPuzzleHolderId: Int)
    fun getNewCoordinates(clickedCoordinates: MatrixCoordinates): MatrixCoordinates
    fun onDestroy()
}