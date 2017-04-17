package g.sw2.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import g.sw2.R;
import g.sw2.fragments.FragmentAllContent;
import g.sw2.fragments.FragmentAllContents;
import g.sw2.fragments.FragmentBookmarks;
import g.sw2.fragments.FragmentPerformance;
import g.sw2.fragments.FragmentProfile;
import g.sw2.fragments.FragmentRewards;
import g.sw2.fragments.FragmentSession;
import g.sw2.other.CircleTransform;
import g.sw2.other.UrlList;

public class MainActivity extends AppCompatActivity implements FragmentSession.OnViewSelected{

    String fragment_name;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;

    // urls to load navigation header background image
    // and profile image
    private String urlNavHeaderBg;
    private static final String urlProfileImg = "http://www.kimyakariyerim.com/uploads/no.jpg";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_BOOKMARKS = "bookmarks";
    private static final String TAG_ACTIVITY = "activity";
    private static final String TAG_METHOD = "method";
    private static final String TAG_FEEDBACK = "feedback";
    private static final String TAG_SHARE = "share";
    public static String CURRENT_TAG = TAG_HOME;


    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    //flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	
	
	    setContentView(R.layout.activity_main);

        mHandler = new Handler();
        loadBottomNavigation();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.nav_view);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        //todo move all network ops into AsyncTask

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
			/* card-view transferrred from activity to fragment */
           // loadNavigationBarFragment();
        }
    }

    private void loadBottomNavigation(){
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
	    AHBottomNavigationItem item1 = new AHBottomNavigationItem("Training", R.mipmap.ic_workout_24dp);
	    AHBottomNavigationItem item2 = new AHBottomNavigationItem("Progress", R.mipmap.ic_performance_24dp);
	    AHBottomNavigationItem item3 = new AHBottomNavigationItem("All Subjects", R.mipmap.ic_all_contents_black_24dp);
	    AHBottomNavigationItem item4 = new AHBottomNavigationItem("Others", R.mipmap.ic_list_black_24dp);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

// Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

// Enable the translation of the FloatingActionButton
        //bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

// Display color under navigation bar (API 21+)
// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);

// Manage titles
        //bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        //bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

// Set current item programmatically
        bottomNavigation.setCurrentItem(0);
        loadBottomToolbarFragment(0);

// Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

// Add or remove notification for each item
        //bottomNavigation.setNotification("1", 3);
// OR
//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_back))
//                .setTextColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_text))
//                .build();
//        bottomNavigation.setNotification(notification, 1);

// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                if(position !=3)
                    loadBottomToolbarFragment(position);
                else
                    drawer.openDrawer(Gravity.RIGHT);

                Log.d("Zenius","Positon"+position);
                return true;
            }
        });
//        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
//            @Override public void onPositionChange(int y) {
//                // Manage the new y position
//            }
//        });

    }

    private void loadBottomToolbarFragment(final int position) {

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getBottomToolbarFragment(position);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_main, fragment);
                fragmentTransaction.commitAllowingStateLoss(); //todo check commitAllowingStateLoss
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }


        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getBottomToolbarFragment(int position) {
        switch (position) {
			/* card-view transferrred from activity to fragment */
            case 0:
                FragmentSession sessionFragment = new FragmentSession();
                return sessionFragment;
            case 1:
                FragmentPerformance performanceFragment = new FragmentPerformance();
                return performanceFragment;
            case 2:
                FragmentAllContents allcontentsFragment = new FragmentAllContents();
                return allcontentsFragment;


            default:
                return new FragmentSession();
        }
    }

    @Override
    public void onViewSelected(int viewId) {
        Intent intent = new Intent(this, SessionActivity.class);
        startActivity(intent);
    }

    private void loadNavHeader () {
        // name, website
        txtName.setText("Durga Ranjan");
        txtWebsite.setText("www.getzenius.com");

        urlNavHeaderBg = UrlList.coverPicChooser();

        // loading header background image
        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

        // showing dot next to notifications label
        //navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }

    private void loadNavigationBarFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        //setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss(); //todo check commitAllowingStateLoss
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
			/* card-view transferrred from activity to fragment */
            case 0:
//				FragmentHome homeFragment = new FragmentHome();
                //	return homeFragment;
            case 1:
                FragmentProfile profileFragment = new FragmentProfile();
                return profileFragment;
            case 2:
                FragmentBookmarks bookmarksFragment = new FragmentBookmarks();
                return bookmarksFragment;
            case 3:
                FragmentRewards etcFragment = new FragmentRewards();
                return etcFragment;
            case 4:
                FragmentAllContent allContentFragment = new FragmentAllContent();
                return allContentFragment;
            default:
                return new FragmentBookmarks();
        }
    }


    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;

                    case R.id.nav_bookmarks:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_BOOKMARKS;
                        break;
                    case R.id.nav_activity:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_ACTIVITY;
                        break;
                    case R.id.nav_feedback:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_FEEDBACK;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, ActivityAboutUs.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_method:
                        // launch new intent instead of loading fragment
                        //startActivity(new Intent(MainActivity.this, ActivityShareApp.class));

                        //share the app
                        //shareApp();

                        //send feedback to developer
                        //sendFeedbackViaEmail();

                        //rate the app in playstore
                        rateApp();

                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                menuItem.setChecked(true);

                //loadNavigationBarFragment();

                return true;
            }
        });
    }


    void shareApp() {
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Zenius - Smarter way to study");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
            startActivity(sendIntent);
        } catch (Exception e) {
            //...
        }

    }

    void sendFeedbackViaEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "contact@getzenius.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Zenius");
        //emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
        startActivity(Intent.createChooser(emailIntent, "Select mail app:-"));

/*		Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
		emailIntent.setData(Uri.parse("mailto: abc@xyz.com"));
		startActivity(Intent.createChooser(emailIntent, "Send feedback"));*/

/*      String[] TO = {"email@server.com"};
		Uri uri = Uri.parse("mailto:email@server.com")
				          .buildUpon()
				          .appendQueryParameter("subject", "subject")
				          .appendQueryParameter("body", "body")
				          .build();
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
		startActivity(Intent.createChooser(emailIntent, "Send mail..."));*/
    }

    void rateApp() {
        //Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Uri uri = Uri.parse("market://details?id=" + "com.upskew.encode");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.upskew.encode")));
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        //checkForCrashes();//HockeyApp
    }

    @Override
    public void onPause() {
        super.onPause();
        //unregisterManagers();//HockeyApp
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregisterManagers();
    }
}
