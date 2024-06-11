package com.cesar.toursolvermobile2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cesar.toursolvermobile2.R;
import com.cesar.toursolvermobile2.model.OperationalOrderAchievement;
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
    private List<OperationalOrderAchievement> achievements;
    private Context context;

    public PlannedOrderAdapter(Context context, List<PlannedOrder> plannedOrders, List<Order> orders,List<OperationalOrderAchievement> achievements) {
        this.context = context;
        this.plannedOrders = plannedOrders;
        this.orders = orders;
        this.achievements = achievements;
    }

    public void updateData(List<PlannedOrder> newPlannedOrders, List<Order> newOrders, List<OperationalOrderAchievement> newAchievements) {
        this.plannedOrders = newPlannedOrders;
        this.orders = newOrders;
        this.achievements = newAchievements;
        notifyDataSetChanged();
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
        OperationalOrderAchievement achievement = achievements.get(position); // Get corresponding achievement

        if (!"Llegada".equals(plannedOrder.getStopId())) {
            holder.bind(plannedOrder, order, achievement); // Pass achievement to bind method
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

        public void bind(PlannedOrder plannedOrder, Order order, OperationalOrderAchievement achievement) {
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

            // Set the icon drawable based on stopId and achievement status
            if ("Salida".equals(plannedOrder.getStopId())) {
                tvIcon.setImageResource(R.drawable.itinerario_logo);
            } else if (achievement != null && "STARTED".equals(achievement.getStatus())) {
                tvIcon.setImageResource(R.drawable.logo_cita_empezada);
            } else if (achievement != null && "CANCELLED".equals(achievement.getStatus())) {
                tvIcon.setImageResource(R.drawable.logo_cita_abandonada);
            } else if (achievement != null && "FINISHED".equals(achievement.getStatus())) {
                tvIcon.setImageResource(R.drawable.logo_cita_finalizada);
            } else {
                tvIcon.setImageResource(R.drawable.logo_cita_aceptada);
            }

            // Add OnClickListener to show a Toast message
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("Salida".equals(plannedOrder.getStopId())) {
                        Toast.makeText(context, "Itinerario", Toast.LENGTH_SHORT).show();
                    } else if (achievement != null && "FINISHED".equals(achievement.getStatus())) {
                        Toast.makeText(context, "Cita terminada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Cita aceptada", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}