package com.zmm.androidallproject.four_big_component;

import com.viewpagerindicator.TabPageIndicator;
import com.zmm.androidallproject.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewpagerIndicatorMainFragment extends Fragment {

	private static final String[] CONTENT = new String[] { "Activity",
			"Service", "ContentPro", "BroadcaseRec" };
	FourComponentAdapter adapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final Context contextThemeWrapper = new ContextThemeWrapper(
				getActivity(), R.style.StyledIndicators);
		LayoutInflater localInflater = inflater
				.cloneInContext(contextThemeWrapper);

		View view = localInflater.inflate(R.layout.fragment_main, null);
		adapter = new FourComponentAdapter(getFragmentManager());
		ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) view
				.findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		return view;
	}

	Fragment4Activity component4Activity = new Fragment4Activity();
	Fragment4Service component4Service = new Fragment4Service();
	Fragment4CP component4cp = new Fragment4CP();
	Fragment4Broadcast component4Broadcast = new Fragment4Broadcast();

	Fragment[] fragments = { component4Activity, component4Service,
			component4cp, component4Broadcast };

	class FourComponentAdapter extends FragmentStatePagerAdapter {

		public FourComponentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragments[arg0];
		}

		@Override
		public int getCount() {
			return CONTENT.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length];
		}

	}
}
