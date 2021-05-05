package mx.tecnm.tepic.ladm_u2_practica2_topos

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat.startActivity


class Lienzo(p: MainActivity2) : View(p) {
    val backgroud = Imagen(this, R.drawable.background,0F ,0F)
    val startgame = Imagen(this,R.drawable.start_button,250F,1000F)
    val levelbox = Imagen(this,R.drawable.level_box,10F,10F)
    val score = Imagen(this,R.drawable.score,levelbox.getWidth()+20,20F)
    val lives = Imagen(this,R.drawable.live_bar1,levelbox.getWidth()+score.getWidth()+200,30F)
    var lvlfinish = Imagen(this,R.drawable.lvl_sprite0,125F,300F)
    var finish = Imagen(this,R.drawable.finish,-10F,1000F)
    val play = Imagen(this,R.drawable.play,600F,1400F)
    val lock = Imagen(this,R.drawable.lock,600F,1400F)
    val reset = Imagen(this,R.drawable.reset,280F,1400F)
    val topo_1 = Topo(this,topo_states, 90F,600F)
    val topo_2 = Topo(this,topo_states, 650F,600F)
    val topo_3 = Topo(this,topo_states, 400F,200F)
    val topo_4 = Topo(this,topo_states, 400F,200F)
    val topo_5 = Topo(this,topo_states, 400F,200F)
    val topo_6 = Topo(this,topo_states, 400F,200F)
    val topo_7 = Topo(this,topo_states, 400F,200F)
    var vidas = 2
    var topos_totales = 3
    var flag=false
    var actuallvl=0
    //x:80 y:350 Texto LVL
    //x:380 y:300 Texto Score
    //x:865 y:300 Texto Score
    //


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val p = Paint()
        //Invisible when game start
        if (actuallvl!=0 && topo_strikes==0) flag=true


        levelfinish(flag)
        backgroud.pintar(canvas)
        this.invalidate()
        startgame.pintar(canvas)
        levelbox.pintar(canvas)
        score.pintar(canvas)
        lives.pintar(canvas)
        lvlfinish.pintar(canvas)
        finish.pintar(canvas)
        play.pintar(canvas)
        lock.pintar(canvas)
        reset.pintar(canvas)
        p.color = Color.WHITE
        p.textSize = 250f
        if (flag)canvas.drawText("$actuallvl",470F,750F,p)
        p.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        p.color = Color.WHITE
        p.textSize = 100f
        canvas.drawText("$actuallvl",85F,170F,p)

        p.color = Color.BLACK
        p.textSize = 80f
        canvas.drawText("${topos_alive}/${topos_totales}",380F,120F,p)
        canvas.drawText("${topo_strikes}/${vidas}",860F,120F,p)
        if (!flag){
            topo_1.pintar(canvas)
            topo_2.pintar(canvas)
            topo_3.pintar(canvas)
            topo_4.pintar(canvas)
            topo_5.pintar(canvas)
            topo_6.pintar(canvas)
            topo_7.pintar(canvas)
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (topo_1.estaEnArea(event.x, event.y) && topo_1.i == 3) topo_1.splashed()
                if (topo_2.estaEnArea(event.x, event.y) && topo_2.i == 3) topo_2.splashed()
                if (topo_3.estaEnArea(event.x, event.y) && topo_3.i == 3) topo_3.splashed()
                if (topo_4.estaEnArea(event.x, event.y) && topo_4.i == 3) topo_4.splashed()
                if (topo_5.estaEnArea(event.x, event.y) && topo_5.i == 3) topo_5.splashed()
                if (topo_6.estaEnArea(event.x, event.y) && topo_6.i == 3) topo_6.splashed()
                if (topo_7.estaEnArea(event.x, event.y) && topo_7.i == 3) topo_7.splashed()
                if (startgame.estaEnArea(event.x, event.y) && actuallvl == 0) {
                    flag = false
                    topo_strikes = 2
                    startgame.invisible = true
                    actuallvl = 1
                    levels(1)

                }
                if (play.estaEnArea(event.x, event.y) && flag && topo_strikes > 0) {
                    if (actuallvl == 5) {
                        flag = false
                        topo_strikes = 2
                        vidas=2
                        actuallvl = 0
                        startgame.invisible = false
                        levels(actuallvl)
                    } else {
                        flag = false
                        actuallvl++
                        Log.d("Nivel", actuallvl.toString())
                        levels(actuallvl)
                    }
                }
                if (reset.estaEnArea(event.x, event.y) && flag) {
                    flag = false
                    topo_strikes = 2 * actuallvl
                    topos_alive = topos_totales
                    levels(actuallvl)
                }
            }
            MotionEvent.ACTION_UP -> {
                if(topos_alive==0&&actuallvl!=0)flag=true
            }
        }
        invalidate()
        return true
    }
    fun levelfinish(show:Boolean){
        if (show){

            if (topo_strikes < vidas/2)lvlfinish.setImg(R.drawable.lvl_sprite0)
            if (topo_strikes > vidas/2)lvlfinish.setImg(R.drawable.lvl_sprite1)
            if (topo_strikes >= vidas/2)lvlfinish.setImg(R.drawable.lvl_sprite2)
            if (topo_strikes == vidas)lvlfinish.setImg(R.drawable.lvl_sprite3)
            if (topo_strikes == 0)lvlfinish.setImg(R.drawable.lvl_sprite_fail)

            lvlfinish.invisible=false
            play.invisible=topo_strikes <= 0
            lock.invisible=topo_strikes > 0
            reset.invisible=false
            if(actuallvl==5&&topo_strikes != 0)finish.invisible=false
        }else{
            lvlfinish.invisible=true
            play.invisible=true
            lock.invisible=true
            reset.invisible=true
            finish.invisible=true
        }
    }
    fun levels (level: Int){
        topo_1.invisible=true
        topo_2.invisible=true
        topo_3.invisible=true
        topo_4.invisible=true
        topo_5.invisible=true
        topo_6.invisible=true
        topo_7.invisible=true
        topo_1.splash=false
        topo_2.splash=false
        topo_3.splash=false
        topo_4.splash=false
        topo_5.splash=false
        topo_6.splash=false
        topo_7.splash=false
        lives.setImg(R.drawable.live_bar1)
        when(level){
            1->{
                topos_totales = 3
                topos_alive = 3
                vidas=2
                topo_1.invisible=false
                topo_1.setPosition(90F,600F)
                topo_1.animation(actuallvl)
                topo_2.invisible=false
                topo_2.setPosition(650F,600F)
                topo_2.animation(actuallvl)
                topo_3.invisible=false
                topo_3.setPosition(360F,930F)
                topo_3.animation(actuallvl)
            }
            2->{
                topos_totales = 4
                topos_alive = 4
                vidas=2*actuallvl
                topo_1.invisible=false
                topo_1.setPosition(90F,600F)
                topo_1.animation(actuallvl)
                topo_2.invisible=false
                topo_2.setPosition(650F,600F)
                topo_2.animation(actuallvl)
                topo_3.invisible=false
                topo_3.setPosition(360F,930F)
                topo_3.animation(actuallvl)
                topo_4.setPosition(400F,200F)
                topo_4.invisible=false
                topo_4.animation(actuallvl)
            }
            3->{
                topos_totales = 5
                topos_alive = 5
                vidas=2*actuallvl
                topo_1.invisible=false
                topo_1.setPosition(180F,200F)
                topo_1.animation(actuallvl)
                topo_2.invisible=false
                topo_2.setPosition(550F,400F)
                topo_2.animation(actuallvl)
                topo_3.invisible=false
                topo_3.setPosition(200F,930F)
                topo_3.animation(actuallvl)
                topo_4.invisible=false
                topo_4.setPosition(700F,1200F)
                topo_4.animation(actuallvl)
                topo_5.invisible=false
                topo_5.setPosition(300F,1400F)
                topo_5.animation(actuallvl)
            }
            4->{
                topos_totales = 6
                topos_alive = 6
                vidas=2*actuallvl
                topo_1.invisible=false
                topo_1.setPosition((30..800).random().toFloat(),200F)
                topo_1.animation(actuallvl)
                topo_2.invisible=false
                topo_2.setPosition((30..800).random().toFloat(),400F)
                topo_2.animation(actuallvl)
                topo_3.invisible=false
                topo_3.setPosition((30..800).random().toFloat(),600F)
                topo_3.animation(actuallvl)
                topo_4.invisible=false
                topo_4.setPosition((30..800).random().toFloat(),800F)
                topo_4.animation(actuallvl)
                topo_5.invisible=false
                topo_5.setPosition((30..800).random().toFloat(),1000F)
                topo_5.animation(actuallvl)
                topo_6.invisible=false
                topo_6.setPosition((30..800).random().toFloat(),1200F)
                topo_6.animation(actuallvl)
            }
            5->{
                lives.setImg(R.drawable.live_bar)
                topos_totales = 7
                topos_alive = 7
                vidas=2*actuallvl
                topo_1.invisible=false
                topo_1.setPosition((30..800).random().toFloat(),650F)
                topo_1.animation(actuallvl)
                topo_2.invisible=false
                topo_2.setPosition((30..800).random().toFloat(),450F)
                topo_2.animation(actuallvl)
                topo_3.invisible=false
                topo_3.setPosition((400..800).random().toFloat(),860F)
                topo_3.animation(actuallvl)
                topo_4.invisible=false
                topo_4.setPosition((30..200).random().toFloat(),1100F)
                topo_4.animation(actuallvl)
                topo_5.invisible=false
                topo_5.setPosition((400..800).random().toFloat(),1200F)
                topo_5.animation(actuallvl)
                topo_6.invisible=false
                topo_6.setPosition((30..800).random().toFloat(),1400F)
                topo_6.animation(actuallvl)
                topo_7.invisible=false
                topo_7.setPosition((30..800).random().toFloat(),200F)
                topo_7.animation(actuallvl)
            }
        }
    }
}