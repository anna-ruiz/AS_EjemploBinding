package anna.ruiz.as_ejemplobinding;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import anna.ruiz.as_ejemplobinding.Modelos.Alumno;
import anna.ruiz.as_ejemplobinding.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> addAlumnoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        inicializarLauncher();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ABRIR LA ACTIVIDAD ALUMNO para recibir la informacion
                addAlumnoLauncher.launch(new Intent(MainActivity.this, AddAlumnoActivity.class));


            }
        });
    }

    private void inicializarLauncher() {
        addAlumnoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){

                            if (result.getData() != null && result.getData().getExtras() != null){
                                Alumno alumno = (Alumno) result.getData().getExtras().getSerializable("ALUMNO");
                                Toast.makeText(MainActivity.this, alumno.toString(), Toast.LENGTH_SHORT).show();
                                //FALTA MOSTRAR LA INFO
                            }else {
                                Toast.makeText(MainActivity.this, "No hay información", Toast.LENGTH_SHORT).show();
                            }


                        }else {
                            Toast.makeText(MainActivity.this, "Acción Cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }


}