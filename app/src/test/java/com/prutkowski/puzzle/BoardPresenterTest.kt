package com.prutkowski.puzzle

import com.prutkowski.puzzle.ui.board.BoardPresenter
import com.prutkowski.puzzle.ui.board.IBoardView
import com.prutkowski.puzzle.exceptions.InvalidBoardDimensionsException
import com.prutkowski.puzzle.dtos.BoardInput
import com.prutkowski.puzzle.dtos.Dimension
import com.prutkowski.puzzle.dtos.MatrixCoordinates
import com.prutkowski.puzzle.dtos.PuzzleHolder
import com.prutkowski.puzzle.ui.board.IBoardPresenter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.util.*

class BoardPresenterTest {

    private lateinit var boardView: IBoardView
    private lateinit var emptyDefaultPosition: MatrixCoordinates
    private lateinit var boardPresenter: IBoardPresenter

    @Before
    fun setup() {
        boardView = mock(IBoardView::class.java)
        emptyDefaultPosition = MatrixCoordinates(1, 1)
        boardPresenter = BoardPresenter(boardView)
    }

    @Test(expected = InvalidBoardDimensionsException::class)
    fun Should_ThrowException_When_SetsDoesNotMeetDimensions() {
        boardPresenter.configureBoard(BoardInput(LinkedHashMap(), Dimension(3, 3), emptyDefaultPosition))

        assertEquals(MatrixCoordinates(5, 0), boardPresenter.getNewCoordinates(MatrixCoordinates(6, 0)))
    }

    @Test(expected = InvalidBoardDimensionsException::class)
    fun Should_Throw2Exception_When_SetsDoesNotMeetDimensions() {
        val list = LinkedHashMap<Int, PuzzleHolder>()
        list[1] = PuzzleHolder(1, null, MatrixCoordinates(1, 1))
        val boardPresenter = BoardPresenter(boardView)
        boardPresenter.configureBoard(BoardInput(list, Dimension(3, 3), emptyDefaultPosition))

        assertEquals(MatrixCoordinates(5, 0), boardPresenter.getNewCoordinates(MatrixCoordinates(6, 0)))
    }

    @Test
    fun IsValidBoardSetsWhenMatrixIsFilled() {
        val list = LinkedHashMap<Int, PuzzleHolder>()
        list[1] = PuzzleHolder(100, null, MatrixCoordinates(0, 0))
        list[2] = PuzzleHolder(101, null, MatrixCoordinates(0, 1))
        list[3] = PuzzleHolder(102, null, MatrixCoordinates(0, 2))
        list[4] = PuzzleHolder(103, null, MatrixCoordinates(1, 0))
        list[5] = PuzzleHolder(104, null, MatrixCoordinates(1, 1))
        list[6] = PuzzleHolder(105, null, MatrixCoordinates(1, 2))
        list[7] = PuzzleHolder(106, null, MatrixCoordinates(2, 0))
        list[8] = PuzzleHolder(107, null, MatrixCoordinates(2, 1))
        list[9] = PuzzleHolder(108, null, MatrixCoordinates(2, 2))
        val boardPresenter = BoardPresenter(boardView)
        boardPresenter.configureBoard(BoardInput(list, Dimension(3, 3), emptyDefaultPosition))

        assertEquals(9, boardPresenter.boardMatrix.puzzleMatrix.size)
        assertEquals(9, boardPresenter.boardMatrix.puzzleIdsHashMap.size)
    }

    @Test
    fun newCoordinatesTest() {
        val list = LinkedHashMap<Int, PuzzleHolder>()
        list[1] = PuzzleHolder(100, null, MatrixCoordinates(0, 0))
        list[2] = PuzzleHolder(101, null, MatrixCoordinates(0, 1))
        list[3] = PuzzleHolder(102, null, MatrixCoordinates(0, 2))
        list[4] = PuzzleHolder(103, null, MatrixCoordinates(1, 0))
        list[5] = PuzzleHolder(104, null, MatrixCoordinates(1, 1))
        list[6] = PuzzleHolder(105, null, MatrixCoordinates(1, 2))
        list[7] = PuzzleHolder(106, null, MatrixCoordinates(2, 0))
        list[8] = PuzzleHolder(107, null, MatrixCoordinates(2, 1))
        list[9] = PuzzleHolder(108, null, MatrixCoordinates(2, 2))
        val boardPresenter = BoardPresenter(boardView)
        boardPresenter.configureBoard(BoardInput(list, Dimension(3, 3), emptyDefaultPosition))

        assertEquals(MatrixCoordinates(1, 1), boardPresenter.getNewCoordinates(MatrixCoordinates(1, 0)))

    }
}