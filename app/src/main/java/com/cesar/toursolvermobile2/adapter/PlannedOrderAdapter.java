package com.cesar.toursolvermobile2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cesar.toursolvermobile2.R;
import com.cesar.toursolvermobile2.model.Order;
import com.cesar.toursolvermobile2.model.PlannedOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlannedOrderAdapter extends RecyclerView.Adapter<PlannedOrderAdapter.PlannedOrderViewHolder> {

    private List<PlannedOrder> plannedOrders;
    private List<Order> orders;
    private Context context;

    public PlannedOrderAdapter(Context context, List<PlannedOrder> plannedOrders, List<Order> orders) { // Add orders here
        this.context = context;
        this.plannedOrders = plannedOrders;
        this.orders = orders; // Add this line
    }

    public void updateData(List<PlannedOrder> newPlannedOrders, List<Order> newOrders) {
        this.plannedOrders = newPlannedOrders;
        this.orders = newOrders;
        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
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
        Order order = orders.get(position); // Get corresponding order

        if (!"Llegada".equals(plannedOrder.getStopId())) {
            holder.bind(plannedOrder, order); // Pass order to bind method
        }
    }

    @Override
    public int getItemCount() {
        return plannedOrders.size();
    }

    public class PlannedOrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime1, tvTime2, tvRoute;
        ImageView tvIcon;

        public PlannedOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime1 = itemView.findViewById(R.id.tv_time1);
            tvTime2 = itemView.findViewById(R.id.tv_time2);
            tvRoute = itemView.findViewById(R.id.tv_route);
            tvIcon = itemView.findViewById(R.id.tv_icon);
        }

        public void bind(PlannedOrder plannedOrder, Order order) { // Add order as parameter
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

            // Set the route label from order
            if (order != null) {
                tvRoute.setText(order.getLabel());
            } else {
                tvRoute.setText("Itinerario");
            }

            // Set the icon drawable based on stopId
            if ("Salida".equals(plannedOrder.getStopId())) {
                tvIcon.setImageResource(R.drawable.itinerario_logo);
            } else {
                tvIcon.setImageResource(R.drawable.logo_cita_aceptada);
            }
        }
    }
}