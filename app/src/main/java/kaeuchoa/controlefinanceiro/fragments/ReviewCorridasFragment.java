package kaeuchoa.controlefinanceiro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kaeuchoa.controlefinanceiro.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewCorridasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewCorridasFragment extends Fragment {

    public static final String FRAGMENT_TAG = ReviewCorridasFragment.class.getName();
    private static final String N_TOTAL_CORRIDAS = "numero_de_corridas";
    private static final String TOTAL_CORRIDAS_VALUE = "valor_total_de_corridas";

    private int nTotalCorridas;
    private double totalValueCorridas;

    private TextView txtNTotalCorridas;
    private TextView txtTotalValueCorridas;


    public ReviewCorridasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param nCorridas contagem do número de corridas inseridas na lista.
     * @param totalValue valor total das corridas somadas.
     * @return A new instance of fragment ReviewCorridasFragment.
     */

    public static ReviewCorridasFragment newInstance(int nCorridas, double totalValue) {
        ReviewCorridasFragment fragment = new ReviewCorridasFragment();
        Bundle args = new Bundle();
        args.putInt(N_TOTAL_CORRIDAS, nCorridas);
        args.putDouble(TOTAL_CORRIDAS_VALUE, totalValue);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nTotalCorridas = getArguments().getInt(N_TOTAL_CORRIDAS);
            totalValueCorridas = getArguments().getDouble(TOTAL_CORRIDAS_VALUE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_corridas, container, false);
        txtNTotalCorridas = (TextView) view.findViewById(R.id.frag_txt_n_corridas);
        txtTotalValueCorridas = (TextView) view.findViewById(R.id.frag_txt_valor);

        txtNTotalCorridas.setText(String.valueOf(nTotalCorridas));
        txtTotalValueCorridas.setText(String.valueOf(totalValueCorridas));


        return view;
    }



}
