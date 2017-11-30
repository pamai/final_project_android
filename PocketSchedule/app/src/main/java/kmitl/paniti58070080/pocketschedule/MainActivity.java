package kmitl.paniti58070080.pocketschedule;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference dataRef;
    FirebaseUser user;

    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.WHITE);

        int date = new Date().getDay();
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(date);

        user = FirebaseAuth.getInstance().getCurrentUser();
        dataRef = FirebaseDatabase.getInstance().getReference();
        Log.e("user", user.getUid());
        dataRef = dataRef.child(user.getUid()).child("schedule");
    }


    @OnClick(R.id.floatingActionButton)
    public void addButtonClicked(View view){
        Intent intent = new Intent(this, AddInfoActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Log.d("menu", "0");
                if (requestExternalStoragePermission()){
                    View screenView = getWindow().getDecorView().getRootView();
                    screenView.setDrawingCacheEnabled(true);
                    Bitmap screenBitmap = Bitmap.createBitmap(screenView.getDrawingCache());
                    screenView.setDrawingCacheEnabled(false);
                    Uri imageUri = getUri(getApplicationContext(), screenBitmap);
                    share(imageUri);
                }
                return true;
            case R.id.logout:
                Log.d("menu", "1");
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Uri getUri(Context context, Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "image", null);
        return Uri.parse(path);
    }

    public void share(Uri imageUri){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Share Screen"));
    }

    private boolean requestExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
            return false;
        } else {
            return true;
        }
    }

}
