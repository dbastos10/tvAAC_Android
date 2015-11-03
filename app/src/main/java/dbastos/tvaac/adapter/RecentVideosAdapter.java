package dbastos.tvaac.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import dbastos.tvaac.R;
import dbastos.tvaac.TvVideo;
import dbastos.tvaac.VideoActivity;

public class RecentVideosAdapter extends ArrayAdapter<TvVideo> implements View.OnClickListener{
    private final Context context;

    public RecentVideosAdapter(Context context, int layout) {
        super(context, layout);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_list_videos_fragment, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.info_text);
        WebView web = (WebView) rowView.findViewById(R.id.webView);

        textView.setText(getItem(position).getTitle());
        textView.setOnClickListener(this);
        textView.setTag(getItem(position));
        web.loadUrl(getItem(position).getThumbnail("medium"));
        web.setOnClickListener(this);

        return rowView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, VideoActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("video",(TvVideo)v.getTag());
        intent.putExtra("video",b);
        context.startActivity(intent);
    }
}
