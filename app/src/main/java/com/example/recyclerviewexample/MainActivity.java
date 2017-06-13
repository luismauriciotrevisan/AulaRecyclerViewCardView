package com.example.recyclerviewexample;

//Referências
//https://developer.android.com/guide/topics/ui/layout/recyclerview.html#animations
//https://developer.android.com/training/material/lists-cards.html
//tutorial http://www.androidhive.info/2016/01/android-working-with-recycler-view/
//tutorial https://guides.codepath.com/android/using-the-recyclerview

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView myRecyclerView;
    RecyclerView.Adapter myAdapter;

    String[]  nomePaises; //Array de strings para salvar nome dos países
    TypedArray bandeiras; //TypedArray para salvar ids das bandeiras

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recupera o contexto desta app
        Context ctx = getApplicationContext();
        //Recupera ref para resources
        Resources res = ctx.getResources();

        //recupera array de nomes de países a partir dos recursos da atividade
        nomePaises = res.getStringArray(R.array.country_names);
        //recupera array de bandeiras a partir dos recursos da atividade
        bandeiras = res.obtainTypedArray(R.array.country_icons);

        //encontra uma referência para a view RecyclerView rv_01
        myRecyclerView = (RecyclerView) findViewById(R.id.rv_01);

        //cria um LinearLayoutManager para esta atividade
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //Associa o LinearLayoutManager à RecyclerView
        myRecyclerView.setLayoutManager(mLayoutManager);

        CountryAdapter.NoClickDoItem handleClick = new CountryAdapter.NoClickDoItem(){
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show();
                atualizaSoma();
            }
        };

        //cria uma instância de Adapter
        myAdapter = new CountryAdapter(handleClick, nomePaises, bandeiras);
        //Associa Adapter ao RecyclerView
        myRecyclerView.setAdapter(myAdapter);

    }

    public void atualizaSoma(){
        Toast.makeText(this, "atualizaSoma", Toast.LENGTH_SHORT).show();
    }
}
