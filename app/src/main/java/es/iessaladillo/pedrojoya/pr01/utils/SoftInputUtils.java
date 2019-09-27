package es.iessaladillo.pedrojoya.pr01.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SoftInputUtils {

    private SoftInputUtils() {
    }

    public static void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
