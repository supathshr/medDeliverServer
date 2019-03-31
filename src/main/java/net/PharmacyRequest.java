package net;

/**
 * A pharmacy request is a request for pharmacy details.
 * Details include name, address, ratings etc.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacyRequest extends Request {
    public final int pharmacyId;

    public PharmacyRequest(int pharmacyId) {
        super(RequestType.GET_PHARMACY);
        this.pharmacyId = pharmacyId;
    }
}
