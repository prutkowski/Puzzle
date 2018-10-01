package com.prutkowski.puzzle;

import com.prutkowski.puzzle.dtos.MatrixCoordinates;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BoardPresenterTest {

    IBoardView boardView;

    @Before
    public void setup() {
        boardView = mock(IBoardView.class);
    }

    @Test
    public void newCoordinatesTest() {
        LinkedHashMap list = new LinkedHashMap();
        list.put(1, 100);
        list.put(2, 101);
        list.put(3, 102);
        BoardPresenter boardPresenter = new BoardPresenter(boardView, list);
        boardPresenter.configureBoard();

        assertEquals(new MatrixCoordinates(5, 0), boardPresenter.getNewCoordinates(new MatrixCoordinates(6, 0)));

    }
}