package mlynchschmidt.sams;

//GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo required call for legal info
//API  KEY AIzaSyAgImkqY-hd91fihaReB7NT1fjJW1OsRq0
//CLIENT ID 101800828534-ea53te5kmdrl5vm4eab4ns6du82989qr.apps.googleusercontent.com
/**
 * Created by Morgan on 10/14/2015.
 */


import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;

/**
 * Created by Morgan on 9/23/2015.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}