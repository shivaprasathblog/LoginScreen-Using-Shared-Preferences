package com.example.loginscreenusingsharedpreferences;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	protected static final String MYDataSaved = "mydatainsharedpreference";
	// Defining the Edittext ,button,checkbox for username,password,signin,clear
	// and remember me
	EditText username_et, password_et;
	Button signin_b, signout_b;
	CheckBox rememberme_cb;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Refering the username and password from XML
		username_et = (EditText) findViewById(R.id.id_username);
		password_et = (EditText) findViewById(R.id.id_password);
		// Refering the signin and clear button from XML
		signin_b = (Button) findViewById(R.id.id_signin);
		signout_b = (Button) findViewById(R.id.id_signout);
		// Refering the remember me checkbox from XML
		rememberme_cb = (CheckBox) findViewById(R.id.id_rememberme);

		// Shared preference
		SharedPreferences sp = getSharedPreferences("MYDataSaved",Context.MODE_PRIVATE);

		String savedname = sp.getString("key_username", "");
		String savedpassword = sp.getString("key_password", "");

		username_et.setText(savedname);
		password_et.setText(savedpassword);

		signin_b.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				String strName = username_et.getText().toString();
				String strPass = password_et.getText().toString();
				// If the Username is Empty
				if (null == strName || strName.trim().length() == 0) 
				{
					username_et.setError("Please Enter Your Name");
					username_et.requestFocus();
				}
				// If the password is Empty
				else if (null == strPass || strPass.trim().length() == 0)
				{
					password_et.setError("Please Enter Your Password");
					password_et.requestFocus();
				}
				// If the Username & Password is not Empty
				else
					// If the Remember me Check box is Checked
					rememberme_cb.setOnCheckedChangeListener(new OnCheckedChangeListener() 
					{
								String strName = username_et.getText().toString();
									
								String strPass = password_et.getText().toString();
									

								@Override
								public void onCheckedChanged(CompoundButton arg0, boolean ischecked)
										 
								{
									// TODO Auto-generated method stub
									if (ischecked == true) 
									{
										// To show the Toast Message if remember
										// me checkbox is checked
										Toast.makeText(MainActivity.this,"User Name and Password is Saved in Shared Preference",Toast.LENGTH_LONG).show();
										SharedPreferences sp = getSharedPreferences(MYDataSaved,Context.MODE_PRIVATE);
										sp.edit().putString("name", strName).putString("pwd", strPass).commit();
										Toast.makeText(	MainActivity.this,"Exit from App and Restart to see the Effect of Shared Preference",Toast.LENGTH_LONG).show();												

									}
									// If the Rememberme checkbox is not Checked
									else 
									{

										Toast.makeText(MainActivity.this,"Tick the Rememberme checkbox to Save the Username and Password in Shared Preference",Toast.LENGTH_LONG).show();
										SharedPreferences sp = getSharedPreferences(MYDataSaved,Context.MODE_PRIVATE);
										
										// Accessing the Editor to remove the
										// Data saved in Shared preference if we
										// don't tick the checkbox rememberme
										// and at last we should commit it to
										// make the effect of removing data from
										// shared preference
										sp.edit().remove("name").remove("pwd").clear().commit();
										Toast.makeText(MainActivity.this,"Data is Cleared",	Toast.LENGTH_LONG).show();

									}

								}
							});

			}
		});
		// If Clear button is clicked
		signout_b.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				// Making Username as Empty
				username_et.setText("");
				username_et.requestFocus();
				// Making Password as Empty
				password_et.setText("");
				// Making the Remember me Check box unchecked
				rememberme_cb.setChecked(false);
				SharedPreferences sp = getSharedPreferences(MYDataSaved,Context.MODE_PRIVATE);					
				// Accessing the Editor to clear the Data saved in Shared
				// preference and removing the Saved Data and at last commiting
				// it to see the effect to clear the saved data
				sp.edit().clear().remove("name").remove("pwd").commit();
			}
		});

	}

}
