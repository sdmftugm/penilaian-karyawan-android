package andevindo.com.penilaiankaryawan.API;


/**
 * Created by -H- on 2/20/2016.
 */
public class API {

    private final String sBaseAPI = "http://sdmftugm.xyz/sdmft/public/api/v1/";

    private static API instance = new API();

    private API() {

    }

    public static API getAPI() {
        return instance;
    }

    public String getBaseAPI() {
        return sBaseAPI;
    }

    public VolleyModel getListKaryawan() {
        return new VolleyModel.VolleyModelBuilder("karyawan")
                .build();
    }

    public VolleyModel givePoint(int karyawanId, int point){
        return new VolleyModel.VolleyModelBuilder("karyawan")
                .addParameter("karyawan_id", karyawanId+"")
                .addParameter("point", point+"")
                .build();
    }


}
