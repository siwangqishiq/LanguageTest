package com.xinlan.language;

import java.util.Locale;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;

public class MainActivity extends Activity {
	private Button mChBtn,mEgBtn,mJpBtn,mGotoBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp=MainActivity.this.getSharedPreferences("lan", Context.MODE_PRIVATE);
        String lan=sp.getString("lan", "ch");
        setLanguage(lan);
        init();
    }
    
    private void setLanguage(String lang) {  
        String languageToLoad = lang;  
        Locale locale = new Locale(languageToLoad);  
        Locale.setDefault(locale);  
        Configuration config = new Configuration();  
        config.locale = locale;  
        getBaseContext().getResources().updateConfiguration(config, null);  
    }  
    
    private void init(){
    	mChBtn=(Button)findViewById(R.id.ch);
    	mEgBtn=(Button)findViewById(R.id.eng);
    	mGotoBtn=(Button)findViewById(R.id.go);
    	mJpBtn=(Button)findViewById(R.id.jp);
    	mChBtn.setOnClickListener(new SetLan("ch"));
    	mEgBtn.setOnClickListener(new SetLan("en"));
    	mJpBtn.setOnClickListener(new SetLan("jp"));
    }
    
    private class SetLan implements OnClickListener{
    	private String lan;
    	public SetLan(String lan){
    		this.lan=lan;
    	}
    	
		public void onClick(View v) {
			setLanguage(lan);
			SharedPreferences sp=MainActivity.this.getSharedPreferences("lan", Context.MODE_PRIVATE);
			 Editor e=sp.edit();
			 e.putString("lan", lan);
			 e.commit();
			Intent it=new Intent();
			it.setClass(MainActivity.this, MainActivity.class);
			MainActivity.this.startActivity(it);
			MainActivity.this.finish();
		}
    }//end class
}//end class
