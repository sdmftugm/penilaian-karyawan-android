package andevindo.com.penilaiankaryawan.View.Custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by heendher on 8/20/2016.
 */
public class PasFotoImageView extends ImageView {

    public PasFotoImageView(Context context) {
        super(context);
    }

    public PasFotoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PasFotoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasFotoImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
