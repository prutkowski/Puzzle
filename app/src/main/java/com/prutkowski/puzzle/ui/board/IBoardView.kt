package com.prutkowski.puzzle.ui.board

import android.view.View
import com.prutkowski.puzzle.dtos.MatrixCoordinates
import com.prutkowski.puzzle.dtos.PuzzleHolder

interface IBoardView {
    fun setupPuzzleHoldersClickListeners(puzzles: LinkedHashMap<MatrixCoordinates, PuzzleHolder>)
    fun switchImages(fromHolder: PuzzleHolder, toHolder: PuzzleHolder)
    fun onMovePuzzle(view: View)
}
