/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.locationterm.service.testng;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.exception.ZipCodeExistsException;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.country.domain.Country;
import omis.country.exception.CountryExistsException;
import omis.country.service.delegate.CountryDelegate;
import omis.location.domain.Location;
import omis.location.exception.LocationExistsException;
import omis.location.service.delegate.LocationDelegate;
import omis.locationterm.domain.LocationTermChangeAction;
import omis.locationterm.exception.AllowedLocationChangeExistsException;
import omis.locationterm.exception.LocationTermChangeActionExistsException;
import omis.locationterm.service.LocationTermService;
import omis.locationterm.service.delegate.AllowedLocationChangeDelegate;
import omis.locationterm.service.delegate.LocationTermChangeActionDelegate;
import omis.organization.domain.Organization;
import omis.organization.exception.OrganizationExistsException;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.region.exception.StateExistsException;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.exception.CorrectionalStatusExistsException;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests lookup of locations for action for correctional status.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 23, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"locationTerm", "service"})
public class LocationTermServiceFindLocationsForActionForCorrectionalStatusTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Service. */
	
	@Autowired
	@Qualifier("locationTermService")
	private LocationTermService locationTermService;

	/* Service delegates. */
	
	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	
	@Autowired
	@Qualifier("locationTermChangeActionDelegate")
	private LocationTermChangeActionDelegate locationTermChangeActionDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusDelegate")
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	@Autowired
	@Qualifier("allowedLocationChangeDelegate")
	private AllowedLocationChangeDelegate allowedLocationChangeDelegate;
	
	/* Test methods. */
	
	/**
	 * Tests look up of allowed locations for action and correctional status.
	 * 
	 * @throws OrganizationExistsException if organization exists
	 * @throws CountryExistsException if country exists
	 * @throws StateExistsException if State exists
	 * @throws CityExistsException if city exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws LocationExistsException if location exists
	 * @throws LocationTermChangeActionExistsException if location term
	 * change action exists
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws AllowedLocationChangeExistsException if allowed location change
	 * exists
	 */
	public void test()
			throws OrganizationExistsException,
				CountryExistsException,
				StateExistsException,
				CityExistsException,
				ZipCodeExistsException,
				LocationExistsException,
				LocationTermChangeActionExistsException,
				CorrectionalStatusExistsException,
				AllowedLocationChangeExistsException {
		
		// Arranges prison, allowed for action on correctional status
		Organization prison = this.organizationDelegate.create(
				"State Prison", "SP", null);
		Country country = this.countryDelegate.create(
				"United States", "USA", true);
		State state = this.stateDelegate.create(
				"Montana", "MT", country, true, true);
		City city = this.cityDelegate.create(
				"Helena", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(
				city, "59601", null, true);
		Address address = this.addressDelegate.findOrCreate(
				"1000 1000TH ST #1000", null, null, null, zipCode);
		Location prisonLocation = this.locationDelegate.create(
				prison, null, address);
		LocationTermChangeAction sendToPrisonAction
			= this.locationTermChangeActionDelegate.create(
					"Send to Prison", true);
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		this.allowedLocationChangeDelegate.create(
				sendToPrisonAction, secure, prisonLocation);
		
		// Action - looks up allowed locations
		List<Location> allowedLocations = this.locationTermService
				.findLocationsForActionForCorrectionalStatus(
						sendToPrisonAction, secure);
		
		// Asserts that single location is allowed
		assert allowedLocations.size() == 1
			: String.format(
					"Wrong number of allowed locations - 1 expected; %d found",
					allowedLocations.size());
		
		// Asserts that prison is allowed
		assert allowedLocations.contains(prisonLocation)
			: String.format("%s not allowed", prisonLocation);
	}
}