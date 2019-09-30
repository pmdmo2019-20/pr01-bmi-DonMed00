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
    private String overweight;

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
        imgBmi.setImageResource(R.drawable.bmi);
        SoftInputUtils.hideKeyboard(txtHeight);
        txtWeight.setError(null);
        txtHeight.setError(null);

    }

    private void calculateBMI() {
        BmiCalculator bmiCalculator = new BmiCalculator();
        float weight, height, result;
        DecimalFormat formatoFloat = new DecimalFormat("#.00");

        if (validate()) {
            weight = Float.parseFloat(txtWeight.getText().toString());
            height = Float.parseFloat(txtHeight.getText().toString());
            result = bmiCalculator.calculateBmi(weight, height);
            imgBmi.setImageResource(getImageClasification(bmiCalculator.getBmiClasification(result)));
            lblResult.setText("BMI: " + formatoFloat.format(result) + " " + overweight);

            SoftInputUtils.hideKeyboard(txtHeight);
        }


    }


    private int getImageClasification(BmiCalculator.BmiClasification clasification) {
        int imageID = 0;
        switch (clasification) {
            case LOW_WEIGHT:
                overweight = "Underweight";
                imageID = R.drawable.underweight;
                break;
            case NORMAL_WEIGHT:
                overweight = "Normal";
                imageID = R.drawable.normal_weight;
                break;
            case OVERWWEIGHT:
                overweight = "Overweight";
                imageID = R.drawable.overweight;
                break;
            case OBESITY_GRADE_1:
                overweight = "Obesity Class 1";
                imageID = R.drawable.obesity1;
                break;
            case OBESITY_GRADE_2:
                overweight = "Obesity Class 2";
                imageID = R.drawable.obesity2;
                break;
            case OBESITY_GRADE_3:
                overweight = "Obesity Class 3";
                imageID = R.drawable.obesity3;
                break;
        }
        return imageID;
    }

    private boolean validate() {
        String main_invalid_weight, main_invalid_height;
        main_invalid_weight = getString(R.string.main_invalid_weight);
        main_invalid_height = getString(R.string.main_invalid_height);
        boolean flag = true;


        if (txtHeight.getText().toString().equals("") || txtHeight.getText().toString().equals(" ") || txtHeight.getText().toString().equals("0")) {
            txtHeight.setError(main_invalid_height);
            flag = false;
        }


        if (txtWeight.getText().toString().equals("") || txtWeight.getText().toString().equals(" ") || txtWeight.getText().toString().equals("0")) {
            txtWeight.setError(main_invalid_weight);
            flag = false;
        }

        return flag;
    }

}
