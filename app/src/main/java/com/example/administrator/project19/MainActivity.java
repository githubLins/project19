package com.example.administrator.project19;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    Button btW,btR;

    String MyFileName="myfile";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btW=(Button)findViewById(R.id.btnWrite);
        btR=(Button)findViewById(R.id.btnRead);

        btW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out=null;
                try {
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        File file=Environment.getExternalStorageDirectory();
                        File myfile=new File(file.getCanonicalPath()+"/"+MyFileName);

                        FileOutputStream fileOutputStream=new FileOutputStream(myfile);
                        out=new BufferedOutputStream(fileOutputStream);
                        String content="lifalin" + " " + "2014011339";
                        try {
                            out.write(content.getBytes(StandardCharsets.UTF_8));
                        }finally {
                            if (out!=null)
                                out.close();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        btR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in=null;
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        File file=Environment.getExternalStorageDirectory();
                        File myfile=new File(file.getCanonicalPath()+"/"+MyFileName);
                        FileInputStream fileInputStream=new FileInputStream(myfile);
                        in=new BufferedInputStream(fileInputStream);
                        int c;
                        StringBuilder stringBuilder=new StringBuilder("");
                        try {
                            while ((c=in.read())!=1){
                                stringBuilder.append((char) c);
                            }
                            Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
                        }finally {
                            if (in !=null)
                                in.close();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}