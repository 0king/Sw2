package g.sw2.exp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.analytics.Tracker;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import g.sw2.R;
import g.sw2.fragments.FragmentAllContent;
import g.sw2.fragments.FragmentBookmarks;
import g.sw2.fragments.FragmentProfile;
import g.sw2.fragments.FragmentRewards;
import g.sw2.other.UrlList;

//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;


/*
* 1. Crashlytics/fabric
* 2. Google Analytics
* 3. Mixpanel
* 4. Crittercism
* 5. ANR watchdog
* 6. HockeyApp x
* 7. AppSee x
* 8. Flurry x
* */

//todo add notifications fragment

public class TestActivity extends AppCompatActivity
		implements FragmentProfile.OnFragmentInteractionListener, FragmentBookmarks.OnFragmentInteractionListener,FragmentRewards.OnFragmentInteractionListener,FragmentAllContent.OnFragmentInteractionListener
{

	private static final String urlProfileImg = "http://www.kimyakariyerim.com/uploads/no.jpg";
	// tags used to attach the fragments
	private static final String TAG_HOME = "home";
	private static final String TAG_PROFILE = "profile";
	private static final String TAG_BOOKMARKS = "bookmarks";
	private static final String TAG_ACTIVITY = "activity";
	private static final String TAG_METHOD = "method";
	private static final String TAG_FEEDBACK = "feedback";
	private static final String TAG_SHARE = "share";
	// index to identify current nav menu item
	public static int navItemIndex = 0;
	public static String CURRENT_TAG = TAG_HOME;
	String mixpanelToken = "fc4e9c7385c84486e86a36beebbe01d2";
	private NavigationView navigationView;
	private DrawerLayout drawer;
	private View navHeader;
	private ImageView imgNavHeaderBg, imgProfile;
	private TextView txtName, txtWebsite;
	private Toolbar toolbar;
	// urls to load navigation header background image
	// and profile image
	private String urlNavHeaderBg;
	// toolbar titles respected to selected nav menu item
	private String[] activityTitles;
	//flag to load home fragment when user presses back key
	private boolean shouldLoadHomeFragOnBackPress = true;
	private Handler mHandler;
	/* google analytics */
	private Tracker gTracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {


		/* StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				                       .detectAll()
				                       .penaltyLog()
				                       .penaltyDeath()
				                       .build());*/

		/* Make sure the *Fabric.with()* line is after all other*3rd-party SDKs that set an *UncaughtExceptionHandler
		Fabric.with(this, new Crashlytics());

		new ANRWatchDog().setANRListener(new ANRWatchDog.ANRListener() {

			@Override
			public void onAppNotResponding(ANRError arg0) {
				Crashlytics.getInstance().core.logException(arg0);
			}
		}).start();*/

		super.onCreate(savedInstanceState);

		//Crittercism.initialize(getApplicationContext(),"c2dfb4f16e2641c497e1b3927716d3df00555300");

		/* start - google analytics code */
		// Obtain the shared Tracker instance.
//		AppController application = (AppController) getApplication();
//		gTracker = application.getDefaultTracker();
//		gTracker.setScreenName("MainActivity");
//		/* start screen_view_hit */
//		gTracker.send(new HitBuilders.ScreenViewBuilder().build());
//		/* end screen_view_hit */
		/* end - google analytics code */


		setContentView(R.layout.activity_main);

		/* start - code to track via Mixpanel
		MixpanelAPI mixpanel = MixpanelAPI.getInstance(this,mixpanelToken);
		try {
			JSONObject props = new JSONObject();
			props.put("Gender", "Gowd");
			props.put("Logged in", true);
			mixpanel.track("MainActivity - onCreate called", props);
		} catch (JSONException e) {
			Log.e("MYAPP", "Unable to add properties to JSONObject", e);
		}*/
		/* end - code to track via Mixpanel */

		/* code for HockeyApp */
		//checkForHockeyAppUpdates();
		//MetricsManager.register(this, getApplication());//HockeyApp

		//Appsee.start("5d6183fb176f43968a3783eb064d7744");

		toolbar = (Toolbar) findViewById(R.id.toolbar); //toolbar.setLogo(R.mipmap.ic_launcher);
		toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
		setSupportActionBar(toolbar); //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		mHandler = new Handler();
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		navigationView = (NavigationView) findViewById(R.id.nav_view);

		// Navigation view header
		navHeader = navigationView.getHeaderView(0);
		//txtName = (TextView) navHeader.findViewById(R.id.name);
		//txtWebsite = (TextView) navHeader.findViewById(R.id.website);
		imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
		//imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

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
			loadHomeFragment();
		}

	}

	/***
	 * Load navigation menu header information
	 * like background image, profile image
	 * name, website, notifications action view (dot)
	 */
	private void loadNavHeader() {
		/*// name, website
		txtName.setText("Durga Ranjan");
		txtWebsite.setText("www.getzenius.com");
*/
		urlNavHeaderBg = UrlList.coverPicChooser();

		// loading header background image
		Glide.with(this).load(urlNavHeaderBg)
				.crossFade()
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(imgNavHeaderBg);

		// Loading profile image
		/*Glide.with(this).load(urlProfileImg)
				.crossFade()
				.thumbnail(0.5f)
				.bitmapTransform(new CircleTransform(this))
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(imgProfile);*/

		// showing dot next to notifications label
		//navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
	}
	/***
	 * Returns respected fragment that user
	 * selected from navigation menu
	 */
	private void loadHomeFragment() {
		// selecting appropriate nav menu item
		selectNavMenu();

		// set toolbar title
		//setToolbarTitle();

		// if user select the current navigation menu again, don't do anything
		// just close the navigation drawer
		if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
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

	private void setToolbarTitle() {
		getSupportActionBar().setTitle(activityTitles[navItemIndex]);
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
					case R.id.nav_exercises:
						navItemIndex = 1;
						CURRENT_TAG = TAG_ACTIVITY;
						break;
					case R.id.nav_feedback:
						navItemIndex = 2;
						CURRENT_TAG = TAG_FEEDBACK;
						break;
					/*case R.id.nav_about_us:
						// launch new intent instead of loading fragment
						startActivity(new Intent(TestActivity.this, ActivityAboutUs.class));
						drawer.closeDrawers();
						return true;*/
					case R.id.nav_science_behind_zenius:
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

				loadHomeFragment();

				return true;
			}
		});


		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

			@Override
			public void onDrawerClosed(View drawerView) {
				// Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
				super.onDrawerOpened(drawerView);
			}
		};

		//Setting the actionbarToggle to drawer layout
		drawer.setDrawerListener(actionBarDrawerToggle);//todo change function

		//calling sync state is necessary or else your hamburger icon wont show up
		actionBarDrawerToggle.syncState();
	}

	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawers();
			return;
		}

		// This code loads home fragment when back key is pressed
		// when user is in other fragment than home
		if (shouldLoadHomeFragOnBackPress) {
			// checking if user is on other navigation menu
			// rather than home
			if (navItemIndex != 0) {
				navItemIndex = 0;
				CURRENT_TAG = TAG_HOME;
				loadHomeFragment();
				return;
			}
		}

		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		//inflate menu_actionbar to get buttons on actionbar
		//MenuInflater inflater = getMenuInflater();
		//inflater.inflate(R.menu.menu_actionbar, menu);

		//AndroidHive:-
		// Inflate the menu; this adds items to the action bar if it is present.

		// show menu only when home fragment is selected
		if (navItemIndex == 0) {
			//getMenuInflater().inflate(R.menu.main, menu);
		}
		//getActionBar().show();

		// when fragment is notifications, load the menu created for notifications
		//if (navItemIndex == 3) {
		//	getMenuInflater().inflate(R.menu.notifications, menu);
		//}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

//		if(id == R.id.action_rewards){
//			/* This is the code that goes into bottom toolbar, the bookmark button calls getTopCardDetails and saves it */
//			FragmentHome fragment = (FragmentHome) getSupportFragmentManager().findFragmentByTag(TAG_HOME);
//			String cardId = fragment.getTopCardDetails().getCardId();
//			Toast.makeText(getApplicationContext(), cardId, Toast.LENGTH_SHORT).show();
//			return true;
//		}

		/*if(id == R.id.action_time){
			Toast.makeText(this, "Hello... Time", Toast.LENGTH_SHORT).show();
		}

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_logout) {
			Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
			return true;
		}
*/
		// user is in notifications fragment
		// and selected 'Mark all as Read'
		//if (id == R.id.action_mark_all_read) {
		//	Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
		//}

		return super.onOptionsItemSelected(item);

		/* use Switch and
		* default:
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);
 */
	}

	@Override
	public void onFragmentInteraction(Uri uri) {

	}

	void shareApp(){
		try{
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.setType("text/plain");
			sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Zenius - Smarter way to study");
			sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
			startActivity(sendIntent);
		}catch (Exception e){
			//...
		}

	}

	void sendFeedbackViaEmail(){
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

	void rateApp(){
		//Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
		Uri uri = Uri.parse("market://details?id=" + "com.upskew.encode");
		Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
		// To count with Play market backstack, After pressing back button,
		// to taken back to our application, we need to add following flags to intent.
		goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		try {
			startActivity(goToMarket);
		} catch (ActivityNotFoundException e) {
			//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.upskew.encode")));
		}
	}

	void checkForHockeyAppUpdates(){
		// Remove this for store builds!
		UpdateManager.register(this);//HockeyApp code
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
	private void checkForCrashes() {
		CrashManager.register(this);//HockeyApp
	}
	private void unregisterManagers() {
		UpdateManager.unregister();//HockeyApp
	}

}















/* //setStatusBarColor
		Window window = MainActivity.this.getWindow();
		// clear FLAG_TRANSLUCENT_STATUS flag:
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		// finally change the color
		window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark);


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    Window window = getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(Color.BLUE);
}

-----

public void onClick(View view) {
    setStatusBarColor();
}

public static void setStatusBarColor() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        // If both system bars are black, we can remove these from our layout,
        // removing or shrinking the SurfaceFlinger overlay required for our views.


        //change here
         Window window = activity.getWindow();
        // By -->>>>> Window window = getWindow();

        //or by this if call in Fragment
        // -->>>>> Window window = getActivity().getWindow();


        int statusBarColor = Color.parseColor("#4CAF50");

        if (statusBarColor == Color.BLACK && window.getNavigationBarColor() == Color.BLACK) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        window.setStatusBarColor(statusBarColor);
    }
}
*/