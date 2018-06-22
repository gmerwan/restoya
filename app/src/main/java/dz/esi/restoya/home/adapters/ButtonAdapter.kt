package dz.esi.restoya.home.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dz.esi.restoya.R
import dz.esi.restoya.models.TextItem
import kotlinx.android.synthetic.main.item_button.view.*

class ButtonAdapter (context: Context, _items: MutableList<TextItem>) : RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {

    private val items= _items
    private val context= context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_button, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.recyclerText.text = item.text
        if (item.checked){
            holder.button.setCardBackgroundColor(context.resources.getColor(R.color.colorPrimary))
            holder.recyclerText.setTextColor(context.resources.getColor(android.R.color.white))
        } else {
            holder.button.setCardBackgroundColor(context.resources.getColor(android.R.color.white))
            holder.recyclerText.setTextColor(context.resources.getColor(R.color.colorPrimaryText))
        }
        holder.button.setOnClickListener {
            init()
            item.checked = true
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun init() {
        for (i in 0 until itemCount) {
            items[i].checked = false
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: CardView = view.textButton
        val recyclerText: TextView = view.recycler_text
    }
}