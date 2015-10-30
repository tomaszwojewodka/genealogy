package com.hudela.genealogy.person.converter;

import com.hudela.genealogy.person.dto.Person;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.hudela.PersonConverter")
public class PersonConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Person person = new Person();
		if (value == null) {
			return person;
		}

		// FIXME no error checking
		String[] nameArray = value.split(" ");
		person.setId(nameArray[0]);
		person.setFirstName(nameArray[1]);
		person.setLastName(nameArray[2]);
		return person;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}
}
