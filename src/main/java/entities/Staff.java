package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Staff  {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		int id;
		private String position;
		private String name;
		private String surname;
		private String passport;
		private String email;
		private String phone;
		private String login;
		private String password;
		private String enabled;
		
		public Staff() {
			
		}
		
		public String getStringForPrint() {
			String txt = "id = " + id;
			txt += "; position: " + position;
			txt += "; name: " + name;
			txt += "; surname: " + surname;
			txt += "; passport: " + passport;
			txt += "; email: " + email;
			txt += "; phone: " + phone;
			txt += "; login: " + login;
			txt += "; password: " + password;
			txt += "; enabled: " + enabled;
			return txt;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getPassport() {
			return passport;
		}

		public void setPassport(String passport) {
			this.passport = passport;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPassword() {
//			char[] array = password.toCharArray();
//			for(int i=0; i<array.length; i++) {
//				array[i] = (char) (array[i] ^ 4);
//				
//			}
//			String word = String.valueOf(array);	
			return password;
		}

		public void setPassword(String password) {			
			String word = convertPassword(password);
			this.password = word;
		}
		
		public static String convertPassword(String password) {
			char[] array = password.toCharArray();
			for(int i=0; i<array.length; i++) {
				array[i] = (char) (array[i] ^ 4);
			}
			String word = String.valueOf(array);
			return word;
		}
		
		public String getEnabled() {
			return enabled;
		}

		public void setEnabled(String enabled) {
			this.enabled = enabled;
		}
		
		
		
}
