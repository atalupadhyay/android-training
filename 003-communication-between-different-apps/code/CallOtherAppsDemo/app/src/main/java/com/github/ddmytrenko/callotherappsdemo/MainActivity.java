package com.github.ddmytrenko.callotherappsdemo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    static final int REQ_PICK_CONTACT = 1;

    @BindView(R.id.labelPhoneNumber)
    TextView labelPhoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        if (requestCode == REQ_PICK_CONTACT && resultCode == RESULT_OK) {
            String phoneNumber = null;
            Uri contactUri = data.getData();
            String[] projection = { ContactsContract.CommonDataKinds.Phone.NUMBER };
            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int col = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    phoneNumber = cursor.getString(col);
                    cursor.close();
                } else {
                    Timber.w("This contact has no phone number set");
                }
            } else {
                Timber.w("Could not resolve %s", contactUri);
            }

            if (TextUtils.isEmpty(phoneNumber)) {
                onNoPhoneNumberFound();
            } else {
                onPhoneNumberRetrieved(phoneNumber);
            }
        }
    }

    @OnClick(R.id.buttonPickPhoneNumber)
    public void actionPickPhoneNumber() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(pickContactIntent, REQ_PICK_CONTACT);
    }

    private void onPhoneNumberRetrieved(@NonNull String phoneNumber) {
        labelPhoneNumber.setVisibility(View.VISIBLE);
        labelPhoneNumber.setText(phoneNumber);
    }

    private void onNoPhoneNumberFound() {
        Toast.makeText(this, R.string.msg_no_phone_number_found, Toast.LENGTH_LONG).show();
    }
}
