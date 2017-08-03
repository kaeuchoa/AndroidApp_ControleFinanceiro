package kaeuchoa.controlefinanceiro;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import kaeuchoa.controlefinanceiro.fragments.AddCorridaFragment;
import kaeuchoa.controlefinanceiro.fragments.ListaCorridaFragment;
import kaeuchoa.controlefinanceiro.fragments.ReviewCorridasFragment;

public class MainActivity extends AppCompatActivity implements AddCorridaFragment.OnFragmentInteractionListener{
    private static final String TAG = MainActivity.class.getName();
    private Toolbar tbMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();



        ListaCorridaFragment listCorrida = (ListaCorridaFragment) getSupportFragmentManager().findFragmentByTag(ListaCorridaFragment.FRAGMENT_TAG);
        if(listCorrida == null){
            listCorrida = new ListaCorridaFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container,listCorrida,ListaCorridaFragment.FRAGMENT_TAG);
            ft.commit();

        }

        ReviewCorridasFragment bottomFragment = (ReviewCorridasFragment) getSupportFragmentManager().findFragmentByTag(ReviewCorridasFragment.FRAGMENT_TAG);
        if(bottomFragment == null ){
            // TODO: Substituir esses valores pelo valor total e numero de elements na lista de corridas
            bottomFragment = ReviewCorridasFragment.newInstance(10,52.3);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.tb_bottom_container,bottomFragment,ReviewCorridasFragment.FRAGMENT_TAG);
            ft.commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_corrida:
                openAddCorridaDialog();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupToolbar(){
        tbMain = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(tbMain);
    }

    private void openAddCorridaDialog(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(AddCorridaFragment.FRAGMENT_TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        // Passar parametros para edição de um item
        final AddCorridaFragment addCorridaFragment = AddCorridaFragment.newInstance(null,null);
        addCorridaFragment.show(ft,AddCorridaFragment.FRAGMENT_TAG);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
