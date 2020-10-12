package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListDestinationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListDestinationFragment extends Fragment {
    @BindView(R.id.rv_destinations)
    RecyclerView rv_destinations;

    private ArrayList<Destination> destinationList = new ArrayList<>();
    private ListDestinationViewModel listDestinationViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListDestinationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListDestinationFragment newInstance(String param1, String param2) {
        ListDestinationFragment fragment = new ListDestinationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_destination, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Daftar Destinasi");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_destinations.setHasFixedSize(true);
        listDestinationViewModel = ((MainActivity) getActivity()).getListDestinationViewModel();
        destinationList.clear();
        destinationList.addAll(listDestinationViewModel.getDestinationList());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rv_destinations.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListDestinationAdapter listDestinationAdapter = new ListDestinationAdapter(destinationList);
        rv_destinations.setAdapter(listDestinationAdapter);

        listDestinationAdapter.setOnItemClickCallback(new ListDestinationAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Destination data) {
                listDestinationViewModel.moveToSelectedDestinationDetails(data, getFragmentManager());
            }
        });
    }
}