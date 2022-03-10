package com.hart.entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User implements UserDetails {
	    @Id
	    @NotEmpty(message = "Mobile Number is required")
	    @Size(min=10,max = 10,message = "Mobile Numbe is wrong")
	    @Pattern(regexp="^(0|[1-9][0-9]*)$",message = "Mobile Number does not exist")
	    @Column(name = "mobilenumber")
	    private String mobilenumber;
	    @NotBlank(message = "Name is required")
	    private String name;
	    @NotEmpty(message = "Batch is required")
	    @Pattern(regexp="^(0|[1-9][0-9]*)$",message = "Batch does not exist")
	    private String batch;
	    @NotEmpty(message = "Hostel Number is required")
	    @Pattern(regexp="^(0|[1-9][0-9]*)$",message = "Hostel Number does not exist")
	    private String hostelnumber;
	    @NotEmpty(message = "Room Number is required")
	    @Pattern(regexp="^(0|[1-9][0-9]*)$",message = "Room Number does not exist")
	    private String roomnumber;
	    @NotEmpty(message = "Password is required")
	    @Size(min=6,message = "Password should have atleast 6 characters")
	    private String password;
	    //images
	   
	    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
	    private List<Image>images=new ArrayList<>();
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return this.mobilenumber;
		}
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
}
