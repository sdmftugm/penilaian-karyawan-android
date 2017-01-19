package andevindo.com.penilaiankaryawan.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by heendher on 8/20/2016.
 */
public class User implements Parcelable {

    private int mId;
    private String mName;
    private String mEmail;
    private String mPhone;
    private String mPhoto;

    protected User(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mEmail = in.readString();
        mPhone = in.readString();
        mPhoto = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mEmail);
        dest.writeString(mPhone);
        dest.writeString(mPhoto);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    public User() {
    }

}
