package nyc.c4q.jonathancolon.catchemall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerHelper;

/**
 * Created by dannylui on 12/14/16.
 */
public class PrisonerAdapter extends RecyclerView.Adapter<PrisonerAdapter.PrisonerViewHolder> {
    private Listener listener;
    private List<Prisoner> prisoners;
    Context context;


    public PrisonerAdapter(Listener listener) {
        this.listener = listener;
    }

    @Override
    public PrisonerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_prisoner, parent, false);
        PrisonerViewHolder vh = new PrisonerViewHolder(view);
        context = parent.getContext();
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

    public void setData(List<Prisoner> prisoners) {
        this.prisoners = prisoners;
        notifyDataSetChanged();
    }

    public class PrisonerViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;

        public PrisonerViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.vh_name);
        }

        public void bind(Prisoner p) {
            final Prisoner prisoner = p;
            PrisonerHelper prisonerHelper = new PrisonerHelper(context);
            prisonerHelper.generateViewHolderPrisonerSprite(p, itemView);

            nameTv.setText(prisoner.getFirstName() + " " + prisoner.getLastName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPrisonerClicked(prisoner);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onPrisonerLongClicked(prisoner);
                    return true;
                }
            });
        }
    }

    interface Listener {
        void onPrisonerClicked(Prisoner prisoner);
        void onPrisonerLongClicked(Prisoner prisoner);
    }
}
