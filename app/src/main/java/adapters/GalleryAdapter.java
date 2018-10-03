package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gayashan.limbcare.GalleryCard;
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
        holder.Gallerytopic.setText(galleryCard.getTopic());
        holder.Gallerydescription.setText(galleryCard.getDescription());

        holder.Galleryyid.setText(galleryCard.getIdgallery());
        byte[] foodImage = galleryCard.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.GalarysImagee.setImageBitmap(bitmap);
    }
    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Galleryyid;
        public TextView Gallerytopic;
        public TextView Gallerydescription;

        public ImageView GalarysImagee;

        public ViewHolder(View itemView) {
            super(itemView);

            Galleryyid = itemView.findViewById(R.id.Galleryyid);
            Gallerytopic = itemView.findViewById(R.id.topicnoteW);
            Gallerydescription = itemView.findViewById(R.id.descriptionnoteW);
            GalarysImagee= itemView.findViewById(R.id.galleryimageNEW);
        }
    }
}
