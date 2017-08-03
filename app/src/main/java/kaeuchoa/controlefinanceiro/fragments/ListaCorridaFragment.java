package kaeuchoa.controlefinanceiro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kaeuchoa.controlefinanceiro.R;
import kaeuchoa.controlefinanceiro.adapters.CorridaAdapter;
import kaeuchoa.controlefinanceiro.models.Corrida;

import static android.content.ContentValues.TAG;


public class ListaCorridaFragment extends Fragment {

    private RecyclerView rvMain;
    private List<Corrida> corridasTeste;
    private float valorTotal = 0;
    public static final String FRAGMENT_TAG = ListaCorridaFragment.class.getName();

    public ListaCorridaFragment() {
        // Required empty public constructor
    }

    public static ListaCorridaFragment newInstance() {
        ListaCorridaFragment fragment = new ListaCorridaFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_corrida, container, false);
        setupRecyclerView(view);



        return view;
    }

    private void setupRecyclerView(View view){
        rvMain = (RecyclerView) view.findViewById(R.id.rv_main);
        rvMain.setHasFixedSize(true);

        Log.d(TAG,"SetupRecyclerView()");


        rvMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                CorridaAdapter adapter = (CorridaAdapter) recyclerView.getAdapter();


                if(corridasTeste.size() == llm.findLastVisibleItemPosition() + 1){

                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvMain.setLayoutManager(layoutManager);
        corridasTeste = getCorridasTeste(10);
        valorTotal = calculateTotalCorridas();


        CorridaAdapter adapter = new CorridaAdapter(getActivity(),corridasTeste);
        rvMain.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvMain.getContext(),
                ((LinearLayoutManager) rvMain.getLayoutManager()).getOrientation());
        rvMain.addItemDecoration(dividerItemDecoration);


    }

    private List<Corrida> getCorridasTeste(int qtd){
        List<Corrida> lista = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            lista.add(new Corrida("Casa","BR",6.00));
        }

        return lista;
    }

    private float calculateTotalCorridas(){
        float total = 0;
        for (Corrida corrida :
                corridasTeste) {
            total += corrida.getValor();
        }
        return total;
    }

    public float getTotalCorridas(){
        return valorTotal;
    }

}
