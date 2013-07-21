/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.Words. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */

package com.aspose.words.android.filechooser;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.aspose.words.android.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileChooserActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setListAdapter(mAdapter);

		mCurrentDir = Environment.getExternalStorageDirectory();
		fill(mCurrentDir);
	}

	private void fill(File f) {

		File[] dirs = f.listFiles();
		this.setTitle(gCurDir + f.getName());
		List<Option> dir = new ArrayList<Option>();
		List<Option> fls = new ArrayList<Option>();
		
		try {
			for (File ff : dirs) {
				if (ff.isDirectory())
					dir.add(new Option(ff.getName(), gFolder, ff
							.getAbsolutePath()));
				else {
					fls.add(new Option(ff.getName(), gFileSize
							+ ff.length(), ff.getAbsolutePath()));
				}
			}
		} 
		
		catch (Exception e) { }
		
		Collections.sort(dir);
		Collections.sort(fls);
		dir.addAll(fls);

		if (f.getParent() != null)
			dir.add(0, new Option("..", gParentDir, f.getParent()));

		mAdapter = new FileArrayAdapter(FileChooserActivity.this,
				R.layout.file_view, dir);
		this.setListAdapter(mAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		super.onListItemClick(l, v, position, id);
		Option o = mAdapter.getItem(position);

		if (gFolder.equalsIgnoreCase(o.getData())
				|| gParentDir.equalsIgnoreCase(o.getData())) {
			mCurrentDir = new File(o.getPath());
			fill(mCurrentDir);
		} else {
			onFileClick(o);
		}

	}

	private void onFileClick(Option o) {
		
		Toast.makeText(this, "Selected: " + o.getName(), Toast.LENGTH_SHORT)
				.show();
		Uri uri = Uri.fromFile(new File(o.getPath()));
		setResult(RESULT_OK, new Intent().setData(uri));
		finish();
		
	}
	
	private static final String gCurDir = "Current Dir: ";
	private static final String gFileSize = "File Size: ";
	private static final String gParentDir = "parent directory";
	private static final String gFolder = "Folder";
	private File mCurrentDir;
	private FileArrayAdapter mAdapter;
}
