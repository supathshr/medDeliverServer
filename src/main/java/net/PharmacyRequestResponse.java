package net;

/**
 * The response to a pharmacy request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacyRequestResponse extends RequestResponse {
    public final int id;
    public final String name;
    public final String address;
    public final String postCode;
    public final int rating;

    public PharmacyRequestResponse(int id, String name, String address, String postCode, int rating) {
        super(RequestType.GET_PHARMACY);
        this.id = id;
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.rating = rating;
    }
}
