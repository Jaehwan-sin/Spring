package com.tech.anno2;

public class College {
		
		private Principal principal;
		
		public College(Principal principal) {// Principal 주입
			this.principal=principal;
		}
		
		public void test() {
			principal.principalInfo();
			System.out.println("testing is call methods");
		}
}
