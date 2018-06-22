package dz.esi.restoya.home.expandable;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.Collapse;
import com.mindorks.placeholderview.annotations.expand.Expand;
import com.mindorks.placeholderview.annotations.expand.Parent;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;
import com.mindorks.placeholderview.annotations.expand.SingleTop;

import dz.esi.restoya.R;
import dz.esi.restoya.database.entities.Collection;

@Parent
@SingleTop
@Layout(R.layout.item_menu_collection)
public class CollectionView {

    @View(R.id.collection_txt)
    TextView headingTxt;

    @View(R.id.toggle_icon)
    ImageView toggleIcon;

    @ParentPosition
    int mParentPosition;

    private Context mContext;
    private Collection mCollection;

    public CollectionView(Context context, Collection collection) {
        mContext = context;
        mCollection = collection;
    }

    @Resolve
    public void onResolved() {
        toggleIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_keyboard_arrow_up));
        headingTxt.setText(mCollection.getName());
    }

    @Expand
    public void onExpand() {
        toggleIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_keyboard_arrow_down));
    }

    @Collapse
    public void onCollapse() {
        toggleIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_keyboard_arrow_up));
    }
}
