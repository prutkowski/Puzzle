package com.prutkowski.puzzle.ui.board.helper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.prutkowski.puzzle.R
import com.prutkowski.puzzle.dtos.BoardSets
import com.prutkowski.puzzle.dtos.Dimension
import com.prutkowski.puzzle.utils.ViewUtils

class BoardBuilderHelper(private val context: Context) : IBoardBuilderHelper {
    private var boardSetup: BoardSets? = null

    override fun getBoardSetup() = boardSetup

    override fun buildBoard(container: ViewGroup, dimensions: Dimension) {
        val boardImages = LinkedHashMap<Int, Int>()

        for (row in 0 until dimensions.x) {
            val linearLayout = addRowContainer(row, container)
            for (cell in 0 until dimensions.y) {
                createPuzzleHolder(Dimension(row, cell), dimensions, linearLayout, boardImages)
            }
        }

        boardSetup = BoardSets(boardImages, dimensions)
    }

    private fun addRowContainer(x: Int, container: ViewGroup): LinearLayout {
        val linearLayout = LinearLayout(context)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        if (x > 0)
            layoutParams.topMargin = ViewUtils.dpToPx(1f, context.resources.displayMetrics)
        linearLayout.layoutParams = layoutParams
        linearLayout.orientation = LinearLayout.HORIZONTAL
        container.addView(linearLayout)
        return linearLayout
    }

    private fun createPuzzleHolder(currentPosition: Dimension, boardDimensions: Dimension, container: ViewGroup, boardImages: LinkedHashMap<Int, Int>) {
        val imageView = ImageView(context)
        imageView.id = View.generateViewId()
        boardImages[imageView.id] = getImageResourceId(boardDimensions)
        val lp = LinearLayout.LayoutParams(context.resources.getDimension(R.dimen.puzzleWidth).toInt(), context.resources.getDimension(R.dimen.puzzleHeight).toInt(), 0F)
        //imageView.setImageDrawable(context.getDrawable(getImageResourceId(boardDimensions)))
        imageView.background = context.getDrawable(R.drawable.puzzle_holder_background)
        val margin = ViewUtils.dpToPx(1f, context.resources.displayMetrics)
        if (currentPosition.y != 0)
            lp.leftMargin = margin
        imageView.layoutParams = lp

        container.addView(imageView)
    }

    private fun getImageResourceId(dimension: Dimension): Int {
        return R.drawable.puzzle1
    }
}