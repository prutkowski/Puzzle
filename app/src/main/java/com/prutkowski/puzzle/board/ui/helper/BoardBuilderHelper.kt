package com.prutkowski.puzzle.board.ui.helper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.prutkowski.puzzle.R
import com.prutkowski.puzzle.dtos.BoardSets
import com.prutkowski.puzzle.dtos.Dimension

class BoardBuilderHelper(private val context: Context): IBoardBuilderHelper {
    private var boardSetup: BoardSets? = null

    override fun getBoardSetup() = boardSetup

    override fun buildBoard(container: ViewGroup) {
        val boardImages = LinkedHashMap<Int, Int>()
        val imageView = ImageView(context)
        imageView.id = View.generateViewId()
        boardImages[imageView.id] = R.drawable.puzzle1
        val lp = LinearLayout.LayoutParams(context.resources.getDimension(R.dimen.puzzleWidth).toInt(), context.resources.getDimension(R.dimen.puzzleHeight).toInt(), 0F)
        imageView.setImageDrawable(context.getDrawable(R.drawable.puzzle1))
        imageView.layoutParams = lp

        val imageView2 = ImageView(context)
        imageView2.id = View.generateViewId()
        boardImages[imageView2.id] = R.drawable.empty_puzzle
        imageView2.setImageDrawable(context.getDrawable(R.drawable.empty_puzzle))
        imageView2.layoutParams = lp

        val imageView3 = ImageView(context)
        imageView3.id = View.generateViewId()
        boardImages[imageView3.id] = R.drawable.puzzle2
        imageView3.setImageDrawable(context.getDrawable(R.drawable.puzzle2))
        imageView3.layoutParams = lp

        val imageView4 = ImageView(context)
        imageView4.id = View.generateViewId()
        boardImages[imageView4.id] = R.drawable.puzzle3
        imageView4.setImageDrawable(context.getDrawable(R.drawable.puzzle3))
        imageView4.layoutParams = lp

        container.addView(imageView)
        container.addView(imageView2)
        container.addView(imageView3)
        container.addView(imageView4)

        boardSetup = BoardSets(boardImages, Dimension(3, 1))
    }
}