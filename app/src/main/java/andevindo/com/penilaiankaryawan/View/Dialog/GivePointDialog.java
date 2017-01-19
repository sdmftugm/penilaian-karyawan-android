package andevindo.com.penilaiankaryawan.View.Dialog;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import andevindo.com.penilaiankaryawan.Controller.MainController;
import andevindo.com.penilaiankaryawan.Model.User;
import andevindo.com.penilaiankaryawan.R;
import andevindo.com.penilaiankaryawan.View.Custom.PasFotoImageView;

/**
 * Created by heendher on 8/22/2016.
 */
public class GivePointDialog extends BaseDialog implements MainController.GivePointPresenter{

    private TextView mDescription, mName;
    private ImageView mPhoto;
    private RatingBar mRatingBar;
    private Button mSubmit, mCancel;
    private int mPoint = 0;
    private int mKaryawanId;
    private MainController mController;
    private AVLoadingIndicatorView mLoading;
    private LinearLayout mButtonContainer;
    private User mUser;

    public GivePointDialog(Context context, User user) {
        super(context);
        mUser = user;
        mKaryawanId = user.getId();
        mController = new MainController(getContext());
    }

    public GivePointDialog(Context context, int themeResId, User user) {
        super(context, themeResId);
        mUser = user;
        mKaryawanId = user.getId();
        mController = new MainController(getContext());
    }

    @Override
    protected int dialogViewId() {
        return R.layout.dialog_give_point;
    }

    @Override
    protected void dialogReady() {
        mButtonContainer = (LinearLayout) findViewById(R.id.button_container);
        mLoading = (AVLoadingIndicatorView)findViewById(R.id.loading);
        mDescription = (TextView)findViewById(R.id.description);
        mRatingBar = (RatingBar)findViewById(R.id.rating_bar);
        mSubmit = (Button)findViewById(R.id.submit);
        mCancel = (Button)findViewById(R.id.cancel);
        mName = (TextView)findViewById(R.id.name);
        mPhoto = (PasFotoImageView)findViewById(R.id.photo);
        Picasso.with(getContext()).load(mUser.getPhoto()).into(mPhoto);
        mName.setText(mUser.getName());
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Log.d("Point", v + "");
                if (v==1.0){
                    mDescription.setText("Kurang memuaskan");
                    mPoint = 1;
                }else if (v==2.0){
                    mDescription.setText("Memuaskan");
                    mPoint = 2;
                }else if (v==3.0){
                    mDescription.setText("Sangat memuaskan");
                    mPoint = 3;
                }
            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPoint==0){
                    Toast.makeText(getContext(), "Silahkan berikan penilaian Anda dengan cara memberikan bintang.", Toast.LENGTH_SHORT).show();
                }else{
                    mButtonContainer.setVisibility(LinearLayout.INVISIBLE);
                    mLoading.setVisibility(AVLoadingIndicatorView.VISIBLE);
                    mController.givePoint(GivePointDialog.this, mKaryawanId, mPoint);
                }

            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public void onSuccess() {
        dismiss();
        new ThankYouDialog(getContext()).show();
    }

    @Override
    public void onFailed() {
        mButtonContainer.setVisibility(LinearLayout.VISIBLE);
        mLoading.setVisibility(AVLoadingIndicatorView.INVISIBLE);
    }
}
