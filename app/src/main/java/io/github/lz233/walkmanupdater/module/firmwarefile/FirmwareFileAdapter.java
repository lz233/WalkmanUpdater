package io.github.lz233.walkmanupdater.module.firmwarefile;

import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.lz233.walkmanupdater.R;

public class FirmwareFileAdapter extends RecyclerView.Adapter<FirmwareFileAdapter.ViewHolder> {
    private List<FirmwareFile> firmwareFileList;

    public FirmwareFileAdapter(List<FirmwareFile> firmwareFileList) {
        this.firmwareFileList = firmwareFileList;
    }

    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_firmware, parent, false);
        final FirmwareFileAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FirmwareFile firmwareFile = firmwareFileList.get(position);
        holder.firmwareFileTitleTextView.setText(firmwareFile.getFirmwareVersion());
        holder.firmwareFileSummaryTextView.setText(firmwareFile.getFirmwareFileSize());
        holder.firmwareFileCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firmwareFile.getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(firmwareFile.getFirmwareFileURL())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.firmwareFileList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        CardView firmwareFileCardView;
        TextView firmwareFileTitleTextView;
        TextView firmwareFileSummaryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            firmwareFileCardView = itemView.findViewById(R.id.firmwareFileCardView);
            firmwareFileTitleTextView = itemView.findViewById(R.id.firmwareFileTitleTextView);
            firmwareFileSummaryTextView = itemView.findViewById(R.id.firmwareFileSummaryTextView);
        }
    }
}
