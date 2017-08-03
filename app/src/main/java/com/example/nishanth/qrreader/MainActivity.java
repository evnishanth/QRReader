package com.example.nishanth.qrreader;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Camera;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.common.StringUtils;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    Button scanBtn;
    TextView displayTextview;
    ZXingScannerView scannerView;
    LinearLayout cameraLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button) findViewById(R.id.scan_button);
        displayTextview = (TextView) findViewById(R.id.display_textview);
        cameraLayout = (LinearLayout) findViewById(R.id.camera_layout);

        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(this);
        cameraLayout.addView(scannerView);
        scannerView.startCamera();

        scanBtn.setText("SCAN");
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (scanBtn.getText() == "SCAN") {
                    cameraLayout.setVisibility(View.VISIBLE);
                    //displayTextview.setVisibility(View.GONE);
                    displayTextview.setVisibility(View.GONE);
                    scanBtn.setText("CANCEL");
                } else {
                    //cameraLayout.removeAllViews();
                    //scannerView.stopCamera();
                    cameraLayout.setVisibility(View.GONE);
                    displayTextview.setVisibility(View.GONE);
                    scanBtn.setText("SCAN");
                }
            }


        });

    }


    //    public void openScanner(View v){
//        scannerView = new ZXingScannerView(this);
////        scannerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
////                LinearLayout.LayoutParams.MATCH_PARENT));
//        cameraLayout.setVisibility(View.VISIBLE);
//        cameraLayout.addView(scannerView);
//        scannerView.setResultHandler(this);
//        scannerView.startCamera();
//    }
    @Override
    protected void onPause() {
        super.onPause();
        scannerView.resumeCameraPreview(this);
    }

    @Override
    public void handleResult(Result result) {

        displayTextview.setText(result.toString());
        scannerView.resumeCameraPreview(this);
        cameraLayout.setVisibility(View.VISIBLE);
        scannerView.startCamera();
        //cameraLayout.removeAllViews();
        //scannerView.stopCamera();
        //scanBtn.setText("CANCEL");
        displayTextview.setVisibility(View.VISIBLE);

    }


}
