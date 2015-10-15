package mlynchschmidt.sams.database;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mlynchschmidt.sams.DatePickerFragment;
import mlynchschmidt.sams.R;


/**
 * Created by Morgan on 10/14/2015.
 */
public class AddAsset extends Fragment {

    private Button mDateButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_asset, container, false);

        mDateButton = (Button)v.findViewById(R.id.date_button);
       // updateDate();
        //mDateButton.setText(DateFormat.format("EEEE, MMM dd, yyyy"));
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
               // DatePickerFragment dialog = DatePickerFragment.newInstance()
               // dialog.show(manager, DIALOG_DATE);
            }
        });

        return v;
    }
}
