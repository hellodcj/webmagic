package com.dcj.webmagic.core;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class DigestUtilsTest {
	 @Test
	    public void DigestUtilsTest(){
	    	String r = DigestUtils.md5Hex("sss");
	    	System.out.println(r);
	    }
}
