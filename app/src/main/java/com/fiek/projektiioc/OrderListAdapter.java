//package com.fiek.projektiioc;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.ImageScaleType;
//import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
//
//import java.util.ArrayList;
//
//public class OrderListAdapter extends ArrayAdapter<Orders> {
//
//    private static final String TAG="OrderListAdapter";
//    private Context mContext;
//    private int mResource;
//    private int lastPosition=-1;
//
//    static class ViewHolder{
//        TextView emri;
//        TextView data;
//        TextView statusi;
//        ImageView image;
//
//    }
//
//    public OrderListAdapter (@NonNull Context context, int resource, @NonNull ArrayList<Orders> objects) {
//        super(context, resource, objects);
//        mContext = context;
//        mResource = resource;
//    }
//
//    @NonNull
//    @Override
//    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        setupImageLoader();
//        //get order's info
//        String emri=getItem(position).getEmri();
//        String data=getItem(position).getData();
//        String statusi=getItem(position).getStatusi();
////        String imgURL = getItem(position).getImgURL();
//
////        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View adapterView = inflater.inflate(R.layout.adapterview, parent, false);
////        TextView tvEmri = (TextView) adapterView.findViewById(R.id.textView1);
////        TextView tvmarresi = (TextView) adapterView.findViewById(R.id.textView2);
////        TextView tvstatusi = (TextView) adapterView.findViewById(R.id.textView3);
////
////        return adapterView;
//
//
////        //create the orders object with infos
////        Orders orders = new Orders(emri, data, statusi);
//
//
//
//        final View result;
//        ViewHolder holder;
//
//        if(convertView==null){
//            LayoutInflater inflater=LayoutInflater.from(mContext);
//            convertView=inflater.inflate(mResource,parent,false);
//
//            holder =new ViewHolder();
//
//
//            holder.emri=(TextView)convertView.findViewById(R.id.textView1);
//            holder.data=(TextView)convertView.findViewById(R.id.textView2);
//            holder.statusi=(TextView)convertView.findViewById(R.id.textView3);
//
//            result=convertView;
//
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder)convertView.getTag();
//            result=convertView;
//        }
//
//
//        Animation animation= AnimationUtils.loadAnimation(mContext, (position>lastPosition) ? R.anim.load_down_anim : R.anim.load_down_anim);
//
//        result.startAnimation(animation);
//        lastPosition = position;
//
//        int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed",null,mContext.getPackageName());
//
//        ImageLoader imageLoader = ImageLoader.getInstance();
//        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
//                .cacheOnDisc(true).resetViewBeforeLoading(true)
//                .showImageForEmptyUri(defaultImage)
//                .showImageOnFail(defaultImage)
//                .showImageOnLoading(defaultImage).build();
//
//        imageLoader.displayImage(imgURL, holder.image, options);
//
//        holder.emri.setText(emri);
//        holder.data.setText(data);
//        holder.statusi.setText(statusi);
//
//        return convertView;
//    }
//    private void setupImageLoader(){
//        // UNIVERSAL IMAGE LOADER SETUP
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheOnDisc(true).cacheInMemory(true)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .displayer(new FadeInBitmapDisplayer(300)).build();
//
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//                mContext)
//                .defaultDisplayImageOptions(defaultOptions)
//                .memoryCache(new WeakMemoryCache())
//                .discCacheSize(100 * 1024 * 1024).build();
//
//        ImageLoader.getInstance().init(config);
//        // END - UNIVERSAL IMAGE LOADER SETUP
//    }
//}
