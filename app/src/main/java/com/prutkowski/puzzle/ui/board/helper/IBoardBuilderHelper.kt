package com.prutkowski.puzzle.ui.board.helper

import android.widget.LinearLayout
import com.prutkowski.puzzle.dtos.BoardInput
import com.prutkowski.puzzle.dtos.Dimension

interface IBoardBuilderHelper {
    fun build(dimension: Dimension): LinearLayout
    fun getBoardSetup(): BoardInput?
}