package com.catatankeuangan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.catatankeuangan.R;
import com.catatankeuangan.TransaksiList;
import com.catatankeuangan.model.Transaksi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransaksiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    Context context;
    List<Transaksi> listTrans;
    TransaksiList transaksiList;
    ViewHolderTransaksi view;
    private DecimalFormat formatter;

    public TransaksiAdapter(Context context , List<Transaksi> listTrans ) {

        this.context = context;
        this.listTrans = listTrans;

    }

    public void setMain(TransaksiList transaksiList){
        this.transaksiList = transaksiList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transaksi, parent, false);
        vh = new ViewHolderTransaksi(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderTransaksi) {
            view = (ViewHolderTransaksi) holder;
            final Transaksi trans = listTrans.get(position);

            formatter = new DecimalFormat("###,###,###");

            view.txtTgl.setText("ID "+ trans.getId()+" / Tgl "+trans.getTglTransaksi());
            view.txtKet.setText("Keterangan: " + trans.getKetTransaksi());
            view.txtJenis.setText("Jenis Transaksi: " + trans.getJenisPengeluaran());
            view.txtSaldo.setText("Biaya: Rp. " + formatter.format(Double.valueOf(trans.getSaldoKeluar())));

            view.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transaksiList.deleteTrans(trans.getId(),
                            trans.getKetTransaksi(),
                            trans.getTglTransaksi(),
                            trans.getJenisPengeluaran(),
                            trans.getSaldoKeluar());

                            view.itemView.setVisibility(View.GONE);
                }
            });
        }


    }

    public class ViewHolderTransaksi  extends RecyclerView.ViewHolder{


        TextView txtTgl,txtKet,txtSaldo,txtJenis;
        FloatingActionButton btnDelete;

        public ViewHolderTransaksi(@NonNull View itemView) {
            super(itemView);
            txtTgl = (TextView) itemView.findViewById(R.id.txtTgl);
            txtKet = (TextView)itemView.findViewById(R.id.txtKet);
            txtSaldo = (TextView)itemView.findViewById(R.id.txtSaldo);
            txtJenis = (TextView)itemView.findViewById(R.id.txtJenis);
            btnDelete = (FloatingActionButton)itemView.findViewById(R.id.btnDelete);

        }
    }

    @Override
    public int getItemCount() {
        return listTrans.size();
    }


}
