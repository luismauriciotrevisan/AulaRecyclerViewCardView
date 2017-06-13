package com.example.recyclerviewexample;

import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by LuisMauricioTrevisan on 11/06/2017.
 */

    //Cria-se um Adapter extendendo RecyclerView.Adapter
    //É necessário passar a classe ViewHolder


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.myViewHolder> {

    String[]  countryNames;
    TypedArray countryFlags;

    NoClickDoItem noClickDoItem;


    //Um ViewHolder representa uma linha de uma lista a ser exibida e pode ter inúmeras Views
    //Neste exemplo a linha tem apenas uma ImageView para a bandeira e um TextView para o Nome do País
    public class myViewHolder extends RecyclerView.ViewHolder{
        public TextView countryName;
        public ImageView flag;

        public myViewHolder(View view){
            super(view);
            countryName = (TextView) view.findViewById(R.id.tv_01);
            flag = (ImageView) view.findViewById(R.id.iv_01);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //chama o onClick da interface passando a view (viewHolder) e posição do item
                    noClickDoItem.onClick(view, getAdapterPosition());
                }
            });
        }

    }

    //O construtor do Adaptador depende do tipo de dados da lista
    public CountryAdapter(NoClickDoItem handle, String[] countryNames, TypedArray countryFlags){
        this.countryNames = countryNames;
        this.countryFlags = countryFlags;
        this.noClickDoItem = handle;
    }

    //Substitui o conteúdo das Views, invocado pelo LayoutManager
    @Override
    public void onBindViewHolder(CountryAdapter.myViewHolder viewHolder, int position){
        viewHolder.countryName.setText(countryNames[position]);
        viewHolder.flag.setImageDrawable(countryFlags.getDrawable(position));

    }

    //Cria novas Views, é invocado pelo LayoutManager
    @Override
    public CountryAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row, parent, false);
        return new myViewHolder(itemView);
    }

    //Retorna a quantidade de ítens da lista
    @Override
    public int getItemCount(){
        return countryNames.length;
    }

    //Interface pública para tratar o click de um item da lista
    public interface NoClickDoItem{
        void onClick(View view, int position);
    }
}
