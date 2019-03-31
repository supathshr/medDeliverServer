package net;

import persistence.pharmacies.Pharmacy;
import persistence.pharmacies.PharmacyGrabber;

/**
 * Handles a pharmacy request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacyRequestHandler extends RequestHandler<PharmacyRequest> {

    @Override
    public RequestResponse handle(PharmacyRequest message) {
        final Pharmacy pharmacy = PharmacyGrabber.get(message.pharmacyId);
        return new PharmacyRequestResponse(message.pharmacyId, pharmacy.getName(), pharmacy.getAddress(), pharmacy.getPostCode(), (int) pharmacy.getRating());
    }
}
