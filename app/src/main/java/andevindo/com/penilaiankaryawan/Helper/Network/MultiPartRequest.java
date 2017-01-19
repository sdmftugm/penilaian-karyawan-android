package andevindo.com.penilaiankaryawan.Helper.Network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import cz.msebera.android.httpclient.entity.mime.content.FileBody;


/**
 * Created by -H- on 10/10/2015.
 */
public class MultiPartRequest extends Request<JSONObject> {

    private Response.Listener<JSONObject> mListener;
    private HttpEntity mHttpEntity;

    public MultiPartRequest(Response.ErrorListener errorListener, Response.Listener listener,
                            File file, String fileName, String endPointAPI, String directory) {
        super(Method.POST, endPointAPI, errorListener);
        mListener = listener;
        mHttpEntity = buildMultipartEntity(file, fileName, directory);

    }

    private HttpEntity buildMultipartEntity(File file, String fileName, String directory) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        FileBody fileBody = new FileBody(file);
        builder.addPart("image", fileBody);
        builder.addTextBody("directory", directory);
        builder.addTextBody("name", fileName);
        return builder.build();
    }




    @Override
    public String getBodyContentType() {
        return mHttpEntity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            mHttpEntity.writeTo(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            VolleyLog.e("" + e);
            return null;
        } catch (OutOfMemoryError e){
            VolleyLog.e("" + e);
            return null;
        }

    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        String string;

        try {
            string = new String(response.data, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            string = new String(response.data);

        }

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Response.success(jsonObject, getCacheEntry());
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

}


