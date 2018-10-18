package com.prutkowski.puzzle.ui.board.helper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.prutkowski.puzzle.R
import com.prutkowski.puzzle.dtos.BoardSets
import com.prutkowski.puzzle.dtos.Dimension
import com.prutkowski.puzzle.dtos.PuzzleImage
import com.prutkowski.puzzle.utils.BoardImageProvider
import com.prutkowski.puzzle.utils.ViewUtils

class BoardBuilderHelper(private val context: Context) : IBoardBuilderHelper {
    private var boardSetup: BoardSets? = null

    override fun getBoardSetup() = boardSetup

    override fun build(dimension: Dimension): LinearLayout {
        val boardViewImages = LinkedHashMap<Int, Int>()
        val boardImageProvider = BoardImageProvider(context)
        val puzzleBitmaps = boardImageProvider.getImageList(dimension)
        val container = LinearLayout(context)
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        container.orientation = LinearLayout.VERTICAL

        var counter = 0
        for (row in 0 until dimension.x) {
            val linearLayout = addRowContainer(row, container)
            for (cell in 0 until dimension.y) {
                createPuzzleHolder(Dimension(row, cell), dimension, linearLayout, boardViewImages, puzzleBitmaps[counter])
                counter++
            }
        }

        boardSetup = BoardSets(boardViewImages, dimension)

        return container
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

    private fun createPuzzleHolder(currentPosition: Dimension, boardDimensions: Dimension, container: ViewGroup, boardImages: LinkedHashMap<Int, Int>, image: PuzzleImage) {
        val imageView = ImageView(context)
        imageView.id = View.generateViewId()
        val lp = LinearLayout.LayoutParams(context.resources.getDimension(R.dimen.puzzleWidth).toInt(), context.resources.getDimension(R.dimen.puzzleHeight).toInt(), 0F)
        if (currentPosition != Dimension(1, 1)) {
            imageView.setImageBitmap(image.imageBitmap)
            boardImages[imageView.id] = image.ImageId
        } else {
            imageView.setImageDrawable(context.getDrawable(R.drawable.empty_puzzle))
            boardImages[imageView.id] = R.drawable.empty_puzzle
        }

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