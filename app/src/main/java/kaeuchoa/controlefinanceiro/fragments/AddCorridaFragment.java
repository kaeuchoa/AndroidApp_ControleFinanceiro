package kaeuchoa.controlefinanceiro.fragments;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import kaeuchoa.controlefinanceiro.DAOs.CorridaDAO;
import kaeuchoa.controlefinanceiro.R;
import kaeuchoa.controlefinanceiro.models.Corrida;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnAddCorridaListener} interface
 * to handle interaction events.
 * Use the {@link AddCorridaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCorridaFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static final String FRAGMENT_TAG = AddCorridaFragment.class.getName();
    private TextInputEditText edtValor;
    private Button btnConfirm;
    private Button btnCancel;
    private Spinner spinnerOrigem;
    private Spinner spinnerDestino;
    private TextView txtDate;

    private static Corrida novaCorrida;

    private OnAddCorridaListener mListener;

    public AddCorridaFragment() {
        // Required empty public constructor
    }


    public static AddCorridaFragment newInstance() {
        AddCorridaFragment fragment = new AddCorridaFragment();
        novaCorrida = new Corrida();

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
        txtDate = (TextView) view.findViewById(R.id.txt_date);

        final Button.OnClickListener btnsClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_fragment_cancel:
                        dismiss();
                        break;
                    case R.id.btn_fragment_confirm:
                        double valor = Double.valueOf(edtValor.getText().toString());
                        novaCorrida.setValor(valor);

                        final CorridaDAO corridaDAO = CorridaDAO.getInstance(getContext());
                        corridaDAO.insertCorrida(novaCorrida);
                        dismiss();
                        break;
                }
            }
        };
        btnConfirm.setOnClickListener(btnsClickListener);
        btnCancel.setOnClickListener(btnsClickListener);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(AddCorridaFragment.this,0);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

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

        spinnerOrigem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                novaCorrida.setOrigem(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                novaCorrida.setDestino(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddCorridaListener) {
            mListener = (OnAddCorridaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddCorridaListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String data = dayOfMonth + "/" + month + "/" + year;
        txtDate.setText(data);
        novaCorrida.setData(data);
    }


    public interface OnAddCorridaListener {
        void onCorridaAdded();
    }
}
