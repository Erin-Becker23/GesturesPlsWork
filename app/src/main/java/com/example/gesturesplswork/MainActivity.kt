package com.example.gesturesplswork

import android.content.Intent
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    var movement: GestureDetectorCompat? = null

    //floats are for left/right swipe
    var x1:Float = 0.0f
    var x2:Float = 0.0f
    var y1:Float = 0.0f
    var y2:Float = 0.0f

    lateinit var gesture: TextView
    lateinit var mika: ImageView

    companion object { //for left/right swipe
        const val MIN_DISTANCE = 150 //always gotta be this for some reason?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gesture = findViewById(R.id.gesture_label)
        mika = findViewById(R.id.mika_pic)


        movement = GestureDetectorCompat(this@MainActivity, this@MainActivity)
        movement?.setOnDoubleTapListener(this@MainActivity)

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean { //this method is not automatically included with others, have to do it maually
        movement?.onTouchEvent(event!!)

        when(event!!.action){ //for left/right swipe
            0-> //when we start to swipe
            {
                x1 = event.x
                y1 = event.y
            }
            1-> //when we end the swipe
            {
                x2 = event.x
                y2 = event.y

                val changeInX:Float = x2-x1
                val changeInY:Float = y2-y1
                if(Math.abs(changeInX) > MIN_DISTANCE){ //this means that there was a greater swipe to the left/right than up/down
                    if(x2>x1){ //x2 is to the right | detects right side swipe
                        Toast.makeText(this, "you swiped right", Toast.LENGTH_LONG).show()
                    }
                    else{ //detects left side swipe
                        Toast.makeText(this, "You swiped left", Toast.LENGTH_LONG).show()
                        Intent(this@MainActivity, Screen2PlsWork::class.java).also{
                            startActivity(it)
                        }
                    }
                }else if(Math.abs(changeInY) > MIN_DISTANCE){
                    if(y2>y1){ //y2 is down | detects bottom side swipe
                        Toast.makeText(this, "you swiped down", Toast.LENGTH_LONG).show()
                    }
                    else{ //detects top side swipe
                        Toast.makeText(this, "You swiped up", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }


        return super.onTouchEvent(event)
    }


    override fun onDown(e: MotionEvent): Boolean {
        gesture.text = "Down"
        return true
    }

    override fun onShowPress(e: MotionEvent) {
        gesture.text = "Press"
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        gesture.text = "Single Tap"
        gesture.setTextColor(Color.parseColor("#800000"))
        return true
    }

    override fun onScroll(e1: MotionEvent,
                          e2: MotionEvent,
                          positionX: Float,
                          positionY: Float): Boolean {
        gesture.text = "Scroll"
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        gesture.text = "Long Press"
        mika.setVisibility(View.VISIBLE)
    }

    override fun onFling(e1: MotionEvent,
                         e2: MotionEvent,
                         velocityX: Float,
                         velocityY: Float): Boolean {
        gesture.text = "Fling"
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        gesture.text = "Single Tap"
        return true
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        gesture.text = "Double Tap"
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent): Boolean {
        gesture.text = "Double Tap"
        return true
    }
}