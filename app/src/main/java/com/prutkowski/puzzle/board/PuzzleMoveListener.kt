package com.prutkowski.puzzle.board

import android.view.View
import com.prutkowski.puzzle.board.ui.IBoardView

class PuzzleMoveListener(private val boardView: IBoardView) : IPuzzleMoveListener {
    override fun onClick(v: View) {
        boardView.onMovePuzzle(v)
    }
}