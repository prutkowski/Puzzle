package com.prutkowski.puzzle.board

import com.prutkowski.puzzle.dtos.BoardSets
import com.prutkowski.puzzle.dtos.MatrixCoordinates

interface IBoardPresenter {
    fun configureBoard(boardSets: BoardSets?)
    fun resolvePuzzleHolderOnClick(clickedPuzzleHolderId: Int)
    fun getNewCoordinates(clickedCoordinates: MatrixCoordinates): MatrixCoordinates
    fun onDestroy()
}