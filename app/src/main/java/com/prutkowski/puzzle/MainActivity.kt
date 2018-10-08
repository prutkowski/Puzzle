package com.prutkowski.puzzle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.prutkowski.puzzle.board.ui.BoardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val boardFragment = BoardView()

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, boardFragment)
                .commit()
    }
}
