package com.prutkowski.puzzle.utils

import com.prutkowski.puzzle.dtos.Dimension
import com.prutkowski.puzzle.dtos.PuzzleImage

interface IBoardImageProvider {
    fun getImageList(dimension: Dimension): ArrayList<PuzzleImage>
}