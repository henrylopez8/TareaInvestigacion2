package sv.edu.udb.cumuniversidaddonbosco;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.nio.channels.Channel;
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

    private void setPendingIntent(Class<?> clsActivity)
    {
        Intent intent = new Intent(this,clsActivity);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(clsActivity);
        stackBuilder.addNextIntent(intent);
        stackBuilder.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);
    }

  /*  private void showNotification(String msg)
    {
        NotificationChannel channel = new NotificationChannel(NotificationChannel.DEFAULT_CHANNEL_ID,"new",
                NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        ShowNewNotification(msg);
    }*/

   /* private void ShowNewNotification(String msg)
    {
        setPendingIntent(HomeActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),
                CHANNEL_ID).
                setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Calculo de CUM - UDB")
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat managerCompact =  NotificationManagerCompat.from(getApplicationContext());
        managerCompact.notify(1,builder.build());

    }*/

    EditText notas;
    EditText uv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificacion= new NotificationCompat.Builder(getApplicationContext());
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

                        setPendingIntent(MainActivity.class);
                        notificacion.setSmallIcon(R.mipmap.ic_launcher);
                        notificacion.setTicker("Registro del CUM");
                        notificacion.setPriority(Notification.PRIORITY_HIGH);
                        notificacion.setWhen(System.currentTimeMillis());
                        notificacion.setContentTitle("Registro de CUM");
                        notificacion.setContentText(resultado.getText());
                        NotificationManagerCompat noti = NotificationManagerCompat.from(getApplicationContext());
                        noti.notify(idunica,notificacion.build());


                    }
                });

            }
        });







    }
}