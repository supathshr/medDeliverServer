package net;

/**
 * Decodes a pharmacy search request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacySearchRequestDecoder extends RequestDecoder<PharmacySearchRequest> {

    @Override
    public PharmacySearchRequest decode(String json) {
        return new PharmacySearchRequest(getString(json, "query"));
    }
}
