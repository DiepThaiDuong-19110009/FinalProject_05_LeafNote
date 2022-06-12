package hcmute.edu.vn.leafnote.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.custom.CustomPhotoActivity;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
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
        TextView txtPhoto,txtDatePhoto;
        Button btnDeletePhoto;

        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            txtPhoto = view.findViewById(R.id.txtPhoto);
            btnDeletePhoto=view.findViewById(R.id.btnDeletePhoto);
            imgView = view.findViewById(R.id.imgViewImage);
            txtDatePhoto=view.findViewById(R.id.txtDatePhoto);
            view.setTag(R.id.imgViewImage, imgView);
            view.setTag(R.id.txtPhoto, txtPhoto);
            view.setTag(R.id.btnDeletePhoto,btnDeletePhoto);
            view.setTag(R.id.txtDatePhoto,txtDatePhoto);
        } else {
            imgView = (ImageView) view.getTag(R.id.imgViewImage);
            txtPhoto = (TextView) view.getTag(R.id.txtPhoto);
            btnDeletePhoto=(Button) view.getTag(R.id.btnDeletePhoto);
            txtDatePhoto=(TextView) view.getTag(R.id.txtDatePhoto);
        }
        Note photo = imagesList.get(i);
        txtPhoto.setText(photo.getTitle());
        txtDatePhoto.setText(photo.getCreated_date()+" "+photo.getCreated_time());
        imgView.setImageURI(Uri.parse(photo.getContent()));
        btnDeletePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonDelete(photo);
            }
        });

        return view;
    }

    private void clickButtonDelete(Note note) {

        new AlertDialog.Builder(activity)
                .setTitle("Xác nhận xóa ghi chú")
                .setMessage("Bạn có muốn xóa ghi chú")
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseConnection.getInstance(activity).noteDao().delete(note);// xóa note
                        Toast.makeText(activity, "Xóa ghi chú thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, CustomPhotoActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                }).setNegativeButton("Không", null).show();
    }
}

