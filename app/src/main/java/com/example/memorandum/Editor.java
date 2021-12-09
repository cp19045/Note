package com.example.memorandum;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import com.example.memorandum.Dao.NoteDao;
import com.example.memorandum.Dao.NoteDaoLmp;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class Editor extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private File file2;
    private Context context;
    private Uri cramuri;
    private static final int RC_EXTERNAL_STORAGE_PERM = 100;
    private File currentImageFile = null;
    private EditText titleView;
    private EditText textView;
    private Spinner typeView;
    private ImageView imageView;
    private String HeadImage=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        //工具条
        context=this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //向上按钮
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        requiresPermission();
        titleView = findViewById(R.id.edit_title);
        textView =findViewById(R.id.edit_text);
        typeView =findViewById(R.id.spinner);
        imageView = findViewById(R.id.imageView);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);//打开新页面
        startActivity(intent);
        //super.onBackPressed();
    }

    public void onClickDone(View view){
        CharSequence textSequence = "保存成功!";
        int duration = Toast.LENGTH_SHORT;

        String title = titleView.getText().toString();
        String text = textView.getText().toString();
        String typeName = String.valueOf(typeView.getSelectedItem());
        if(typeName.equals("学习")){
            if(HeadImage==null){
                NoteDao noteDao = new NoteDaoLmp(getApplicationContext());
                noteDao.addNote(new Note(title, text, typeName));
            }else{
                NoteDao noteDao = new NoteDaoLmp(getApplicationContext());
                noteDao.addNote(new Note(title, text, typeName,HeadImage));
            }
        }
        else if (typeName.equals("生活")){
            if(HeadImage==null){
                NoteDao noteDao = new NoteDaoLmp(getApplicationContext());
                noteDao.addNote(new Note(title, text, typeName));
            }else{
                NoteDao noteDao = new NoteDaoLmp(getApplicationContext());
                noteDao.addNote(new Note(title, text, typeName,HeadImage));
            }
        }
        else if (typeName.equals("其他")){
            if(HeadImage==null){
                NoteDao noteDao = new NoteDaoLmp(getApplicationContext());
                noteDao.addNote(new Note(title, text, typeName));
            }else{
                NoteDao noteDao = new NoteDaoLmp(getApplicationContext());
                noteDao.addNote(new Note(title, text, typeName,HeadImage));
            }
        }
        textView.setText(null);
        titleView.setText(null);


        //Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("name",1);
        //startActivity(intent);

        Toast toast = Toast.makeText(this, textSequence, duration);
        toast.show();
    }

    public void onClickDones(View view){
        callGallery();
    }

    private void callGallery() {
        final String[] getPictureWays = new String[]{"相册", "相机"};
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setItems(getPictureWays, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                btablum();
                                break;
                            case 1:
                                btcamera();
                                break;
                        }
                    }
                }).create();
        dialog.show();
    }

    private void btablum(){
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent,1);
    }

    private  void btcamera(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            currentImageFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + ".jpg");
        }
        // 启动系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cramuri = FileProvider.getUriForFile(context, "com.example.memorandum.fileprovider", currentImageFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            cramuri = Uri.fromFile(currentImageFile);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cramuri);
        startActivityForResult(intent,2);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 调用相机后返回
            case 1:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // 得到图片的全路径
                        Uri uri = data.getData();
                        startPhotoZoom(uri);
                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    startPhotoZoom(cramuri);
                }
                break;
            case 3:
                imageView.setImageURI(Uri.fromFile(file2));
                String file = ImageUtils.getRealPathFromUri(context, Uri.fromFile(file2));
                HeadImage=file;
        }
    }

    //裁剪并压缩图片
    public void startPhotoZoom(Uri uri) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file2 = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + ".jpg");
        }
        Intent intent = new Intent("com.android.camera.action.CROP"); // 裁剪图片意图
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);//裁剪框 X 比值
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);//裁剪后输出宽度
        intent.putExtra("outputY", 600);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file2));
        intent.putExtra("return-data", false); //是否在Intent中返回数据
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        startActivityForResult(intent, 3);
    }

    private void requiresPermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(Editor.this, perms)) {
        } else {
            EasyPermissions.requestPermissions(this,  getResources().getString(R.string.tips_crema), RC_EXTERNAL_STORAGE_PERM, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        new AppSettingsDialog.Builder(this)
                .setTitle(getResources().getString(R.string.tips_limit))
                .setPositiveButton(getResources().getString(R.string.tips_limit_true))
                .setNegativeButton(getResources().getString(R.string.tips_limit_false))
                .setRationale(getResources().getString(R.string.tips_limit_crema))
                .setRequestCode(RC_EXTERNAL_STORAGE_PERM)
                .build()
                .show();
    }
}
