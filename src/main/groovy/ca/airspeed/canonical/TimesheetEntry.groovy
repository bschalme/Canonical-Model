/*
    Copyright 2011 Airspeed Consulting

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

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import groovy.transform.ToString;

@ToString(includeNames = true)
class TimesheetEntry {
	@NotBlank
	String id
	
	String sourceSystemName
	
	@NotBlank
	String username
	
	@NotNull
	Date workDate
	
	Date startTime
	Date endTime
	
	@NotNull
	@Range(min=0L, max=1440L)
	Integer durationMinutes
	
	InetAddress ipIn
	InetAddress ipOut
	
	@NotBlank
	String customerName
	
	@NotBlank
	String jobName
	
	@NotBlank
	String itemName
	
	String notes
	String billableStatus
}