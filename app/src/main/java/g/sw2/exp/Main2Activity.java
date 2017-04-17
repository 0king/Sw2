package g.sw2.exp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import g.sw2.R;

public class Main2Activity extends AppCompatActivity implements
		GoogleApiClient.OnConnectionFailedListener {
	
	
	private GoogleApiClient mGoogleApiClient;
	private static final int RC_SIGN_IN = 9001;
	
	TextView textView;
	Button bttnSignin, bttnSignOut;
	
	ProgressDialog mProgressDialog;
	private static final String TAG = "GoogleActivity";
	
	
	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		
		mAuth = FirebaseAuth.getInstance();
		
		mAuthListener = new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
				FirebaseUser user = firebaseAuth.getCurrentUser();
				if (user != null) {
					// User is signed in
					Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
				} else {
					// User is signed out
					Log.d(TAG, "onAuthStateChanged:signed_out");
				}
				// ...
			}
		};
		
		mAuth.addAuthStateListener(mAuthListener);
		
		/* Google sign in */
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				                          .requestIdToken(getString(R.string.default_web_client_id)) //required by firebase //651611255225-iptd28uib238gj48huqv0mtu0dl39khc.apps.googleusercontent.com
				                          .requestEmail()
				                          .build();
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				                   .enableAutoManage(this, this)
				                   .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
				                   .build();
		SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
		textView = (TextView) findViewById(R.id.sign_in_message);
		bttnSignin = (Button) findViewById(R.id.button_signin);
		bttnSignOut = (Button) findViewById(R.id.button_signout);
		bttnSignin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signIn();
			}
		});
		bttnSignOut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signOut();
			}
		});
		
		//signIn();//on button click
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		
		OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
		if (opr.isDone()) {
			// If the user's cached credentials are valid, the OptionalPendingResult will be "done"
			// and the GoogleSignInResult will be available instantly.
			Log.d("silentSignIn:", "Got cached sign-in");
			GoogleSignInResult result = opr.get();
			handleSignInResult(result);
		} else {
			// If the user has not previously signed in on this device or the sign-in has expired,
			// this asynchronous branch will attempt to sign in the user silently.  Cross-device
			// single sign-on will occur in this branch.
			
			//***//showProgressDialog();
			
			opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
				@Override
				public void onResult(GoogleSignInResult googleSignInResult) {
					
					//***//hideProgressDialog();
					
					handleSignInResult(googleSignInResult);
				}
			});
		}
	}
	
	// [START onActivityResult]
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
		if (requestCode == RC_SIGN_IN) {
			GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
			handleSignInResult(result);
		}
	}
	// [END onActivityResult]
	
	// [START handleSignInResult]
	private void handleSignInResult(GoogleSignInResult result) {
		Log.d(TAG, "handleSignInResult:" + result.isSuccess());
		if (result.isSuccess()) {
			// Signed in successfully, show authenticated UI.
			GoogleSignInAccount acct = result.getSignInAccount();
			firebaseAuthWithGoogle(acct);
			textView.setText(acct.getDisplayName());
			updateUI(true);
		} else {
			// Signed out, show unauthenticated UI.
			updateUI(false);
		}
	}
	// [END handleSignInResult]
	
	// [START signIn]
	private void signIn() {
		Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
		startActivityForResult(signInIntent, RC_SIGN_IN);
	}
	// [END signIn]
	
	private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
		Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
		
		AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
		mAuth.signInWithCredential(credential)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
						
						// If sign in fails, display a message to the user. If sign in succeeds
						// the auth state listener will be notified and logic to handle the
						// signed in user can be handled in the listener.
						if (!task.isSuccessful()) {
							Log.w(TAG, "signInWithCredential", task.getException());
							Toast.makeText(Main2Activity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
						} else {
							startActivity(new Intent(Main2Activity.this, Main3Activity.class));
							finish();
						}
						// ...
					}
				});
	}
	
	// [START signOut]
	private void signOut() {
		mAuth.signOut();//firebaseAuth
		Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
				new ResultCallback<Status>() {
					@Override
					public void onResult(Status status) {
						// [START_EXCLUDE]
						updateUI(false);
						// [END_EXCLUDE]
					}
				});
	}
	// [END signOut]
	
	// [START revokeAccess]
	private void revokeAccess() {
		Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
				new ResultCallback<Status>() {
					@Override
					public void onResult(Status status) {
						// [START_EXCLUDE]
						updateUI(false);
						// [END_EXCLUDE]
					}
				});
	}
	// [END revokeAccess]
	
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		// An unresolvable error has occurred and Google APIs (including Sign-In) will not
		// be available.
		Log.d(TAG, "onConnectionFailed:" + connectionResult);
	}
	
	private void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage("Loading");
			mProgressDialog.setIndeterminate(true);
		}
		
		mProgressDialog.show();
	}
	
	private void hideProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.hide();
		}
	}
	
	private void updateUI(boolean signedIn) {
		if (signedIn) {
			findViewById(R.id.sign_in_button).setVisibility(View.GONE);
			//findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
		} else {
			textView.setText("Signed out");
			
			findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
			//findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		if (mAuthListener != null) {
			mAuth.removeAuthStateListener(mAuthListener);
		}
	}
	
	void firebaseSignOut() {
		FirebaseAuth.getInstance().signOut();
	}
}
