package sv.edu.udb.cumuniversidaddonbosco;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText NumMaterias;
    Button btnIngresar;
    double  UM=0;
    double TotalUV=0;
    int num=5;
    float count=5;
    List<EditText> ArrayNotas = new ArrayList<EditText>();
    List<EditText> ArrayUV = new ArrayList<EditText>();
    NotificationCompat.Builder notificacion;
    private static final int idunica=001;


    EditText notas;
    EditText uv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificacion= new NotificationCompat.Builder(this);
        notificacion.setAutoCancel(true);
        LinearLayout linearLayout =(LinearLayout) findViewById(R.id.layout);
        NumMaterias= (EditText) findViewById(R.id.NumeroMaterias);
        btnIngresar=(Button) findViewById(R.id.btningresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                for (int i = 0; i < Integer.parseInt(NumMaterias.getText().toString()); i++) {

                    notas = new EditText(MainActivity.this);
                    uv = new EditText(MainActivity.this);
                    notas.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                    uv.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

                    notas.setHint("Nota de la materia "+(i+1));
                    uv.setHint("Uv de la materia "+(i+1));

                    ArrayNotas.add(notas);
                    ArrayUV.add(uv);
                    notas.setContentDescription("Nota "+i); //Id para identificarlo
                    uv.setContentDescription("uv "+i); //Id para identificarlo

                    linearLayout.addView(notas);
                    linearLayout.addView(uv);

                }
                Button boton = new Button(MainActivity.this);
                boton.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                boton.setText("Calcular");
                linearLayout.addView(boton);
                TextView resultado = new TextView(MainActivity.this);
                resultado.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));


                linearLayout.addView(resultado);


                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        for (int i = 0; i < Integer.parseInt(NumMaterias.getText().toString()); i++) {

                            notas.getContentDescription();
                           UM=((Double.parseDouble(ArrayNotas.get(i).getText().toString()))*(Double.parseDouble(ArrayUV.get(i).getText().toString())))+UM;
                          TotalUV=Double.parseDouble(ArrayUV.get(i).getText().toString())+TotalUV;

                            resultado.setText("CUM: "+(UM/TotalUV));

                        }
                        notificacion.setSmallIcon(R.mipmap.ic_launcher);
                        notificacion.setTicker("Registro del CUM");
                        notificacion.setPriority(Notification.PRIORITY_HIGH);
                        notificacion.setWhen(System.currentTimeMillis());
                        notificacion.setContentTitle("Titulo");
                        notificacion.setContentText("Hay un nuevo registro del CUM!");
                        Intent intent = new Intent(MainActivity.this,MainActivity.class);

                        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        notificacion.setContentIntent(pendingIntent);
                        NotificationManager noti = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        noti.notify(idunica,notificacion.build());


                    }
                });

            }
        });







    }
}