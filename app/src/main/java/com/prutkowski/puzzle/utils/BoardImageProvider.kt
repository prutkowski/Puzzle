package com.prutkowski.puzzle.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.prutkowski.puzzle.dtos.Dimension
import com.prutkowski.puzzle.dtos.PuzzleImage
import com.prutkowski.puzzle.exceptions.ImageProviderException
import java.io.IOException
import java.io.InputStream


class BoardImageProvider(private val context: Context) : IBoardImageProvider {
    override fun getImageList(dimension: Dimension): ArrayList<PuzzleImage> {
        return ArrayList((0 until dimension.size()).shuffled().map {
            PuzzleImage(getImageBitmap(it), it)
        })
    }

    private fun getImageBitmap(index: Int): Bitmap? {
        val assetManager = context.assets

        val input: InputStream
        val bitmap: Bitmap?
        try {
            input = assetManager.open("defined/dimen_3x3/1_$index.png")
            bitmap = BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            throw ImageProviderException()
        }

        return bitmap
    }
}