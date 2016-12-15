package nyc.c4q.jonathancolon.catchemall;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerAttributes;

/**
 * Created by dannylui on 12/14/16.
 */
public class PrisonerAdapter extends RecyclerView.Adapter<PrisonerAdapter.PrisonerViewHolder> {
    private Listener listener;
    private List<Prisoner> prisoners;

    public PrisonerAdapter(Listener listener) {
        this.listener = listener;
    }

    @Override
    public PrisonerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_prisoner, parent, false);
        PrisonerViewHolder vh = new PrisonerViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(PrisonerViewHolder holder, int position) {
        Prisoner prisoner = prisoners.get(position);
        holder.bind(prisoner);
    }

    @Override
    public int getItemCount() {
        return prisoners.size();
    }

    public void setData(List<Prisoner> cats) {
        this.prisoners = cats;
        notifyDataSetChanged();
    }

    public class PrisonerViewHolder extends RecyclerView.ViewHolder {
        private ImageView eyeColorLayer;
        private ImageView skinToneLayer;
        private ImageView hairStyleLayer;
        private ImageView accessoryLayer;
        private ImageView beardLayer;

        private TextView nameTv;

        public PrisonerViewHolder(View itemView) {
            super(itemView);
            eyeColorLayer = (ImageView) itemView.findViewById(R.id.vh_eyes);
            skinToneLayer = (ImageView) itemView.findViewById(R.id.vh_skintone);
            hairStyleLayer = (ImageView) itemView.findViewById(R.id.vh_hair);
            beardLayer = (ImageView) itemView.findViewById(R.id.vh_beard);
            accessoryLayer = (ImageView) itemView.findViewById(R.id.vh_accessory);

            nameTv = (TextView) itemView.findViewById(R.id.vh_name);
        }

        public void bind(Prisoner p) {
            final Prisoner prisoner = p;

            //they all look the same
            eyeColorLayer.setBackgroundResource(PrisonerAttributes.BLUE);
            skinToneLayer.setBackgroundResource(PrisonerAttributes.TAN);
            hairStyleLayer.setBackgroundResource(PrisonerAttributes.BALD_BLONDE);
            beardLayer.setBackgroundResource(PrisonerAttributes.BLACK_BEARD);

            nameTv.setText(prisoner.getFirstName() + " " + prisoner.getLastName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPrisonerClicked(prisoner);
                }
            });
        }
    }

    interface Listener {
        void onPrisonerClicked(Prisoner prisoner);
    }
}
