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

public class SendEmailExecution extends AsyncTask<Message,String,String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

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
    }
}
