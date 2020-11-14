package com.flamecode.itfest.ui.base.Diagnostics

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.flamecode.itfest.R
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonCancellable.isCancelled
import java.util.concurrent.TimeUnit

class ThirdStadeFragment : Fragment() {

    private lateinit var countDownTimer: CountDownTimer
    private var milisDays:Long = 1000*60*1440*14
    private var sustainingTime: Int = 15
    private lateinit var button_stop:ImageView

    private lateinit var duration: TextView
    lateinit var timeRemaining : String


    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_stade, container, false)
        duration = view.findViewById(R.id.duration_quarantine)
        button_stop = view.findViewById(R.id.stop)



        timer(milisDays, 1000).start()
        var paused  = 1
        button_stop.setOnClickListener {
            if(paused == 1){
                Toast.makeText(context, "Paused", Toast.LENGTH_SHORT).show()
                paused = 0
            } else{
                Toast.makeText(context, "You still have" + timeRemaining + "days", Toast.LENGTH_SHORT).show()
                paused = 0
            }
        }
        return view
    }

    @InternalCoroutinesApi
    private fun timer(millisInFuture:Long, countDownInterval:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                 timeRemaining = timeString(millisUntilFinished).toString()
                if (isCancelled){
                    duration.text = "${duration.text}\nStopped.(Cancelled)"
                    cancel()
                }else{
                    duration.text = (timeRemaining+1).toString()
                    if((timeRemaining+1).toInt() < sustainingTime){
                        //sendNotification
                        val message = "Don't warry, try to feel good and resist" + duration + "days"
                        var builder = NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.logo)
                            .setContentTitle("Save you")
                            .setContentText(message)
                            .setStyle(NotificationCompat.BigTextStyle()
                                .bigText("Much longer text that cannot fit one line..."))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        sustainingTime = (timeRemaining+1).toInt()
                    }
                }
            }

            override fun onFinish() {
                duration.text = "Congrats, now you can "
                button_stop.isEnabled = false
            }
        }
    }

    private fun timeString(millisUntilFinished:Long): Long {
        var millisUntilFinished:Long = millisUntilFinished
        val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
        millisUntilFinished -= TimeUnit.DAYS.toMillis(days)

        return days
    }

}