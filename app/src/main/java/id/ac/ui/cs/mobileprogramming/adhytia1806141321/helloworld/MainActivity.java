package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private ListDestinationViewModel listDestinationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listDestinationViewModel = new ListDestinationViewModel(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ListDestinationFragment listDestinationFragment = new ListDestinationFragment();
        fragmentManager
                .beginTransaction()
                .add(R.id.frame_container, listDestinationFragment, ListDestinationFragment.class.getSimpleName())
                .commit();
    }

    public ListDestinationViewModel getListDestinationViewModel() {
        return this.listDestinationViewModel;
    }
}