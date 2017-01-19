package andevindo.com.penilaiankaryawan.View.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import andevindo.com.penilaiankaryawan.Adapter.BaseAdapter;
import andevindo.com.penilaiankaryawan.Adapter.MainAdapter;
import andevindo.com.penilaiankaryawan.Controller.MainController;
import andevindo.com.penilaiankaryawan.Model.User;
import andevindo.com.penilaiankaryawan.R;
import andevindo.com.penilaiankaryawan.View.Custom.DefaultItemDecoration;
import andevindo.com.penilaiankaryawan.View.Dialog.GivePointDialog;

public class MainActivity extends AppCompatActivity implements BaseAdapter.BaseAdapterPresenter<User>, MainController.GetKaryawanPresenter {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MainAdapter mAdapter;
    private MainController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mController = new MainController(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        mAdapter = new MainAdapter(this);
        mAdapter.setBasePresenter(this);
        mRecyclerView.setAdapter(mAdapter);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        else
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new DefaultItemDecoration((int) getResources().getDimension(R.dimen.margin_list)));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.getKaryawan(MainActivity.this);
            }
        });

        if (savedInstanceState == null){
            mSwipeRefreshLayout.setRefreshing(true);
            mController.getKaryawan(this);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("user", ((ArrayList<User>) mAdapter.getData()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        List<User> list = savedInstanceState.getParcelableArrayList("user");
        mAdapter.setData(list);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    @Override
    public void onClick(User user) {
        new GivePointDialog(new ContextThemeWrapper(this, R.style.GivePointDialog), user).show();
    }

    @Override
    public void onLongClick(User user) {

    }

    @Override
    public void onSuccess(List<User> list) {

        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setData(list);
    }

    @Override
    public void onNull() {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setData(null);
    }

    @Override
    public void onFailed() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
