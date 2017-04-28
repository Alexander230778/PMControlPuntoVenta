package pe.edu.upc.pmcontrolpuntoventa.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


import pe.edu.upc.pmcontrolpuntoventa.R;
import pe.edu.upc.pmcontrolpuntoventa.models.Attendance;

/**
 * Created by proyecto on 10/04/2017.
 */

public class AttendancesAdapter extends RecyclerView.Adapter<AttendancesAdapter.ViewHolder> {
    private List<Attendance> attendances;
    @Override
    public AttendancesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_attendances,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AttendancesAdapter.ViewHolder holder, final int position) {
        holder.nameTextView.setText(attendances.get(position).getDate().toString());
        holder.descriptionTextView.setText(attendances.get(position).getDescription());
//        holder.sourceCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CatchUpApp
//                        .getInstance()
//                        .setCurrentSource(sources.get(position));
//                v.getContext().startActivity(
//                        new Intent(
//                            v.getContext(),
//                            ArticlesActivity.class));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return attendances.size();
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setSources(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView sourceCardView;
        TextView nameTextView;
        TextView descriptionTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            sourceCardView = (CardView) itemView.findViewById(R.id.attendancesCardView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
