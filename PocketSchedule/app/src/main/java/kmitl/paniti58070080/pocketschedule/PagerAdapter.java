package kmitl.paniti58070080.pocketschedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import kmitl.paniti58070080.pocketschedule.fragment.PageFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 7;
    private String[] pageTitle = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PageFragment fragment0 = new PageFragment("Sunday");
                return fragment0;

            case 1:
                PageFragment fragment1 = new PageFragment("Monday");
                return fragment1;

            case 2:
                PageFragment fragment2 = new PageFragment("Tuesday");
                return fragment2;

            case 3:
                PageFragment fragment3 = new PageFragment("Wednesday");
                return fragment3;

            case 4:
                PageFragment fragment4 = new PageFragment("Thursday");
                return fragment4;

            case 5:
                PageFragment fragment5 = new PageFragment("Friday");
                return fragment5;

            case 6:
                PageFragment fragment6 = new PageFragment("Saturday");
                return fragment6;
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }
}
