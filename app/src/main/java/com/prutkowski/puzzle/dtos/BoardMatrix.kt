package com.prutkowski.puzzle.dtos

class BoardMatrix(var puzzleMatrix: LinkedHashMap<MatrixCoordinates, PuzzleHolder>,
                  var puzzleIdsHashMap: LinkedHashMap<Int, MatrixCoordinates> = LinkedHashMap(), val dimension: Dimension) {

    constructor(dimension: Dimension) : this(LinkedHashMap(), LinkedHashMap(), dimension)

    fun addPuzzleHolder(holderId: Int, puzzleHolder: PuzzleHolder) {
        puzzleMatrix[puzzleHolder.coordinates] = puzzleHolder
        puzzleIdsHashMap[holderId] = puzzleHolder.coordinates
    }

    fun getPuzzleHolder(coordinates: MatrixCoordinates): PuzzleHolder? {
        return puzzleMatrix[coordinates]
    }

    fun getPuzzleHolder(holderId: Int): PuzzleHolder? {
        return puzzleMatrix[puzzleIdsHashMap[holderId]]
    }

    fun getCoordinates(puzzleHolder: PuzzleHolder): MatrixCoordinates? {
        return puzzleIdsHashMap[puzzleHolder.holderId]
    }
}