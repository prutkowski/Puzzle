package com.prutkowski.puzzle

class BoardPresenter(var view: IBoardView?) : IBoardPresenter {
    override fun onDestroy() {
        view = null
    }
}