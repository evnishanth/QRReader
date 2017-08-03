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

    Button scanBtn, infoBtn;
    TextView displayTextview;
    ZXingScannerView scannerView;
    LinearLayout cameraLayout;
    int valueLen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button) findViewById(R.id.scan_button);
        infoBtn = (Button) findViewById(R.id.more_info_button);
        displayTextview = (TextView) findViewById(R.id.display_textview);
        cameraLayout = (LinearLayout) findViewById(R.id.camera_layout);

        infoBtn.setVisibility(View.GONE);
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
                    displayTextview.setText("");
                    infoBtn.setVisibility(View.GONE);
                }
            }


        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.resumeCameraPreview(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        scannerView.resumeCameraPreview(this);
    }

//    @Override
//    protected void onStop(){
//        super.onStop();
//        scannerView.stopCameraPreview();
//    }

//    @Override
//    protected void onRestart(){
//        super.onRestart();
//        scannerView.resumeCameraPreview(this);
//    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();
        scannerView.removeAllViews();
    }
    @Override
    public void handleResult(Result result) {

        displayTextview.setText(result.toString());
        scannerView.resumeCameraPreview(this);
        cameraLayout.setVisibility(View.VISIBLE);
        //scannerView.startCamera();
        //cameraLayout.removeAllViews();
        //scannerView.stopCamera();
        displayTextview.setVisibility(View.VISIBLE);
        valueLen = displayTextview.toString().length();
        if (valueLen == 0)
            infoBtn.setVisibility(View.GONE);
        else
            infoBtn.setVisibility(View.VISIBLE);

    }


}
