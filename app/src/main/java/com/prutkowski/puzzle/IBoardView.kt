package com.prutkowski.puzzle

import com.prutkowski.puzzle.dtos.MatrixCoordinates
import com.prutkowski.puzzle.dtos.PuzzleHolder

interface IBoardView {
    fun setupPuzzleHoldersClickListeners(puzzles: LinkedHashMap<MatrixCoordinates, PuzzleHolder>)
    fun updateImagesBetweenHolders(fromHolder: PuzzleHolder, toHolder: PuzzleHolder)
}
