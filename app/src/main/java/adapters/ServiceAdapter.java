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

import com.example.gayashan.limbcare.R;
import com.example.gayashan.limbcare.ServiceCard;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private List<ServiceCard> cardItems;
    private Context context;

    public ServiceAdapter(List<ServiceCard> cardItems, Context context) {
        this.cardItems = cardItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ServiceCard serviceCard = cardItems.get(position);
        holder.Sertopic.setText(serviceCard.getTopic());
        holder.Serdescription.setText(serviceCard.getDescription());

        holder.Sertype.setText(serviceCard.getType());
        holder.Serprice.setText(serviceCard.getPrice());
        byte[] foodImage = serviceCard.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imgService.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Sertopic;
        public TextView Serdescription;
        public TextView Sertype;
        public TextView Serprice;
        public ImageView imgService;

        public ViewHolder(View itemView) {
            super(itemView);
            Sertype= itemView.findViewById(R.id.type);
            Sertopic = itemView.findViewById(R.id.topic);
            Serprice = itemView.findViewById(R.id.price);
            Serdescription = itemView.findViewById(R.id.description);
            imgService=itemView.findViewById(R.id.serviceSImage);
        }
    }
}
