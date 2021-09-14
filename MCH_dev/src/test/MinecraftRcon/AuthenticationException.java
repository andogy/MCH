package test.MinecraftRcon;

/**
 * Thrown if an authentication exception occurs. This could be when the password
 * is wrong, or you are already or not authenticated when trying to send a
 * command.
 * 
 * @see ErrorType
 */
public class AuthenticationException extends Exception {

	private static final long serialVersionUID = -7310707583704316944L;
	private final ErrorType type;

	/**
	 * The type of the authentication error
	 */
	public enum ErrorType {
		/**
		 * Wrong password: The password you entered and the password that was
		 * defined on the server don't match.
		 */
		WRONG_PASSWORD,
		/**
		 * Already authenticated: You already logged on to the remote server.
		 */
		ALREADY_AUTHENTICATED,
		/**
		 * Not authenticated: RCON requires you to provide a password to log in
		 */
		NOT_AUTHENTICATED
	}

	/**
	 * Constructs a new AuthenticationException with given message and error
	 * type.
	 * 
	 * @param message
	 *            the message to show
	 * @param type
	 *            the authentication error type
	 * @see ErrorType
	 */
	public AuthenticationException(String message, ErrorType type) {
		super(message);
		this.type = type;
	}

	/**
	 * Returns the error type of the exception
	 * 
	 * @return The error type
	 * @see ErrorType
	 */
	public ErrorType getType() {
		return type;
	}

}
