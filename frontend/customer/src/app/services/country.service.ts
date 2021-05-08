import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
 
@Injectable({providedIn: 'root'})
export class CountryService {
 
  private apiUrl: string;
 
  constructor(private http: HttpClient) {
    this.apiUrl = environment.apiBaseUrl;
  }
  getCountries() {
    return this.http.get(this.apiUrl+'/countries');
  }
}