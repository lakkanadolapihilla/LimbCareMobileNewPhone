package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.topic.setText(serviceCard.getTopic());
        holder.description.setText(serviceCard.getDescription());
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
