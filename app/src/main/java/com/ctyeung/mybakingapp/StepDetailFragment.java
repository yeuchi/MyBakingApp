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
import android.widget.Toast;
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
    private SimpleExoPlayerView mSimpleExoPlayerView;
    private SimpleExoPlayer mPlayer;

    private Timeline.Window mWindow;
    private DataSource.Factory mMediaDataSourceFactory;
    private DefaultTrackSelector mTrackSelector;
    private boolean mShouldAutoPlay;
    private BandwidthMeter mBandwidthMeter;
    private Context mContext;
    private Uri mUri;

    ////////////////////////////////////////////////////////////////

    private View mRootView;

    private List<Step> mSteps;
    private int mRecipeStepIndex = 1;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState)
    {
        mContext = this.getActivity();
        mShouldAutoPlay = true;
        mBandwidthMeter = new DefaultBandwidthMeter();
        mMediaDataSourceFactory = new DefaultDataSourceFactory(mContext, Util.getUserAgent(mContext, "mediaPlayerSample"),
                                                                (TransferListener<? super DataSource>) mBandwidthMeter);
        mWindow = new Timeline.Window();

        mRootView = inflater.inflate(R.layout.fragment_step_detail, container, false);
        render();

        return mRootView;
    }

    @Override
    public void setElements(List<Step> mSteps,
                            int i)
    {
        this.mRecipeStepIndex = i;
        this.mSteps = mSteps;

        if(null!=mRootView)
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
        Step step = mSteps.get(mRecipeStepIndex);
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

        TextView textView = (TextView) mRootView.findViewById(R.id.detail_item);
        textView.setText(str);
    }

    private void loadVideo(Step step)
    {
        TextView textView = (TextView)mRootView.findViewById(R.id.fragment_video_nada);
        mSimpleExoPlayerView = (SimpleExoPlayerView) mRootView.findViewById(R.id.player_view);

        ProgressBar progressBar = (ProgressBar)mRootView.findViewById(R.id.fragment_progress);
        progressBar.setVisibility(View.INVISIBLE);

        Uri videoUri = step.getVideoUri();
        Uri thumbnailUri = step.getThumbnailUri();

        if(null == videoUri &&
                null == thumbnailUri)
        {
            // show error
            textView.setVisibility(View.VISIBLE);
            mSimpleExoPlayerView.setVisibility(View.INVISIBLE);
            Toast.makeText(mContext,"No Video available",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            mUri = (null==videoUri)?
                        thumbnailUri:
                        videoUri;

            textView.setVisibility(View.INVISIBLE);
            mSimpleExoPlayerView.setVisibility(View.VISIBLE);
            initializePlayer();
        }
    }

    //////////////////////////////////////////////
    // exo-video-player


    private void initializePlayer() {

        if(null==mUri || 0==mUri.toString().length())
            return;

        if(null!=mPlayer)
        {
            mPlayer.stop();
            mPlayer.release();
        }

        mSimpleExoPlayerView.requestFocus();

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(mBandwidthMeter);

        mTrackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        mPlayer = ExoPlayerFactory.newSimpleInstance(mContext, mTrackSelector);

        mSimpleExoPlayerView.setPlayer(mPlayer);

        mPlayer.setPlayWhenReady(mShouldAutoPlay);

        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(mUri.toString()),
                                                            mMediaDataSourceFactory,
                                                            extractorsFactory,
                                                            null,
                                                            null);

        mPlayer.stop();
        mPlayer.prepare(mediaSource);
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            mShouldAutoPlay = mPlayer.getPlayWhenReady();
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
            mTrackSelector = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23 && null==mPlayer) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || mPlayer == null)) {
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
