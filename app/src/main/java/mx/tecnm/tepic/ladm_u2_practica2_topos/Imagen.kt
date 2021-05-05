package mx.tecnm.tepic.ladm_u2_practica2_topos

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log

open class Imagen (var lienzo: Lienzo, imagen: Int, posX: Float, posY:Float){
    var x = posX
    var y = posY
    var img: Bitmap = BitmapFactory.decodeResource(lienzo.resources, imagen)
    var invisible = false

    fun pintar(c: Canvas){
        if (invisible) return
        c.drawBitmap(img, x, y, Paint())
    }
    fun setImg(imagen: Int){
        img = BitmapFactory.decodeResource(lienzo.resources, imagen)
    }

    fun estaEnArea(toqueX: Float, toqueY: Float):Boolean{
        val x2 = x+img.width
        val y2 = y+img.height
        if (toqueX in x..x2) if (toqueY in y..y2) return true
        return false
    }

    fun setPosition(toqueX: Float, toqueY: Float){
        x=toqueX
        y=toqueY
    }
    fun mover(toqueX: Float, toqueY: Float){
        Log.d("X", x.toString())
        Log.d("Y", y.toString())
        x=toqueX-img.width/2
        y=toqueY-img.height/2

    }
    fun getWidth(): Float {
        return img.width.toFloat()
    }
    fun getHeight(): Float {
        return img.height.toFloat()
    }
    fun collision(objectB:Imagen):Boolean{
        val x2 = x+img.width
        val y2 = y+img.height
        if (objectB.estaEnArea(x,y))return true
        if (objectB.estaEnArea(x2,y))return true
        if (objectB.estaEnArea(x,y2))return true
        if (objectB.estaEnArea(x2,y2))return true
        return false
    }
}