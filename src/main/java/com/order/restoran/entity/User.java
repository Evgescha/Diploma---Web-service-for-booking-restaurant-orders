package com.order.restoran.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name = "myUsers")
@Data
public class User extends AbstractEntity {

	@Column(unique = true)
	@NotNull
	private String username;

//	@JsonIgnore
	@NotNull
	private String password;

	@NotNull
	private String firstname;

	@NotNull
	private String lastname;

	@NotNull
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
	private List<Order> myOrders = new ArrayList<Order>();

	@ManyToOne
	@JoinColumn(name = "role_id")
	Role role;

	public User() {
		super();
	}

	public List<String> getRoleListNames() {
		List<String> roleNames = new ArrayList<>();
		roleNames.add(role.getName());
		return roleNames;
	}

}
