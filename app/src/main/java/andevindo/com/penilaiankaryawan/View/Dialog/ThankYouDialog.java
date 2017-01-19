package andevindo.com.penilaiankaryawan.View.Dialog;

import android.content.Context;
import android.os.Handler;

import andevindo.com.penilaiankaryawan.R;

/**
 * Created by heendher on 8/23/2016.
 */
public class ThankYouDialog extends BaseDialog {

    public ThankYouDialog(Context context) {
        super(context);
    }

    @Override
    protected int dialogViewId() {
        return R.layout.dialog_thank_you;
    }

    @Override
    protected void dialogReady() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 1500);
    }
}
