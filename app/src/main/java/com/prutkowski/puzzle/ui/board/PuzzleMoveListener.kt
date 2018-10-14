package com.prutkowski.puzzle.ui.board

import android.view.View

class PuzzleMoveListener(private val boardView: IBoardView) : IPuzzleMoveListener {
    override fun onClick(v: View) {
        boardView.onMovePuzzle(v)
    }
}