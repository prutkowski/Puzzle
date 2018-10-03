package com.prutkowski.puzzle.dtos

class BoardMatrix(var puzzleMatrix: LinkedHashMap<MatrixCoordinates, PuzzleHolder>,
                  var puzzleIdsHashMap: LinkedHashMap<Int, MatrixCoordinates> = LinkedHashMap()) {

    constructor() : this(LinkedHashMap(), LinkedHashMap())

    fun addPuzzleBoard(column: Int, holderId: Int, currentDrawableId: Int) {
        val currentCoords = MatrixCoordinates(column, 0)
        puzzleMatrix[currentCoords] = PuzzleHolder(holderId, currentDrawableId)
        puzzleIdsHashMap[holderId] = currentCoords
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