package g.sw2.exp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import g.sw2.R;

public class Main3Activity extends AppCompatActivity {
	/* sending, storing data on firebase */
	
	
	// Firebase instance variables
	private FirebaseAuth mFirebaseAuth;
	private FirebaseUser mFirebaseUser;
	
	
	FirebaseStorage storage;
	StorageReference storageRef;//lightweight references
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		
		// Initialize Firebase Auth
		mFirebaseAuth = FirebaseAuth.getInstance();
		mFirebaseUser = mFirebaseAuth.getCurrentUser();
		if (mFirebaseUser == null) {
			// Not signed in, launch the Sign In activity
			startActivity(new Intent(this, Main2Activity.class));
			finish();
			return;
		} else {
			String mUsername = mFirebaseUser.getDisplayName();
			if (mFirebaseUser.getPhotoUrl() != null) {
				String mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
			}
		}
		
		//accessing your storage bucket
		storage = FirebaseStorage.getInstance();
		// Create a storage reference from our app
		storageRef = storage.getReference();
		
		
		// Create a child reference
		// imagesRef now points to "images"
		StorageReference imagesRef = storageRef.child("images");
		
		//grand child
		StorageReference spaceRef = storageRef.child("images/space.jpg");
	}
}
