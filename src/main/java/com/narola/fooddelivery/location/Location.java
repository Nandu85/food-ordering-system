package com.narola.fooddelivery.location;

import com.narola.fooddelivery.user.User;

public class Location {
	private int LocationId;
	private String AddressLine;
	private String Area;
	private String City;
	private String State;
	private int Pincode;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getLocationId() {
		return LocationId;
	}

	public void setLocationId(int locationId) {
		LocationId = locationId;
	}

	public String getAddressLine() {
		return AddressLine;
	}

	public void setAddressLine(String addressLine) {
		AddressLine = addressLine;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public int getPincode() {
		return Pincode;
	}

	public void setPincode(int pincode) {
		Pincode = pincode;
	}

	@Override
	public String toString() {
		return "" + AddressLine + ", " + Area + ", "
				+ City + ", " + State + ", " + Pincode + ".";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (AddressLine == null) {
			if (other.AddressLine != null)
				return false;
		} else if (!AddressLine.equals(other.AddressLine))
			return false;
		if (Area == null) {
			if (other.Area != null)
				return false;
		} else if (!Area.equals(other.Area))
			return false;
		if (City == null) {
			if (other.City != null)
				return false;
		} else if (!City.equals(other.City))
			return false;
		if (LocationId != other.LocationId)
			return false;
		if (Pincode != other.Pincode)
			return false;
		if (State == null) {
			if (other.State != null)
				return false;
		} else if (!State.equals(other.State))
			return false;
		return true;
	}

}
