package andevindo.com.penilaiankaryawan.Helper.Network;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.Map;

import andevindo.com.penilaiankaryawan.Helper.Config;
import andevindo.com.penilaiankaryawan.Helper.Manager.JSONManager;


/**
 * Created by -Andevindo- on 10/8/2015.
 */
public class VolleyRequest {
    private RequestQueue mRequestQueue;
    private CustomRequest mCustomRequest;
    private VolleyRequestPresenter mPresenter;
    private MultiPartRequest mMultiPartRequest;
    private Context mContext;

    public VolleyRequest(Context context, VolleyRequestPresenter presenter) {
        mContext = context;
        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();
        mPresenter = presenter;
    }

    public void sendGetRequest(final String tag, String endPoint) {
        mCustomRequest = new CustomRequest(mContext, Request.Method.GET, endPoint, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                sendResponse(response, tag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (VolleyErrorMessage.isConnectionProblem(error))
                    mPresenter.onNetworkError(tag);
                else
                    mPresenter.onServerError(tag);
            }
        });
        mCustomRequest.setRetryPolicy(new DefaultRetryPolicy(Config.SOCKET_TIME_OUT,
                Config.RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mCustomRequest.setTag(tag);
        mRequestQueue.add(mCustomRequest);
    }

    public void sendDeleteRequest(final String tag, Map<String, String> params, String endPoint) {
        mCustomRequest = new CustomRequest(mContext, Request.Method.DELETE, endPoint, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                sendResponse(response, tag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (VolleyErrorMessage.isConnectionProblem(error))
                    mPresenter.onNetworkError(tag);
                else
                    mPresenter.onServerError(tag);

            }
        });
        mCustomRequest.setRetryPolicy(new DefaultRetryPolicy(Config.SOCKET_TIME_OUT,
                Config.RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mCustomRequest.setTag(tag);
        mRequestQueue.add(mCustomRequest);
    }

    public void sendPostRequest(final String tag, Map<String, String> params, String endPoint) {
        mCustomRequest = new CustomRequest(mContext, Request.Method.POST, endPoint, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                sendResponse(response, tag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (VolleyErrorMessage.isConnectionProblem(error))
                    mPresenter.onNetworkError(tag);
                else
                    mPresenter.onServerError(tag);

            }
        });
        mCustomRequest.setRetryPolicy(new DefaultRetryPolicy(Config.SOCKET_TIME_OUT,
                Config.RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mCustomRequest.setTag(tag);
        mRequestQueue.add(mCustomRequest);
    }

    public void sendPutRequest(final String tag, Map<String, String> params, String endPoint) {
        mCustomRequest = new CustomRequest(mContext, Request.Method.PUT, endPoint, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                sendResponse(response, tag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (VolleyErrorMessage.isConnectionProblem(error))
                    mPresenter.onNetworkError(tag);
                else
                    mPresenter.onServerError(tag);

            }
        });
        mCustomRequest.setRetryPolicy(new DefaultRetryPolicy(Config.SOCKET_TIME_OUT,
                Config.RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mCustomRequest.setTag(tag);
        mRequestQueue.add(mCustomRequest);
    }

    private void sendResponse(JSONObject response, String tag) {
        if (JSONManager.isSuccess(response))
            mPresenter.onSuccess(response, tag);
        else if (JSONManager.isNull(response))
            mPresenter.onNull(response, tag);
        else if (JSONManager.isMissingInput(response))
            mPresenter.onMissingInput(response, tag);
        else if (JSONManager.isBooked(response))
            mPresenter.onAlreadyBooked(response, tag);
        else
            mPresenter.onFailed(response, tag);

    }

    public void cancelAllRequest(String tag) {
        if (mCustomRequest != null)
            mRequestQueue.cancelAll(tag);

    }

    public interface VolleyRequestPresenter {
        void onSuccess(JSONObject jsonObject, String tag);

        void onNull(JSONObject jsonObject, String tag);

        void onAlreadyBooked(JSONObject jsonObject, String tag);

        void onMissingInput(JSONObject jsonObject, String tag);

        void onFailed(JSONObject jsonObject, String tag);

        void onNetworkError(String tag);

        void onServerError(String tag);
    }
}
