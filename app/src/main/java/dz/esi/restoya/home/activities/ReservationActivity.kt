package dz.esi.restoya.home.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import dz.esi.restoya.R
import dz.esi.restoya.home.adapters.ButtonAdapter

class ReservationActivity : AppCompatActivity() {

    private val numberItems: ArrayList<String> = ArrayList()
    private val timeItems: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        initButtonsDialogs()
        initNumberItems()
        initTimeItems()
        initRecyclerViews()

    }

    private fun initButtonsDialogs() {
        val dateButton = findViewById<RecyclerView>(R.id.da)
        val timeButton = findViewById<RecyclerView>(R.id.number_recyclerView)
    }

    private fun initRecyclerViews() {
        val numberRecyclerView = findViewById<RecyclerView>(R.id.number_recyclerView)
        val timeRecyclerView = findViewById<RecyclerView>(R.id.time_recyclerView)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        numberRecyclerView.layoutManager = layoutManager
        timeRecyclerView.layoutManager = layoutManager
        numberRecyclerView.adapter = ButtonAdapter(numberItems)
        timeRecyclerView.adapter = ButtonAdapter(timeItems)
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
