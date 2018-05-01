package dz.esi.restoya.home.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import dz.esi.restoya.R
import kotlinx.android.synthetic.main.item_button.view.*

class ButtonAdapter (context: Context, _items: MutableList<String>) : RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {

    private val items= _items
    private val context= context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_button, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.button.text = item
        holder.button.setOnClickListener {
            holder.button.setBackgroundColor(context.resources.getColor((R.color.colorPrimary)))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.textButton
    }
}