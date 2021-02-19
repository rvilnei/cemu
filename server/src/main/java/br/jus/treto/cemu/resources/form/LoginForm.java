package br.jus.treto.cemu.resources.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
		private String email;
		private String senha;
		  private String[] roles;

		  public LoginForm(String email, String senha, String... roles) {
		    this.email = email;
		    this.senha = senha;
		    this.roles = roles;
		  }
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getSenha() {
			return senha;
		}
		
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public String[] getRoles() {
			return roles;
		}
		public void setRoles(String[] roles) {
			this.roles = roles;
		}
		public UsernamePasswordAuthenticationToken converter() {
			return new UsernamePasswordAuthenticationToken( email, senha );
		}
		
		
}
