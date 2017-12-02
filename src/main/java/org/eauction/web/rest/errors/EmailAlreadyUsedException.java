package org.eauction.web.rest.errors;

public class EmailAlreadyUsedException extends BadRequestAlertException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6011779163561049998L;

	public EmailAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Email address already in use", "userManagement", "emailexists");
    }
}
