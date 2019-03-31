package net;

/**
 * Decodes a pharmacy request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacyRequestDecoder extends RequestDecoder<PharmacyRequest> {

    @Override
    public PharmacyRequest decode(String json) {
        return new PharmacyRequest(getInt(json, "pharmacyId"));
    }

}
