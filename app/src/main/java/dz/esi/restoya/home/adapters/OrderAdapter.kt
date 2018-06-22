package dz.esi.restoya.home.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dz.esi.restoya.R
import dz.esi.restoya.database.entities.Dish
import kotlinx.android.synthetic.main.item_menu.view.*

class OrderAdapter (_items: MutableList<Dish>, _context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private val items= _items
    private val context = _context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_menu_dish, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        val options: RequestOptions = RequestOptions().centerCrop()
        options.centerCrop()
        Glide.with(context).load(item.image).apply(options).into(holder.image)
        holder.name.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
        val name: TextView = view.name
    }
}