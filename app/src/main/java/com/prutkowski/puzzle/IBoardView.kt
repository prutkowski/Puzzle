package com.prutkowski.puzzle

import android.view.View

interface IBoardView {
    fun setupPuzzleClickedListeners()
    fun getPuzzleClickedListener(): View.OnClickListener
}
