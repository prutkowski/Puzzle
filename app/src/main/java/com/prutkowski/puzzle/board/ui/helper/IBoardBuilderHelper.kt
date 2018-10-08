package com.prutkowski.puzzle.board.ui.helper

import android.view.ViewGroup
import com.prutkowski.puzzle.dtos.BoardSets

interface IBoardBuilderHelper {
    fun buildBoard(container: ViewGroup)
    fun getBoardSetup(): BoardSets?
}