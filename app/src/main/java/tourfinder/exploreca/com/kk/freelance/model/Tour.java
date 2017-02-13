package tourfinder.exploreca.com.kk.freelance.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.NumberFormat;

public class Tour implements Parcelable {

    public static final String LOG_TAG = Tour.class.getSimpleName();

    //	private int id;
    private long id;
    private String title;
    private String description;
    private double price;
    private String image;

	/*public int getId() {
        return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return title + "\n(" + nf.format(price) + ")";
    }

    public Tour() {
    }

    public Tour(Parcel in) {
        Log.i(LOG_TAG, "TEST : Tour() called Parcel constructor");

        id = in.readLong();
        title = in.readString();
        description = in.readString();
        price = in.readDouble();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.i(LOG_TAG, "TEST : writeToParcel() called...");

        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeString(image);
    }

    public static final Parcelable.Creator<Tour> CREATOR =
            new Parcelable.Creator<Tour>() {

                @Override
                public Tour createFromParcel(Parcel source) {
                    Log.i(LOG_TAG, "createFromParcel");
                    return new Tour(source);
                }

                @Override
                public Tour[] newArray(int size) {
                    Log.i(LOG_TAG, "newArray");
                    return new Tour[size];
                }

            };
}
