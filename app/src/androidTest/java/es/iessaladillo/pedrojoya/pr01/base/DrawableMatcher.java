package es.iessaladillo.pedrojoya.pr01.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

@SuppressWarnings("SimplifiableIfStatement")
public class DrawableMatcher extends TypeSafeMatcher<View> {

    private final int expectedResourceId;

    public DrawableMatcher(@DrawableRes int expectedResourceId) {
        super(View.class);
        this.expectedResourceId = expectedResourceId;
    }

    @Override
    protected boolean matchesSafely(View item) {
        if (!(item instanceof ImageView)) return false;

        ImageView imageView = (ImageView) item;
        if (expectedResourceId == 0) return imageView.getDrawable() == null;

        Drawable expectedDrawable = ContextCompat.getDrawable(item.getContext(), expectedResourceId);
        if (expectedDrawable == null) return false;

        Drawable actualDrawable = imageView.getDrawable();

        if (expectedDrawable instanceof VectorDrawable) {
            if (!(actualDrawable instanceof VectorDrawable)) return false;
            return vectorToBitmap((VectorDrawable) expectedDrawable).sameAs(vectorToBitmap((VectorDrawable) actualDrawable));
        }

        if (expectedDrawable instanceof BitmapDrawable) {
            if (!(actualDrawable instanceof BitmapDrawable)) return false;
            return ((BitmapDrawable) expectedDrawable).getBitmap().sameAs(((BitmapDrawable) actualDrawable).getBitmap());
        }

        throw new IllegalArgumentException("Unsupported drawable: " + imageView.getDrawable());
    }

    private static Bitmap vectorToBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("with drawable id: ").appendValue(expectedResourceId);
    }

}
