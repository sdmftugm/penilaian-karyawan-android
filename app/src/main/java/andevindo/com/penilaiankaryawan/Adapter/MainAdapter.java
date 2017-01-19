package andevindo.com.penilaiankaryawan.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import andevindo.com.penilaiankaryawan.Model.User;
import andevindo.com.penilaiankaryawan.R;
import andevindo.com.penilaiankaryawan.View.Custom.PasFotoImageView;


/**
 * Created by heendher on 8/20/2016.
 */
public class MainAdapter extends BaseAdapter<User> {

    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder bindData(View view) {
        return new BaseViewHolder<User>(view) {
            @Override
            public void bindData(User user) {
                PasFotoImageView photo = (PasFotoImageView) itemView.findViewById(R.id.photo);
                TextView name = (TextView)itemView.findViewById(R.id.name);
                try{
                    Picasso.with(getContext()).load(user.getPhoto()).fit().into(photo);
                }catch (IllegalArgumentException e){
                    Log.d("PhotoNull", user.getName());
                }

                name.setText(user.getName());
            }
        };
    }

    @Override
    protected int itemViewId(int viewType) {
        return R.layout.recycler_main;
    }
}
