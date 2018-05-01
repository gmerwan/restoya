package dz.esi.restoya.home.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dz.esi.restoya.R
import dz.esi.restoya.home.activities.FavoriteActivity
import dz.esi.restoya.home.activities.RestaurantActivity
import dz.esi.restoya.home.models.Restaurant
import kotlinx.android.synthetic.main.item_restaurant.view.*
import org.jetbrains.anko.startActivity

class RestaurantAdapter (_items: MutableList<Restaurant>, _context: Context) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    private val items= _items
    private val context = _context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_restaurant, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val options: RequestOptions = RequestOptions().centerCrop()
        options.centerCrop()
        Glide.with(context).load(item.images[0]).apply(options).into(holder.image)
        holder.name.text = item.name
        holder.place.text = item.address
        if (item.favorite) {
            holder.favorite.setImageResource(R.drawable.ic_favorite)
        } else {
            holder.favorite.setImageResource(R.drawable.ic_non_favorite)
        }
        holder.favorite.setOnClickListener {
            if (item.favorite) {
                item.favorite = false
                holder.favorite.setImageResource(R.drawable.ic_non_favorite)
            } else {
                item.favorite = true
                holder.favorite.setImageResource(R.drawable.ic_favorite)
            }
        }
        holder.card.setOnClickListener {
            context.startActivity<RestaurantActivity>("restaurant" to item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.card_view
        val image: ImageView = view.image
        val favorite: ImageView = view.favorite
        val name: TextView = view.name
        val place: TextView = view.place
    }
}