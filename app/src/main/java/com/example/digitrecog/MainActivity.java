package com.example.digitrecog;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nex3z.fingerpaintview.FingerPaintView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    FingerPaintView input;
    Button predict;
    TextView output;


    private Detector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input=findViewById(R.id.inputBox);
        predict=findViewById(R.id.predictButton);
        output=findViewById(R.id.outputText);

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                init();
                if (detector == null) {
                    Log.e(LOG_TAG, "onDetectClick(): Classifier is not initialized");
                    return;
                } else if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No input", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bitmap image = input.exportToBitmap(
                        Detector.IMG_WIDTH, Detector.IMG_HEIGHT);
                Output result = detector.classify(image);
                output.setText(result.getNumber()+"");

                input.clear();
            }
        });
    }

    private void init() {
        try {
            detector = new Detector(MainActivity.this);
        } catch (IOException e) {
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
            Log.e(LOG_TAG, "init(): Failed to create Classifier", e);
        }
    }

}