package es.iessaladillo.pedrojoya.pr01.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
    private String clasification;

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

        lblResult.setText("");
        txtWeight.requestFocus();
        btnCalculate.setOnClickListener(v -> calculateBMI());
        btnReset.setOnClickListener(v -> reset());

    }

    /**
     * Reset all the fields, images and texts.
     */
    private void reset() {
        txtWeight.setText("");
        txtHeight.setText("");
        lblResult.setText("");
        imgBmi.setImageResource(R.drawable.bmi);
        SoftInputUtils.hideKeyboard(txtHeight);
        txtWeight.setError(null);
        txtHeight.setError(null);

    }

    /**
     * This calculate BMI based on the others methods and show it to the user
     */
    private void calculateBMI() {
        BmiCalculator bmiCalculator = new BmiCalculator();
        float weight, height, result;

        if (validate()) {
            weight = Float.parseFloat(txtWeight.getText().toString());
            height = Float.parseFloat(txtHeight.getText().toString());
            result = bmiCalculator.calculateBmi(weight, height);

            getImageClasification(bmiCalculator.getBmiClasification(result));
            lblResult.setText(getString(R.string.main_bmi, result, clasification));
            SoftInputUtils.hideKeyboard(txtHeight);
        }
    }

    /**
     * @param bmiClasification BMI
     * Get clasification and image
     */
    private void getImageClasification(BmiCalculator.BmiClasification bmiClasification) {
        switch (bmiClasification) {
            case LOW_WEIGHT:
                this.clasification = getString(R.string.main_underweight);
                imgBmi.setImageResource(R.drawable.underweight);
                break;
            case NORMAL_WEIGHT:
                this.clasification = getString(R.string.main_normal);
                imgBmi.setImageResource(R.drawable.normal_weight);
                break;
            case OVERWWEIGHT:
                this.clasification = getString(R.string.main_overweight);
                imgBmi.setImageResource(R.drawable.overweight);
                break;
            case OBESITY_GRADE_1:
                this.clasification = getString(R.string.main_obesity1);
                imgBmi.setImageResource(R.drawable.obesity1);
                break;
            case OBESITY_GRADE_2:
                this.clasification = getString(R.string.main_obesity2);
                imgBmi.setImageResource(R.drawable.obesity2);
                break;
            case OBESITY_GRADE_3:
                this.clasification = getString(R.string.main_obesity3);
                imgBmi.setImageResource(R.drawable.obesity3);
                break;
        }
    }

    /**
     * Check if the edittexts are valid
     *
     * @return A boolean depends if the EditTexts are valid
     */
    private boolean validate() {
        boolean flag = true;

        if (txtHeight.getText().toString().isEmpty() || Integer.parseInt(txtHeight.getText().toString()) <= 0) {
            txtHeight.setError(getString(R.string.main_invalid_height));
            flag = false;
        }

        if (txtWeight.getText().toString().isEmpty() || Integer.parseInt(txtWeight.getText().toString()) <= 0) {
            txtWeight.setError(getString(R.string.main_invalid_weight));
            flag = false;
        }
        return flag;
    }
}