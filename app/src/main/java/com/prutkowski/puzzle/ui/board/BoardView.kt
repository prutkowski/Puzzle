package com.prutkowski.puzzle.ui.board

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.prutkowski.puzzle.R
import com.prutkowski.puzzle.dtos.Dimension
import com.prutkowski.puzzle.ui.board.helper.BoardBuilderHelper
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
            createBoard()

            puzzleMoveListener = PuzzleMoveListener(this)

            presenter = BoardPresenter(this)
            presenter.configureBoard(boardBuilderHelper.getBoardSetup())
        }

        return rootView
    }

    private fun createBoard() {
        boardBuilderHelper = BoardBuilderHelper(activity!!.applicationContext)
        val board = boardBuilderHelper.build(Dimension(3, 3))
        (rootView.findViewById(R.id.container) as LinearLayout).addView(board)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun setupPuzzleHoldersClickListeners(puzzles: LinkedHashMap<MatrixCoordinates, PuzzleHolder>) {
        puzzles.forEach {
            rootView.findViewById<ImageView>(it.value.holderId)?.setOnClickListener(puzzleMoveListener)
        }
    }

    override fun onMovePuzzle(view: View) {
        presenter.resolvePuzzleHolderOnClick(view.id)
    }

    override fun switchImages(fromHolder: PuzzleHolder, toHolder: PuzzleHolder) {
        val fromImageView = rootView.findViewById<ImageView>(fromHolder.holderId)
        val toImageView = rootView.findViewById<ImageView>(toHolder.holderId)

        fromImageView.setImageBitmap(null)
        toImageView.setImageDrawable(null)


        fromImageView.setImageDrawable(context?.getDrawable(R.drawable.empty_puzzle))
        toImageView.setImageBitmap(toHolder.currImage?.imageBitmap)

    }

    private fun switchImage(puzzleHolder: PuzzleHolder, drawable: Drawable) {
        rootView.findViewById<ImageView>(puzzleHolder.holderId).setImageDrawable(drawable)
    }
}
