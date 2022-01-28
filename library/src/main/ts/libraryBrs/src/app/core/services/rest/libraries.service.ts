import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Response } from './api/api';
import { RestService } from './rest.service';
const URL = "api/libraries";

@Injectable()
export class LibrariesService {
  public libraries: BehaviorSubject<[]> = new BehaviorSubject([]);

  constructor(private restService: RestService) { }

  getContact(): Observable<Response<any>> {
    return this.restService.get(`${URL}/contact`);
  }

  getTerms(): Observable<Response<any>> {
    return this.restService.get(`${URL}/terms`);
  }
}
