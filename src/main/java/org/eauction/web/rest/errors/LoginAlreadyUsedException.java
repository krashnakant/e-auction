package org.eauction.web.rest.errors;

public class LoginAlreadyUsedException extends BadRequestAlertException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1524588024459771200L;

	public LoginAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Login already in use", "userManagement", "userexists");
    }
}
