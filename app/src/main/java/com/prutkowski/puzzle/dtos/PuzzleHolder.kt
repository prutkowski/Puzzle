package com.prutkowski.puzzle.dtos

data class PuzzleHolder(var holderId: Int, var currImage: PuzzleImage?, val coordinates: MatrixCoordinates)