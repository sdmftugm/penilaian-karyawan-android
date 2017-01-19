package andevindo.com.penilaiankaryawan.API;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by -H- on 2/20/2016.
 */
public class VolleyModel {

    private String mUrl;
    private Map<String, String> mParameter;

    private VolleyModel(VolleyModelBuilder volleyModelBuilder) {
        mParameter = new HashMap<>();
        mUrl = volleyModelBuilder.mUrl;
        mParameter = volleyModelBuilder.mParameter;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = API.getAPI().getBaseAPI() + url;
    }

    public Map<String, String> getParameter() {
        return mParameter;
    }

    public void setParameter(Map<String, String> parameter) {
        mParameter = parameter;
    }

    public void addParameter(String key, String value){
        mParameter.put(key, value);
    }

    public static class VolleyModelBuilder{

        private String mUrl;
        private Map<String, String> mParameter;

        public VolleyModelBuilder(String url){
            mUrl = API.getAPI().getBaseAPI() + url;
            mParameter = new HashMap<>();
        }

        public VolleyModelBuilder addParameter(String key, String value){
            mParameter.put(key, value);
            return this;
        }

        public VolleyModel build(){
            return new VolleyModel(this);
        }

    }

}
