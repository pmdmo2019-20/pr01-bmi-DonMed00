package es.iessaladillo.pedrojoya.pr01.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.text.DecimalFormat;

import es.iessaladillo.pedrojoya.pr01.R;
import es.iessaladillo.pedrojoya.pr01.bmi.BmiCalculator;
import es.iessaladillo.pedrojoya.pr01.utils.SoftInputUtils;

public class MainActivity extends AppCompatActivity {

    private TextView txtWeight;
    private TextView txtHeight;
    private Button btnReset;
    private Button btnCalculate;
    private TextView lblResult;
    private ImageView imgBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setupViews();


    }

    private void setupViews() {
        txtWeight = ActivityCompat.requireViewById(this, R.id.txtWeight);
        txtHeight = ActivityCompat.requireViewById(this, R.id.txtHeight);
        btnReset = ActivityCompat.requireViewById(this, R.id.btnReset);
        btnCalculate = ActivityCompat.requireViewById(this, R.id.btnCalculate);
        lblResult = ActivityCompat.requireViewById(this, R.id.lblResult);
        imgBmi = ActivityCompat.requireViewById(this, R.id.imgBmi);
        txtWeight.requestFocus();
        btnCalculate.setOnClickListener(v -> calculateBMI());
        btnReset.setOnClickListener(v -> reset());


    }

    private void reset() {
        txtWeight.setText("");
        txtHeight.setText("");
        lblResult.setText("");
    }

    private void calculateBMI() {
        BmiCalculator bmiCalculator = new BmiCalculator();
        float weight, height, result;
        String clasification;
        boolean flag = true;
        DecimalFormat formatoFloat = new DecimalFormat("#.00");


        if (!txtHeight.getText().toString().matches("[-+]?[0-9]*\\.?[0-9]+") || txtHeight.getText().toString().equals("")|| txtHeight.getText().toString().equals(" ")) {
            txtHeight.setError("This field must be a number");
            flag = false;
        }
        if (!txtWeight.getText().toString().matches("[-+]?[0-9]*\\.?[0-9]+") || txtWeight.getText().toString().equals("")|| txtWeight.getText().toString().equals(" ")) {
            txtWeight.setError("This field must be a number");
            flag = false;
        }
        if (flag) {
            weight = Float.parseFloat(txtWeight.getText().toString());
            height = Float.parseFloat(txtHeight.getText().toString());
            result = bmiCalculator.calculateBmi(weight, height);
            clasification = bmiCalculator.getBmiClasification(result).toString();

            lblResult.setText("BMI " + formatoFloat.format(result) + " " + clasification);
            SoftInputUtils.hideKeyboard(txtHeight);
        }


    }

    // TODO

}
