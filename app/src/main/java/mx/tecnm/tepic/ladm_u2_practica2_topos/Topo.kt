package mx.tecnm.tepic.ladm_u2_practica2_topos

import android.graphics.BitmapFactory
import android.graphics.Canvas
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.FieldPosition

var topo_strikes = 0
var topos_alive = 0
var init=true
var topo_states= arrayOf(R.drawable.sprite_0,R.drawable.sprite_1,R.drawable.sprite_2,R.drawable.sprite_3)
var topo_hitstates= arrayOf(R.drawable.sprite_hit_0,R.drawable.sprite_hit_1,R.drawable.sprite_hit_2)


class Topo(lienzo:Lienzo, img:Array<Int>, posX:Float, posY:Float): Imagen(lienzo,img.get(0),posX,posY){
    var i:Int=0
    var up=true
    var l = lienzo
    var splash = false
    fun animation(level: Int){
        if(level!=0) {
            topo_strikes = 2 * level
            i = 0
            pintar()
            splash = false
        }
        GlobalScope.launch {
            while (vidas()&&toposAlive()){
                if (!splash) {
                    if (up && i < 2) {
                        delay(getRandom(1000- (150 * level), 7000 - (1000 * level)))
                    } else {
                        if (i > 2) delay(5800 - (1000 * level).toLong())
                        delay(100)
                    }
                    pintar()
                }
                else{
                    if (i>2) i=2
                    pintarhit()
                    delay(100)
                }
            }
        }
    }

    private  fun getRandom(min:Int,max:Int): Long{
        return (min..max).random().toLong()
    }
    private fun pintar(){
        if (i == 0) up = true
        if (i == 3)up = false
        if (i==2&&!up)topo_strikes--
        if (topo_strikes<0)topo_strikes=0
        if (up) i++ else --i
        img = BitmapFactory.decodeResource(l.resources, topo_states.get(i))
    }
    private fun pintarhit(){
        if (i == 0) up = true
        if (i == 2) up = false
        if (up) i++ else i--
        img = BitmapFactory.decodeResource(l.resources, topo_hitstates.get(i))
    }
    private fun vidas():Boolean{
        return topo_strikes>0
    }
    fun splashed(){
        if (!splash){
            splash = true
            topos_alive--
            animation(0)
        }
    }
    private fun toposAlive():Boolean{
        return topos_alive!=0
    }
}