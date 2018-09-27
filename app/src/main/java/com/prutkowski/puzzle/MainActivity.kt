package com.prutkowski.puzzle

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.prutkowski.puzzle.dtos.PuzzleHolder

class MainActivity : AppCompatActivity(), IBoardView {

    lateinit var presenter: IBoardPresenter
    var puzzleClickListener: View.OnClickListener? = null
    var puzzles = ArrayList<PuzzleHolder>()
    var puzzleIdsHashMap: LinkedHashMap<Int, Int> = LinkedHashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = BoardPresenter(this)

        setContentView(R.layout.activity_main)

        //Model?
        setupPuzzles()

        setupPuzzleClickedListeners()
    }

    private fun setupPuzzles() {
        puzzles.add(PuzzleHolder(0, R.id.image1, getDrawable(R.drawable.puzzle1)))
        puzzleIdsHashMap[R.id.image1] = 0

        puzzles.add(PuzzleHolder(1, R.id.image2, getDrawable(R.drawable.puzzle2)))
        puzzleIdsHashMap[R.id.image2] = 1

        puzzles.add(PuzzleHolder(2, R.id.image3, getDrawable(R.drawable.puzzle1)))
        puzzleIdsHashMap[R.id.image3] = 2
    }

    override fun setupPuzzleClickedListeners() {
        puzzles.forEach {
            findViewById<ImageView>(it.id).setOnClickListener(getPuzzleClickedListener())
        }
    }

    override fun getPuzzleClickedListener(): View.OnClickListener {
        if (puzzleClickListener == null) {
            puzzleClickListener = View.OnClickListener { v ->
                resolvePuzzleImageChange(v.id)
            }
        }
        return puzzleClickListener as View.OnClickListener
    }

    private fun resolvePuzzleImageChange(id: Int) {
        val clickedPosition = puzzleIdsHashMap[id]

        if (clickedPosition != null) {
            val clickedPuzzleHolder = puzzles[clickedPosition]
            var newPosition = clickedPosition
            if (clickedPosition < puzzles.size - 1) {
                newPosition++
            } else
                newPosition--

            val newPuzzleHolder = puzzles[newPosition]

            val switchedDrawable = clickedPuzzleHolder.image
            clickedPuzzleHolder.image = newPuzzleHolder.image
            newPuzzleHolder.image = switchedDrawable
            updateImageInPuzzleHolder(clickedPuzzleHolder)

            switchImagesBetweenPuzzleHolders(clickedPuzzleHolder, newPuzzleHolder)
        }
    }

    private fun switchImagesBetweenPuzzleHolders(clickedPuzzleHolder: PuzzleHolder, newPuzzleHolder: PuzzleHolder) {
        updateImageInPuzzleHolder(clickedPuzzleHolder)
        updateImageInPuzzleHolder(newPuzzleHolder)
    }

    private fun updateImageInPuzzleHolder(puzzleHolder: PuzzleHolder) {
        findViewById<ImageView>(puzzleHolder.id).setImageDrawable(puzzleHolder.image)
    }
}
