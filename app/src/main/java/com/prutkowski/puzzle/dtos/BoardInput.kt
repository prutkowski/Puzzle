package com.prutkowski.puzzle.dtos

class BoardInput(val imagesSet: LinkedHashMap<Int, PuzzleHolder>, val dimension: Dimension, val emptyDefaultPosition: MatrixCoordinates)