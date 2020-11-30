package com.dji.uxsdkdemo;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    //StorageReference mStorageRef;
    //public Uri imguri;

    //private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // When the compile and target version is higher than 22, please request the
        // following permissions at runtime to ensure the
        // SDK work well.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.VIBRATE,
                            Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.WAKE_LOCK, Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW,
                            Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA,

                    }
                    , 1);
        }

        setContentView(R.layout.activity_main);


    }
    @Override
    protected void onResume() {
        super.onResume();

        //mStorageRef = FirebaseStorage.getInstance().getReference("Image");
        // 以下為截圖程式
        ImageView ScreenShotView = (ImageView) findViewById(R.id.ScreenShotView);

        //View Widget = (View)  findViewById(R.id.FPVWidget);
        Button ScreenCapture = (Button) findViewById(R.id.ScreenCapture);
        ScreenCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date now = new Date();
                android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

                View v1 = getWindow().getDecorView().getRootView();


                v1.setDrawingCacheEnabled(true);

                Bitmap b = Bitmap.createBitmap(v1.getDrawingCache());
                v1.setDrawingCacheEnabled(false);
                ScreenShotView.setImageBitmap(b);

                String mPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DroneFiles";
                File m = new File(mPath);
                if (!m.exists()) {
                    m.mkdirs();
                }
                File imageFile = new File(m, "ScreenShot" + now + ".jpg");
                //imguri = Uri.fromFile(imageFile);

                try {
                    FileOutputStream outputStream = new FileOutputStream(imageFile);
                    int quality = 100;
                    b.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    Toast.makeText(MainActivity.this,"已存入內部儲存空間",Toast.LENGTH_SHORT).show();
                    openScreenshot(imageFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button UploadFirebase = (Button) findViewById(R.id.UploadFirebase);
        UploadFirebase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Fileuploader();


            }
        });


        Button btn_capture = (Button) findViewById(R.id.btn_capture);
        btn_capture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //captureAction();


            }
        });
    }



//    private  String getExtension(Uri uri){
//        ContentResolver cr = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
//    }
//    private void Fileuploader()
//    {
//        StorageReference Ref = mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imguri));
//        Ref.putFile(imguri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        // Get a URL to the uploaded content
//                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                        Toast.makeText(MainActivity.this,"圖片上傳成功",Toast.LENGTH_LONG).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle unsuccessful uploads
//                        // ...
//                        Toast.makeText(MainActivity.this,"圖片上傳失敗",Toast.LENGTH_LONG).show();
//                    }
//                });
//    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    //拍照
//    private void captureAction(){
//
//        final Camera camera = DemoApplication.getCameraInstance();
//        if (camera != null) {
//
//            SettingsDefinitions.ShootPhotoMode photoMode = SettingsDefinitions.ShootPhotoMode.SINGLE; // Set the camera capture mode as Single mode
//            camera.setShootPhotoMode(photoMode, new CommonCallbacks.CompletionCallback(){
//                @Override
//                public void onResult(DJIError djiError) {
//                    if (null == djiError) {
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                camera.startShootPhoto(new CommonCallbacks.CompletionCallback() {
//                                    @Override
//                                    public void onResult(DJIError djiError) {
//                                        if (djiError == null) {
//                                            //showToast("take photo: success");
//                                            Toast.makeText(MainActivity.this,"拍照成功",Toast.LENGTH_SHORT).show();
//                                        } else {
//                                            //showToast(djiError.getDescription());
//                                            Toast.makeText(MainActivity.this,djiError.getDescription(),Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//                            }
//                        }, 2000);
//                    }
//                }
//            });
//        }
//    }

}

