package br.edu.infnet.sightseeing;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {


    HashMap<String, String> dicionario;
    String placeLatLong = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dados a serem exibidos na caixa de seleção
        String[] places = {
                "-","Pão de Açucar","Corcovado",
                "Copacabana","Maracanã"};
        String[] latLongs = {
                "-",
                "-22.9512272,-43.1649618",
                "-22.9516917,-43.2096117",
                "-22.9828371,-43.1891771",
                "-22.9125958,-43.2298439"
        };

        dicionario = new HashMap<String, String>();
        for (int i = 1; i < places.length; i++){
            dicionario.put(places[i], latLongs[i]);
        }

        // elemento de interface entre os dados e as views
        // que aparecem na caixa de seleção
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                places);

        // referência para a caixa de seleção
        Spinner placeSpinner = findViewById(R.id.place_spinner);
        // ligação entre o adapter e a caixa de seleção
        placeSpinner.setAdapter(adapter);
        placeSpinner.setOnItemSelectedListener(this);
    }

    public void goToPlace(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // dar informação sobre o que quero visualizar
        intent.setData(Uri.parse(
                "google.streetview:cbll=" + placeLatLong));
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView selectedView = (TextView) view;
        String place = selectedView.getText().toString();

        if (!place.equals("-")){
            placeLatLong = dicionario.get(place);
        }

        /*String placeLatlong = "";
        switch (place){
            case "Copacabana":
                //pegar lat log de Copacabana
                placeLatlong = "-22.9828371,-43.1891771";
                goToPlace(placeLatlong);
                break;
            case "Pão de Açúcar":
                break;
            default:
                break;
        }*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // fazer nada por enquanto; dados não vão sumir!
    }
}
