package com.example.g0569.mazegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.R;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;

public class MazeActivity extends AppCompatActivity {

    private MazeView mazeView;
    private MazeContract.Presenter presenter;
    private boolean isMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (mazeView == null) {
//            mazeView = new MazeView(this);
//        }
        setContentView(R.layout.activity_mazegame);
        mazeView = findViewById(R.id.mazeview);
        Bundle bundle = getIntent().getExtras();
        Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
        presenter = new MazePresenter(mazeView, inventory);

        final LinearLayout menuLayout = findViewById(R.id.menu_layout);
        menuLayout.setVisibility(View.GONE);
        Button menuBtn = findViewById(R.id.meny_btn);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuVisible) {
                    isMenuVisible = false;
                    menuLayout.setVisibility(View.VISIBLE);
                } else {
                    isMenuVisible = true;
                    menuLayout.setVisibility(View.GONE);
                }

            }
        });
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        presenter.resumeStopWatch();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        presenter.pauseStopWatch();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }
}
