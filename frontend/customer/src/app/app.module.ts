import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { CustomerService } from './services/Customer.service';
import { CountryService } from './services/country.service';
import { StateService } from './services/state.service';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [CustomerService, CountryService, StateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
