package com.example.chhaichivon.crud_retrofit.controlls;

import android.app.Activity;
import android.app.Application;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.chhaichivon.crud_retrofit.MainActivity;
import com.example.chhaichivon.crud_retrofit.R;
import com.example.chhaichivon.crud_retrofit.adapter.CategoryAdapter;
import com.example.chhaichivon.crud_retrofit.api.Api;
import com.example.chhaichivon.crud_retrofit.models.Category;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chhaichivon on 6/22/17.
 */

public class CategoryController implements Callback<List<Category>> {

	private static String CATEOGRY_URL ="http://10.0.0.191/gomez/public/api/categories/";
	private Activity context;
	private RecyclerView mRecycler;
	private RecyclerView.Adapter mAdapter;


	public CategoryController(MainActivity mainActivity, RecyclerView mRecycler) {
		this.context = mainActivity;
		this.mRecycler = mRecycler;
	}

	public void getAllCategory(){
		Gson gson = new GsonBuilder()
						.setLenient()
						.create();

		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
			@Override
			public okhttp3.Response intercept(Chain chain) throws IOException{
				 Request newRequest  = chain.request().newBuilder()
						.addHeader("Authorization", "Bearer " + "GzagzMFlT7QDpkpqgLP0P1JZBo7GyX29Xxp6TtvMTTAlp3B9xLDkVm4XTASs")
						.build();
				return chain.proceed(newRequest);
			}
		}).build();

		Retrofit retrofit = new Retrofit
								.Builder()
								.client(client)
								.baseUrl(CATEOGRY_URL)
								.addConverterFactory(GsonConverterFactory.create(gson))
								.build();

		Api api = retrofit.create(Api.class);
		Call<List<Category>> call = api.getAllCategoris();
		call.enqueue(this);
	}
	@Override
	public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
		if(response.isSuccessful()){
			List<Category>  categoryList = response.body();
			mAdapter = new CategoryAdapter(this.context.getApplicationContext(), categoryList);
			this.mRecycler.setAdapter(mAdapter);
			mAdapter.notifyDataSetChanged();

			for(Category category : categoryList){
				Log.d("Category Name " , category.getName());
				Log.d("Category Description " , category.getDescription());
			}

		}else{
			Log.d("ERROR ", response.errorBody()  +"");
		}
	}

	@Override
	public void onFailure(Call<List<Category>> call, Throwable t) {
		Log.d("onFailure", t.getMessage());
	}
}
