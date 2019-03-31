package net;

/**
 * A pharmacy search request is a request for pharmacies that match
 * a certain search query e.g. if the query is "cant", a list of pharmacies will be
 * returned such as "canterbury", "canterbury 1", "cant touch this pharmacy" etc.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacySearchRequest extends Request {
    public final String query;

    public PharmacySearchRequest(String query) {
        super(RequestType.GET_PHARMACIES);
        this.query = query;
    }
}
