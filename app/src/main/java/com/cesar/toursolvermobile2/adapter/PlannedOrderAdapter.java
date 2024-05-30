package com.cesar.toursolvermobile2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cesar.toursolvermobile2.R;
import com.cesar.toursolvermobile2.model.PlannedOrder;
import java.util.List;

public class PlannedOrderAdapter extends RecyclerView.Adapter<PlannedOrderAdapter.PlannedOrderViewHolder> {

    private List<PlannedOrder> plannedOrders;
    private Context context;

    public PlannedOrderAdapter(Context context, List<PlannedOrder> plannedOrders) {
        this.context = context;
        this.plannedOrders = plannedOrders;
    }

    @NonNull
    @Override
    public PlannedOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cita_recycler_row, parent, false);
        return new PlannedOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlannedOrderViewHolder holder, int position) {
        PlannedOrder plannedOrder = plannedOrders.get(position);
        holder.bind(plannedOrder);
    }

    @Override
    public int getItemCount() {
        return plannedOrders.size();
    }

    public class PlannedOrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime1, tvTime2, tvRoute;

        public PlannedOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime1 = itemView.findViewById(R.id.tv_time1);
            tvTime2 = itemView.findViewById(R.id.tv_time2);
            tvRoute = itemView.findViewById(R.id.tv_route);
        }

        public void bind(PlannedOrder plannedOrder) {
            tvTime1.setText(plannedOrder.getStopStartTime());
            tvTime2.setText(plannedOrder.getStopStartTime()); // Cambiar por el campo correspondiente
            tvRoute.setText(plannedOrder.getResourceId()); // Cambiar por el campo correspondiente
        }
    }
}
