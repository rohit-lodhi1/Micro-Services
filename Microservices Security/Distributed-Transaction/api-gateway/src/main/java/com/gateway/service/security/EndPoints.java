package com.api.gateway.security;

public class EndPoints {

	private EndPoints() {
		throw new UnsupportedOperationException("Utility class should not be instantiated");
	}
	public static final String PERMIT_ALL[] = {"/auth/**", "/agency-super-admin/public/**", "/agency-admin/public/**",
			                                    "/agency-relationship-manager/public/**","/agency-team-member/public/**",
			                                    "/customer-admin/public/**","/customer-team-member/public/**","/api/v1/email/**","/public/login","/tasks/**","/events/**","/agency/**"};

	public static final String AGENCY_SUPER_ADMIN[] = { "/agency-super-admin/secure/**" };
	public static final String AGENCY_ADMIN[] = { "/auth/**", "/agency-admin/secure/**" };
	public static final String AGENCY_RELATIONSHIP_MANAGER[] = { "/agency-relationship-manager/secure/**" };
	public static final String AGENCY_TEAM_MEMEBER[] = { "/agency-team-member/secure/**" };
	public static final String CUSTOMER_ADMIN[] = { "/customer-admin/secure/**" };
	public static final String CUSTOMER_TEAM_MEMBER[] = { "/customer-team-member/secure/**" };

}
