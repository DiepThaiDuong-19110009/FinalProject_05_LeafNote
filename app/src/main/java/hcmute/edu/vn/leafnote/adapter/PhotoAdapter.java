package hcmute.edu.vn.leafnote.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.entity.Note;

public class PhotoAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    List<Note> imagesList;

    public PhotoAdapter() {
    }

    public PhotoAdapter(Activity activity, int layout, List<Note> imagesList) {
        this.activity = activity;
        this.layout = layout;
        this.imagesList = imagesList;
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public Object getItem(int i) {
        return imagesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imgView;
        TextView txtPhoto;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            txtPhoto = view.findViewById(R.id.txtPhoto);
            imgView = view.findViewById(R.id.imgViewImage);
            view.setTag(R.id.imgViewImage, imgView);
            view.setTag(R.id.txtPhoto, txtPhoto);
        } else {
            imgView = (ImageView) view.getTag(R.id.imgViewImage);
            txtPhoto = (TextView) view.getTag(R.id.txtPhoto);

        }
        Note photo = imagesList.get(i);
        txtPhoto.setText(photo.getTitle());
        imgView.setImageURI(Uri.parse(photo.getContent()));
        return view;
    }
}

