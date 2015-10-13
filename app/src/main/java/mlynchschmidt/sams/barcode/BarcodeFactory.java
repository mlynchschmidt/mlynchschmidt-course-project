package mlynchschmidt.sams.barcode;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.Tracker;
/**
 * Created by Morgan on 10/12/2015.
 */
class BarcodeFactory implements MultiProcessor.Factory<Barcode> {

    /**
     * Factory for creating a tracker and associated graphic to be associated with a new barcode.  The
     * multi-processor uses this factory to create barcode trackers as needed -- one for each barcode.
     */
        private GraphicOverlay<BarcodeOverlay> mGraphicOverlay;

        BarcodeFactory(GraphicOverlay<BarcodeOverlay> barcodeGraphicOverlay) {
            mGraphicOverlay = barcodeGraphicOverlay;
        }

        @Override
        public Tracker<Barcode> create(Barcode barcode) {
            BarcodeOverlay graphic = new BarcodeOverlay(mGraphicOverlay);
            return new GraphicTracker(mGraphicOverlay, graphic);
        }

    }

