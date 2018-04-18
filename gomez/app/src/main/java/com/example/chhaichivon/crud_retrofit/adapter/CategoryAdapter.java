package com.example.chhaichivon.crud_retrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chhaichivon.crud_retrofit.R;
import com.example.chhaichivon.crud_retrofit.controlls.CategoryController;
import com.example.chhaichivon.crud_retrofit.models.Category;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chhaichivon on 6/21/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

	private List<Category> categoryList  = new ArrayList<>();
	private Context  context;

	public CategoryAdapter(Context context) {
		this.context = context;
	}

	public CategoryAdapter(Context context, List<Category> categoryList) {
		this.context = context;
		this.categoryList = categoryList;
	}

	@Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
		return new ViewHolder(view);
	}

	@Override public void onBindViewHolder(ViewHolder holder, int position) {
		holder.title.setText(categoryList.get(position).getName());
	}

	public void appendToList(List<Category> list) {
		if (list == null) {
			return;
		}
		categoryList.addAll(list);
	}

	public void clear() {
		categoryList.clear();
	}

	@Override public int getItemCount() { return categoryList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.name) public TextView title;
		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this,itemView);
		}
	}

}


//}
