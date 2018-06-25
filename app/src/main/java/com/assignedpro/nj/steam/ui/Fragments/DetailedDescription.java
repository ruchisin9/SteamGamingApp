package com.assignedpro.nj.steam.ui.Fragments;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assignedpro.nj.steam.R;
import com.assignedpro.nj.steam.Request.Appid;
import com.assignedpro.nj.steam.Response.GetAllDetails;
import com.assignedpro.nj.steam.Response.GetOnlinePlayersCount;
import com.assignedpro.nj.steam.interfacehelper.VolleyResponse;
import com.assignedpro.nj.steam.network.Network;
import com.assignedpro.nj.steam.sharedprefrences.SharedPrefrences;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by Ankur_ on 6/18/2018.
 */

public class DetailedDescription extends android.support.v4.app.Fragment {


    private View view;
    private SharedPrefrences shPrfs;
    private Network network;

    public String domain = "";
    private SpotsDialog spotsDialog;
    private TextView fuck_emailid;
    private TextView game_name;
    private TextView game_detail;
    private TextView game_releasedate;
    private TextView game_developer;
    private TextView game_publisher;
    private TextView game_minimum;
    private TextView game_recommended;
    private TextView game_about;
    private ImageView game_background;
    private SpannableStringBuilder htmlSpannable;
    private Button job_complete;
   String appurl ="https://steamdb.info/api/GetGraph/?type=concurrent_week&appid=";
    private TextView getcount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        network = Network.getInstance(getActivity());
        shPrfs = SharedPrefrences.getsharedprefInstance(getActivity());
        spotsDialog = new SpotsDialog(getActivity());


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detaildescription, container, false);

        game_name = (TextView) view.findViewById(R.id.game_name);
        game_about = (TextView) view.findViewById(R.id.game_about);
        game_detail = (TextView) view.findViewById(R.id.game_detail);
        game_releasedate = (TextView) view.findViewById(R.id.game_releasedate);
        game_developer = (TextView) view.findViewById(R.id.game_developer);
        game_publisher = (TextView) view.findViewById(R.id.game_publisher);
        game_minimum = (TextView) view.findViewById(R.id.game_minimum);
        game_recommended = (TextView) view.findViewById(R.id.game_recommended);
        job_complete = (Button)view.findViewById(R.id.job_complete);
        game_background = (ImageView) view.findViewById(R.id.game_background);

        new RequestTask().execute("https://store.steampowered.com/api/appdetails/?appids="+shPrfs.getAppId());
        /*http://store.steampowered.com/api/appdetails/?appids=782570*/
        job_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitCountAPI();

            }
        });
        return view;

    }

    private void hitCountAPI() {


        new GameCounts().execute("https://steamdb.info/api/GetGraph/?type=concurrent_week&appid="+shPrfs.getAppId());

    }


    private void openPlayersCount() {

        final Dialog DmgShrtgeDialog = new Dialog(getActivity());
        DmgShrtgeDialog.setContentView(R.layout.shortage);
        DmgShrtgeDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        DmgShrtgeDialog.show();

        ImageView shortage_cross = (ImageView) DmgShrtgeDialog.findViewById(R.id.shortage_cross);
        ImageView thumbnailimg = (ImageView) DmgShrtgeDialog.findViewById(R.id.thumbnailimg);
        getcount = (TextView)DmgShrtgeDialog.findViewById(R.id.getcount);

        Glide.with(getActivity())
                .load(R.drawable.game)
                .into(thumbnailimg);


        shortage_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DmgShrtgeDialog.dismiss();
            }
        });

    }

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new MySpannable(false) {
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "See Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, ".. See More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }



    class RequestTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spotsDialog.show();
            spotsDialog.setCancelable(false);

        }

        @Override
        protected String doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(spotsDialog.isShowing())
            {
                spotsDialog.dismiss();
            }
                try
                {
                    JSONObject o = new JSONObject(result);
                    JSONObject startObj = o.getJSONObject(shPrfs.getAppId());

                    JSONObject getAllData = startObj.getJSONObject("data");

                    String name = getAllData.getString("name");
                    game_name.setText(name);




                    String detail =getAllData.getString("detailed_description");
                    Spanned detailspan = Html.fromHtml(detail);
                    PicassoImageGetter imageGetter = new PicassoImageGetter(game_detail,getActivity());
                    Spannable html;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        html = (Spannable) Html.fromHtml(detail, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
                    } else {
                        html = (Spannable) Html.fromHtml(detail, imageGetter, null);
                    }
                    game_detail.setText(html);


                   // makeTextViewResizable(game_detail, 3, "See More", true);

                    String about_the_game = getAllData.getString("about_the_game");

                    String shortdecs =getAllData.getString("short_description");
                    Spanned htmlSpaned = Html.fromHtml(shortdecs);

                    String background = getAllData.getString("header_image");


                    game_about.setText(htmlSpaned);
                    makeTextViewResizable(game_about, 3, "See More", true);




                    JSONArray developer=getAllData.getJSONArray("developers");
                    for(int c=0;c<developer.length();c++)
                    {
                       String f= developer.getString(c);
                        game_developer.setText(f);
                    }
                    JSONArray publisher=getAllData.getJSONArray("publishers");
                    for(int c2=0;c2<publisher.length();c2++)
                    {
                        String f2= publisher.getString(c2);
                        game_publisher.setText(f2);
                    }

                    JSONObject release_date = getAllData.getJSONObject("release_date");
                    String date = release_date.getString("date");
                    game_releasedate.setText(date);
                    JSONObject requirements = getAllData.getJSONObject("pc_requirements");

                    String htmlAsString = requirements.getString("minimum");     // used by WebView
                    Spanned htmlAsSpanned = Html.fromHtml(htmlAsString);

                    game_minimum.setText(htmlAsSpanned);

                    String htmlAsStr =requirements.getString("recommended");     // used by WebView
                    Spanned htmlSpanned = Html.fromHtml(htmlAsStr);

                    game_recommended.setText(htmlSpanned);


                    Glide.with(getActivity())
                            .load(background)
                            .into(game_background);


                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }  }


    private class GameCounts extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spotsDialog.show();
            spotsDialog.setCancelable(false);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(spotsDialog.isShowing())
            {
                spotsDialog.dismiss();
            }
            try
            {
                openPlayersCount();
                JSONObject obj = new JSONObject(s);

                JSONObject getAllData = obj.getJSONObject("data");


                JSONArray developer=getAllData.getJSONArray("values");

                String tot_str = developer.getString(developer.length()-1);

                getcount.setText(tot_str);

/*
                for(int c=0;c<developer.length();c++)
                {


                    if(c==developer.length()-1)
                    {
                        String f= developer.getString(c);
                        getcount.setText(f);
                    }

                }
*/






            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }




        }

        @Override
        protected String doInBackground(String... strings) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(strings[0]));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }
    }
};
