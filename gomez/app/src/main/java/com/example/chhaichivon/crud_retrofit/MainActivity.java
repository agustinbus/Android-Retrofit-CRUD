package com.example.chhaichivon.crud_retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chhaichivon.crud_retrofit.adapter.CategoryAdapter;
import com.example.chhaichivon.crud_retrofit.controlls.CategoryController;
import com.example.chhaichivon.crud_retrofit.models.Category;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	ProgressDialog pd;
	private RecyclerView mRecycler;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRecycler = (RecyclerView)  this.findViewById(R.id.rv);
		mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		mRecycler.setLayoutManager(mManager);

		mAdapter = new CategoryAdapter(this);
		mRecycler.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();

		pd = new ProgressDialog(this);
		pd.setMessage("Loading ...");
		pd.setCancelable(false);
		pd.show();

		CategoryController categoryController = new CategoryController(this, mRecycler);
		categoryController.getAllCategory();
		pd.hide();
	}
}
