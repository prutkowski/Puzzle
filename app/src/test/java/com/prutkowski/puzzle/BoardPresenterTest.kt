package com.prutkowski.puzzle

import com.prutkowski.puzzle.board.BoardPresenter
import com.prutkowski.puzzle.board.ui.IBoardView
import com.prutkowski.puzzle.exceptions.InvalidBoardDimensionsException
import com.prutkowski.puzzle.dtos.BoardSets
import com.prutkowski.puzzle.dtos.Dimension
import com.prutkowski.puzzle.dtos.MatrixCoordinates
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.util.*

class BoardPresenterTest {

    lateinit var boardView: IBoardView

    @Before
    fun setup() {
        boardView = mock(IBoardView::class.java)
    }

    @Test(expected = InvalidBoardDimensionsException::class)
    fun Should_ThrowException_When_SetsDoesNotMeetDimensions() {
        val list = LinkedHashMap<Int, Int>()
        list[1] = 100
        val boardPresenter = BoardPresenter(boardView)
        boardPresenter.configureBoard(BoardSets(list, Dimension(3, 1)))

        assertEquals(MatrixCoordinates(5, 0), boardPresenter.getNewCoordinates(MatrixCoordinates(6, 0)))

    }

    @Test
    fun IsValidBoardSetsWhenMatrixIsFilled() {
        val list = LinkedHashMap<Int, Int>()
        list[1] = 100
        list[2] = 101
        list[3] = 102
        val boardPresenter = BoardPresenter(boardView)
        boardPresenter.configureBoard(BoardSets(list, Dimension(3, 1)))

        assertEquals(3, boardPresenter.boardMatrix.puzzleMatrix.size)
        assertEquals(3, boardPresenter.boardMatrix.puzzleIdsHashMap.size)
    }

    @Test
    fun newCoordinatesTest() {
        val list = LinkedHashMap<Int, Int>()
        list[1] = 100
        list[2] = 101
        list[3] = 102
        val boardPresenter = BoardPresenter(boardView)
        boardPresenter.configureBoard(BoardSets(list, Dimension(3, 1)))

        assertEquals(MatrixCoordinates(5, 0), boardPresenter.getNewCoordinates(MatrixCoordinates(6, 0)))

    }
}