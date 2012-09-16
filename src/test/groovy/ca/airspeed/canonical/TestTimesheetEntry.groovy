/*
    Copyright 2012 Airspeed Consulting

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package ca.airspeed.canonical

import static org.junit.Assert.*
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=['/applicationContext-TEST.xml'])
class TestTimesheetEntry {
	
	@Autowired
	Validator validator

	@Test
	void testIdIsNull() {
		def entry = new TimesheetEntry(id:null, username:"Steve.Jobs", workDate: new Date(), 
			durationMinutes:120, customerName:"Apple", jobName:"Timesheet System", itemName:"Development")
		List<FieldError> errors = checkIt(entry, "timesheet", "id")
		assertTrue "Expected a NotBlank error.", errors[0].codes.contains("NotBlank")
	}
	
	@Test
	void testIdIsBlank() {
		def entry = new TimesheetEntry(id:"", username:"Steve.Jobs", workDate: new Date(), 
			durationMinutes:120, customerName:"Apple", jobName:"Timesheet System", itemName:"Development")
		List<FieldError> errors = checkIt(entry, "timesheet", "id")
		assertTrue "Expected a NotBlank error.", errors[0].codes.contains("NotBlank")
	}
	
	@Test
	void testIdIsSpaces() {
		def entry = new TimesheetEntry(id:"   ", username:"Steve.Jobs", workDate: new Date(), 
			durationMinutes:120, customerName:"Apple", jobName:"Timesheet System", itemName:"Development")
		List<FieldError> errors = checkIt(entry, "timesheet", "id")
		assertTrue "Expected a NotBlank error.", errors[0].codes.contains("NotBlank")
	}
	
	@Test
	void testWorkDateIsNull() {
		def entry = new TimesheetEntry(id:"ABC123", username:"Steve.Jobs", workDate: null, 
			durationMinutes:120, customerName:"Apple", jobName:"Timesheet System", itemName:"Development")
		List<FieldError> errors = checkIt(entry, "timesheet", "workDate")
		assertTrue "Expected a NotNull error.", errors[0].codes.contains("NotNull")
	}
	
	@Test
	void testDurationMinutesIsNull() {
		def entry = new TimesheetEntry(id:"ABC123", username:"Steve.Jobs", workDate: new Date(),
			durationMinutes:null, customerName:"Apple", jobName:"Timesheet System", itemName:"Development")
		List<FieldError> errors = checkIt(entry, "timesheet", "durationMinutes")
		assertTrue "Expected a NotNull error.", errors[0].codes.contains("NotNull")

	}
	
	@Test
	void testDurationMinutesBelowRange() {
		def entry = new TimesheetEntry(id:"ABC123", username:"Steve.Jobs", workDate: new Date(),
			durationMinutes:-1, customerName:"Apple", jobName:"Timesheet System", itemName:"Development")
		List<FieldError> errors = checkIt(entry, "timesheet", "durationMinutes")
		assertTrue "Expected a Range error.", errors[0].codes.contains("Range")

	}
	
	@Test
	void testDurationMinutesAboveRange() {
		def entry = new TimesheetEntry(id:"ABC123", username:"Steve.Jobs", workDate: new Date(),
			durationMinutes:1441, customerName:"Apple", jobName:"Timesheet System", itemName:"Development")
		List<FieldError> errors = checkIt(entry, "timesheet", "durationMinutes")
		assertTrue "Expected a Range error.", errors[0].codes.contains("Range")

	}
	
	@Test
	void testCustomerNameIsNull() {
		def entry = new TimesheetEntry(id:"ABC123", username:"Steve.Jobs", workDate: new Date(), 
			durationMinutes:120, customerName:null, jobName:"Timesheet System", itemName:"Development")
		List<FieldError> errors = checkIt(entry, "timesheet", "customerName")
		assertTrue "Expected a NotBlank error.", errors[0].codes.contains("NotBlank")
	}
	
	@Test
	void testJobNameIsNull() {
		def entry = new TimesheetEntry(id:"ABC123", username:"Steve.Jobs", workDate: new Date(), 
			durationMinutes:120, customerName:"Apple", jobName:null, itemName:"Development")
		List<FieldError> errors = checkIt(entry, "timesheet", "jobName")
		assertTrue "Expected a NotBlank error.", errors[0].codes.contains("NotBlank")
	}
	
	@Test
	void testItemNameIsNull() {
		def entry = new TimesheetEntry(id:"ABC123", username:"Steve.Jobs", workDate: new Date(), 
			durationMinutes:120, customerName:"Apple", jobName:"Timesheet System", itemName:null)
		List<FieldError> errors = checkIt(entry, "timesheet", "itemName")
		assertTrue "Expected a NotBlank error.", errors[0].codes.contains("NotBlank")
	}
	
	private List<FieldError> checkIt(Object fixture, String objectName, String field) {
		Errors errors = new BeanPropertyBindingResult(fixture, objectName)
		validator.validate(fixture, errors)
		def fieldErrors = errors.getFieldErrors(field)
		assertEquals "Number of errors on " + field + ";", 1, fieldErrors.size()
		return fieldErrors
	}

}
