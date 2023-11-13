package com.celes.botanysage.mlModelClasses;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.celes.botanysage.R;
import com.celes.botanysage.helpers.ImageHelperActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.linkfirebase.FirebaseModelSource;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.custom.CustomImageLabelerOptions;

import java.util.List;

public class FlowerModel extends ImageHelperActivity {
    ImageLabeler imageLabeler;
    CustomImageLabelerOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Flower Detector");

        CustomRemoteModel remoteModel = new CustomRemoteModel.Builder(new FirebaseModelSource
                .Builder("flowerModel").build()).build();

        DownloadConditions downloadConditions = new DownloadConditions.Builder().build();
        RemoteModelManager.getInstance().download(remoteModel, downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        options = new CustomImageLabelerOptions.Builder(remoteModel)
                                .setConfidenceThreshold(0.7f)
                                .setMaxResultCount(5).build();
                        imageLabeler = ImageLabeling.getClient(options);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FlowerModel.this, "Try again later", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void runClassification(Bitmap bitmap) {
        InputImage inputImage =  InputImage.fromBitmap(bitmap, 0);
        imageLabeler.process(inputImage).addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
            @Override
            public void onSuccess(List<ImageLabel> imageLabels) {
                if(imageLabels.size()>0){
                    StringBuilder builder = new StringBuilder();
                    for(ImageLabel label : imageLabels){
                        float conf = label.getConfidence() * 100;
                        builder.append(label.getText()).append(" : ").append(conf).append("\n");
                    }
                    getOutputTextView().setText(builder.toString());
                }
                else{
                    getOutputTextView().setText(R.string.cantClassify);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Toast.makeText(FlowerModel.this, "not working", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
