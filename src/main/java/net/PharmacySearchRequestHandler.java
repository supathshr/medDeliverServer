package net;

import persistence.pharmacies.PharmacyGrabber;

/**
 * Handles a pharmacy search request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacySearchRequestHandler extends RequestHandler<PharmacySearchRequest> {

    @Override
    public RequestResponse handle(PharmacySearchRequest message) {
        return new PharmacySearchRequestResponse(PharmacyGrabber.get(message.query));
    }
}
