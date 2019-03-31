package net;

import persistence.pharmacies.Pharmacy;

/**
 * The response to a pharmacy search request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacySearchRequestResponse extends RequestResponse {
    public final Pharmacy[] pharmacies;

    public PharmacySearchRequestResponse(Pharmacy[] pharmacies) {
        super(RequestType.GET_PHARMACIES);
        this.pharmacies = pharmacies;
    }
}
