package andevindo.com.penilaiankaryawan.Controller;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import andevindo.com.penilaiankaryawan.API.API;
import andevindo.com.penilaiankaryawan.Helper.Manager.JSONManager;
import andevindo.com.penilaiankaryawan.Model.User;


/**
 * Created by heendher on 8/20/2016.BaseDialog
 */
public class MainController extends Controller {

    private static String sGetKaryawan = "getKaryawan";
    private static String sGivePoint = "givePoint";

    private GetKaryawanPresenter mGetKaryawanPresenter;
    private GivePointPresenter mGivePointPresenter;

    public MainController(Context context) {
        super(context);
    }

    public void getKaryawan(GetKaryawanPresenter presenter){
        mGetKaryawanPresenter = presenter;
        sendGet(API.getAPI().getListKaryawan(), sGetKaryawan);
    }

    public void givePoint(GivePointPresenter presenter, int karyawanId, int point){
        mGivePointPresenter = presenter;
        sendPost(API.getAPI().givePoint(karyawanId, point), sGivePoint);
    }

    @Override
    public void onSuccess(JSONObject jsonObject, String tag) {
        if (tag.equals(sGetKaryawan)){
            mGetKaryawanPresenter.onSuccess(JSONManager.getListKaryawan(jsonObject));
        }else if (tag.equals(sGivePoint)){
            mGivePointPresenter.onSuccess();
        }
    }

    @Override
    public void onNull(JSONObject jsonObject, String tag) {
        if (tag.equals(sGetKaryawan)){
            mGetKaryawanPresenter.onNull();
        }
        Toast.makeText(getContext(), "Untuk saat ini tidak data karyawan yang dapat ditampilkan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(JSONObject jsonObject, String tag) {
        if (tag.equals(sGetKaryawan)){
            mGetKaryawanPresenter.onFailed();
        }else if (tag.equals(sGivePoint)){
            mGivePointPresenter.onFailed();
        }
        Toast.makeText(getContext(), "Mohon maaf, server kami sedang dalam perbaikan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkError(String tag) {
        if (tag.equals(sGetKaryawan)){
            mGetKaryawanPresenter.onFailed();
        }else if (tag.equals(sGivePoint)){
            mGivePointPresenter.onFailed();
        }
        Toast.makeText(getContext(), "Mohon cek koneksi Anda, dan silahkan coba lagi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServerError(String tag) {
        if (tag.equals(sGetKaryawan)){
            mGetKaryawanPresenter.onFailed();
        }else if (tag.equals(sGivePoint)){
            mGivePointPresenter.onFailed();
        }
        Toast.makeText(getContext(), "Mohon maaf, server kami sedang dalam perbaikan", Toast.LENGTH_SHORT).show();
    }

    public interface GetKaryawanPresenter{
        void onSuccess(List<User> list);
        void onNull();
        void onFailed();
    }

    public interface GivePointPresenter{
        void onSuccess();
        void onFailed();
    }
}
