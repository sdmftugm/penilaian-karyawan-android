package andevindo.com.penilaiankaryawan.View.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import andevindo.com.penilaiankaryawan.R;

/**
 * Created by heendher on 8/18/2016.
 */
public abstract class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context);

    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(dialogViewId());
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
        getWindow().getAttributes().windowAnimations = R.style.GivePointDialogAnimation;
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogReady();
    }

    protected abstract int dialogViewId();

    protected abstract void dialogReady();

}
