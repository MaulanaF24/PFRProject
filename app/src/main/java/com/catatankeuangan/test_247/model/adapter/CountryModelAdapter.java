package com.catatankeuangan.test_247.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.catatankeuangan.R;
import com.catatankeuangan.test_247.model.CountryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryModelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<CountryModel> listconModel;
    ViewHolderTransaksi view;


    public CountryModelAdapter(Context context , List<CountryModel> listconModel ) {

        this.context = context;
        this.listconModel = listconModel;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transaksi, parent, false);
        vh = new CountryModelAdapter.ViewHolderTransaksi(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CountryModelAdapter.ViewHolderTransaksi) {
            view = (CountryModelAdapter.ViewHolderTransaksi) holder;
            final CountryModel conModel = listconModel.get(position);


            view.txtTgl.setText(conModel.getCapital());
            view.txtKet.setText(conModel.getName());
            view.txtJenis.setText(conModel.getPopulation());
            //view.imgFlag.setImageResource(Integer.parseInt(conModel.getFlag()));
        }
    }

    @Override
    public int getItemCount () {
        return listconModel.size();
    }


    public class ViewHolderTransaksi extends RecyclerView.ViewHolder {


        TextView txtTgl, txtKet, txtSaldo, txtJenis;
        ImageView imgFlag;
        FloatingActionButton btnDelete;

        public ViewHolderTransaksi(@NonNull View itemView) {
            super(itemView);
            txtTgl = (TextView) itemView.findViewById(R.id.txtTgl);
            txtKet = (TextView) itemView.findViewById(R.id.txtKet);
            txtSaldo = (TextView) itemView.findViewById(R.id.txtSaldo);
            txtJenis = (TextView) itemView.findViewById(R.id.txtJenis);
            imgFlag = itemView.findViewById(R.id.imgFlag);

        }
    }
}
