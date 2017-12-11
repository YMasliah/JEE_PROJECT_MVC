package springapp.web.annotation;

import java.net.MalformedURLException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraints.URL;

public class URLValidator implements ConstraintValidator<URL, String> {
	private String protocol;
	private String host;
	private int port;

	public void initialize(URL url) {
		this.protocol = url.protocol();
		this.host = url.host();
		this.port = url.port();
	}

	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null || value.length() == 0) {
			return true;
		}

		java.net.URL url;
		try {
			url = new java.net.URL(value);
		} catch (MalformedURLException e) {
			return false;
		}

		if (protocol != null && protocol.length() > 0 && !url.getProtocol().equals(protocol)) {
			return false;
		}

		if (host != null && host.length() > 0 && !url.getHost().equals(host)) {
			return false;
		}

		if (port != -1 && url.getPort() != port) {
			return false;
		}

		return true;
	}
}