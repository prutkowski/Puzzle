package com.prutkowski.puzzle.board.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.prutkowski.puzzle.R
import com.prutkowski.puzzle.board.BoardPresenter
import com.prutkowski.puzzle.board.IBoardPresenter
import com.prutkowski.puzzle.board.IPuzzleMoveListener
import com.prutkowski.puzzle.board.PuzzleMoveListener
import com.prutkowski.puzzle.board.ui.helper.BoardBuilderHelper
import com.prutkowski.puzzle.dtos.MatrixCoordinates
import com.prutkowski.puzzle.dtos.PuzzleHolder

class BoardView : Fragment(), IBoardView {

    private lateinit var rootView: View
    private lateinit var presenter: IBoardPresenter
    private lateinit var boardBuilderHelper: BoardBuilderHelper
    private lateinit var puzzleMoveListener: IPuzzleMoveListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.board_view_fragment, container, false)

        if (activity != null) {
            boardBuilderHelper = BoardBuilderHelper(activity!!.applicationContext)
            boardBuilderHelper.buildBoard(rootView.findViewById(R.id.container))
            puzzleMoveListener = PuzzleMoveListener(this)

            presenter = BoardPresenter(this)
            presenter.configureBoard(boardBuilderHelper.getBoardSetup())
        }

        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun setupPuzzleHoldersClickListeners(puzzles: LinkedHashMap<MatrixCoordinates, PuzzleHolder>) {
        puzzles.forEach {
            rootView.findViewById<ImageView>(it.value.holderId).setOnClickListener(puzzleMoveListener)
        }
    }

    override fun onMovePuzzle(view: View) {
        presenter.resolvePuzzleHolderOnClick(view.id)
    }

    override fun switchImages(fromHolder: PuzzleHolder, toHolder: PuzzleHolder) {
        val fromDrawable = rootView.findViewById<ImageView>(fromHolder.holderId).drawable
        val toDrawable = rootView.findViewById<ImageView>(toHolder.holderId).drawable
        switchImage(fromHolder, toDrawable)
        switchImage(toHolder, fromDrawable)
    }

    private fun switchImage(puzzleHolder: PuzzleHolder, drawable: Drawable) {
        rootView.findViewById<ImageView>(puzzleHolder.holderId).setImageDrawable(drawable)
    }
}
