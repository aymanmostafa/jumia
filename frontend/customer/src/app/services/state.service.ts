import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
 
@Injectable({providedIn: 'root'})
export class StateService {
 
  private apiUrl: string;
 
  constructor(private http: HttpClient) {
    this.apiUrl = environment.apiBaseUrl;
  }
  getstates() {
    return this.http.get(this.apiUrl+'/phone-number-states');
  }
}