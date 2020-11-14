package com.flamecode.itfest.ui.base.Diagnostics

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.flamecode.itfest.R
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonCancellable.isCancelled
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer

class ThirdStadeFragment : Fragment() {

    private lateinit var countDownTimer: CountDownTimer
    private var milisDays:Long = 1000*60*1440*14
    private var sustainingTime: Int = 15

    private lateinit var duration: TextView


    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_stade, container, false)
        duration = view.findViewById(R.id.duration_quarantine)
        timer(milisDays, 1000).start()


        return view
    }

    @InternalCoroutinesApi
    private fun timer(millisInFuture:Long, countDownInterval:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                val timeRemaining = timeString(millisUntilFinished)
                if (isCancelled){
                    duration.text = "${duration.text}\nStopped.(Cancelled)"
                    cancel()
                }else{
                    duration.text = (timeRemaining+1).toString()
                    if((timeRemaining+1).toInt() < sustainingTime){
                        //sendNotification
                        sustainingTime = (timeRemaining+1).toInt()
                    }
                }
            }

            override fun onFinish() {
                duration.text = "Congrats, now you can "

                //button_start.isEnabled = true
                //button_stop.isEnabled = false
            }
        }
    }

    private fun timeString(millisUntilFinished:Long):Long{
        var millisUntilFinished:Long = millisUntilFinished
        val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
        millisUntilFinished -= TimeUnit.DAYS.toMillis(days)

        return days
    }

}