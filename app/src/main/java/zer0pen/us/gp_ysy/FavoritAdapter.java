package zer0pen.us.gp_ysy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 박재현 on 2016-05-16.
 */
public class FavoritAdapter extends ArrayAdapter<ItemFavoriteUrl> {

    private List<ItemFavoriteUrl> items = ItemFavoriteUrl.listAll(ItemFavoriteUrl.class);

    public FavoritAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ItemFavoriteUrl getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_favorite, parent, false);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.webView.loadUrl(items.get(pos).url);
                    MainActivity.drawer.closeDrawer(GravityCompat.START);
                }
            });

            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setItems(R.array.items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ItemFavoriteUrl db = ItemFavoriteUrl.findById(ItemFavoriteUrl.class, items.get(pos).getId());
                            db.delete();
                            notifyDataSetChanged();
                        }
                    });
                    builder.create().show();
                    return false;
                }
            });

            TextView title, url;

            title = (TextView) convertView.findViewById(R.id.title);
            url = (TextView) convertView.findViewById(R.id.url);

            //ItemFavoriteUrl db = ItemFavoriteUrl.findById(ItemFavoriteUrl.class, position);

            title.setText(items.get(position).title);
            url.setText(items.get(position).url);

            //Log.d("db", ItemFavoriteUrl.findById(ItemFavoriteUrl.class, position).getTitle() + ", " + ItemFavoriteUrl.findById(ItemFavoriteUrl.class, position).getUrl());
        }

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        items = ItemFavoriteUrl.listAll(ItemFavoriteUrl.class);
        super.notifyDataSetChanged();
    }
}