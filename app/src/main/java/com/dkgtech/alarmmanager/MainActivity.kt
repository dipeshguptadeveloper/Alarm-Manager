package com.dkgtech.alarmmanager

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import com.dkgtech.alarmmanager.databinding.ActivityMainBinding
import java.time.Month
import java.time.Year
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {

/*            btnSetAlarm.setOnClickListener {

                val time = System.currentTimeMillis() + 5000

                val iReceiver = Intent(this@MainActivity, MyAlarmReceiver::class.java)

                val pi =
                    PendingIntent.getBroadcast(
                        this@MainActivity,
                        100,
                        iReceiver,
                        PendingIntent.FLAG_IMMUTABLE
                    )

                val alarmManger = getSystemService(ALARM_SERVICE) as AlarmManager
                alarmManger.set(AlarmManager.RTC_WAKEUP, time, pi)

            }*/


            btnSetAlarm.setOnClickListener {

                val calendar = Calendar.getInstance()

                val defaultYear = calendar.get(Calendar.YEAR)
                val defaultMonth = calendar.get(Calendar.MONTH)
                val defaultDate = calendar.get(Calendar.DAY_OF_MONTH)
                val defaultHour = calendar.get(Calendar.HOUR_OF_DAY)
                val defaultMinute = calendar.get(Calendar.MINUTE)

                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

                DatePickerDialog(this@MainActivity, object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker?,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int
                    ) {
                        Log.d("Date Set", "$dayOfMonth/${month + 1}/$year")

                        TimePickerDialog(
                            this@MainActivity,
                            object : TimePickerDialog.OnTimeSetListener {
                                override fun onTimeSet(
                                    view: TimePicker?,
                                    hourOfDay: Int,
                                    minute: Int
                                ) {
                                    Log.d("Date Time Set", "$hourOfDay:$minute")

                                    val setCalendar = Calendar.getInstance()
                                    setCalendar.set(Calendar.YEAR, year)
                                    setCalendar.set(Calendar.MONTH, month)
                                    setCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                                    setCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                    setCalendar.set(Calendar.MINUTE, minute)

                                    val time = setCalendar.timeInMillis


                                    val iReceiver =
                                        Intent(this@MainActivity, MyAlarmReceiver::class.java)

                                    val pi =
                                        PendingIntent.getBroadcast(
                                            this@MainActivity,
                                            100,
                                            iReceiver,
                                            PendingIntent.FLAG_IMMUTABLE
                                        )
                                    alarmManager.set(AlarmManager.RTC_WAKEUP, time, pi)
                                }

                            },
                            defaultHour,
                            defaultMinute,
                            false
                        ).show()

                    }

                }, defaultYear, defaultMonth, defaultDate).show()

            }

        }

    }
}