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

import groovy.transform.ToString;

@ToString(includeNames = true)
class Invoice {
	String id
	String sourceSystemName
	String invoiceNumber
	Date invoiceDate
	Date dueDate
	String terms
	String customerName
	String jobName
	String poNumber
	// BillTo Address stuff will go here.
	InvoiceLine[] invoiceLines
	Float subtotal
	Float salesTaxTotal
	Boolean toBePrinted
	Boolean toBeEmailed
	Boolean paid
	String other
	String emailTemplatePlain
	String emailTemplateHtml
	Date timeCreated
	Date timeModified
}
