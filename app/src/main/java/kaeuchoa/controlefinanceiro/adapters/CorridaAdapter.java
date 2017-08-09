package kaeuchoa.controlefinanceiro.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kaeuchoa.controlefinanceiro.R;
import kaeuchoa.controlefinanceiro.models.Corrida;

/**
 * Created by kaeuc on 22/06/2017.
 */

public class CorridaAdapter extends RecyclerView.Adapter <CorridaAdapter.CorridaViewHolder>{
    private List<Corrida> corridaList;
    private LayoutInflater mLayoutInflater;

    public CorridaAdapter(Context context, List<Corrida> corridaList) {
        this.corridaList = corridaList;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CorridaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_corrida, parent,false);
        CorridaViewHolder viewHolder = new CorridaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CorridaViewHolder holder, int position) {
        holder.txtOrigem.setText(corridaList.get(position).getOrigem());
        holder.txtDestino.setText(corridaList.get(position).getDestino());
        holder.txtValor.setText("R$ "+ corridaList.get(position).getValor());
        holder.txtData.setText(corridaList.get(position).getData().toString());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clique funcionando", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "delete clique", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return corridaList.size();
    }

    public class CorridaViewHolder extends RecyclerView.ViewHolder {

        public TextView txtOrigem, txtDestino, txtValor, txtData;
        public ImageView imgEdit, imgDelete;


        public CorridaViewHolder(View itemView) {
            super(itemView);

            txtOrigem = (TextView) itemView.findViewById(R.id.txt_origem);
            txtDestino = (TextView) itemView.findViewById(R.id.txt_destino);
            txtValor = (TextView) itemView.findViewById(R.id.txt_valor);
            txtData = (TextView) itemView.findViewById(R.id.txt_data);

            imgEdit = (ImageView) itemView.findViewById(R.id.img_edit);
            imgDelete = (ImageView) itemView.findViewById(R.id.img_delete);

        }
    }
}
