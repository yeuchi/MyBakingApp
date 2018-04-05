package com.ctyeung.mybakingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.data.Step;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.util.Util;
import org.json.JSONObject;
import java.util.List;

/////////////////////////////////

import com.ctyeung.mybakingapp.StepDetailFragment;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;
import android.content.Context;

/**
 * Created by ctyeung on 3/17/18.
 */

public class StepDetailFragment extends BaseFragment
{
    // exo-player
    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;

    private Timeline.Window window;
    private DataSource.Factory mediaDataSourceFactory;
    private DefaultTrackSelector trackSelector;
    private boolean shouldAutoPlay;
    private BandwidthMeter bandwidthMeter;

    private ImageView ivHideControllerButton;
    private Context context;
    private Uri uri;

    ////////////////////////////////////////////////////////////////

    private View rootView;

    private List<Step> mSteps;
    private TextView btnPrevious;
    private TextView btnNext;
    private int recipeStepIndex = 1;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState)
    {
        context = this.getActivity();
        shouldAutoPlay = true;
        bandwidthMeter = new DefaultBandwidthMeter();
        mediaDataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "mediaPlayerSample"), (TransferListener<? super DataSource>) bandwidthMeter);
        window = new Timeline.Window();

        rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);
        render();

        //ivHideControllerButton = (ImageView) findViewById(R.id.exo_controller);

        return rootView;
    }

    @Override
    public void setElements(List<Step> mSteps,
                            int i)
    {
        this.recipeStepIndex = i;
        this.mSteps = mSteps;

        if(null!=rootView)
            render();
    }

    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + "must implement OnClickListener");
        }
    }

    private void render()
    {
        Step step = mSteps.get(recipeStepIndex);
        loadDescription(step);
        loadVideo(step);
    }

    private void loadDescription(Step step)
    {
        String desc = step.getDescription();
        String shortDesc = step.getShortDescription();

        String str = "no description available.";

        if(null!=desc && desc.length()>0)
            str = desc;

        else if (null!=desc && desc.length()>0)
            str = desc;

        TextView textView = (TextView) rootView.findViewById(R.id.detail_item);
        textView.setText(str);
    }

    private void loadVideo(Step step)
    {
        TextView textView = (TextView)rootView.findViewById(R.id.fragment_video_nada);
        //VideoView videoView = (VideoView) rootView.findViewById(R.id.video_item);
        simpleExoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.player_view);

        ProgressBar progressBar = (ProgressBar)rootView.findViewById(R.id.fragment_progress);
        progressBar.setVisibility(View.INVISIBLE);

        Uri videoUri = step.getVideoUri();
        Uri thumbnailUri = step.getThumbnailUri();

        if(null == videoUri &&
                null == thumbnailUri)
        {
            // show error
            textView.setVisibility(View.VISIBLE);
            simpleExoPlayerView.setVisibility(View.INVISIBLE);
            return;
        }
        else {
            uri = (null==videoUri)?
                        thumbnailUri:
                        videoUri;

            textView.setVisibility(View.INVISIBLE);
            simpleExoPlayerView.setVisibility(View.VISIBLE);

           // initializePlayer();

            // videoView.stopPlayback();
           // videoView.setVideoURI(uri);
           // videoView.start();
        }
    }

    //////////////////////////////////////////////
    // exo-video-player


    private void initializePlayer() {

        simpleExoPlayerView.requestFocus();

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);

        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);

        simpleExoPlayerView.setPlayer(player);

        player.setPlayWhenReady(shouldAutoPlay);
/*        MediaSource mediaSource = new HlsMediaSource(Uri.parse("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8"),
                mediaDataSourceFactory, mainHandler, null);*/

        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        //MediaSource mediaSource = new ExtractorMediaSource(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"),
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(uri.toString()),
        mediaDataSourceFactory, extractorsFactory, null, null);

        player.stop();
        player.prepare(mediaSource);
/*
        ivHideControllerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleExoPlayerView.hideController();
            }
        }); */
    }

    private void releasePlayer() {
        if (player != null) {
            shouldAutoPlay = player.getPlayWhenReady();
            player.release();
            player = null;
            trackSelector = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }
}
