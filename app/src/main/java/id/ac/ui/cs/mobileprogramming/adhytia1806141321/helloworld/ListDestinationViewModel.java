package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import android.content.Context;
import android.content.res.TypedArray;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class ListDestinationViewModel {
    private ArrayList<Destination> destinationList = new ArrayList<>();
    private String[] dataName;
    private String[] dataLocation;
    private String[] dataPrice;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private Context context;
    private Destination selectedDestination;

    public ListDestinationViewModel(Context context) {
        this.context = context;
    }

    public Destination getSelectedDestination() {
        return selectedDestination;
    }

    public void setSelectedDestination(int selectedDestination) {
        this.selectedDestination = destinationList.get(selectedDestination);
    }

    ArrayList<Destination> getDestinationList() {
        prepare();
        for (int i = 0; i < dataName.length; i++) {
            Destination destination = new Destination();
            destination.setId(i);
            destination.setFoto(dataPhoto.getResourceId(i, -1));
            destination.setNama(dataName[i]);
            destination.setDeskripsi(dataDescription[i]);
            destination.setLokasi(dataLocation[i]);
            destination.setBiaya(dataPrice[i]);
            destinationList.add(destination);
        }
        return destinationList;
    }

    private void prepare() {
        destinationList.clear();
        dataName = context.getResources().getStringArray(R.array.data_name);
        dataLocation = context.getResources().getStringArray(R.array.data_lokasi);
        dataPrice = context.getResources().getStringArray(R.array.data_biaya);
        dataDescription = context.getResources().getStringArray(R.array.data_deskripsi);
        dataPhoto = context.getResources().obtainTypedArray(R.array.data_foto);
    }

    public void moveToSelectedDestinationDetails(Destination destination, FragmentManager fragmentManager) {
        setSelectedDestination(destination.getId());
        DetailDestinationFragment detailDestinationFragment = new DetailDestinationFragment();
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, detailDestinationFragment, DetailDestinationFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
    }
}
