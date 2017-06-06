package g.sw2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import g.sw2.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentBookmarks.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentBookmarks#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBookmarks extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	//FragmentHome fragmentHome;

	public FragmentBookmarks() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment FragmentBookmarks.
	 */
	// TODO: Rename and change types and number of parameters
	public static FragmentBookmarks newInstance(String param1, String param2) {
		FragmentBookmarks fragment = new FragmentBookmarks();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_bookmarks, container, false);

		ImageView imageView = (ImageView) view.findViewById(R.id.book_image);
		TextView textView = (TextView) view.findViewById(R.id.text_view);

		/*fragmentHome = new FragmentHome();
		String cardtext = fragmentHome.getTopCardDetails().getText();
		textView.setText(cardtext);*/

		//imageView.setImageURI(Uri.parse("http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg"));
		//Glide.with(getContext()).load("http://www.telugubang.in/wp-content/uploads/2017/02/Hot-Disha-patani-at-Jio-Filmfare-Award-2017t.jpg").into(imageView);
		return view;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					                           + " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
