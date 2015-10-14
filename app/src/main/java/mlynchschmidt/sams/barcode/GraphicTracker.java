package mlynchschmidt.sams.barcode;

import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.Detector;

/**
 * Created by Morgan on 10/12/2015.
 */
public class GraphicTracker extends Tracker<Barcode> {
    /**
     * Generic tracker which is used for tracking or reading a barcode (and can really be used for
     * any type of item).  This is used to receive newly detected items, add a graphical representation
     * to an overlay, update the graphics as the item changes, and remove the graphics when the item
     * goes away.
     */
        private GraphicOverlay<BarcodeOverlay> mOverlay;
        private BarcodeOverlay mGraphic;

        GraphicTracker(GraphicOverlay<BarcodeOverlay> overlay, BarcodeOverlay graphic) {
            mOverlay = overlay;
            mGraphic = graphic;
        }

        /**
         * Start tracking the detected item instance within the item overlay.
         */
        @Override
        public void onNewItem(int id, Barcode item) {
            mGraphic.setId(id);
        }

        /**
         * Update the position/characteristics of the item within the overlay.
         */
        @Override
        public void onUpdate(Detector.Detections<Barcode> detectionResults, Barcode item) {
            mOverlay.add(mGraphic);
            mGraphic.updateItem(item);
        }

        /**
         * Hide the graphic when the corresponding object was not detected.  This can happen for
         * intermediate frames temporarily, for example if the object was momentarily blocked from
         * view.
         */
        @Override
        public void onMissing(Detector.Detections<Barcode> detectionResults) {
            mOverlay.remove(mGraphic);
        }

        /**
         * Called when the item is assumed to be gone for good. Remove the graphic annotation from
         * the overlay.
         */
        @Override
        public void onDone() {
            mOverlay.remove(mGraphic);
        }
    }

