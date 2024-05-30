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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        if (!"Llegada".equals(plannedOrder.getStopId())) {
            holder.bind(plannedOrder);
        }
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
            SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

            try {
                // Parsear stopStartTime
                Date startTime = inputFormat.parse(plannedOrder.getStopStartTime());
                // Formatear stopStartTime a "HH:mm"
                String formattedStartTime = outputFormat.format(startTime);
                tvTime1.setText(formattedStartTime);

                // Parsear stopDuration
                String[] durationParts = plannedOrder.getStopDuration().split(":");
                int durationMinutes = Integer.parseInt(durationParts[1]);

                // Crear un calendario para sumar stopDuration a startTime
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startTime);
                calendar.add(Calendar.MINUTE, durationMinutes);

                // Formatear el tiempo final a "HH:mm"
                String formattedEndTime = outputFormat.format(calendar.getTime());
                tvTime2.setText(formattedEndTime);

            } catch (ParseException e) {
                e.printStackTrace();
                tvTime1.setText(plannedOrder.getStopStartTime());
                tvTime2.setText(plannedOrder.getStopStartTime()); // o algún valor por defecto
            }

            tvRoute.setText(plannedOrder.getResourceId()); // Cambiar por el campo correspondiente
        }
    }
}