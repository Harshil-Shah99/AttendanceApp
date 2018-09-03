import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


public class AdminPageFragmentAdapter extends FragmentPagerAdapter{

public  AdminPageFragmentAdapter (FragmentManager fm) {
        super(fm);
        }
@Override
public Fragment getItem(int position) {
        if(position==0){
        return  new AdminPage();
        }

        else {
        return new AdminStatsPage();
        }
        }

@Override
public int getCount() {
        return 2;
        }
}