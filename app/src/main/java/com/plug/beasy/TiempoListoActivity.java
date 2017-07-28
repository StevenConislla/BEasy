package com.plug.beasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TiempoListoActivity extends AppCompatActivity {
    TextView etResult;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo_listo);
        String tiempo=getIntent().getStringExtra("variable2");
        etResult=(TextView)findViewById(R.id.etResult);
        btnSalir=(Button)findViewById(R.id.btnSalir);
        etResult.setText(tiempo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(TiempoListoActivity.this,IdentificacionActivity.class);
                startActivity(intent);
            }
        });
    }
}
