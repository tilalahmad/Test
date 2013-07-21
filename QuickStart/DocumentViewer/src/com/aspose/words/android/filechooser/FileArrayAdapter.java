/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.Words. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */

package com.aspose.words.android.filechooser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.aspose.words.android.R;

import java.util.List;

public class FileArrayAdapter extends ArrayAdapter<Option> {

	public FileArrayAdapter(Context context, int textViewResourceId,
			List<Option> objects) {
		super(context, textViewResourceId, objects);
		mContext = context;
		mId = textViewResourceId;
		mItems = objects;
	}

	public Option getItem(int i) {
		return mItems.get(i);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(mId, null);
		}
		final Option o = mItems.get(position);
		if (o != null) {
			TextView t1 = (TextView) v.findViewById(R.id.TextView01);
			TextView t2 = (TextView) v.findViewById(R.id.TextView02);

			if (t1 != null)
				t1.setText(o.getName());
			if (t2 != null)
				t2.setText(o.getData());

		}
		return v;
	}

	private Context mContext;
	private int mId;
	private List<Option> mItems;
	
}
