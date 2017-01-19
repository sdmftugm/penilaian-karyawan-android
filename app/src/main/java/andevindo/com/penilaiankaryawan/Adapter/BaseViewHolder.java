package andevindo.com.penilaiankaryawan.Adapter;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by heendher on 8/7/2016.
 */
public abstract class BaseViewHolder<T extends Parcelable> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void setBasePresenter(final T t, final BaseAdapter.BaseAdapterPresenter<T> presenter){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClick(t);
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                presenter.onLongClick(t);
                return true;
            }
        });
    }

    public abstract void bindData(final T t);
}
