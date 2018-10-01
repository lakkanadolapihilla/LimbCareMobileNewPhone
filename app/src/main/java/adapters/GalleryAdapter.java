package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gayashan.limbcare.GalleryCard;
import com.example.gayashan.limbcare.NoticeCard;
import com.example.gayashan.limbcare.R;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<GalleryCard> cardItems;
    private Context context;

    public GalleryAdapter(List<GalleryCard> cardItems, Context context) {
        this.cardItems = cardItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallerycard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GalleryCard galleryCard = cardItems.get(position);
        holder.topic.setText(galleryCard.getTopic());
        holder.description.setText(galleryCard.getDescription());
    }
    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView topic;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.topic);
            description = itemView.findViewById(R.id.description);
        }
    }
}
