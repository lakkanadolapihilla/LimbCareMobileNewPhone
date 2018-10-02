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

import com.example.gayashan.limbcare.NoticeCard;
import com.example.gayashan.limbcare.R;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private List<NoticeCard> cardItems;
    private Context context;

    public NoticeAdapter(List<NoticeCard> cardItems, Context context) {
        this.cardItems = cardItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noticecard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        NoticeCard noticeCard = cardItems.get(position);
        holder.topic.setText(noticeCard.getTopic());
        holder.description.setText(noticeCard.getDescription());
        byte[] foodImage = noticeCard.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imgteamsnote.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView topic;
        public TextView description;
        public ImageView imgteamsnote;



        public ViewHolder(View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.topic);
            description = itemView.findViewById(R.id.description);
            imgteamsnote=itemView.findViewById(R.id.noticeImage);
        }
    }
}
