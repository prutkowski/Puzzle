package com.prutkowski.puzzle

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.prutkowski.puzzle.dtos.MatrixCoordinates
import com.prutkowski.puzzle.dtos.PuzzleHolder

class MainActivity : AppCompatActivity(), IBoardView {

    private lateinit var presenter: IBoardPresenter
    private var puzzleHolderClickListener: View.OnClickListener? = null
    private var imageIds = LinkedHashMap<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBoardViews()

        presenter = BoardPresenter(this, imageIds)
        presenter.configureBoard()
    }

    private fun setupBoardViews() {
        val imageView = ImageView(this)
        imageView.id = View.generateViewId()
        imageIds[imageView.id] = R.drawable.puzzle1
        val lp = LinearLayout.LayoutParams(resources.getDimension(R.dimen.puzzleWidth).toInt(), resources.getDimension(R.dimen.puzzleHeight).toInt(), 0F)
        imageView.setImageDrawable(getDrawable(R.drawable.puzzle1))
        imageView.layoutParams = lp

        val imageView2 = ImageView(this)
        imageView2.id = View.generateViewId()
        imageIds[imageView2.id] = R.drawable.empty_puzzle
        imageView2.setImageDrawable(getDrawable(R.drawable.empty_puzzle))
        imageView2.layoutParams = lp

        val imageView3 = ImageView(this)
        imageView3.id = View.generateViewId()
        imageIds[imageView3.id] = R.drawable.puzzle2
        imageView3.setImageDrawable(getDrawable(R.drawable.puzzle2))
        imageView3.layoutParams = lp

        val imageView4 = ImageView(this)
        imageView4.id = View.generateViewId()
        imageIds[imageView4.id] = R.drawable.puzzle3
        imageView4.setImageDrawable(getDrawable(R.drawable.puzzle3))
        imageView4.layoutParams = lp

        findViewById<LinearLayout>(R.id.container).addView(imageView)
        findViewById<LinearLayout>(R.id.container).addView(imageView2)
        findViewById<LinearLayout>(R.id.container).addView(imageView3)
        findViewById<LinearLayout>(R.id.container).addView(imageView4)
    }

    override fun updateImagesBetweenHolders(fromHolder: PuzzleHolder, toHolder: PuzzleHolder) {
        val fromDrawable = findViewById<ImageView>(fromHolder.holderId).drawable
        val toDrawable = findViewById<ImageView>(toHolder.holderId).drawable
        updateImageInPuzzleHolder(fromHolder, toDrawable)
        updateImageInPuzzleHolder(toHolder, fromDrawable)
    }

    override fun setupPuzzleHoldersClickListeners(puzzles: LinkedHashMap<MatrixCoordinates, PuzzleHolder>) {
        puzzles.forEach {
            findViewById<ImageView>(it.value.holderId).setOnClickListener(getPuzzleHolderClickListener())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun getPuzzleHolderClickListener(): View.OnClickListener {
        if (puzzleHolderClickListener == null) {
            puzzleHolderClickListener = View.OnClickListener { v ->
                presenter.resolvePuzzleHolderOnClick(v.id)
            }
        }
        return puzzleHolderClickListener as View.OnClickListener
    }

    private fun updateImageInPuzzleHolder(puzzleHolder: PuzzleHolder, drawable: Drawable) {
        findViewById<ImageView>(puzzleHolder.holderId).setImageDrawable(drawable)
    }
}
