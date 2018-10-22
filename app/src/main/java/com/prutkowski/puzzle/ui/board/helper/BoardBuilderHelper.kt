package com.prutkowski.puzzle.ui.board.helper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.prutkowski.puzzle.R
import com.prutkowski.puzzle.dtos.*
import com.prutkowski.puzzle.utils.BoardImageProvider
import com.prutkowski.puzzle.utils.ViewUtils

class BoardBuilderHelper(private val context: Context) : IBoardBuilderHelper {
    private lateinit var boardSetup: BoardInput
    private var emptyDefaultPosition = MatrixCoordinates(1, 1)

    override fun getBoardSetup() = boardSetup

    override fun build(dimension: Dimension): LinearLayout {
        val puzzleHolders = LinkedHashMap<Int, PuzzleHolder>()
        val boardImageProvider = BoardImageProvider(context)
        val puzzleBitmaps = boardImageProvider.getImageList(dimension)
        val container = LinearLayout(context)
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        container.orientation = LinearLayout.VERTICAL

        var counter = 0
        for (row in 0 until dimension.x) {
            val linearLayout = addRowContainer(row, container)
            for (column in 0 until dimension.y) {
                createPuzzleHolder(MatrixCoordinates(row, column), linearLayout, puzzleHolders, puzzleBitmaps[counter])
                counter++
            }
        }

        boardSetup = BoardInput(puzzleHolders, dimension, emptyDefaultPosition)

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

    private fun createPuzzleHolder(currentPosition: MatrixCoordinates, container: ViewGroup, puzzleHolders: LinkedHashMap<Int, PuzzleHolder>, image: PuzzleImage) {
        val imageView = ImageView(context)
        imageView.id = View.generateViewId()
        val lp = LinearLayout.LayoutParams(context.resources.getDimension(R.dimen.puzzleWidth).toInt(), context.resources.getDimension(R.dimen.puzzleHeight).toInt(), 0F)
        if (currentPosition != MatrixCoordinates(emptyDefaultPosition.row, emptyDefaultPosition.column)) {
            imageView.setImageBitmap(image.imageBitmap)
            puzzleHolders[imageView.id] = PuzzleHolder(imageView.id, image, currentPosition)
        } else {
            imageView.setImageDrawable(context.getDrawable(R.drawable.empty_puzzle))
            puzzleHolders[imageView.id] = PuzzleHolder(imageView.id, null, currentPosition)
        }

        imageView.background = context.getDrawable(R.drawable.puzzle_holder_background)
        val margin = ViewUtils.dpToPx(1f, context.resources.displayMetrics)
        if (currentPosition.column != 0)
            lp.leftMargin = margin
        imageView.layoutParams = lp

        container.addView(imageView)
    }

    private fun getImageResourceId(dimension: Dimension): Int {
        return R.drawable.puzzle1
    }
}