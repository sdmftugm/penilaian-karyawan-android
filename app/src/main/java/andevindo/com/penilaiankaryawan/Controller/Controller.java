package andevindo.com.penilaiankaryawan.Controller;

import android.content.Context;

import org.json.JSONObject;

import andevindo.com.penilaiankaryawan.API.VolleyModel;
import andevindo.com.penilaiankaryawan.Helper.Network.VolleyRequest;


/**
 * Created by -H- on 2/6/2016.
 */
public abstract class Controller implements VolleyRequest.VolleyRequestPresenter{

    private VolleyRequest mVolleyRequest;
    private Context mContext;

    public Controller(Context context) {
        mVolleyRequest = new VolleyRequest(context, this);
        mContext = context;
    }

    public Context getContext(){
        return mContext;
    }

    public void sendPost(VolleyModel volleyModel, String tag){
        mVolleyRequest.sendPostRequest(tag, volleyModel.getParameter(), volleyModel.getUrl());
    }

    public void sendDelete(VolleyModel volleyModel, String tag){
        mVolleyRequest.sendDeleteRequest(tag, volleyModel.getParameter(), volleyModel.getUrl());
    }

    public void sendGet(VolleyModel volleyModel, String tag){
        mVolleyRequest.sendGetRequest(tag, volleyModel.getUrl());
    }

    public void sendPut(VolleyModel volleyModel, String tag){
        mVolleyRequest.sendPutRequest(tag, volleyModel.getParameter(), volleyModel.getUrl());
    }

    public void cancel(String tag){
        mVolleyRequest.cancelAllRequest(tag);
    }

    @Override
    public abstract void onSuccess(JSONObject jsonObject, String tag);

    @Override
    public abstract void onNull(JSONObject jsonObject, String tag);

    @Override
    public void onAlreadyBooked(JSONObject jsonObject, String tag) {

    }

    @Override
    public void onMissingInput(JSONObject jsonObject, String tag) {

    }

    @Override
    public abstract void onFailed(JSONObject jsonObject, String tag);

    @Override
    public abstract void onNetworkError(String tag);

    @Override
    public abstract void onServerError(String tag);
}
