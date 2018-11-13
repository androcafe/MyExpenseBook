package visitindia.androcafe.myexpensebook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import visitindia.androcafe.vj.fragment.AddFragment;
import visitindia.androcafe.vj.fragment.ExpenseFragment;
import visitindia.androcafe.vj.fragment.StatusFragment;

public class HomeActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    Fragment fragment=null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_add:
                    fragment= AddFragment.newInstance();
                    break;
                case R.id.navigation_expense:
                    fragment= ExpenseFragment.newInstance();
                    break;
                case R.id.navigation_status:
                    fragment= StatusFragment.newInstance();
                    break;
                default:
                    fragment= AddFragment.newInstance();
                    break;
            }
            loadFragment(fragment);
            return false;
        }
    };

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frameLayout=findViewById(R.id.framelayout);

        fragment=AddFragment.newInstance();
        loadFragment(fragment);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
