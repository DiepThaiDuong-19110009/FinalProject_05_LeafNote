package hcmute.edu.vn.leafnote.service;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class SendEmailExcution extends AsyncTask<Message,String,String> {
    //private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progressDialog=ProgressDialog.show(progressDialog.getOwnerActivity(), "Vui lòng đợi","Đang gửi email",true,false);
    }

    @Override
    protected String doInBackground(Message... messages) {
        try{
            Transport.send(messages[0]);
            return "Success";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //progressDialog.dismiss();
//        if(s.equals("Success")){
//            AlertDialog.Builder builder=new AlertDialog.Builder();
//            builder.setCancelable(false);
//            builder.setMessage("Gửi email thành công");
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            });
//            builder.show();
//        }
//        else{
//            Toast.makeText(progressDialog.getOwnerActivity(), "Có lỗi trong quá trình xử lý", Toast.LENGTH_SHORT).show();
//        }
    }
}
