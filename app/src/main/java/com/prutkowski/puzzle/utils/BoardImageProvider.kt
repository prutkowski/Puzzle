package com.prutkowski.puzzle.utils

import android.content.Context
import com.prutkowski.puzzle.dtos.Dimension
import com.prutkowski.puzzle.dtos.PuzzleImage
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.prutkowski.puzzle.exceptions.ImageProviderException
import java.io.IOException
import java.io.InputStream


class BoardImageProvider(private val context: Context): IBoardImageProvider {
    override fun getImageList(dimension: Dimension): ArrayList<PuzzleImage> {
        val images = ArrayList<PuzzleImage>()
        for (index in 0 until dimension.size())
            images.add(PuzzleImage(getImageBitmap(index), index))

        return images
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