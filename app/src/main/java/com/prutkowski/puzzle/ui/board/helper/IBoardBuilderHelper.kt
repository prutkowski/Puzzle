package com.prutkowski.puzzle.ui.board.helper

import android.view.ViewGroup
import com.prutkowski.puzzle.dtos.BoardSets
import com.prutkowski.puzzle.dtos.Dimension

interface IBoardBuilderHelper {
    fun buildBoard(container: ViewGroup, dimension: Dimension)
    fun getBoardSetup(): BoardSets?
}