package com.transtour.backend.auth;

public class JwtConfig {
	
	private JwtConfig() {
		
	}
	
	public static final String SECRET_KEY = "transtour.key.2023*";

	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEowIBAAKCAQEAumITdX/gaLQLI9I368HMbXDde8mJk0TJx/uuDJxTmSCd5+ee\r\n"
			+ "lv5+QI4ChwIoh6Woo7wIq1t8XmubrzgwyAhWjr2y/s3sPqztsocD3ogwmP4Hwo5f\r\n"
			+ "F6ZRrNx2py0VZV7XrsKX25isHSPLjsI0MFOv8m+W9pk8zRrQhGqs63qKzbs+bSaM\r\n"
			+ "Sa9XuwF8o6wuDQsZa8VuV89kHdsOtwQuzn9mRD49B7oNDLuADoXSQHtud8A0Tf4A\r\n"
			+ "pd18HMtn/e52z/H6jGnEe7gcUYJwqQnKtKCJMyqyUmF+d9qTxf/KFEG9JneMtbf7\r\n"
			+ "jiF3lEv8FRGOgTerYJykVyh1cyMw+6mlti8ZKQIDAQABAoIBAC4L0uDhgdB8QpBL\r\n"
			+ "/DLLgzS6gI13gfyXLv6wKBTHfkPDch978os9PnW2X9t45gh1Hc+twbqL9JLjJOhK\r\n"
			+ "CoswvCmxpgobV7U4RdUoVAVjQhZaFBK117phBaaeJNYyZinQH5r2SkwfUM42LLEN\r\n"
			+ "O5WA52QOu44a15GYgVFlhWj7slUW9GktU0W5eVnEm70vTxXecmAXdI7ty1irq23Q\r\n"
			+ "3NVpt4szq4LAaIIGsfjwyCCMLu2qUnt2opOZjKuhdbusawGJZZeZHYYmgbLbh+CH\r\n"
			+ "kN8Xw61eKo9MiF5g5TYaRIRvbsZ5yHemmLtrMd5o/Ze6wba/GCrexyiw0/vfVCKs\r\n"
			+ "F63JewECgYEA3YOLaTPPPM4GYO4nou+sR29eT91KtZGiV0J4loPwn+WtRZGA9L3J\r\n"
			+ "xrd6yn6+bm1i3FJZxdFLqZ+kxm+y9rMsHgEJ5e3uv7lOlKB0FxilrwcNcdZbws5u\r\n"
			+ "IFwvwSO4f9j/6iiJZHxcbti2+tjKF+LvlZ2uccJ2lQIU7S49z5RVeYkCgYEA12Zk\r\n"
			+ "gId3q7CRFfT/ybFfAZG+JlSpG0pJ0qSXUQqXrWOPKzCHPvG3xph5VBqn5oH5COYC\r\n"
			+ "hwlwwbHI1T2PnxJQtsFwEu6tbPQUAQfp5+8jFGcwBV/olRVxIy/jcPt6b9Itm4pN\r\n"
			+ "pzZjwjySjdVjykSGLVo2oON3eaAzLxsA4jK32qECgYATNH53xognK22eJcfF9qWn\r\n"
			+ "I963aeY6S8AZdKfj1Nid5FnbeDwH/BRsaCf7OnXkOROjKniiD/1RXYf5tGF9xhcD\r\n"
			+ "Gu/ywWg7syM++5JVFkv3GtNtlTorb55Yq+VwB1rNqtC1SoJu9eFFswBaS0VqQMCo\r\n"
			+ "5hzjvApv7NhHoAS35JJskQKBgQC0P7D2hWY1PEoM77pk05LHQ2i1tr7UYXQpWyZs\r\n"
			+ "4MpQhDG4WKG0oJ3DCLDvGmdKU5VMRv3ydonu7JE9MIjQ+oikhnb8wNJLjGx4+GBa\r\n"
			+ "gBkA5+CdfsaoiwrkARz43Nh2XpFfyXp15OPSFNIm8lzRVEpcBVQVioPeOZrbXmN8\r\n"
			+ "5ihIQQKBgDaPbd+cHCeL7ZQxzUjdJKSCrQlBr7n1ssQhTefYXRrgMedcWvnkEJnZ\r\n"
			+ "OWFpgQX9Z3+QoA2sHbQVTE6C3H77LGKEX5a3ezxIoXd4U7VmgeuhPPoK4LVpS7vw\r\n"
			+ "V4OmjuvxCtjA2IcIPNXk/e7JEt9xEtYTy+MU4TST1rE1iTrUJEoQ\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAumITdX/gaLQLI9I368HM\r\n"
			+ "bXDde8mJk0TJx/uuDJxTmSCd5+eelv5+QI4ChwIoh6Woo7wIq1t8XmubrzgwyAhW\r\n"
			+ "jr2y/s3sPqztsocD3ogwmP4Hwo5fF6ZRrNx2py0VZV7XrsKX25isHSPLjsI0MFOv\r\n"
			+ "8m+W9pk8zRrQhGqs63qKzbs+bSaMSa9XuwF8o6wuDQsZa8VuV89kHdsOtwQuzn9m\r\n"
			+ "RD49B7oNDLuADoXSQHtud8A0Tf4Apd18HMtn/e52z/H6jGnEe7gcUYJwqQnKtKCJ\r\n"
			+ "MyqyUmF+d9qTxf/KFEG9JneMtbf7jiF3lEv8FRGOgTerYJykVyh1cyMw+6mlti8Z\r\n"
			+ "KQIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
