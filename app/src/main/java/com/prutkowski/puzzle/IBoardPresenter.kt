package com.prutkowski.puzzle

import com.prutkowski.puzzle.dtos.MatrixCoordinates

interface IBoardPresenter {
    fun configureBoard()
    fun resolvePuzzleHolderOnClick(clickedPuzzleHolderId: Int)
    fun getNewCoordinates(clickedCoordinates: MatrixCoordinates): MatrixCoordinates
    fun onDestroy()
}