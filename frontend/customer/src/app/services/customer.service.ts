import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from '../../environments/environment';
 
@Injectable({providedIn: 'root'})
export class CustomerService {
 
  private apiUrl: string;
 
  constructor(private http: HttpClient) {
    this.apiUrl = environment.apiBaseUrl;
  }
  getCustomers(country: string, state: string) {
    let params = new HttpParams();
    params = params.append('country', country);
    params = params.append('state', state);
    return this.http.get(this.apiUrl+'/customers', {params: params});
  }
}