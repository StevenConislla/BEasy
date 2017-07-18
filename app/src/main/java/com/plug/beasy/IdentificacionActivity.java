package com.plug.beasy;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import android.app.AlertDialog;

import java.io.IOException;
import java.util.UUID;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by OSKAR on 04/07/2017.
 */

public class IdentificacionActivity extends AppCompatActivity {
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Button scan_btn;
    //private EditText edt_QRD = (EditText)result.getContents();
    private TextView textViewQRD;
    private SimpleDraweeView ivImagen;
    //private ImageView ivCarnet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identificacion);
        //new ConnectBT().execute(); //Call the class to connect
        textViewQRD = (TextView) findViewById(R.id.textViewQRD);
        //edt_QRD= (EditText)findViewById(R.id.edt_QRD);
        scan_btn = (Button) findViewById(R.id.scan_btn);
        ivImagen=(SimpleDraweeView)findViewById(R.id.ivCarnet);
        //ivCarnet=(ImageView)findViewById(R.id.ivCarnet);

        final Activity activity = this;

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void Seguir(View v) {
        startActivity(new Intent(this, DeviceListActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        final String A="Alumno_A";
        final String B="Alumno_B";
        final String C="Alumno_C";
        final String UURLCARNET;
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                // textViewQRD.setText(result.getContents());
                /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Codigo detectado");
                builder.setMessage("Hola! " + result.getContents() + "\n" + "Datos: ");
                //ivImagen.setImageURI(Uri.parse("http://2.bp.blogspot.com/-T7fUDGa1KUc/TbCb2o_7HXI/AAAAAAAABXs/n2OvUKOIPNk/s1600/estado.jpg"));
                AlertDialog alertDialog = builder.create();
                alertDialog.show();*/
                UURLCARNET=result.getContents();

                if(UURLCARNET.equals(A)){
                    //AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    Toast.makeText(this, "Alumno_A reconocido", Toast.LENGTH_LONG).show();
                    Log.i("TAG","Hola");
                    identificar();
                    //builder.setTitle("Verificacion de identidad");
                    //builder.setMessage("Hola! " + result.getContents() + "\n" + "Datos: ");
                    //ivImagen.setImageURI(Uri.parse("http://2.bp.blogspot.com/-T7fUDGa1KUc/TbCb2o_7HXI/AAAAAAAABXs/n2OvUKOIPNk/s1600/estado.jpg"));
                    //AlertDialog alertDialog = builder.create();
                    //alertDialog.show();
                }
                else if(UURLCARNET.equals(B)){
                    //AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    Toast.makeText(this, "Alumno_B reconocido", Toast.LENGTH_LONG).show();
                    Log.i("TAG","Hola2");
                    identificar2();
                    //builder.setTitle("Verificacion de identidad");
                    //builder.setMessage("Hola! " + result.getContents() + "\n" + "Datos: ");
                    //ivImagen.setImageURI(Uri.parse("http://2.bp.blogspot.com/-T7fUDGa1KUc/TbCb2o_7HXI/AAAAAAAABXs/n2OvUKOIPNk/s1600/estado.jpg"));
                    //AlertDialog alertDialog = builder.create();
                    //alertDialog.show();
                }
                else if(UURLCARNET.equals(C)){
                    //AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    Toast.makeText(this, "Alumno_C reconocido", Toast.LENGTH_LONG).show();
                    Log.i("TAG","Hola3");
                    identificar3();
                    //builder.setTitle("Verificacion de identidad");
                    //builder.setMessage("Hola! " + result.getContents() + "\n" + "Datos: ");
                    //ivImagen.setImageURI(Uri.parse("http://2.bp.blogspot.com/-T7fUDGa1KUc/TbCb2o_7HXI/AAAAAAAABXs/n2OvUKOIPNk/s1600/estado.jpg"));
                    //AlertDialog alertDialog = builder.create();
                    //alertDialog.show();
                }
                textViewQRD.setText(result.getContents());
                //Log.i("TAG","No_entro");
                //Log.i("TAG",UURLCARNET);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void identificar(){
        final Dialog dialog=new Dialog(IdentificacionActivity.this);
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.carnet_oskar2).build();
        dialog.setContentView(R.layout.carnet_detalle);
        ivImagen=(SimpleDraweeView)dialog.findViewById(R.id.ivCarnet);

        dialog.getWindow().setLayout(700,800); //Primero es ancho, el segundo es el alto
        ivImagen.setImageURI(Uri.parse("res:/"+R.drawable.carnet_oskar2));
        //ivImagen.setImageURI(imageRequest.getSourceUri());
        //Toast.makeText(this, "Entramos otra vez", Toast.LENGTH_SHORT).show();
        dialog.show();
    }

    private void identificar2(){
        final Dialog dialog=new Dialog(IdentificacionActivity.this);
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.carnet_oskar2).build();
        dialog.setContentView(R.layout.carnet_detalle);
        ivImagen=(SimpleDraweeView)dialog.findViewById(R.id.ivCarnet);

        dialog.getWindow().setLayout(700,800); //Primero es ancho, el segundo es el alto
        ivImagen.setImageURI(Uri.parse("res:/"+R.drawable.carnet_israel));
        //ivImagen.setImageURI(imageRequest.getSourceUri());
        //Toast.makeText(this, "Entramos otra vez", Toast.LENGTH_SHORT).show();
        dialog.show();
    }

    private void identificar3(){
        final Dialog dialog=new Dialog(IdentificacionActivity.this);
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.carnet_oskar2).build();
        dialog.setContentView(R.layout.carnet_detalle);
        ivImagen=(SimpleDraweeView)dialog.findViewById(R.id.ivCarnet);

        dialog.getWindow().setLayout(700,800); //Primero es ancho, el segundo es el alto
        ivImagen.setImageURI(Uri.parse("res:/"+R.drawable.carnet_salazar));
        //ivImagen.setImageURI(imageRequest.getSourceUri());
        //Toast.makeText(this, "Entramos otra vez", Toast.LENGTH_SHORT).show();
        dialog.show();
    }



}
