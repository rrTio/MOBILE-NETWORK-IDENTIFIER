package com.example.mobilenetworkidentifier;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationBar();

        Button btnIdentify = (Button) findViewById(R.id.btnIdentify);
        ImageButton btnClear = (ImageButton) findViewById(R.id.btnClear);
        final EditText editText = (EditText) findViewById(R.id.txtInput);
        final TextView textView = (TextView) findViewById(R.id.lblNetwork);
        final ImageView image = (ImageView) findViewById(R.id.viewNetwork);

        btnIdentify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    String num1 = editText.getText().toString();

                    if(num1.length() == 4)
                    {
                        String prefix = num1.substring(0,4);
                        if(Objects.equals(checkSim(prefix), "Smart"))
                        { image.setImageResource(R.drawable.smartlogo); Log.e("status", "SMART");}
                        else if(Objects.equals(checkSim(prefix), "Sun"))
                        { image.setImageResource(R.drawable.sunlogo); Log.e("status", "SUN");}
                        else if(Objects.equals(checkSim(prefix), "TNT"))
                        { image.setImageResource(R.drawable.tntlogo); Log.e("status", "TNT");}
                        else if(Objects.equals(checkSim(prefix), "Globe/TM"))
                        { image.setImageResource(R.drawable.globeortm); Log.e("status", "GLOBE/TM");}
                        else {error();}
                    }

                    else if(num1.length() == 5)
                    {
                     String prefixFive = num1.substring(0,5);
                     if(Objects.equals(checkSim(prefixFive), "Globe PostPaid"))
                     { image.setImageResource(R.drawable.globelogo); Log.e("status", "Globe PostPaid"); }
                     else {error();}
                    }

                    else {error();}
                } catch (Exception e) {errorText(); Log.e("status", "BLANK TEXT FIELD");}
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e("status", "CLEAR TEXT FIELD");
                textView.setText("INPUT YOUR MOBILE NUMBER");
                image.setImageResource(R.drawable.nosign);
                editText.setHint("NUMBER HERE");
                editText.getText().clear();
            }
        });
    }

    public static String checkSim(String prefix)
    {
        if(prefix.equals("0908") || prefix.equals("0918") || prefix.equals("0919") || prefix.equals("0920")
                || prefix.equals("0921") || prefix.equals("0928") || prefix.equals("0929") || prefix.equals("0939")
                || prefix.equals("0998")) {return "Smart";}

        if(prefix.equals("0922") || prefix.equals("0923") || prefix.equals("0924") || prefix.equals("0925")
                || prefix.equals("0931") || prefix.equals("0932") || prefix.equals("0933") || prefix.equals("0934")
                || prefix.equals("0940") || prefix.equals("0941") || prefix.equals("0942") || prefix.equals("0943")
                || prefix.equals("0973") || prefix.equals("0974")) {return "Sun";}

        if(prefix.equals("0907") || prefix.equals("0909") || prefix.equals("0910") || prefix.equals("0912")
                || prefix.equals("0930") || prefix.equals("0938") || prefix.equals("0946") || prefix.equals("0948")
                || prefix.equals("0950")) {return "TNT";}

        if(prefix.equals("0817") || prefix.equals("0905") || prefix.equals("0906") || prefix.equals("0915") || prefix.equals("0916")
                || prefix.equals("0917") || prefix.equals("0926") || prefix.equals("0927") || prefix.equals("0935")
                || prefix.equals("0936") || prefix.equals("0937") || prefix.equals("0945") || prefix.equals("0953")
                || prefix.equals("0954") || prefix.equals("0955") || prefix.equals("0956") || prefix.equals("0965")
                || prefix.equals("0966") || prefix.equals("0967") || prefix.equals("0975") || prefix.equals("0977")
                || prefix.equals("0978") || prefix.equals("0979") || prefix.equals("0995") || prefix.equals("0996")
                || prefix.equals("0997")) {return "Globe/TM";}

        if(prefix.equals("09173") || prefix.equals("09175") || prefix.equals("09176") || prefix.equals("09178") || prefix.equals("09253")
                || prefix.equals("09255") || prefix.equals("09256") || prefix.equals("09257") || prefix.equals("09258")) {return "Globe PostPaid";}
        else {return null;}
    }

    public void error()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("ERROR"); builder.setMessage("INVALID MOBILE NUMBER");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        { @Override public void onClick(DialogInterface dialog, int which) { dialog.dismiss(); } });
        AlertDialog dialog = builder.create(); dialog.show();
    }

    public void errorText()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("ERROR"); builder.setMessage("PLEASE ENTER A VALID NUMBER");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        { @Override public void onClick(DialogInterface dialog, int which) { dialog.dismiss(); } });
        AlertDialog dialog = builder.create(); dialog.show();
    }

    public void navigationBar()
    {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}