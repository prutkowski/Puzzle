package com.prutkowski.puzzle.dtos

import com.prutkowski.puzzle.R

data class PuzzleHolder(var holderId: Int, var currentDrawableId: Int) {
    fun isEmptyHolder(): Boolean {
        return currentDrawableId == R.drawable.empty_puzzle
    }
}