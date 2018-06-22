package dz.esi.restoya.home.activities

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import dz.esi.restoya.R
import dz.esi.restoya.home.adapters.ButtonAdapter
import java.util.*
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.text.format.DateFormat
import android.widget.TextView
import dz.esi.restoya.models.TextItem


class ReservationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private val numberItems: ArrayList<TextItem> = ArrayList()
    private val timeItems: ArrayList<TextItem> = ArrayList()

    private var dateButton: CardView? = null
    private var timeButton: CardView? = null
    private var dateText: TextView? = null
    private var timeText: TextView? = null
    private var reserveButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        dateButton = this.findViewById(R.id.date_button)
        timeButton = this.findViewById(R.id.time_button)
        dateText = this.findViewById(R.id.date_text)
        timeText = this.findViewById(R.id.time_text)
        reserveButton = this.findViewById(R.id.reserve_button)

        initButtonsDialogs()
        initNumberItems()
        initTimeItems()
        initRecyclerViews()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val newDate = Calendar.getInstance()
        newDate.set(year, month, dayOfMonth)
        val dateFormat = android.text.format.DateFormat.getDateFormat(applicationContext)
        dateText?.text = dateFormat.format(newDate.time)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val newDate = Calendar.getInstance()
        newDate.set(newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH),
                newDate.get(Calendar.DAY_OF_MONTH), hourOfDay, minute)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)
        timeText?.text = timeFormat.format(newDate.time)
    }

    private fun initButtonsDialogs() {
        dateButton?.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this, this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialog.setTitle("Select Date")
            datePickerDialog.show()
        }

        timeButton?.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(this, this,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(this))
            timePickerDialog.setTitle("Select Time")
            timePickerDialog.show()
        }

        reserveButton?.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Reserving table, please wait.")
            progressDialog.show()
            Handler().postDelayed({
                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                }
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Reservation")
                        .setMessage("Congrats, your reservation is confirmed.")
                        .setPositiveButton(android.R.string.ok) { dialog, _ ->
                            dialog.dismiss()
                            finish()
                        }
                        .setIcon(R.drawable.ic_menu_table)
                        .show()
            }, 2000)
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
        numberItems.add(TextItem(false, "1"))
        numberItems.add(TextItem(false, "2"))
        numberItems.add(TextItem(false, "3"))
        numberItems.add(TextItem(false, "4"))
        numberItems.add(TextItem(false, "5"))
        numberItems.add(TextItem(false, "6"))
        numberItems.add(TextItem(false, "7"))
        numberItems.add(TextItem(false, "8"))
        numberItems.add(TextItem(false, "9"))
        numberItems.add(TextItem(false, "10"))
    }

    private fun initTimeItems() {
        timeItems.add(TextItem(false, "11:00"))
        timeItems.add(TextItem(false, "12:00"))
        timeItems.add(TextItem(false, "13:00"))
        timeItems.add(TextItem(false, "14:00"))
        timeItems.add(TextItem(false, "15:00"))
        timeItems.add(TextItem(false, "16:00"))
        timeItems.add(TextItem(false, "17:00"))
        timeItems.add(TextItem(false, "18:00"))
        timeItems.add(TextItem(false, "19:00"))
        timeItems.add(TextItem(false, "20:00"))
        timeItems.add(TextItem(false, "21:00"))
    }
}
