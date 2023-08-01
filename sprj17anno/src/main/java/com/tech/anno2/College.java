package com.tech.anno2;

public class College {
		
		private Principal principal;
		
		/*
		 * public College(Principal principal) {// Principal 생성자로 주입
		 * this.principal=principal; }
		 */
		
		public void test() {
			principal.principalInfo();
			System.out.println("testing is call methods");
		}
		
		// Setter이용
		public void setPrincipal(Principal principal) {
			this.principal = principal;
		}
		
		
}
