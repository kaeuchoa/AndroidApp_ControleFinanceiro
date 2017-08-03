package kaeuchoa.controlefinanceiro.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import kaeuchoa.controlefinanceiro.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddCorridaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddCorridaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCorridaFragment extends DialogFragment {

    public static final String FRAGMENT_TAG = AddCorridaFragment.class.getName();
    private TextInputEditText edtValor;
    private Button btnConfirm;
    private Button btnCancel;
    private Spinner spinnerOrigem;
    private Spinner spinnerDestino;

    private OnFragmentInteractionListener mListener;

    public AddCorridaFragment() {
        // Required empty public constructor
    }


    public static AddCorridaFragment newInstance(String param1, String param2) {
        AddCorridaFragment fragment = new AddCorridaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Evita que a Janela de Dialogo seja fechada ao clicar fora do quadro
        setCancelable(false);

        // Ajusta o layout da janela
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Material_Light_Dialog_NoActionBar_MinWidth);
        }
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add_corrida, container, false);
        // Inflate the layout for this fragment

        edtValor = (TextInputEditText) view.findViewById(R.id.edt_valor);
        btnCancel = (Button) view.findViewById(R.id.btn_fragment_cancel);
        btnConfirm = (Button) view.findViewById(R.id.btn_fragment_confirm);

        final Button.OnClickListener btnsClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_fragment_cancel:
                        dismiss();
                        break;
                    case R.id.btn_fragment_confirm:
//                        String jsonReturn = parseFormToJson();
//                        final PlaceDAO placeDAO = PlaceDAO.getInstance();
//                        placeDAO.insertPlace(jsonReturn);
                        dismiss();
                        break;
                }
            }
        };
        btnConfirm.setOnClickListener(btnsClickListener);
        btnCancel.setOnClickListener(btnsClickListener);
        spinnersSetup(view);


        return view;
    }


    private void spinnersSetup(View view){
        spinnerOrigem = (Spinner) view.findViewById(R.id.spinner_origem);
        spinnerDestino = (Spinner) view.findViewById(R.id.spinner_destino);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(view.getContext(),
              R.array.locais, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_item);

        spinnerOrigem.setAdapter(arrayAdapter);
        spinnerDestino.setAdapter(arrayAdapter);

        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // RETORNAR O VALOR DO ITEM
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinnerOrigem.setOnItemSelectedListener(onItemSelectedListener);
        spinnerDestino.setOnItemSelectedListener(onItemSelectedListener);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
