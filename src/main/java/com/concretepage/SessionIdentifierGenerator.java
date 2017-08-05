package com.concretepage;

import java.security.SecureRandom;
import java.math.BigInteger;

public final class SessionIdentifierGenerator {
	
	
	
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(80, random).toString(10);
    }
    
    
    
    public static void main(String[] args) {
		System.err.println( new SessionIdentifierGenerator().nextSessionId()) ; 
	}
}