package dz.esi.restoya.home.expandable;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.ChildPosition;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;

import dz.esi.restoya.R;
import dz.esi.restoya.database.entities.Dish;

@Layout(R.layout.item_menu_dish)
public class DishView {

    @ParentPosition
    int mParentPosition;

    @ChildPosition
    int mChildPosition;

    @View(R.id.titleTxt)
    TextView titleTxt;

    @View(R.id.imageView)
    ImageView imageView;

    Dish mdish;
    Context mContext;

    public DishView(Context context, Dish dish) {
        mContext = context;
        mdish = dish;
    }

    @Resolve
    public void onResolved() {
        titleTxt.setText(mdish.getName());
        Glide.with(mContext).load(mdish.getImage()).into(imageView);
    }
}
