import { Component } from '@angular/core';
import { Customer } from './models/customer.model';
import {HttpClient} from '@angular/common/http';
import { CustomerService } from './services/Customer.service';
import { CountryService } from './services/country.service';
import { StateService } from './services/state.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Customer List';
  customers : Customer[];
  countries: string[];
  states: string[];
  selectedCountry: string;
  selectedState: string;
 
  constructor(public http: HttpClient, private customerService: CustomerService, 
    private countryService: CountryService, private stateService: StateService){
      this.selectedCountry = "";
      this.selectedState = "";
  }
 
  ngOnInit(): void {
    this.getCountries();
    this.getStates();
    this.getCustomers();
  }

  onSubmit() {
    this.getCustomers();
  }

  getCountries() {
    this.countryService
    .getCountries()
    .subscribe((data:string[]) => {
      this.countries = data;
    });
  }

  getStates() {
    this.stateService
    .getstates()
    .subscribe((data:string[]) => {
      this.states = data;
    });
  }

  getCustomers() {
    this.customerService
    .getCustomers(this.selectedCountry, this.selectedState)
    .subscribe((data:Customer[]) => {
      this.customers = data;
    });
  }
}
