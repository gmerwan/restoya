package dz.esi.restoya.home.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import dz.esi.restoya.R
import dz.esi.restoya.home.adapters.ButtonAdapter
import java.util.*


class ReservationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private val numberItems: ArrayList<String> = ArrayList()
    private val timeItems: ArrayList<String> = ArrayList()

    private var dateButton: Button? = null
    private var timeButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        dateButton = this.findViewById(R.id.date_button)
        timeButton = this.findViewById(R.id.time_button)

        initButtonsDialogs()
        initNumberItems()
        initTimeItems()
        initRecyclerViews()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val newDate = Calendar.getInstance()
        newDate.set(year, month, dayOfMonth)
        val dateFormat = android.text.format.DateFormat.getDateFormat(applicationContext)
        dateButton?.text = dateFormat.format(newDate.time)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val newDate = Calendar.getInstance()
        newDate.set(newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH),
                newDate.get(Calendar.DAY_OF_MONTH), hourOfDay, minute)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)
        timeButton?.text = timeFormat.format(newDate.time)
    }

    private fun initButtonsDialogs() {
        dateButton?.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this, this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.setTitle("Select Date")
            datePickerDialog.show()
        }

        timeButton?.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(this, this,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), true)
            timePickerDialog.setTitle("Select Time")
            timePickerDialog.show()
        }
    }

    private fun initRecyclerViews() {
        val numberRecyclerView = findViewById<RecyclerView>(R.id.number_recyclerView)
        val timeRecyclerView = findViewById<RecyclerView>(R.id.time_recyclerView)
        val numberLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val timeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        numberRecyclerView.layoutManager = numberLayoutManager
        timeRecyclerView.layoutManager = timeLayoutManager
        numberRecyclerView.adapter = ButtonAdapter(this, numberItems)
        timeRecyclerView.adapter = ButtonAdapter(this, timeItems)
    }

    private fun initNumberItems() {
        numberItems.add("1")
        numberItems.add("2")
        numberItems.add("3")
        numberItems.add("4")
        numberItems.add("5")
        numberItems.add("6")
        numberItems.add("7")
        numberItems.add("8")
        numberItems.add("9")
        numberItems.add("10")
    }

    private fun initTimeItems() {
        timeItems.add("11:00")
        timeItems.add("12:00")
        timeItems.add("13:00")
        timeItems.add("14:00")
        timeItems.add("15:00")
        timeItems.add("16:00")
        timeItems.add("17:00")
        timeItems.add("18:00")
        timeItems.add("19:00")
        timeItems.add("20:00")
        timeItems.add("21:00")
    }
}
