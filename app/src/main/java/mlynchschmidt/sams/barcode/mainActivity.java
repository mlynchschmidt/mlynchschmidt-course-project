package mlynchschmidt.sams.barcode;

//Directory created to separate out multiple classes necessary for barcode implementation. May readjust later if necessary.
/*Building a sample barcode app functionality based off of Google Android Vision sample for the API.
Intent of this initial step is to get a functional framework to customize to the needs of my particular app;
Building off a functional structure will hopefully help me understand how the API runs.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by Morgan on 10/12/2015.
 */


    import com.google.android.gms.common.api.CommonStatusCodes;
    import com.google.android.gms.vision.barcode.Barcode;

import mlynchschmidt.sams.R;
import mlynchschmidt.sams.database.AddAsset;

/**
     * Main activity demonstrating how to pass extra parameters to an activity that
     * reads barcodes.
     */
    public class mainActivity extends Activity implements View.OnClickListener {

        // use a compound button so either checkbox or switch widgets work.
        private CompoundButton autoFocus;
        private CompoundButton useFlash;
        private TextView statusMessage;
        private TextView barcodeValue;

        private static final int RC_BARCODE_CAPTURE = 9001;
        private static final String TAG = "BarcodeMain";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            statusMessage = (TextView)findViewById(R.id.status_message);
            barcodeValue = (TextView)findViewById(R.id.barcode_value);

            autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
            useFlash = (CompoundButton) findViewById(R.id.use_flash);

            findViewById(R.id.read_barcode).setOnClickListener(this);
            //findViewById(R.id.date_button).setOnClickListener(this); Sorry. Not today.
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.read_barcode) {
                // launch barcode activity.
                Intent intent = new Intent(this, BarcodeCapture.class);
                intent.putExtra(BarcodeCapture.AutoFocus, autoFocus.isChecked());
                intent.putExtra(BarcodeCapture.UseFlash, useFlash.isChecked());

                startActivityForResult(intent, RC_BARCODE_CAPTURE);
            }
            if(v.getId() == R.id.date_button) {
                //jump to rough Asset add UI
                Intent intent = new Intent(this, AddAsset.class);
                startActivity(intent);
            }

        }

        /**
         * Called when an activity you launched exits, giving you the requestCode
         * you started it with, the resultCode it returned, and any additional
         * data from it.  The <var>resultCode</var> will be
         * {@link #RESULT_CANCELED} if the activity explicitly returned that,
         * didn't return any result, or crashed during its operation.
         * <p/>
         * <p>You will receive this call immediately before onResume() when your
         * activity is re-starting.
         * <p/>
         *
         * @param requestCode The integer request code originally supplied to
         *                    startActivityForResult(), allowing you to identify who this
         *                    result came from.
         * @param resultCode  The integer result code returned by the child activity
         *                    through its setResult().
         * @param data        An Intent, which can return result data to the caller
         *                    (various data can be attached to Intent "extras").
         * @see #startActivityForResult
         * @see #createPendingResult
         * @see #setResult(int)
         */
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == RC_BARCODE_CAPTURE) {
                if (resultCode == CommonStatusCodes.SUCCESS) {
                    if (data != null) {
                        Barcode barcode = data.getParcelableExtra(BarcodeCapture.BarcodeObject);
                        statusMessage.setText(R.string.barcode_success);
                        barcodeValue.setText(barcode.displayValue);
                        Log.d(TAG, "Barcode read: " + barcode.displayValue);
                    } else {
                        statusMessage.setText(R.string.barcode_failure);
                        Log.d(TAG, "No barcode captured, intent data is null");
                    }
                } else {
                    statusMessage.setText(String.format(getString(R.string.barcode_error),
                            CommonStatusCodes.getStatusCodeString(resultCode)));
                }
            }
            else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

