package org.union.trans.ubica.plus.soap.client.provider;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;
import java.util.Map;


public class ClientPasswordCallback implements CallbackHandler {

	private Map<String, String> userPasswords = new HashMap<>();
	private Map<String, String> keyPasswords = new HashMap<>();

	public ClientPasswordCallback() {
		userPasswords.put("307883", "Equidad2208*");
		keyPasswords.put("equidadtes", "Rie22*");
	}

	public void handle(Callback[] callbacks) {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pwcb = (WSPasswordCallback) callbacks[i];
			String id = pwcb.getIdentifier();
			String pass;
			switch (pwcb.getUsage()) {
				case WSPasswordCallback.USERNAME_TOKEN:
					pass = userPasswords.get(id);
					pwcb.setPassword(pass);
					break;
				case WSPasswordCallback.SIGNATURE:
				case WSPasswordCallback.DECRYPT:
					pass = keyPasswords.get(id);
					pwcb.setPassword(pass);
					break;
			}
		}

	}
}