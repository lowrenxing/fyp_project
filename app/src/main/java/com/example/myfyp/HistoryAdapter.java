package com.example.myfyp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter
        extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private final List<HistoryItem> historyList;
    private final OnHistoryClickListener listener;

    public interface OnHistoryClickListener {
        void onClick(HistoryItem item);
    }

    public HistoryAdapter(
            List<HistoryItem> historyList,
            OnHistoryClickListener listener
    ) {
        this.historyList = historyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view =
                LayoutInflater.from(
                        parent.getContext()
                ).inflate(
                        R.layout.item_history,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        HistoryItem item =
                historyList.get(position);

        String shortSessionId =
                item.getSessionId();

        if (shortSessionId.length() > 12) {

            shortSessionId =
                    shortSessionId.substring(
                            0,
                            12
                    );
        }

        holder.txtSession.setText(
                "Session ID : " +
                        shortSessionId
        );

        holder.txtDate.setText(
                "Date : " +
                        item.getTimestamp()
        );

        holder.txtBestPlatform.setText(
                "🏆 " +
                        item.getBestPlatform()
        );

        holder.itemView.setOnClickListener(v -> {

            if (listener != null) {

                listener.onClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtSession;
        TextView txtDate;
        TextView txtBestPlatform;

        public ViewHolder(
                @NonNull View itemView
        ) {
            super(itemView);

            txtSession =
                    itemView.findViewById(
                            R.id.txtSession
                    );

            txtDate =
                    itemView.findViewById(
                            R.id.txtDate
                    );

            txtBestPlatform =
                    itemView.findViewById(
                            R.id.txtBestPlatform
                    );
        }
    }
}