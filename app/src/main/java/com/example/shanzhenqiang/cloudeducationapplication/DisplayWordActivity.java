package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class DisplayWordActivity extends AppCompatActivity {

    private LinearLayout ll1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);


        //header's onclick event
        ll1 = (LinearLayout) findViewById(R.id.headerId);
        ImageButton backBtn = (ImageButton) ll1.findViewById(R.id.backId);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(DisplayWordActivity.this, CurriculumMaterialActivity.class);
                startActivity(intent);
            }
        });


        TextView textView = (TextView) findViewById(R.id.textWordId);

        //Intent 查看word文档 尝试
//        textView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                String uri = "http://7xsd7r.com1.z0.glb.clouddn.com/issues.docx";
//                Intent intent =  new Intent(DisplayWordActivity.getWordFileIntent(uri));
//                context.startActivity(intent);
//            }
//        });
        textView.setText("http://7xsd7r.com1.z0.glb.clouddn.com/issues.docx");

        Intent intent = this.getWordFileIntent("http://7xsd7r.com1.z0.glb.clouddn.com/issues.docx");
        System.out.println("IWouldLikeThisResult==="+intent);

    }

    // word 文档的查看 （Intent的形式）尝试
    public static Intent getWordFileIntent(String param) {
        Intent intent = null;
        try {
            intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "application/msword");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return intent;
    }

    private boolean isIntentAvailable(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, packageManager.GET_RESOLVED_FILTER);
        return list.size() > 0;
    }

    // word文档的查看 （iText尝试）
//    public void getWord(){
//        try {
//            Document document = new Document(PageSize.A4);
//            RtfWriter2.getInstance(document, new FileOutputStream("F:\\test.doc"));
//            document.open();
//            Paragraph title = new Paragraph("Hello human...");
//            document.add(title);
//            document.close();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//
//
//    }

}
